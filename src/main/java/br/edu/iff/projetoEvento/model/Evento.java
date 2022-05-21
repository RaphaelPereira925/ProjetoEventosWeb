
package br.edu.iff.projetoEvento.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Evento implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false, length = 100)
    @NotBlank(message = "O campo nome é obrigatório.")
    @Length(max = 100, message = "O campo nome deve ter no máximo 100 caracteres.")
    private String nome;
    @Column(nullable = false, length = 100)
    @NotBlank(message = "O campo organizacao é obrigatório.")
    @Length(max = 100, message = "O campo organizacao deve ter no máximo 100 caracteres.")
    private String organizacao;
    @Column(nullable = false)
    @Min(0)@Max(100000)
    @Digits(integer = 100000, fraction = 0, message = "A quantidade de ingressos deve ser um número inteiro.")//Coloquei um número de cem mil no integer devido considerar que alguns eventos podem ter lotação imensas, como 100 mil e por aí vai a depender do evento.
    private int qtdeIngresso;
    @Column(nullable = false, length = 20)
    @Enumerated (EnumType.STRING)
    @NotNull(message = "Tipo de Status do Evento é um campo obrigatório.")
    private TipoStatusEventoEnum status;
    @Column(nullable = false, updatable = false, length = 20)
    @NotNull(message = "Data e horário do evento são campos obrigatórios.")
    @FutureOrPresent(message = "Data e horário devem ser no mínimo hoje ou em datas futuras.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dataHora;
    @Embedded
    @NotNull(message = "Campo endereço é obrigatório.")
    @Valid
    private Endereco endereco;
    @Embedded
    @NotNull(message = "Campo contato é obrigatório.")
    @Valid
    private Contato contato;
    @JsonIgnore
    @OneToMany(orphanRemoval = true, mappedBy = "evento")
    @Size(min = 0)
    private List<Ingresso> ingressos = new ArrayList<>();
    
    
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

    public int getQtdeIngresso() {
        return qtdeIngresso;
    }

    public void setQtdeIngresso(int qtdeIngresso) {
        this.qtdeIngresso = qtdeIngresso;
    }

    public TipoStatusEventoEnum getStatus() {
        return status;
    }

    public void setStatus(TipoStatusEventoEnum status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.ID);
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
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }
    
}
