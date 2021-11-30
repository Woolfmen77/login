import java.sql.*;

public class Methods implements Dao{

    Data_base db = new Data_base();

    @Override
    public boolean singIn(String login, String password) throws ClassNotFoundException, SQLException {
        if (db.select_log_pass(login, password)){
            if (db.select_admin(login)){
                System.out.println("Все пользователи: ");
                db.select_users();
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean singUp(String login, String password, String name) throws SQLException {

        if (db.chek_log(login)){
            db.insert(login, password, name);
            return true;
        }
        return false;
    }
}
