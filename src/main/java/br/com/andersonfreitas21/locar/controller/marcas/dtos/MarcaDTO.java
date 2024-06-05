package br.com.andersonfreitas21.locar.controller.marcas.dtos;

import br.com.andersonfreitas21.locar.model.marca.MarcaEntity;

import java.time.Instant;

public record MarcaDTO(Integer id,
                       String nome,
                       Instant createdAt) {
    public static MarcaDTO fromEntity(MarcaEntity marcaEntity){
        return new MarcaDTO(
                marcaEntity.getId(),
                marcaEntity.getNome(),
                marcaEntity.getCreatedAt()
        );
    }
}
