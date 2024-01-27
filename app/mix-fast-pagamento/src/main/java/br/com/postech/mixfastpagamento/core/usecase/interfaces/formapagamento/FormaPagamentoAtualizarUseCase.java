package br.com.postech.mixfastpagamento.core.usecase.interfaces.formapagamento;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;

public interface FormaPagamentoAtualizarUseCase {

    FormaPagamento atualizar(String codigo, FormaPagamento formaPagamento);
}
