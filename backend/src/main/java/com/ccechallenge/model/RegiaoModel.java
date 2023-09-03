package com.ccechallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "regiao_model")
public class RegiaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sigla;

    @OneToOne(cascade = CascadeType.ALL)
    private GeracaoModel geracao;

    @OneToOne(cascade = CascadeType.ALL)
    private CompraModel compra;

    @OneToOne(cascade = CascadeType.ALL)
    private PrecoMedioModel precoMedio;

    @ManyToOne
    @JoinColumn(name = "agente_id")
    private AgenteModel agenteModel;
}
