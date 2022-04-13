import java.sql.Connection;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("12345678");
		
		comboPooledDataSource.setMaxPoolSize(10);
		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection recuperarConexao() throws Exception{
		return this.dataSource.getConnection();
	}

}
