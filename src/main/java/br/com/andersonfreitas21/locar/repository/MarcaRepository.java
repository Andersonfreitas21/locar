package br.com.andersonfreitas21.locar.repository;

import br.com.andersonfreitas21.locar.controller.dto.MarcaDTO;
import br.com.andersonfreitas21.locar.model.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    Optional<Marca> findByNome(String marca);

    @Query("""
            SELECT
                 new br.com.andersonfreitas21.locar.controller.dto.MarcaDTO(m.id, m.nome, m.createdAt)
            FROM Marca m
            """)
    Page<MarcaDTO> findMarcas(Pageable pageable);

    @Query(
            """
                    SELECT
                         new br.com.andersonfreitas21.locar.controller.dto.MarcaDTO(m.id, m.nome, m.createdAt)
                    FROM Marca m
                    WHERE m.id = ?1
                    """)
    Optional<MarcaDTO> findMarcaById(Integer id);
}
