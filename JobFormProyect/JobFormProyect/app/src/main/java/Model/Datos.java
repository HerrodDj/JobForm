package Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Datos implements Serializable {

    private List<User> usuarios;
    private List<Form> forms;

    public Datos() {
        this.usuarios=new ArrayList<>();
        this.forms=new ArrayList<>();
        iniciarU();
        iniciarJ();
    }

    private void iniciarU() {
        User k = new User("admin","admin",0);
        this.usuarios.add(k);
        k = new User("info","info",1);
        this.usuarios.add(k);
        k = new User("Diego","123",2);
        this.usuarios.add(k);
        k = new User("Dj","123",2);
        this.usuarios.add(k);
    }

    private void iniciarJ() {
        Form f = new Form("Diego","Monterrey","San Pablo","Quintana",
                "Heredia","Heredia","40192","Japan","demilio12@hotmail.com","506","87101280","Scrum Master","12/10/2020");
        this.forms.add(f);
        f = new Form("Djenane","Hernandez","Bijagua","Bijagua1",
                "Alajuela","Alajuela","57298","Costa Rica","djenane@hotmail.com","506","88720880","Developer","01/05/2020");
        this.forms.add(f);
            f = new Form("Carlos","Villareal","Calle Larga","Las montañas",
                "Cartago","Cartago","48297","Costa Rica","villareal456@hotmail.com","506","815858280","Human Resources","20/04/2020");
        this.forms.add(f);
    }

    public List<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public void add(Form f){
        this.forms.add(f);
    }
}
