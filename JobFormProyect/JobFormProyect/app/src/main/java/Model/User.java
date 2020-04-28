package Model;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String pass;
    private int rol;

    public User(String userName, String pass, int rol) {
        this.userName = userName;
        this.pass = pass;
        this.rol=rol;
    }

    public User() {
        this(null,null,0);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", pass='" + pass + '\'' +
                ", rol=" + rol +
                '}';
    }
}
