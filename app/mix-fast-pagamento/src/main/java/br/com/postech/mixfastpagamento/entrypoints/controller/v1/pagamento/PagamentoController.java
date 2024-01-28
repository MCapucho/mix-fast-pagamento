package br.com.postech.mixfastpagamento.entrypoints.controller.v1.pagamento;

import br.com.postech.mixfastpagamento.core.usecase.interfaces.pagamento.PagamentoGerarQRCodeUseCase;
import br.com.postech.mixfastpagamento.entrypoints.docs.PagamentoDocumentable;
import br.com.postech.mixfastpagamento.entrypoints.http.PagamentoHttp;
import br.com.postech.mixfastpagamento.entrypoints.http.mapper.PagamentoHttpMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping(value = "/v1/pagamentos")
public class PagamentoController implements PagamentoDocumentable {

    private final PagamentoHttpMapper pagamentoHttpMapper;
    private final PagamentoGerarQRCodeUseCase gerarQRCodeUseCase;

    @PostMapping
    public ResponseEntity<String> gerarQRCode(@Valid @RequestBody PagamentoHttp pagamentoHttp) {
        String qrCode = gerarQRCodeUseCase.gerarQRCode(pagamentoHttpMapper.httpToEntity(pagamentoHttp));
        log.info("QRCode gerado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(qrCode);
    }
}
