package br.com.juliomoraes.restaurante.dao;

import br.com.juliomoraes.restaurante.entity.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Categoria categoria) {
        this.entityManager.persist(categoria);
    }

    public Categoria consultar(Integer id) {
        return this.entityManager.find(Categoria.class, id);
    }

    public void excluir(Categoria categoria) {
        this.entityManager.remove(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.entityManager.merge(categoria);
    }
}
