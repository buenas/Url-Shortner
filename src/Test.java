import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		
		
		Core core = new Core();
		System.out.println(core.getShortUrl("https://stackoverflow.com/questions/15864387/java-how-to-check-if-username-already-exists-in-a-mysql-table"));
		System.out.println(core.getShortUrl("www.http:wfefefbi n ijo"));
		System.out.println(core.getShortUrl("www.http:wfefefbi n ijo"));
		System.out.println(core.getShortUrl("https://stackoverflow.com/questions/15864387/java-how-to-check-if-username-already-exists-in-a-mysql-table"));
		System.out.println(core.getShortUrl("www.http:wfefefbinteijo"));
		System.out.println(core.getOriginalUrl("http://cl.ip/u1nxUZf@Yj"));
		
	}

}
