package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conection = connectionFactory.recuperarConexao();
        
		PreparedStatement stm = conection.prepareStatement("select id, nome, descricao from produto");
		stm.execute();
		
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {
			
			Integer id = rst.getInt("id");
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			
			System.out.print(id);
			System.out.print(" - " + nome);
			System.out.println(" - " + descricao);
		}
		
		conection.close();

	}

}
