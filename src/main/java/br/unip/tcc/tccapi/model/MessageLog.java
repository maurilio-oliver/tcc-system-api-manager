package br.unip.tcc.tccapi.model;

import java.util.Map;
import java.util.Objects;

public class MessageLog {

    public static Map<String, Object> simpleMessageError(Exception ex){
       StackTraceElement principal =  ex.getStackTrace()[0];
        String message = ex.getMessage();

        String localize = ex.getLocalizedMessage();

        return Map.of("message", message, "localizedMessage", localize, "principal", principal);

    }
}
