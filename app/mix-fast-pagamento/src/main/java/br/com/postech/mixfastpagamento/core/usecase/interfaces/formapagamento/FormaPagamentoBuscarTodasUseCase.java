package br.com.postech.mixfastpagamento.core.usecase.interfaces.formapagamento;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;

import java.util.List;

public interface FormaPagamentoBuscarTodasUseCase {

    List<FormaPagamento> buscarTodas();
}
