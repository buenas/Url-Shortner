/**
 * @author oziomaaniagu
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Core {
	/**
	 * Domain name defaults to http://cl.ip/
	 */
	private String domain;
	public static final char[] ELEMENTS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$*%!"
			.toCharArray();

	DbAccess dbAccess = new DbAccess();
	Connection connection = dbAccess.getConnection();
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	/**
	 * Default Constructor
	 */
	public Core() {
		domain = "http://cl.ip/";

	}

	/**
	 * Generate 10 random alphanumeric key between [0-9],[a-z] & [A-Z] inclusive
	 * 
	 * @return String data type
	 */
	public String generateKey() {
		String key = "";
		StringBuilder stringbuilder = new StringBuilder();
		Random random = new Random();
		key = "";
		for (int i = 0; i < 10; i++) {
			char cha = ELEMENTS[random.nextInt(ELEMENTS.length)];
			stringbuilder.append(cha);
			key = stringbuilder.toString();
		}
		return key;
	}

	/**
	 * Shortens a given Url
	 * @param longUrl
	 * @return
	 * @throws SQLException
	 */
	public String getShortUrl(String longUrl) throws SQLException {
		String shortUrl = "";
		// connection = DbConnection.connector();
		String query = "SELECT shorturl FROM url WHERE longurl=?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, longUrl);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			shortUrl = resultSet.getString("shorturl");
		} else {
			shortUrl = domain + generateKey();
			saveUrl(longUrl, shortUrl);
		}
		return shortUrl;
	}
	/**
	 * Save a given url to the databse
	 * @param longUrl
	 * @param shortUrl
	 * @throws SQLException
	 */
	public void saveUrl(String longUrl, String shortUrl) throws SQLException {
		String query = "INSERT INTO url (longurl, shorturl) VALUES(?, ?)";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, longUrl);
		preparedStatement.setString(2, shortUrl);
		preparedStatement.execute();
	}

	/**
	 * returns back the original URL given the shortened url
	 * @param shortUrl
	 * @return String data type
	 * @throws SQLException
	 */
	public String getOriginalUrl(String shortUrl) throws SQLException {
		String longUrl = "";
		String query = "SELECT longurl FROM url WHERE shorturl=?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, shortUrl);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			longUrl = resultSet.getString("longurl");
			return longUrl;
		} else {
			return "Error, the short Url given does not exist!";
		}
	}
	/**
	 * Checks whether the database to avoid generating duplicate short url
	 * @param shortUrl
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean isShortUrlInDataBase(String shortUrl) throws SQLException {
		String query = "SELECT shorturl FROM url WHERE shorturl=?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, shortUrl);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return true;
		}
		return false;
	}

}
