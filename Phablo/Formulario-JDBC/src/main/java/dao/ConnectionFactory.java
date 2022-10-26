package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  public Connection recuperarConexaoComBancoDeDados(){
	try {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Desafio Formulário", "postgres", "postgres");
	}catch(SQLException e) {
		System.out.println("Não Foi possível conectar ao banco de dados.");
		e.printStackTrace();
		return null;
	}
  }
}
