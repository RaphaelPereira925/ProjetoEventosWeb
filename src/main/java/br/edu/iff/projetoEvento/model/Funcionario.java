
package br.edu.iff.projetoEvento.model;

import java.util.List;

public class  Funcionario extends Usuario{

    private String setor;
    private String senha;

    private List<Ingresso> ingressos;

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
