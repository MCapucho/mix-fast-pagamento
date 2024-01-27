package br.com.postech.mixfastpagamento.core.usecase.impl.formapagamento;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;
import br.com.postech.mixfastpagamento.core.gateway.FormaPagamentoGateway;
import br.com.postech.mixfastpagamento.core.usecase.interfaces.formapagamento.FormaPagamentoAtualizarUseCase;
import br.com.postech.mixfastpagamento.core.usecase.interfaces.formapagamento.FormaPagamentoBuscarPorCodigoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormaPagamentoAtualizarUseCaseImpl implements FormaPagamentoAtualizarUseCase {

    private final FormaPagamentoGateway formaPagamentoGateway;
    private final FormaPagamentoBuscarPorCodigoUseCase formaPagamentoBuscarPorCodigoUseCase;

    @Override
    public FormaPagamento atualizar(String codigo, FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoEncontrada = formaPagamentoBuscarPorCodigoUseCase.buscarPorCodigo(codigo);

        formaPagamentoEncontrada.setDescricao(formaPagamento.getDescricao() != null ?
                formaPagamento.getDescricao() :
                formaPagamentoEncontrada.getDescricao());

        return formaPagamentoGateway.cadastrarOuAtualizar(formaPagamentoEncontrada);
    }
}
