package br.com.postech.mixfastpagamento.core.usecase.impl.pagamento;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;
import br.com.postech.mixfastpagamento.core.gateway.PagamentoGateway;
import br.com.postech.mixfastpagamento.core.usecase.interfaces.pagamento.PagamentoGerarQRCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PagamentoGerarQRCodeUseCaseImpl implements PagamentoGerarQRCodeUseCase {

    private final PagamentoGateway pagamentoGateway;

    @Override
    public String gerarQRCode(Pagamento pagamento) {
        return pagamentoGateway.gerarQrCode(pagamento);
    }
}
