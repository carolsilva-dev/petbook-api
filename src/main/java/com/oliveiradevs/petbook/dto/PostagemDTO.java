package com.oliveiradevs.petbook.dto;

import java.util.UUID;

public class PostagemDTO {
    private UUID id;
    private String titulo;
    private String descricao;
    private UUID usuarioId;
    private UUID fotoId;
    private UUID petId;

    public PostagemDTO() {}

    public PostagemDTO(UUID id, String titulo, String descricao, UUID usuarioId, UUID fotoId, UUID petId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
        this.fotoId = fotoId;
        this.petId = petId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getFotoId() {
        return fotoId;
    }

    public void setFotoId(UUID fotoId) {
        this.fotoId = fotoId;
    }

    public UUID getPetId() {
        return petId;
    }

    public void setPetId(UUID petId) {
        this.petId = petId;
    }
}
