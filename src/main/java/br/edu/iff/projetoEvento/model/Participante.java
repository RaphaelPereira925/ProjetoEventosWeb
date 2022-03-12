
package br.edu.iff.projetoEvento.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Participante extends Usuario{
    @Column(nullable = false, unique = true, updatable = false, length = 200) //Professor fui colocando essas marcações para seguir o que foi proposto nas minhas regras de negócio.
    private String documentos;
    @JsonBackReference
    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "participante")
    private List<Ingresso> ingressos = new ArrayList<>();

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
