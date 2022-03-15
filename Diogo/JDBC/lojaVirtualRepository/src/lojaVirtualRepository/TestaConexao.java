package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("Iniciando conex�o");
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conection = connectionFactory.recuperarConexao();
        conection.close();
        
    	System.out.println("Conex�o finalizada");
    	
	}

}
