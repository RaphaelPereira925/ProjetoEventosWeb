
package br.edu.iff.projetoEvento.exception;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ValidationError extends Error{
    
    private List<PropErro> errors = new ArrayList<>();
      
    public ValidationError(Calendar timeStamp, Integer status, String error, String message, String path) {
        super(timeStamp, status, error, message, path);
    }

    public List<PropErro> getErrors() {
        return errors;
    }

    public void setErrors(List<PropErro> errors) {
        this.errors = errors;
    }
    
    
}
