package com.mesttra.model.dao;

import com.mesttra.model.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class VeiculoDAO {
    private final EntityManager entityManager;

    public VeiculoDAO() {
        this.entityManager = Persistence.createEntityManagerFactory("multas").createEntityManager();
    }

    public void salvar(Veiculo veiculo) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(veiculo);
        this.entityManager.getTransaction().commit();
    }

    public void atualizar(Veiculo veiculo) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(veiculo);
        this.entityManager.getTransaction().commit();
    }

    public void excluir(Veiculo veiculo) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(veiculo);
        this.entityManager.getTransaction().commit();
    }

    public Veiculo buscar(String placa) {
        return this.entityManager.find(Veiculo.class, placa);
    }

    public List<Veiculo> buscarTodos() {
        return this.entityManager.createQuery("SELECT v FROM veiculos v", Veiculo.class).getResultList();
    }

    public void close() {
        this.entityManager.close();
    }
}
