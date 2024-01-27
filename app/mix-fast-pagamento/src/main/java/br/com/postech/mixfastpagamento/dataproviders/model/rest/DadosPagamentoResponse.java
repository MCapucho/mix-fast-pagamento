package br.com.postech.mixfastpagamento.dataproviders.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosPagamentoResponse {

    @JsonProperty("qr_data")
    private String qrDados;
}
