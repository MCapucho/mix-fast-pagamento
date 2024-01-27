package br.com.postech.mixfastpagamento.dataproviders.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestMensagemErro {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<String> causes;
}
