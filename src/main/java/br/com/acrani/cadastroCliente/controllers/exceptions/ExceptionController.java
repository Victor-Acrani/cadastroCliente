package br.com.acrani.cadastroCliente.controllers.exceptions;

import br.com.acrani.cadastroCliente.services.exceptions.DuplicatedDataException;
import br.com.acrani.cadastroCliente.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found!";
        Map<String,String> message = new HashMap<>();
        message.put("Id:", e.getMessage());

        StandartError standartError = new StandartError(LocalDateTime.now()
                , HttpStatus.NOT_FOUND.value(), error, message, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        String error = "Argument not valid!";
        Map<String, String> message = e.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (a, b) -> a + " && " + b
                ));

        StandartError standartError = new StandartError(LocalDateTime.now()
                , HttpStatus.BAD_REQUEST.value(), error, message, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity<StandartError> duplicatedData(DuplicatedDataException e, HttpServletRequest request){
        String error = "Duplicated data!";
        Map<String,String> message = new HashMap<>();
        message.put("Error:", e.getMessage());

        StandartError standartError = new StandartError(LocalDateTime.now()
                , HttpStatus.CONFLICT.value(), error, message, request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(standartError);
    }
}
