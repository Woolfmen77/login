import java.sql.*;
import java.util.ArrayList;

public class Data_base  {
    ArrayList<String> resul = new ArrayList<>();
    ArrayList<Users> res = new ArrayList<>();
    ArrayList<Logins> ress = new ArrayList<>();

    private static final String user = "root";
    private static final String pass = "1234rusanov";
    private static final String conUrl = "jdbc:mysql://localhost:3306/tester";
    private static Connection conn = null;

    public Connection getConn() {
        return conn;
    }
//    private static final String sql_select = "SELECT * FROM gala.book";
//    private static final String sql_insert = "INSERT INTO gala.book (Title, Author, Price, Amount) Value (?,?,?,?)";

//    private static final String INSERT_NEW_USER = "INSERT INTO tester.users (login, password, name) VALUES(?, ?, ?);";
   // private static final String LOGIN = "SELECT * FROM users WHERE login = ? AND password = ?;";





    public static void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(conUrl, user, pass);
        System.out.println("БД Подключена");
    }

    public boolean chek_log(String login) throws SQLException{


        PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM tester.users");
        ResultSet rs = ps1.executeQuery();
        while(rs.next()) {
            String login_bd = rs.getString("login");
            resul.add(login_bd);
        }
        boolean containsElement =
                resul.contains(login);
        System.out.println(containsElement);
        if (containsElement){
            System.out.println("Логин не прошел проверку");
            return false;
        }
        return true;
    }
    public void select_users() throws SQLException {

        PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM tester.users");
        ResultSet rs2 = ps2.executeQuery();
        while(rs2.next()) {
            String login_bd = rs2.getString("login");
            String name_bd = rs2.getString("name");
            Users users = new Users();
            users.setLogin(login_bd);
            users.setPassword(name_bd);
            res.add(users);
        }
        res.forEach(System.out::println);

    }
    public boolean select_admin(String login) throws SQLException {

        PreparedStatement pre_st = conn.prepareStatement("SELECT * FROM tester.users");
        ResultSet rs = pre_st.executeQuery();
        while(rs.next()) {
            String login_bd = rs.getString("login");
            String role_bd = rs.getString("role");
            Logins rol = new Logins();
            rol.setLogin_in(login_bd);
            rol.setRole(role_bd);
            ress.add(rol);
        }
        Logins log = new Logins();
        log.setLogin_in(login);
        log.setRole("Admin");
        int val = (int) ress.stream().filter(x -> (x.getRole().equals(log.getRole())) && (x.getLogin_in().equals(log.getLogin_in()))).count();
        if (val > 0){
            System.out.println("Привет Admin " + login);
            return true;
        }else {
            System.out.println("USER");
            return false;
        }
    }

    public boolean select_log_pass(String login, String password) throws SQLException{

            PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM tester.users");
        ResultSet rs2 = ps2.executeQuery();
        while(rs2.next()) {
            String login_bd = rs2.getString("login");
            String password_bd = rs2.getString("password");
            Users users = new Users();
            users.setLogin(login_bd);
            users.setPassword(password_bd);
            res.add(users);
        }
        int val = (int) res.stream().filter(x -> x.getLogin().equals(login) && x.getPassword().equals(password)).count();
        if (val > 0){
            System.out.println("Привет " + login);
            return true;
        }else {
            System.out.println("Problem User Login");
            return false;
        }
    }

    public void insert(String login, String password, String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tester.users (login, password, name, role) VALUES(?, ?, ?, 'user');");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.executeUpdate();
            System.out.println("Пользователь "+ name + " добавлен");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Ошибка добавления пользователя!");
        }
    }

    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("БД отключена!!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
