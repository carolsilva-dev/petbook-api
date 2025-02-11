package com.oliveiradevs.petbook.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oliveiradevs.petbook.model.enums.TipoFoto;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table (name = "foto", schema = "public")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFoto tipo;

    @Column(nullable = false)
    private Boolean compartilhada;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Foto() {}

    public Foto(UUID id, String url, TipoFoto tipo, Boolean compartilhada, Pet pet, Usuario usuario) {
        this.id = id;
        this.url = url;
        this.tipo = tipo;
        this.compartilhada = compartilhada;
        this.pet = pet;
        this.usuario = usuario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Boolean getCompartilhada() {
        return compartilhada;
    }

    public void setCompartilhada(Boolean compartilhada) {
        this.compartilhada = compartilhada;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Foto foto = (Foto) o;
        return Objects.equals(id, foto.id) && Objects.equals(url, foto.url) && tipo == foto.tipo && Objects.equals(compartilhada, foto.compartilhada) && Objects.equals(pet, foto.pet) && Objects.equals(usuario, foto.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, tipo, compartilhada, pet, usuario);
    }

    @Override
    public String toString() {
        return "Foto{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", tipo=" + tipo +
                ", compartilhada=" + compartilhada +
                ", pet=" + pet +
                ", usuario=" + usuario +
                '}';
    }
}


