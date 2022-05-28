
package br.edu.iff.projetoEvento.model;

import br.edu.iff.projetoEvento.annotation.CEPValidation;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


@Embeddable
public class Endereco implements Serializable{
    private static final Long serialVersionUID = 1L;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "O campo rua é obrigatório.")
    @Length(max = 100, message = "O campo rua deve ter no máximo 100 caracteres.")
    private String rua;
    @Column(nullable = false)
    @Digits(integer = 4, fraction = 0, message = "O campo número deve ser inteiro e ter até 4 dígitos.")
    private int numero;
    @Column(nullable = false, length = 50)
    @NotBlank(message = "O campo bairro é obrigatório.")
    @Length(max = 50, message = "O campo bairro deve ter no máximo 50 caracteres.")
    private String bairro;
    @Column(nullable = false, length = 100)
    @NotBlank(message = "O campo cidade é obrigatório.")
    @Length(max = 100, message = "O campo cidade deve ter no máximo 100 caracteres.")
    private String cidade;
    @Column(nullable = false, length = 9)
    @NotBlank(message = "O campo CEP é obrigatório.")
    @Length(min = 9, max = 9, message = "O campo CEP deve ter no extamente 9 caracteres. Ex.: 99999-999")
    @CEPValidation(message = "CEP inválido.")
    private String CEP;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.rua);
        hash = 67 * hash + this.numero;
        hash = 67 * hash + Objects.hashCode(this.bairro);
        hash = 67 * hash + Objects.hashCode(this.cidade);
        hash = 67 * hash + Objects.hashCode(this.CEP);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.CEP, other.CEP)) {
            return false;
        }
        return true;
    }
    
}
