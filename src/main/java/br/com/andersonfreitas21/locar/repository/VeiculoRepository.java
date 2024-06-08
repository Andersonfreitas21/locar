package br.com.andersonfreitas21.locar.repository;

import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoDTO;
import br.com.andersonfreitas21.locar.model.veiculo.VeiculoEntity;
import br.com.andersonfreitas21.locar.repository.criteria.VeiculoRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Integer>, VeiculoRepositoryCustom {
    Optional<VeiculoEntity> findByChassi(String chassi);

    @Query("""
            SELECT
                 new br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoDTO(v.id, v.chassi, v.placa, v.descricao, v.idMarca, v.idModelo)
            FROM VeiculoEntity v
            """)
    Page<VeiculoDTO> findVeiculos(Pageable pageable);

    @Query("""
            SELECT
                 new br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoDTO(v.id, v.chassi, v.placa, v.descricao, v.idMarca, v.idModelo)
            FROM VeiculoEntity v
            WHERE v.id = ?1
            """)
    Optional<VeiculoDTO> findVeiculoById(Integer id);
}
