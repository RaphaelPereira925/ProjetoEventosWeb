
package br.edu.iff.projetoEvento.model;

import java.io.Serializable;

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
    
}
