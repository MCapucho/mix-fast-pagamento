package br.com.postech.mixfastpagamento.dataproviders.gateway.api;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;
import br.com.postech.mixfastpagamento.dataproviders.model.rest.DadosPagamentoResponse;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagamentoGatewayImplTest {

    @InjectMocks
    private PagamentoGatewayImpl pagamentoGatewayImpl;
    @Mock
    private IPagamentoClient pagamentoClient;

    private Pagamento pagamento;
    private DadosPagamentoResponse dadosPagamentoResponse;

    @BeforeEach
    void setUp() {
        pagamento = Pagamento.builder()
                .pedido(UUID.randomUUID().toString())
                .valorTotal(new BigDecimal("120.00"))
                .build();

        dadosPagamentoResponse = DadosPagamentoResponse.builder()
                .qrDados("abc123")
                .build();
    }

    @Test
    void gerarQrCode() {
        when(pagamentoClient.gerarQRCode(any(), anyString()))
                .thenReturn(dadosPagamentoResponse);

        String qrCodeGerado = pagamentoGatewayImpl.gerarQrCode(pagamento);

        Assertions.assertEquals("abc123", qrCodeGerado);
    }
}