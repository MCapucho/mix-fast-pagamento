package br.com.postech.mixfastpagamento.dataproviders.model.mapper;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;
import br.com.postech.mixfastpagamento.dataproviders.model.db.FormaPagamentoDB;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface FormaPagamentoDBMapper {

    @Mapping(target = "descricao", source = "descricao")
    FormaPagamentoDB entityToDB(FormaPagamento formaPagamento);

    @Mapping(target = "codigo", source = "codigo")
    @Mapping(target = "descricao", source = "descricao")
    FormaPagamento dbToEntity(FormaPagamentoDB formaPagamentoDB);
}
