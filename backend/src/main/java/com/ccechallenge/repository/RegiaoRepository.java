package com.ccechallenge.repository;

import com.ccechallenge.model.RegiaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegiaoRepository extends JpaRepository<RegiaoModel, Long> {

    List<RegiaoModel> findBySigla(String sigla);

}
