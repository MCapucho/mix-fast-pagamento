package br.com.postech.mixfastpagamento.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    private String pedido;
    private BigDecimal valorTotal;
}
