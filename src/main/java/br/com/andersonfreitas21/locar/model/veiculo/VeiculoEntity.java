package br.com.andersonfreitas21.locar.model.veiculo;

import br.com.andersonfreitas21.locar.model.veiculo.enums.CategoriaVeiculo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "veiculos")
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class VeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String chassi;
    @Column(nullable = false)
    private String placa;
    private String descricao;
    @Column(name = "ano_fabricacao")
    private String anoFabricacao;
    @Column(name = "km_atual")
    private Integer kmAtual;
    @Column(name = "id_marca", nullable = false)
    private Integer idMarca;
    @Column(name = "id_modelo", nullable = false)
    private Integer idModelo;
    @Enumerated(EnumType.STRING)
    @Column(name = "cat")
    private CategoriaVeiculo categoriaVeiculo;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;
    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    private Instant updatedAt;

    public VeiculoEntity(String chassi, String placa, Integer idMarca, Integer idModelo,String descricao, String anoFabricacao, Integer kmAtual, Instant createdAt) {
        this.chassi = chassi;
        this.placa = placa;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.descricao = descricao;
        this.anoFabricacao = anoFabricacao;
        this.kmAtual = kmAtual;
        this.createdAt = createdAt;
    }
}
