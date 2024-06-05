package br.unip.tcc.tccapi.model;

import br.unip.tcc.tccapi.view.GenericJsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum OrderState {
    CANCELED(15),
    ERROR(0),
    SENDED(7),
    DELIVERED(8),
    DELIVERD_CONFIRMED(7),

    ACCEPTED(3),
    REQUESTED(2),
    PENDING_PAYMENT(4),
    PAID(5);

    private int id;
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

     OrderState(int id){
        this.id = id;
    }
    @Converter
    public static class Convert extends GenericJsonConverter<OrderState> {

    }
}