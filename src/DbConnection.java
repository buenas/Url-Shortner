/**
 * @author oziomaaniagu
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	private static DbConnection dbConnection = new  DbConnection();
	
	private DbConnection(){
		
	}
	public static DbConnection getInstance(){
		
		return dbConnection;
		
	}
	public static Connection connector() {

		try {

			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:urlShortner.sqlite");
			return conn;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
