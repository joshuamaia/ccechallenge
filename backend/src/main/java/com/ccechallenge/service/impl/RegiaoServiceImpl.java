package com.ccechallenge.service.impl;

import com.ccechallenge.dto.Geracao;
import com.ccechallenge.dto.PrecoMedio;
import com.ccechallenge.dto.Regiao;
import com.ccechallenge.model.CompraModel;
import com.ccechallenge.model.GeracaoModel;
import com.ccechallenge.model.PrecoMedioModel;
import com.ccechallenge.model.RegiaoModel;
import com.ccechallenge.service.RegiaoService;
import com.ccechallenge.service.port.BuscarRegiaoBySiglaPort;
import com.ccechallenge.utils.UtilityConvert;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegiaoServiceImpl implements RegiaoService, UtilityConvert<RegiaoModel, Regiao, Regiao> {

    private final ModelMapper modelMapper;

    private final BuscarRegiaoBySiglaPort<String, RegiaoModel> buscarRegiaoBySiglaPort;

    public RegiaoServiceImpl(ModelMapper modelMapper, BuscarRegiaoBySiglaPort<String, RegiaoModel> buscarRegiaoBySiglaPort) {
        this.modelMapper = modelMapper;
        this.buscarRegiaoBySiglaPort = buscarRegiaoBySiglaPort;
    }

    @Override
    public RegiaoModel convertRequestForEntity(Regiao regiao) {
        return this.modelMapper.map(regiao, RegiaoModel.class);
    }

    @Override
    public Regiao convertEntityForResponse(RegiaoModel regiaoModel) {
        return this.modelMapper.map(regiaoModel, Regiao.class);
    }

    @Override
    public List<Regiao> buscarRegiaoBySigle(String sigla) {
        List<Regiao> listResponse = new ArrayList<>();
        List<RegiaoModel> listRegiaoModel = this.buscarRegiaoBySiglaPort.execute(sigla);

        if(!listRegiaoModel.isEmpty()){
            listRegiaoModel.stream().forEach(regiao -> {
                CompraModel compraModel = regiao.getCompra();
                GeracaoModel geracaoModel = regiao.getGeracao();
//                PrecoMedioModel precoMedioModel = regiao.getPrecoMedio();

                if(compraModel != null){
                    List<BigDecimal> listaValor = compraModel.getValores().stream().map(v-> v.getValor()).collect(Collectors.toList());
                    compraModel.setValor(listaValor);
                }

                if(geracaoModel != null){
                    List<BigDecimal> listaValor = geracaoModel.getValores().stream().map(v-> v.getValor()).collect(Collectors.toList());
                    geracaoModel.setValor(listaValor);
                }

                    //Preco Medio deve vir null
//                if(precoMedioModel != null){
//                    List<BigDecimal> listaValor = precoMedioModel.getValores().stream().map(v-> v.getValor()).collect(Collectors.toList());
//                    precoMedioModel.setValor(listaValor);
//                }
            });

            listResponse = listRegiaoModel.stream().map(this::convertEntityForResponse).collect(Collectors.toList());

            return listResponse;
        }else {
            throw new EntityNotFoundException("Não foi encontrada nenhuma região para essa sigla - " + sigla);
        }
    }
}
