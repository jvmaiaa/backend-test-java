package com.desafio.backend.mapper;

import com.desafio.backend.domain.dto.request.RelatorioRequestDTO;
import com.desafio.backend.domain.dto.response.RelatorioResponseDTO;
import com.desafio.backend.domain.entity.EmpresaEntity;
import com.desafio.backend.domain.entity.RelatorioEntity;
import com.desafio.backend.domain.entity.VeiculoEntity;
import com.desafio.backend.domain.repository.EmpresaRepository;
import com.desafio.backend.domain.repository.VeiculoRepository;
import com.desafio.backend.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import static com.desafio.backend.exception.ExceptionMessages.VEICULO_NAO_ENCONTRADO;

public class RelatorioMapper {

    public static RelatorioEntity requestToEntity(RelatorioRequestDTO request,
                                                  VeiculoRepository veiculoRepository,
                                                  EmpresaRepository empresaRepository){
        if (request == null){
            return null;
        }
        RelatorioEntity entity = new RelatorioEntity();
        VeiculoEntity veiculoEntity = veiculoRepository.findById(request.getIdVeiculo())
                .orElseThrow(() -> new EntityNotFoundException(String.format(VEICULO_NAO_ENCONTRADO, request.getIdVeiculo())));
        entity.setVeiculoEntity(veiculoEntity);
        EmpresaEntity empresaEntity = empresaRepository.findById(request.getIdEmpresa())
                        .orElseThrow(() -> new EntityNotFoundException(String.format(VEICULO_NAO_ENCONTRADO, request.getIdEmpresa())));
        entity.setEmpresa(empresaEntity);
        entity.setTipoVeiculo(veiculoEntity.getTipoDeVeiculo());
        return entity;
    }

    public static RelatorioResponseDTO entityToResponse(RelatorioEntity entity){
        if (entity == null){
            return null;
        }
        RelatorioResponseDTO response = new RelatorioResponseDTO();
        response.setId(entity.getId());
        response.setIdEmpresa(entity.getEmpresa().getId());
        response.setIdVeiculo(entity.getVeiculoEntity().getId());
        response.setDataEntrada(entity.getDataEntrada());
        response.setDataSaida(entity.getDataSaida());
        response.setTipoVeiculo(entity.getTipoVeiculo());
        return response;
    }

}
