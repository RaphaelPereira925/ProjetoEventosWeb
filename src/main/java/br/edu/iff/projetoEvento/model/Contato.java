
package br.edu.iff.projetoEvento.model;

import br.edu.iff.projetoEvento.annotation.CelValidation;
import br.edu.iff.projetoEvento.annotation.EmailValidation;
import br.edu.iff.projetoEvento.annotation.TelValidation;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Embeddable
public class Contato implements Serializable{    
    private static final Long serialVersionUID = 1L;
    
    @Column(nullable = false, length = 14)
    @NotBlank(message = "O campo cel é obrigatório.")
    @Length(min = 13, max = 14, message = "O campo cel deve ter 13 ou 14 caracteres. Ex.: (99)99999-9999 ou (99)9999-9999")
    @CelValidation(message = "Celular inválido.")
    private String cel;
    @Column(nullable = true, length = 13)
    @Length(min = 13, max = 13, message = "O campo tel deve ter exatamente 13 caracteres. Ex.: (99)9999-9999")
    @TelValidation(message = "Telefone inválido.")
    private String tel;
    @Column(nullable = false, length = 100)
    @NotBlank(message = "O campo Email é obrigatório.")
    @EmailValidation(message = "Email inválido.")
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cel);
        hash = 17 * hash + Objects.hashCode(this.tel);
        hash = 17 * hash + Objects.hashCode(this.email);
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
        final Contato other = (Contato) obj;
        if (!Objects.equals(this.cel, other.cel)) {
            return false;
        }
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
}
