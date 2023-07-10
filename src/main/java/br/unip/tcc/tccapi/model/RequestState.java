package br.unip.tcc.tccapi.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum RequestState {
    REQUESTED,
    ACCEPTED,
    PAYMENT_PROCESS,
    CONFIRMED,
    IN_PREPARATION,
    SENDED,
    RECEIVED

    ;

    @Converter
    public static class
    Convert implements AttributeConverter<RequestState, String> {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(RequestState state) {
            try {
                return objectMapper.writeValueAsString(state);
            } catch (JsonProcessingException e) {
                // Tratar a exceção adequadamente
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public RequestState convertToEntityAttribute(String json) {
            try {
                return objectMapper.readValue(json, RequestState.class);
            } catch (JsonProcessingException e) {
                // Tratar a exceção adequadamente
                e.printStackTrace();
                return null;
            }
        }
    }
}