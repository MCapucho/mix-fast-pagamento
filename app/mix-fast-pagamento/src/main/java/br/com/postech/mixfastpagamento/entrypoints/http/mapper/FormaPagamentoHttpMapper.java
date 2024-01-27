package br.com.postech.mixfastpagamento.entrypoints.http.mapper;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;
import br.com.postech.mixfastpagamento.entrypoints.http.FormaPagamentoHttp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface FormaPagamentoHttpMapper {

    FormaPagamentoHttpMapper INSTANCE = Mappers.getMapper( FormaPagamentoHttpMapper.class );

    FormaPagamentoHttp entityToHttp(FormaPagamento formaPagamento);

    @Mapping(target = "codigo", ignore = true)
    FormaPagamento httpToEntity(FormaPagamentoHttp formaPagamentoHttp);

    List<FormaPagamentoHttp> entityListToHttpList(List<FormaPagamento> formaPagamentoList);
    List<FormaPagamento> httpListToEntityList(List<FormaPagamentoHttp> formaPagamentoHttpList);
}
