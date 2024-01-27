package br.com.postech.mixfastpagamento.core.usecase.impl.pagamento;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;
import br.com.postech.mixfastpagamento.core.gateway.PagamentoGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagamentoGerarQRCodeUseCaseImplTest {

    @InjectMocks
    private PagamentoGerarQRCodeUseCaseImpl pagamentoGerarQRCodeUseCaseImpl;
    @Mock
    private PagamentoGateway pagamentoGateway;

    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        pagamento = Pagamento.builder()
                .pedido(UUID.randomUUID().toString())
                .valorTotal(new BigDecimal("120.00"))
                .build();
    }

    @Test
    void deveUmQRCodeComoPagamentoComSucesso() {
        when(pagamentoGateway.gerarQrCode(any(Pagamento.class)))
                .thenReturn("abc1234");

        String qrCodeGerado = pagamentoGerarQRCodeUseCaseImpl.gerarQRCode(pagamento);

        Assertions.assertNotNull(qrCodeGerado);
        Assertions.assertEquals("abc1234", qrCodeGerado);
    }
}