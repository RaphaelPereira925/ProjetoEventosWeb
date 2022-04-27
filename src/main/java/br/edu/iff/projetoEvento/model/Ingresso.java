
package br.edu.iff.projetoEvento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Ingresso implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false, length = 20)
    @Enumerated (EnumType.STRING)
    private TipoIngressoEnum tipoIngresso;
    @Column(nullable = false)
    private float valor;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Campo evento é obrigatório.")
    private Evento evento;
    @ManyToOne
    @NotNull(message = "Campo participante é obrigatório.")
    @JoinColumn(nullable = false)
    private Participante participante;
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Campo funcionario é obrigatório.")
    private Funcionario funcionario;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public TipoIngressoEnum getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(TipoIngressoEnum tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    
        
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.ID);
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
        final Ingresso other = (Ingresso) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

}
