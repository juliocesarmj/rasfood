package br.com.juliomoraes.restaurante.service.teste;

import br.com.juliomoraes.restaurante.dao.PratoDao;
import br.com.juliomoraes.restaurante.entity.Prato;
import br.com.juliomoraes.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PratoService {
    public static void main(String[] args) {
        Prato risoto = new Prato();
        risoto.setNome("risoto de frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        risoto.setDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));

        Prato salmon = new Prato();
        salmon.setNome("salmao ao molho de maracujá");
        salmon.setDescricao("Salmao grelhado ao molho de maracujá");
        salmon.setDisponivel(true);
        salmon.setValor(BigDecimal.valueOf(60.00));

        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        PratoDao pratoDao = new PratoDao(entityManager);
        entityManager.getTransaction().begin();
        pratoDao.cadastrar(risoto);
        entityManager.flush();
        pratoDao.cadastrar(salmon);
        entityManager.flush();
        System.out.println("o prato consultado foi: " + pratoDao.consultar(1));
        System.out.println("o prato consultado foi: " + pratoDao.consultar(2));
        pratoDao.excluir(salmon);
        System.out.println("o prato consultado foi: " + pratoDao.consultar(2));
        entityManager.clear();
        risoto.setValor(BigDecimal.valueOf(75.50));
        pratoDao.atualizar(risoto);
        System.out.println("O prato consultado foi: " + pratoDao.consultar(1));



    }
}
