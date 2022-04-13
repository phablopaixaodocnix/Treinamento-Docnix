package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import antlr.collections.List;
import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		cadastrarProduto();
		Long id = 1l;
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());

	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Nokia Tijol�o", "Quebra nunca", new BigDecimal("800"), celulares);
		 
		 EntityManager em = JPAUtil.getEntityManager();
		 ProdutoDao produtoDao = new ProdutoDao(em);
		 CategoriaDao categoriaDao = new CategoriaDao(em);
		 
		 em.getTransaction().begin();
		 
		 categoriaDao.cadastrar(celulares);
		 produtoDao.cadastrar(celular);
		 
		 em.getTransaction().commit();
		 em.close();
	}

}
