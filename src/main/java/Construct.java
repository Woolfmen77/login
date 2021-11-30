import java.sql.SQLException;
import java.util.Scanner;

public class Construct {
    public void menu() {
        Data_base data_base = new Data_base();
        Methods methods = new Methods();
        int a;
        do first: {
            System.out.println("""
                    Здравствуй пользователь!\s
                    Чтобы ввсести логин, пароль нажмите - 1\s
                    Чтобы пройти регистрацию нажмите - 2\s
                    Чтобы закрыть приложение нажмите - 0""");
            Scanner scan = new Scanner(System.in);
            a = scan.nextInt();
            switch (a) {
                case (1) -> {
                    try {
                        System.out.println("Введите логин и пароль");
                        Scanner scanner = new Scanner(System.in);
                        String a1 = scanner.next();
                        String a2 = scanner.next();
                        if (data_base.getConn() == null) {
                            Data_base.connect();
                        }
                        if (!methods.singIn(a1, a2)) {
                            System.out.println("Такой login не зарегистрирован! Пройдите регистрацию");
                            break first;
                        }
                    } catch (SQLException | ClassNotFoundException x) {
                        x.getLocalizedMessage();
                    }
                    if (data_base.getConn() != null) {
                        data_base.disconnect();
                    }
                }
                case (2) -> {
                    try {
                        if (data_base.getConn() == null) {
                            Data_base.connect();
                        }
                        boolean b;
                        do {
                            System.out.println("Введите логин, пароль и имя");
                            Scanner sc = new Scanner(System.in);
                            String log = sc.next();
                            String pas = sc.next();
                            String name = sc.next();
                            if (!methods.singUp(log, pas, name)) {
                                System.out.println("Такой логин уже существует, попробуйте повторить ввод данных!");
                                b = false;
                            } else {
                                b = true;
                            }
                        }while (!b);


                    } catch (SQLException | ClassNotFoundException e) {
                        e.getLocalizedMessage();
                    }
                    if (data_base.getConn() != null) {
                        data_base.disconnect();
                    }
                }
            }
        }while (a != 0);
        System.out.println("Вы вышли из приложения!");
    }
}
