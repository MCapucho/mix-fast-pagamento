package br.com.postech.mixfastpagamento.dataproviders.model.db;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_formas_pagamento")
public class FormaPagamentoDB {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigo;

    @Column(name = "descricao", length = 50, nullable = false, unique = true)
    private String descricao;
}
