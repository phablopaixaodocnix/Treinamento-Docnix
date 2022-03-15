package br.com.alura.jdbc;

import java.sql.Connection;
import br.com.alura.jdbc.modelo.Produto;

public class PersistenciaProduto {
	
	private Connection connection;
	
	public PersistenciaProduto(Connection connection ) {
		this.connection = connection;
		
	}
	
	public void salvarProduto(Produto produto) {
		
		
		
	}

}
