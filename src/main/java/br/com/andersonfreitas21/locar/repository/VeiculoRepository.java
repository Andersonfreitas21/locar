package br.com.andersonfreitas21.locar.repository;

import br.com.andersonfreitas21.locar.model.veiculo.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Integer> {
}
