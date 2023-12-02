package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum OrderState {
    ERROR,
    CONFIRMATION_PENDING,
    PAYMENT_PROCESS,
    IN_PREPARATION,
    SENDED,
    DELIVERED,
    DELIVERD_CONFIRMED

    ;

    @Converter
    public static class Convert extends GenericJsonConverter<OrderState> {

    }
}