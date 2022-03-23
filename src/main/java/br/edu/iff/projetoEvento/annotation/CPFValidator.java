
package br.edu.iff.projetoEvento.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CPFValidator implements ConstraintValidator<CPFValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        if(value.contains(" ")) return false;
        return value.matches("(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)");
    }
    
}
