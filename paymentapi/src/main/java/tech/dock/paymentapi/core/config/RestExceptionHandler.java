package tech.dock.paymentapi.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import tech.dock.paymentapi.core.dto.ErrorDto;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(
            MethodArgumentNotValidException ex,
            WebRequest request
    ) {
        this.logger.error(ex.getMessage());
        ErrorDto errorDto = ErrorDto.newBuilder()
                .message("Argumento Inválido")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date().getTime())
                .errors(ex.getBindingResult().getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
