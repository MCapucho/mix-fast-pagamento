package br.com.postech.mixfastpagamento.core.usecase.impl.formapagamento;

import br.com.postech.mixfastpagamento.core.gateway.FormaPagamentoGateway;
import br.com.postech.mixfastpagamento.core.usecase.interfaces.formapagamento.FormaPagamentoBuscarPorCodigoUseCase;
import br.com.postech.mixfastpagamento.core.usecase.interfaces.formapagamento.FormaPagamentoDeletarPorCodigoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormaPagamentoDeletarPorCodigoUseCaseImpl implements FormaPagamentoDeletarPorCodigoUseCase {

    private final FormaPagamentoGateway formaPagamentoGateway;
    private final FormaPagamentoBuscarPorCodigoUseCase formaPagamentoBuscarPorCodigoUseCase;

    @Override
    public void deletarPorCodigo(String codigo) {
        formaPagamentoBuscarPorCodigoUseCase.buscarPorCodigo(codigo);
        formaPagamentoGateway.deletarPorCodigo(codigo);
    }
}
