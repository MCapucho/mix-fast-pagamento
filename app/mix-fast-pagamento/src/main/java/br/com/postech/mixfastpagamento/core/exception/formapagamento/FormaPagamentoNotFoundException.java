package br.com.postech.mixfastpagamento.core.exception.formapagamento;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FormaPagamentoNotFoundException extends RuntimeException {

    public FormaPagamentoNotFoundException(String message) {
        super(message);
    }
}
