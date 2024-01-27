package br.com.postech.mixfastpagamento.core.usecase.interfaces.formapagamento;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;

public interface FormaPagamentoBuscarPorCodigoUseCase {

    FormaPagamento buscarPorCodigo(String codigo);
}
