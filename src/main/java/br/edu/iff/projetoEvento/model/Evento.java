
package br.edu.iff.projetoEvento.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;


public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long ID;
    private String nome;
    private String organizacao;
    private String espaco;
    private String qtdeIngresso;
    private String status;
    private Calendar data;
    private Calendar horario;

    private Endereco endereco;
    private Contato contato;
    private List<Ingresso> ingressos;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(String organizacao) {
        this.organizacao = organizacao;
    }

    public String getEspaco() {
        return espaco;
    }

    public void setEspaco(String espaco) {
        this.espaco = espaco;
    }

    public String getQtdeIngresso() {
        return qtdeIngresso;
    }

    public void setQtdeIngresso(String qtdeIngresso) {
        this.qtdeIngresso = qtdeIngresso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Calendar getHorario() {
        return horario;
    }

    public void setHorario(Calendar horario) {
        this.horario = horario;
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

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }


}