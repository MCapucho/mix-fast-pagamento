package br.com.postech.mixfastpagamento.core.usecase.impl.formapagamento;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;
import br.com.postech.mixfastpagamento.core.exception.formapagamento.FormaPagamentoBadRequestException;
import br.com.postech.mixfastpagamento.core.exception.formapagamento.FormaPagamentoDuplicatedException;
import br.com.postech.mixfastpagamento.core.gateway.FormaPagamentoGateway;
import br.com.postech.mixfastpagamento.core.usecase.interfaces.formapagamento.FormaPagamentoCadastrarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormaPagamentoCadastrarUseCaseImpl implements FormaPagamentoCadastrarUseCase {

    private final FormaPagamentoGateway formaPagamentoGateway;

    @Override
    public FormaPagamento cadastrar(FormaPagamento formaPagamento) {
        if (formaPagamentoGateway.encontrarPorDescricao(formaPagamento.getDescricao())) {
            throw new FormaPagamentoDuplicatedException("Forma de Pagamento informada já cadastrada");
        }

        FormaPagamento formaPagamentoCadastrada = formaPagamentoGateway.cadastrarOuAtualizar(formaPagamento);

        if (formaPagamentoCadastrada == null) {
            throw new FormaPagamentoBadRequestException("Cadastro de forma de pagamento não foi concluído");
        }

        return formaPagamentoCadastrada;
    }
}
