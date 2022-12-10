package com.mesttra.model.dao;

import com.mesttra.model.entity.Multa;
import com.mesttra.model.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class MultaDAO {
    private final EntityManager entityManager;

    public MultaDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("multas").createEntityManager();
    }

    public void salvar(Multa multa) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(multa);
        this.entityManager.getTransaction().commit();
    }

    public void atualizar(Multa multa) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(multa);
        this.entityManager.getTransaction().commit();
    }

    public void excluir(Multa multa) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(multa);
        this.entityManager.getTransaction().commit();
    }

    public Multa buscarPorId(Long id) {
        return this.entityManager.find(Multa.class, id);
    }

    public List<Multa> buscarPorPlaca(Veiculo veiculo) {
        return this.entityManager.createQuery("SELECT m FROM multas m WHERE m.veiculo.placa = :placa", Multa.class)
                .setParameter("placa", veiculo.getPlaca())
                .getResultList();
    }

    public List<Multa> buscarTodos() {
        return this.entityManager.createQuery("SELECT m FROM multas m", Multa.class).getResultList();
    }

    public void close() {
        this.entityManager.close();
    }
}
