
package br.edu.iff.projetoEvento.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CelValidator implements ConstraintValidator<CelValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        if(value.contains(" ")) return false;
        return value.matches("^[1−9]2[0-9]{5}\\-[0-9]{4}$");
    }
}