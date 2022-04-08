import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws Exception {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();
		
		System.out.println("Fechando conexão");
		
		Statement stm =  connection.createStatement();
		stm.execute("SELECT ID, nome, descricao FROM PRODUTO");
		
		ResultSet rst = stm.getResultSet();
		
		while (rst.next()) {
			Integer id = rst.getInt("id");
			System.out.println(id);
			String nome = rst.getString("nome");
			System.out.println(nome);
			String descricao = rst.getString("descricao");
			System.out.println(descricao);
		}
		
		connection.close();

	}

}
