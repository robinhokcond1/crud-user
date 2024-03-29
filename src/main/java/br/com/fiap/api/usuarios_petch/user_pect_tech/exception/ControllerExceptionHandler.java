package br.com.fiap.api.usuarios_petch.user_pect_tech.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    private StandardError validateError = new StandardError();
    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StandardError> entityNOtFound(
            ControllerNotFoundException e,
            HttpServletRequest request){

           HttpStatus status = HttpStatus.NOT_FOUND;
           validateError.setTimestamp(Instant.now());
           validateError.setStatus(status.value());
           validateError.setError("Entity not Found");
           validateError.setMessage(e.getMessage());
           validateError.setPath(request.getRequestURI());

           return ResponseEntity.status(status).body(this.validateError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(
            MethodArgumentNotValidException e,
            HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidateError validateError = new ValidateError();
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(status.value());
        validateError.setError("ERRO DE VALIDAÇÃO");
        validateError.setMessage(e.getMessage());
        validateError.setPath(request.getRequestURI());

        for(FieldError f: e.getBindingResult().getFieldErrors()){
            validateError.addMensagens(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(validateError);
    }
}
