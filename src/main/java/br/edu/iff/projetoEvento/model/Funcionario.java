
package br.edu.iff.projetoEvento.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class  Funcionario extends Usuario{
    @Column(nullable = false, length = 50)
    private String setor;
    @Column(nullable = false)
    private String senha;
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Ingresso> ingressos = new ArrayList<>();

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    
}
