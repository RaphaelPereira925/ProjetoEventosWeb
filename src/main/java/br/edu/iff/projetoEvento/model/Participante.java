
package br.edu.iff.projetoEvento.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Participante extends Usuario{
    @Column(nullable = false, unique = true, updatable = false, length = 200) //Professor fui colocando essas marcações para seguir o que foi proposto nas minhas regras de negócio.
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
