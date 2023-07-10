package br.unip.tcc.tccapi.view;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @apiNote this view was created to assist in converting complex data into database
 * entities and vice versa
 * @param <T> in with T is an entity with persistence in table column
 *
 */
public abstract class GenericJsonConverter<T> implements AttributeConverter<T, String> {


    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Type type;


    /**
     *
     * configation initialize
     */
    @SuppressWarnings("unchecked")
    public GenericJsonConverter() {
        Type[] typeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        this.type = typeArguments[0];
    }

    /**
     *
     * @param entityAttribute in which you want to convert to a database table
     * @return converted json entity
     * @throws JsonProcessingException case not can convert in json and return null
     */
    @Override
    public String convertToDatabaseColumn(T entityAttribute) {
        try {
            return objectMapper.writeValueAsString(entityAttribute);
        } catch (JsonProcessingException e) {
            // Tratar a exceção adequadamente
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param json string in which we will convert to entity
     * @return entity converted
     * @throws IOException if the json was not successfully mapped or is divergent from the entity
     */
    @Override
    public T convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, objectMapper.constructType(type));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}