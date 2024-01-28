package br.com.postech.mixfastpagamento.entrypoints.docs;

import br.com.postech.mixfastpagamento.entrypoints.handler.ErrorResponse;
import br.com.postech.mixfastpagamento.entrypoints.http.PagamentoHttp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Pagamentos")
public interface PagamentoDocumentable {

    @Operation(summary = "Gerar um novo QRCode de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "QRCode gerado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar uma nova forma de pagamento com os dados informados",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "409", description = "Erro na comunicação com o banco de dados",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") })})
    ResponseEntity<String> gerarQRCode(@Parameter PagamentoHttp pagamentoHttp);
}
