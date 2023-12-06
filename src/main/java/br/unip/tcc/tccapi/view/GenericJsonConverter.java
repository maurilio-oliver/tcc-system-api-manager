package br.unip.tcc.tccapi.view;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.internal.filter.ValueNodes;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.hibernate.dialect.PostgreSQLJsonbJdbcType;
import org.springframework.jdbc.core.SqlTypeValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Converter
public class GenericJsonConverter<T> extends GenericJsonConverterHelp implements AttributeConverter<T, String> {
private Logger log = Logger.getLogger(GenericJsonConverter.class.getName());
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void config() {

        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
           ObjectMapper mapper = objectMapper.findAndRegisterModules();
            this.config();
             if (attribute == null){
                 return null;
             } else {


                 return mapper.writeValueAsString(attribute); //mapper.writeValueAsString(attribute);
             }

        } catch (Exception e) {
            // Trate exceções adequadamente
            throw new RuntimeException("Error converting to JSONB", e);
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        this.config();
        try {
            if(Objects.equals(dbData, null)) {
                return null;


            } else {
                JsonNode a = objectMapper.readTree(dbData);
                ObjectMapper objectMapper2 = objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                HashMap mapper = objectMapper2.readValue(dbData, HashMap.class);
                return (T) objectMapper.readValue(dbData, (Class.forName((String) mapper.get("typeName"))).newInstance().getClass());
            }
        } catch (IOException | ClassNotFoundException e) {
            // Trate exceções adequadamente
            throw new RuntimeException("Error converting to entity", e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }






}