package br.com.postech.mixfastpagamento.core.gateway;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;

import java.util.List;

public interface FormaPagamentoGateway {

    FormaPagamento cadastrarOuAtualizar(FormaPagamento formaPagamento);
    List<FormaPagamento> buscarTodas();
    FormaPagamento buscarPorCodigo(String codigo);
    void deletarPorCodigo(String codigo);
    Boolean encontrarPorDescricao(String descricao);
}
