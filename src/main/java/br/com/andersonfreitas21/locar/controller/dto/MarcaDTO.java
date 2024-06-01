package br.com.andersonfreitas21.locar.controller.dto;

import br.com.andersonfreitas21.locar.model.Marca;

import java.time.Instant;

public record MarcaDTO(Integer id,
                       String nome,
                       Instant createdAt) {
    public static MarcaDTO fromEntity(Marca marca){
        return new MarcaDTO(
                marca.getId(),
                marca.getNome(),
                marca.getCreatedAt()
        );
    }
}
