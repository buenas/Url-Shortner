/**
 * @author oziomaaniagu
 */
import java.sql.Connection;

public class DbAccess {
	
	Object connection = DbConnection.getInstance();

	

	public DbAccess() {
		connection = DbConnection.connector();
		if (connection == null) {
			System.out.println("No database connection!");
		}
	}

	public Connection getConnection() {
		return (Connection) this.connection;

	}

}
