package controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody 
	
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return "Campo: " + e.getBindingResult().getFieldError().getField() + "Erro: " + e.getBindingResult().getFieldError().getDefaultMessage();
	}
}

//fazer a conexão com o banco de dados e finalizar as exception