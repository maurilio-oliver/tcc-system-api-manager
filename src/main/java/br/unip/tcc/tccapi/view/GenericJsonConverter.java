package br.unip.tcc.tccapi.view;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
           ObjectMapper mapper = objectMapper.findAndRegisterModules();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
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