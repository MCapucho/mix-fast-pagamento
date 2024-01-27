package br.com.postech.mixfastpagamento.dataproviders.gateway.database;

import br.com.postech.mixfastpagamento.core.entity.FormaPagamento;
import br.com.postech.mixfastpagamento.core.gateway.FormaPagamentoGateway;
import br.com.postech.mixfastpagamento.dataproviders.exception.ResourceFailedException;
import br.com.postech.mixfastpagamento.dataproviders.model.db.FormaPagamentoDB;
import br.com.postech.mixfastpagamento.dataproviders.model.mapper.FormaPagamentoDBMapper;
import br.com.postech.mixfastpagamento.dataproviders.repository.FormaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class FormaPagamentoGatewayImpl implements FormaPagamentoGateway {

    private static final String BANCO_DE_DADOS = "Erro na comunicação com o banco de dados";

    private final FormaPagamentoDBMapper formaPagamentoDBMapper;
    private final FormaPagamentoRepository formaPagamentoRepository;

    @Override
    public FormaPagamento cadastrarOuAtualizar(FormaPagamento formaPagamento) {
        try {
            FormaPagamentoDB formaPagamentoDB = formaPagamentoRepository.save(formaPagamentoDBMapper.entityToDB(formaPagamento));
            return formaPagamentoDBMapper.dbToEntity(formaPagamentoDB);
        } catch (Exception e) {
            log.error("Erro ao cadastrar/atualizar uma forma de pagamento", e);
            throw new ResourceFailedException(BANCO_DE_DADOS);
        }
    }

    @Transactional
    @Override
    public List<FormaPagamento> buscarTodas() {
        try {
            List<FormaPagamentoDB> listaFormasPagamentoDB = formaPagamentoRepository.findAll();
            List<FormaPagamento> listaFormasPagamento = new ArrayList<>();

            listaFormasPagamentoDB.forEach(result -> {
                FormaPagamento formaPagamento = formaPagamentoDBMapper.dbToEntity(result);
                listaFormasPagamento.add(formaPagamento);
            });

            return listaFormasPagamento;
        } catch (Exception e) {
            log.error("Erro ao buscar todas formas de pagamento", e);
            throw new ResourceFailedException(BANCO_DE_DADOS);
        }
    }

    @Override
    public FormaPagamento buscarPorCodigo(String codigo) {
        try {
            return formaPagamentoRepository.findById(codigo)
                    .stream()
                    .map(formaPagamentoDBMapper::dbToEntity)
                    .findAny()
                    .orElse(null);
        } catch (Exception e) {
            log.error("Erro ao buscar uma forma de pagamento por código", e);
            throw new ResourceFailedException(BANCO_DE_DADOS);
        }
    }

    @Override
    public void deletarPorCodigo(String codigo) {
        try {
            formaPagamentoRepository.deleteById(codigo);
        } catch (Exception e) {
            log.error("Erro ao deletar uma forma de pagamento por código", e);
            throw new ResourceFailedException(BANCO_DE_DADOS);
        }
    }

    @Override
    public Boolean encontrarPorDescricao(String descricao) {
        try {
            return formaPagamentoRepository.existsByDescricao(descricao);
        } catch (Exception e) {
            log.error("Erro ao buscar uma forma de pagamento por descrição", e);
            throw new ResourceFailedException(BANCO_DE_DADOS);
        }
    }
}
