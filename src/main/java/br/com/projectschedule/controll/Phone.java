package br.com.projectschedule.controll;

public class Phone {
    private int id;
    private int idContato;
    private String ddd;
    private String telefone;

    public Phone() {
    }

    public Phone(int id, int idContato, String ddd, String telefone) {
        this.id = id;
        this.idContato = idContato;
        this.ddd = ddd;
        this.telefone = telefone;
    }

    public Phone(String ddd, String telefone) {
        this.ddd = ddd;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }
    
    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Phone{" + "ddd=" + ddd + ", telefone=" + telefone + '}';
    }
    
    
}
