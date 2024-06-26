package br.com.andersonfreitas21.locar.model.marca;

import br.com.andersonfreitas21.locar.controller.marcas.dtos.MarcaDTO;
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
@Table(name = "marcas")
@NoArgsConstructor
public class MarcaEntity {
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

    public MarcaEntity(String nome, Instant createdAt) {
        this.nome = nome;
        this.createdAt = createdAt;
    }

    public static MarcaDTO fromDTO(MarcaEntity marcaEntity) {
        return new MarcaDTO(marcaEntity.getId(), marcaEntity.getNome(), marcaEntity.getCreatedAt());
    }
}
