package com.mesttra.model.dao;

import com.mesttra.model.entity.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class CondutorDAO {
    private final EntityManager entityManager;

    public CondutorDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("multas").createEntityManager();
    }

    public void salvar(Condutor condutor) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(condutor);
        this.entityManager.getTransaction().commit();
    }

    public void atualizar(Condutor condutor) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(condutor);
        this.entityManager.getTransaction().commit();
    }

    public void excluir(Condutor condutor) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(condutor);
        this.entityManager.getTransaction().commit();
    }

    public Condutor buscar(String nro_cnh) {
        return this.entityManager.find(Condutor.class, nro_cnh);
    }

    public List<Condutor> buscarTodos() {
        return this.entityManager.createQuery("SELECT c FROM condutores c", Condutor.class).getResultList();
    }

    public void close() {
        this.entityManager.close();
    }
}
