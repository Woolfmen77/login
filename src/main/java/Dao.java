import java.sql.SQLException;

public interface Dao {

    boolean singIn(String login, String password) throws SQLException, ClassNotFoundException;
    boolean singUp(String login, String password, String name) throws SQLException;

   // List<Users> user = new ArrayList<Users>();
}
