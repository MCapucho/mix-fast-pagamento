package br.com.postech.mixfastpagamento.core.usecase.impl.pagamento;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;
import br.com.postech.mixfastpagamento.core.gateway.PagamentoGateway;
import br.com.postech.mixfastpagamento.core.usecase.interfaces.pagamento.PagamentoGerarQRCodeUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class PagamentoGerarQRCodeUseCaseImpl implements PagamentoGerarQRCodeUseCase {

    private final PagamentoGateway pagamentoGateway;

    @Override
    public String gerarQRCode(Pagamento pagamento) {
        log.info("Use Case");
        return pagamentoGateway.gerarQrCode(pagamento);
    }
}
