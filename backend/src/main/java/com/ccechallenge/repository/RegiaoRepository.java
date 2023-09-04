package com.ccechallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ccechallenge.dto.ConsolidadoRegiaoDto;
import com.ccechallenge.model.RegiaoModel;

@Repository
public interface RegiaoRepository extends JpaRepository<RegiaoModel, Long> {

    List<RegiaoModel> findBySigla(String sigla);
    
    @Query(value = "SELECT sigla, sum(valorcompra) as valorcompra, sum(valorgeracao) as valorgeracao\r\n"
    		+ "FROM (SELECT sigla, sum(v.valor) as valorcompra, 0 as valorgeracao\r\n"
    		+ "FROM regiao_model r\r\n"
    		+ "JOIN compra_model_valores c ON r.compra_id = c.compra_model_id\r\n"
    		+ "JOIN valor_model v ON c.valores_id = v.id\r\n"
    		+ "GROUP BY 1\r\n"
    		+ "\r\n"
    		+ "UNION\r\n"
    		+ "\r\n"
    		+ "SELECT sigla, 0 as valorcompra, sum(v.valor) as valorgeracao\r\n"
    		+ "FROM regiao_model r\r\n"
    		+ "JOIN geracao_model_valores g ON r.geracao_id = g.geracao_model_id\r\n"
    		+ "JOIN valor_model v ON g.valores_id = v.id\r\n"
    		+ "GROUP BY 1) as tabela\r\n"
    		+ "GROUP BY 1", nativeQuery = true)
    public List<ConsolidadoRegiaoDto> consolidadoRegiao();

}
