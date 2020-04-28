package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Datos implements Serializable {

    private List<User> usuarios;

    public Datos() {
        this.usuarios=new ArrayList<>();
        iniciarU();
    }

    private void iniciarU() {
        User k = new User("Diego","123",2);
        this.usuarios.add(k);
        k = new User("admin","admin",0);
        this.usuarios.add(k);
        k = new User("info","info",1);
    }

    public List<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }
}
