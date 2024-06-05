package br.unip.tcc.tccapi.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class ListConverter<T> implements AttributeConverter<Collection<T>, String> {

    private static final String DELIMITER = ",";
    private final ObjectMapper objectMapper = new ObjectMapper();



    public ListConverter() {

    }

    @Override
    public String convertToDatabaseColumn(Collection<T> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting list to JSON", e);
        }
    }

    @Override
    public Collection<T> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            Collection<T> array = objectMapper.readValue(dbData, Collection.class);
            return array;
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to list", e);
        }
    }

    public static void main(String[] args) {
        System.out.printf("");
    }
}
