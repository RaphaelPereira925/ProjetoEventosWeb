package br.edu.iff.projetoEvento.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public abstract class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(nullable = false, unique = false, updatable = true, length = 150) //Professor não sei se precisa colocar o updatable aqui. Coloquei pois é um atributo que pode ser alterado segundo minhas regras de negócio.
    private String nome;
    @Column(nullable = false, unique = true, updatable = false, length = 14)
    private String CPF;
    @Column(nullable = false, unique = true, updatable = false, length = 12)
    private String RG;

    private Endereco endereco;
    private Contato contato;

    public long getId() {
        return ID;
    }

    public void setId(long ID) {
        this.ID = ID;
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
        hash = 29 * hash + (int) (this.ID ^ (this.ID >>> 32));
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
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }
    
}
