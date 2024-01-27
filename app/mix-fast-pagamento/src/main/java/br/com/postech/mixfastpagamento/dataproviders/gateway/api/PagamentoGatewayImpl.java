package br.com.postech.mixfastpagamento.dataproviders.gateway.api;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;
import br.com.postech.mixfastpagamento.core.gateway.PagamentoGateway;
import br.com.postech.mixfastpagamento.dataproviders.model.rest.DadosPagamentoRequest;
import br.com.postech.mixfastpagamento.dataproviders.model.rest.DadosPagamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

        DadosPagamentoResponse dadosPagamentoResponse = pagamentoClient.gerarQRCode(dadosPagamentoRequest, accessToken);

        return dadosPagamentoResponse.getQrDados();
    }
}
