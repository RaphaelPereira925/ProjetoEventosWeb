
package br.edu.iff.projetoEvento.model;

import java.io.Serializable;


public class Contato implements Serializable{    
    private static final long serialVersionUID = 1L;

    private String cel;
    private String tel;
    private String email;

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
