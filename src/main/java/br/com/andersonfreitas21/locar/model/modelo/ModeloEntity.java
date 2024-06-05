package br.com.andersonfreitas21.locar.model.modelo;

import br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "modelos")
@NoArgsConstructor
public class ModeloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;
    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        updatedAt = Instant.now();
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public ModeloEntity(String nome, Instant createdAt) {
        this.nome = nome;
        this.createdAt = createdAt;
    }

    public static ModeloDTO fromDTO(ModeloEntity modeloEntity) {
        return new ModeloDTO(modeloEntity.getId(), modeloEntity.getNome(), modeloEntity.getCreatedAt());
    }
}
