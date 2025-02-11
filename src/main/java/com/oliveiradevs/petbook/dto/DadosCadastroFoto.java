package com.oliveiradevs.petbook.dto;

import com.oliveiradevs.petbook.model.enums.TipoFoto;

import java.util.UUID;

public class DadosCadastroFoto {
   private String url;
   private TipoFoto tipo;
   private Boolean compartilhado;
   private UUID donoID;
   private UUID petID;

   public DadosCadastroFoto() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TipoFoto getTipo() {
        return tipo;
    }

    public void setTipo(TipoFoto tipo) {
        this.tipo = tipo;
    }

    public Boolean getCompartilhado() {
        return compartilhado;
    }

    public void setCompartilhado(Boolean compartilhado) {
        this.compartilhado = compartilhado;
    }

    public UUID getDonoID() {
        return donoID;
    }

    public void setDonoID(UUID donoID) {
        this.donoID = donoID;
    }

    public UUID getPetID() {
        return petID;
    }

    public void setPetID(UUID petID) {
        this.petID = petID;
    }
}
