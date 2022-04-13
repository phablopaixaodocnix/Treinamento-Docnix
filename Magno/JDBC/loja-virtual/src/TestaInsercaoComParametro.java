import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws Exception {
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();

		connection.setAutoCommit(false);

		try (PreparedStatement stm = connection.prepareStatement("INSERT INTO produto(nome, descricao) VALUES (?,?)",
				Statement.RETURN_GENERATED_KEYS);) {
			adicionarVariavel("SmartTV", "45 polegadas", stm);
			adicionarVariavel("Rádio", "Rádio de pilha", stm);

			connection.commit();

			stm.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ROLLBACK EXECUTADO");
			connection.rollback();
		}

	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);

		if (nome.equals("Rádio")) {
			throw new RuntimeException("Não foi possível adicionar o produto");
		}

		stm.execute();

		try (ResultSet rst = stm.getGeneratedKeys()) {

			while (rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O ID criado foi: " + id);
			}
		}
	}

}
