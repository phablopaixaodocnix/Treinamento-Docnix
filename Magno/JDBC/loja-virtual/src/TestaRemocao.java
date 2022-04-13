import java.sql.Connection;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws Exception {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection  = criaConexao.recuperarConexao();
		
		Statement stm = connection.createStatement();
		stm.execute("DELETE FROM produto WHERE id > 4");
		
		Integer linhasModificadas = stm.getUpdateCount();
		
		System.out.println("Quantidade de linhas modificadas: "+linhasModificadas);

	}

}