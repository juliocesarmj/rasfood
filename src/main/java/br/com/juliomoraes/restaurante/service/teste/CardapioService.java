package br.com.juliomoraes.restaurante.service.teste;

import br.com.juliomoraes.restaurante.dao.CardapioDao;
import br.com.juliomoraes.restaurante.dao.CategoriaDao;
import br.com.juliomoraes.restaurante.entity.Cardapio;
import br.com.juliomoraes.restaurante.entity.Categoria;
import br.com.juliomoraes.restaurante.util.JPAUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        cadastrarProdutoCardapio(entityManager, cadastrarCategoria(entityManager));
    }

    private static Categoria cadastrarCategoria(EntityManager entityManager) {
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        Categoria pratoPrincipal = new Categoria("Prato principal");
        entityManager.getTransaction().begin();

        categoriaDao.cadastrar(pratoPrincipal);
        entityManager.getTransaction().commit();
        entityManager.clear();

        return pratoPrincipal;
    }

    private static void cadastrarProdutoCardapio(EntityManager entityManager, Categoria categoria) {
        Cardapio risoto = new Cardapio();
        risoto.setNome("risoto de frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        risoto.setDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));
        risoto.setCategoria(categoria);

        Cardapio salmon = new Cardapio();
        salmon.setNome("salmao ao molho de maracujá");
        salmon.setDescricao("Salmao grelhado ao molho de maracujá");
        salmon.setDisponivel(true);
        salmon.setValor(BigDecimal.valueOf(60.00));
        salmon.setCategoria(categoria);

        CardapioDao cardapioDao = new CardapioDao(entityManager);
        entityManager.getTransaction().begin();

        cardapioDao.cadastrar(risoto);
        entityManager.flush();
        cardapioDao.cadastrar(salmon);
        entityManager.flush();
        //System.out.println("o prato consultado foi: " + cardapioDao.consultarPorId(1));
        //System.out.println("o prato consultado foi: " + cardapioDao.consultarPorId(2));
       // cardapioDao.consultarPorValor(BigDecimal.valueOf(60.00)).forEach(System.out::println);

        System.out.println(cardapioDao.consultarPorNome("RISoT"));

        entityManager.close();

    }
}
