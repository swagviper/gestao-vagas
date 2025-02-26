package dev.swagviper.gestao_vagas.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String message = messageSource.getMessage(
                error,
                LocaleContextHolder.getLocale()
            );

            dto.add(new ErrorMessageDTO(message, error.getField()));
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

}
