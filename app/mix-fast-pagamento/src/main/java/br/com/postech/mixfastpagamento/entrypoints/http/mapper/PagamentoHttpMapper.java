package br.com.postech.mixfastpagamento.entrypoints.http.mapper;

import br.com.postech.mixfastpagamento.core.entity.Pagamento;
import br.com.postech.mixfastpagamento.entrypoints.http.PagamentoHttp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface PagamentoHttpMapper {

    PagamentoHttpMapper INSTANCE = Mappers.getMapper( PagamentoHttpMapper.class );

    Pagamento httpToEntity(PagamentoHttp pagamentoHttp);
}
