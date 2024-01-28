package br.com.postech.mixfastpagamento.dataproviders.gateway.api;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;
import br.com.postech.mixfastpagamento.core.gateway.PagamentoGateway;
import br.com.postech.mixfastpagamento.dataproviders.model.rest.DadosPagamentoRequest;
import br.com.postech.mixfastpagamento.dataproviders.model.rest.DadosPagamentoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class PagamentoGatewayImpl implements PagamentoGateway {

    private static final String PREFIX_TOKEN = "Bearer ";

    private static final String UNIDADE = "un";
    private static final String URL_NOTIFICACAO = "https://webhook.site/8a357810-a318-40b0-9fb4-71ee1cf1f27e";

    @Value("${spring.application.name}")
    private String aplicacao;

    @Value("${feign.client.config.pagamento.token}")
    private String token;

    private final IPagamentoClient pagamentoClient;

    @Override
    public String gerarQrCode(Pagamento pagamento) {
        log.info("Gateway 1");
        List<DadosPagamentoRequest.DadosPagamentoItensRequest> listaDadosPagamentoItensRequest = new ArrayList<>();

        DadosPagamentoRequest.DadosPagamentoItensRequest dadosPagamentoItensRequest =
                    DadosPagamentoRequest.DadosPagamentoItensRequest.builder()
                            .codigo("")
                            .categoria("")
                            .titulo("")
                            .descricao("")
                            .precoUnitario(pagamento.getValorTotal())
                            .quantidade(1)
                            .unidade(UNIDADE)
                            .valorTotal(pagamento.getValorTotal().multiply(new BigDecimal(1)))
                            .build();

        listaDadosPagamentoItensRequest.add(dadosPagamentoItensRequest);

        DadosPagamentoRequest dadosPagamentoRequest = DadosPagamentoRequest.builder()
                .referenciaExterna(pagamento.getPedido())
                .descricao(aplicacao)
                .titulo(aplicacao)
                .urlNotificacao(URL_NOTIFICACAO)
                .valorTotal(pagamento.getValorTotal())
                .itens(listaDadosPagamentoItensRequest)
                .build();

        String accessToken = PREFIX_TOKEN + token;
        log.info("Gateway 2");
        DadosPagamentoResponse dadosPagamentoResponse = pagamentoClient.gerarQRCode(dadosPagamentoRequest, accessToken);
        log.info("Gateway 3");
        return dadosPagamentoResponse.getQrDados();
    }
}
