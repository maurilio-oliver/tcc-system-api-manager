package br.unip.tcc.tccapi.config;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.ElementType;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class ClientGeneratorConfiguration extends AbstractProcessor {
    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
        @Override
        public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
            for (TypeElement annotation : annotations) {
                Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);

                for (Element element : elements) {
                    if (element instanceof TypeElement) {
                        TypeElement classElement = (TypeElement) element;
                        String className = classElement.getQualifiedName().toString();

                        try {
                            generateClientClass(className);
                        } catch (IOException | ClassNotFoundException e) {
                            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Erro ao gerar o cliente para: " + className);
                        }
                    }
                }
            }
            return true;
        }

        private void generateClientClass(String className) throws IOException, ClassNotFoundException {
            // Implemente a lógica de geração da classe cliente aqui
            // Você pode usar o Filer para criar um arquivo de origem
            Filer filer = processingEnv.getFiler();
            JavaFileObject clientFile = filer.createSourceFile(className + "Client");
            Class.forName(className);
            try (PrintWriter writer = new PrintWriter(clientFile.openWriter())) {
                writer.println("public interface " + className + "Client {");

                // Gere chamadas para cada método mapeado do controlador
                // Você precisará analisar a classe do controlador para obter os métodos anotados
                // e gerar o código apropriado.
                writer.println("    // Gere chamadas para métodos mapeados aqui");
                writer.println("}");
            }
        }
}
