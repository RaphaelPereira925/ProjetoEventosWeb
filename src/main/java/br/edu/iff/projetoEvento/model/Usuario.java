package br.edu.iff.projetoEvento.model;

//import br.edu.iff.projetoEvento.annotation.CPFValidation;
import br.edu.iff.projetoEvento.annotation.CPFValidation;
import br.edu.iff.projetoEvento.annotation.RGValidation;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.br.CPF;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false, updatable = true, length = 150) //Professor não sei se precisa colocar o updatable aqui. Coloquei pois é um atributo que pode ser alterado segundo minhas regras de negócio.
    @NotBlank(message = "O campo Nome é obrigatório.")
    @Length(max = 150, message = "O campo Nome deve ter no máximo 150 caracteres.")
    private String nome;
    @Column(nullable = false, unique = true, updatable = false, length = 14)
    @NotBlank(message = "O campo CPF é obrigatório.")
    //@CPF(message = "CPF inválido.")
    @CPFValidation(message = "CPF inválido") //Coloquei minha própria anotação que verifica o formato, não sei se fica redundante.
    private String CPF;
    @Column(nullable = false, unique = true, updatable = false, length = 12)
    @NotBlank(message = "O campo RG é obrigatório.")
    @RGValidation(message = "RG inválido")
    private String RG;

    @Embedded
    @NotNull(message = "O campo Endereço é obrigatório.")
    @Valid
    private Endereco endereco;
    @Embedded
    @Valid
    private Contato contato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
