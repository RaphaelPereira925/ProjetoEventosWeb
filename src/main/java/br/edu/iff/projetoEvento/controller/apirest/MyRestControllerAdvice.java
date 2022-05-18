
package br.edu.iff.projetoEvento.controller.apirest;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.edu.iff.projetoEvento.exception.Error;
import br.edu.iff.projetoEvento.exception.PropErro;
import br.edu.iff.projetoEvento.exception.ValidationError;
import java.util.Calendar;
import javassist.NotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


@RestControllerAdvice
public class MyRestControllerAdvice {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity erroPadrao (Exception e, HttpServletRequest request){
        
        Error erro = new Error(
                Calendar.getInstance(), 
                HttpStatus.BAD_REQUEST.value(), 
                HttpStatus.BAD_REQUEST.name(), 
                e.getMessage(), 
                request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);   
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity erroValidacao(ConstraintViolationException e, HttpServletRequest request){
        
        ValidationError teste = new ValidationError(
                Calendar.getInstance(), 
                HttpStatus.UNPROCESSABLE_ENTITY.value(), 
                HttpStatus.UNPROCESSABLE_ENTITY.name(), 
                "Erro de validação.",
                request.getRequestURI());
        
        for(ConstraintViolation cv : e.getConstraintViolations()){
            PropErro pe = new PropErro(cv.getPropertyPath().toString(), cv.getMessage());
            teste.getErrors();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(teste);   
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erroValidacao(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError erro = new ValidationError(
                Calendar.getInstance(), 
                HttpStatus.UNPROCESSABLE_ENTITY.value(), 
                HttpStatus.UNPROCESSABLE_ENTITY.name(), 
                "Erro de Validação.", 
                request.getRequestURI());
        for(FieldError fe : e.getBindingResult().getFieldErrors()){
            PropErro p = new PropErro(fe.getField(), fe.getDefaultMessage());
            erro.getErrors();
        }       
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity erroPadrao(NotFoundException e, HttpServletRequest request){
        Error erro = new Error(
                Calendar.getInstance(), 
                HttpStatus.NOT_FOUND.value(), 
                HttpStatus.NOT_FOUND.name(), 
                e.getMessage(), 
                request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    
}
