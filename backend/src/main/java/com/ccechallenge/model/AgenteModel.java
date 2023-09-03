package com.ccechallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agente_model")
public class AgenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codigo;

    private LocalDateTime data;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegiaoModel> regiao;

    @ManyToOne
    @JoinColumn(name = "agentes_id")
    private AgentesModel agentesModel;
}
