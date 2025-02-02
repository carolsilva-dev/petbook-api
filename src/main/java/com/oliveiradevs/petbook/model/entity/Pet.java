package com.oliveiradevs.petbook.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oliveiradevs.petbook.model.enums.GereroPet;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table (name = "pet", schema = "public")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GereroPet genero;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dono_id", nullable = false, unique = true)
    private Usuario dono;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Foto>fotos;


    public Pet() {}

    public Pet(UUID id, String nome, Integer idade, GereroPet genero, Usuario dono, List<Foto> fotos) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.dono = dono;
        this.fotos = fotos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public GereroPet getGenero() {
        return genero;
    }

    public void setGenero(GereroPet genero) {
        this.genero = genero;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(dono, pet.dono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dono);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}
