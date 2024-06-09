package br.com.andersonfreitas21.locar.repository;

import br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloDTO;
import br.com.andersonfreitas21.locar.model.modelo.ModeloEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModeloRepository extends JpaRepository<ModeloEntity, Integer> {
    Optional<ModeloEntity> findByNome(String modeloEntity);

    @Query("""
            SELECT
                 new br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloDTO(m.id, m.nome, m.idMarca, m.createdAt)
            FROM ModeloEntity m
            """)
    Page<ModeloDTO> findModelos(Pageable pageable);

    @Query(
            """
                    SELECT
                         new br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloDTO(m.id, m.nome, m.idMarca, m.createdAt)
                    FROM ModeloEntity m
                    WHERE m.id = ?1
                    """)
    Optional<ModeloDTO> findModeloById(Integer id);
    @Query(
            """
                    SELECT
                         new br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloDTO(m.id, m.nome, m.idMarca, m.createdAt)
                    FROM ModeloEntity m
                    WHERE m.idMarca = ?1
                    """)
    List<ModeloDTO> findModeloByIdMarca(Integer idMarca);
}
