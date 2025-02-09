package com.oliveiradevs.petbook.dto;
import com.oliveiradevs.petbook.model.enums.GereroPet;
import java.util.UUID;

public class DadosCadastroPet {
private String nome;
private Integer idade;
private GereroPet genero;
private UUID donoId;

public DadosCadastroPet() {}


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

    public UUID getDonoId() {
        return donoId;
    }

    public void setDonoId(UUID donoId) {
        this.donoId = donoId;
    }
}


