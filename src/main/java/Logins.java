import java.util.Objects;

public class Logins {
    private String login_in;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logins logins = (Logins) o;
        return Objects.equals(login_in, logins.login_in) && Objects.equals(role, logins.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login_in, role);
    }

    @Override
    public String toString() {
        return "Logins{" +
                "login_in='" + login_in + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getLogin_in() {
        return login_in;
    }

    public void setLogin_in(String login_in) {
        this.login_in = login_in;
    }
}
