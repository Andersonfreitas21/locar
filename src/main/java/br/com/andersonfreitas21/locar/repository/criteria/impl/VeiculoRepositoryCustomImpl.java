package br.com.andersonfreitas21.locar.repository.criteria.impl;

import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoRequestCriteria;
import br.com.andersonfreitas21.locar.model.veiculo.VeiculoEntity;
import br.com.andersonfreitas21.locar.repository.criteria.VeiculoRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VeiculoRepositoryCustomImpl implements VeiculoRepositoryCustom {
    private final EntityManager entityManager;

    public VeiculoRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<VeiculoEntity> findVeiculosFilter(VeiculoRequestCriteria criteria) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<VeiculoEntity> query = criteriaBuilder.createQuery(VeiculoEntity.class);
        Root<VeiculoEntity> veiculo = query.from(VeiculoEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(criteria.chassi())) {
            predicates.add(criteriaBuilder.like(veiculo.get("chassi"), "%" + criteria.chassi() + "%"));
        }
        if (Objects.nonNull(criteria.placa())) {
            predicates.add(criteriaBuilder.like(veiculo.get("placa"), "%" + criteria.placa() + "%"));
        }
        if (Objects.nonNull(criteria.anoFabricacao())) {
            predicates.add(criteriaBuilder.like(veiculo.get("anoFabricacao"), "%" + criteria.anoFabricacao() + "%"));
        }
        if (Objects.nonNull(criteria.kmAtual())) {
            predicates.add(criteriaBuilder.like(veiculo.get("kmAtual"), "%" + criteria.kmAtual() + "%"));
        }
        if (Objects.nonNull(criteria.idMarca())) {
            predicates.add(criteriaBuilder.like(veiculo.get("idMarca"), "%" + criteria.idMarca() + "%"));
        }
        if (Objects.nonNull(criteria.idModelo())) {
            predicates.add(criteriaBuilder.like(veiculo.get("idModelo"), "%" + criteria.idModelo() + "%"));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(Predicate[]::new));
        }
        TypedQuery<VeiculoEntity> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();
    }
}
