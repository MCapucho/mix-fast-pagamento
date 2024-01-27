package br.com.postech.mixfastpagamento.entrypoints.controller.v1.pagamento;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;
import br.com.postech.mixfastpagamento.core.entity.Pagamento;
import br.com.postech.mixfastpagamento.core.usecase.interfaces.pagamento.PagamentoGerarQRCodeUseCase;
import br.com.postech.mixfastpagamento.entrypoints.handler.RestExceptionHandler;
import br.com.postech.mixfastpagamento.entrypoints.http.PagamentoHttp;
import br.com.postech.mixfastpagamento.entrypoints.http.mapper.PagamentoHttpMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class PagamentoControllerTest {

    @InjectMocks
    private PagamentoController pagamentoController;
    @Mock
    private PagamentoHttpMapper pagamentoHttpMapper;
    @Mock
    private PagamentoGerarQRCodeUseCase pagamentoGerarQRCodeUseCase;

    private JacksonTester<PagamentoHttp> jacksonTester;
    private MockMvc mvc;
    private PagamentoHttp pagamentoHttp;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(pagamentoController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();

        pagamentoHttp = PagamentoHttp.builder()
                .pedido(UUID.randomUUID().toString())
                .valorTotal(new BigDecimal(120))
                .build();
    }

    @SneakyThrows
    @Test
    void gerarQRCode() {
        when(pagamentoHttpMapper.httpToEntity(any(PagamentoHttp.class)))
                .thenReturn(new Pagamento());

        when(pagamentoGerarQRCodeUseCase.gerarQRCode(any(Pagamento.class)))
                .thenReturn("abc1234");

        MockHttpServletResponse response =
                mvc.perform(
                        post("/v1/pagamentos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jacksonTester.write(pagamentoHttp).getJson())
                ).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}