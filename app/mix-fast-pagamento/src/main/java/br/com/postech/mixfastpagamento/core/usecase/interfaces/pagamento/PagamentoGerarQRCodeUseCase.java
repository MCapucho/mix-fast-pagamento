package br.com.postech.mixfastpagamento.core.usecase.interfaces.pagamento;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;

public interface PagamentoGerarQRCodeUseCase {

    String gerarQRCode(Pagamento pagamento);
}
