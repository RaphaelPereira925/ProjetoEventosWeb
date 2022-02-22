
package br.edu.iff.projetoEvento.model;

import java.util.List;


public class Participante extends Usuario{
    
    private String documentos;

    private List<Ingresso> ingressos;


    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

}
