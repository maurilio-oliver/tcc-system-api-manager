package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.persistence.Embeddable;

@Embeddable
public enum ProductState {

    ACTIVE,
    DISABLED;

    @Converter
    public static class
    Convert implements AttributeConverter<ProductState, String> {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(ProductState state) {
            try {
                return objectMapper.writeValueAsString(state);
            } catch (JsonProcessingException e) {
                // Tratar a exceção adequadamente
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public ProductState convertToEntityAttribute(String json) {
            try {
                return objectMapper.readValue(json, ProductState.class);
            } catch (JsonProcessingException e) {
                // Tratar a exceção adequadamente
                e.printStackTrace();
                return null;
            }
        }
    }


}
