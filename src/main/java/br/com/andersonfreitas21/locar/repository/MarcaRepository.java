package br.com.andersonfreitas21.locar.repository;

import br.com.andersonfreitas21.locar.controller.marcas.dtos.MarcaDTO;
import br.com.andersonfreitas21.locar.model.marca.MarcaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer> {
    Optional<MarcaEntity> findByNome(String marcaEntity);

    @Query("""
            SELECT
                 new br.com.andersonfreitas21.locar.controller.marcas.dtos.MarcaDTO(m.id, m.nome, m.createdAt)
            FROM MarcaEntity m
            """)
    Page<MarcaDTO> findMarcas(Pageable pageable);

    @Query(
            """
                    SELECT
                         new br.com.andersonfreitas21.locar.controller.marcas.dtos.MarcaDTO(m.id, m.nome, m.createdAt)
                    FROM MarcaEntity m
                    WHERE m.id = ?1
                    """)
    Optional<MarcaDTO> findMarcaById(Integer id);
}
