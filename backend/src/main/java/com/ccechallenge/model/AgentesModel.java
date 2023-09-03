package com.ccechallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agentes_model")
public class AgentesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String versao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgenteModel> agente;
}
