package br.com.andersonfreitas21.locar.controller.modelos.dtos;


import br.com.andersonfreitas21.locar.model.modelo.ModeloEntity;

import java.time.Instant;

public record ModeloDTO(Integer id,
                        String nome,
                        Integer idMarca,
                        Instant createdAt) {
    public static ModeloDTO fromEntity(ModeloEntity marcaEntity){
        return new ModeloDTO(
                marcaEntity.getId(),
                marcaEntity.getNome(),
                marcaEntity.getIdMarca(),
                marcaEntity.getCreatedAt()
        );
    }
}
