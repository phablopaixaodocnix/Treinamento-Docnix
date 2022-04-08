import java.sql.*;
import modelo.Produto;

public class PersistenciaProduto {
	
	private Connection connection;
	
	public PersistenciaProduto(Connection connection) {
		this.connection = connection;
	}
	
	public void salvarProduto (Produto produto) throws Exception{
		String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?,?)";
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			
			pstm.execute();
			
		}
	}
	
}
