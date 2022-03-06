
package br.edu.iff.projetoEvento.model;

import java.io.Serializable;
import java.util.Objects;

public class Ingresso implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long ID;
    private TipoIngressoEnum tipoIngresso;
    private float valor;

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
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.ID);
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
