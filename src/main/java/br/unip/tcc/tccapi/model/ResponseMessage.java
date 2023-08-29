package br.unip.tcc.tccapi.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseMessage {

    private LocalDateTime date;
    private String message;
    private static String type = "br.unip.tcc.tccapi.model.ResponseMessage";

    public static ResponseMessage getMessage(String message) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(message);
        responseMessage.setDate(LocalDateTime.now());
        return responseMessage;
    }

}
