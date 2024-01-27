package br.com.postech.mixfastpagamento.dataproviders.repository;

import br.com.postech.mixfastpagamento.dataproviders.model.db.FormaPagamentoDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoDB, String> {

    Boolean existsByDescricao(String descricao);
}
