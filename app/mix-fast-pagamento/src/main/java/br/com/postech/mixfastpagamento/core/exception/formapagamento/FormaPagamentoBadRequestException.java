package br.com.postech.mixfastpagamento.core.exception.formapagamento;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaPagamentoBadRequestException extends RuntimeException {

    public FormaPagamentoBadRequestException(String message) {
        super(message);
    }
}
