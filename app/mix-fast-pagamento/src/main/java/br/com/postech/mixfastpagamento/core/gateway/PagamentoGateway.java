package br.com.postech.mixfastpagamento.core.gateway;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;

public interface PagamentoGateway {

    String gerarQrCode(Pagamento pagamento);
}
