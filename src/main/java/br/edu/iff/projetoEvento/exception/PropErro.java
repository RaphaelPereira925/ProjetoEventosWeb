
package br.edu.iff.projetoEvento.exception;

import java.io.Serializable;



public class PropErro implements Serializable{

    private static final Long serialVersionUID = 1L;
    
    private String field;
    private String message;

    public PropErro(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
 
}
