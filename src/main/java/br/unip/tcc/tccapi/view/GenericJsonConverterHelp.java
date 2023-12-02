package br.unip.tcc.tccapi.view;

public abstract class GenericJsonConverterHelp<T> {
    public String getTypeName() {
        throw new UnsupportedOperationException("Subclasses devem implementar este método.");
    }
}
