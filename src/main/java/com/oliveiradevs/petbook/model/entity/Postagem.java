package com.oliveiradevs.petbook.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table (name = "postagem", schema = "public")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_postagem", nullable = false)
    private LocalDateTime dataPostagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "foto_id", nullable = false)
    private Foto foto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    public Postagem() {
        this.dataPostagem = LocalDateTime.now();
    }

    public Postagem(UUID id, String titulo, String descricao, Usuario usuario, Foto foto, Pet pet) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.foto = foto;
        this.pet = pet;
        this.dataPostagem = LocalDateTime.now();
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

    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDateTime dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Postagem postagem = (Postagem) o;
        return Objects.equals(id, postagem.id) && Objects.equals(titulo, postagem.titulo) && Objects.equals(descricao, postagem.descricao) && Objects.equals(dataPostagem, postagem.dataPostagem) && Objects.equals(usuario, postagem.usuario) && Objects.equals(foto, postagem.foto) && Objects.equals(pet, postagem.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, dataPostagem, usuario, foto, pet);
    }

    @Override
    public String toString() {
        return "Postagem{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataPostagem=" + dataPostagem +
                ", usuario=" + usuario +
                ", foto=" + foto +
                ", pet=" + pet +
                '}';
    }
}
