
package br.edu.iff.projetoEvento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class  Funcionario extends Usuario{
    @Column(nullable = false, length = 50)
    @NotBlank(message = "O campo setor é obrigatório.")
    @Length(max = 50, message = "O campo setor deve ter no máximo 50 caracteres.")
    private String setor;
    @Column(nullable = false)
    @NotBlank(message = "O campo senha é obrigatório.")
    @Length(min = 8, message = "O campo senha deve ter no mínimo 8 caracteres.")
    private String senha;
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Ingresso> ingressos = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Funcionário deve ter no mínimo uma permissão de acesso.")
    private List<Permissao> permissoes;
    
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

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    
    
}
