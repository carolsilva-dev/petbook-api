package com.oliveiradevs.petbook.service;

import com.oliveiradevs.petbook.dto.DadosCadastroPet;
import com.oliveiradevs.petbook.model.entity.Pet;
import com.oliveiradevs.petbook.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {
    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet salvarPet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> buscarPetPorNome(String nome) {
        return petRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<Pet> buscarPetPeloID(UUID id) {
        return petRepository.findById(id);
    }

    public Pet atualizarPet(UUID id, DadosCadastroPet dados) {
        return petRepository.findById(id).map(pet -> {
            pet.setNome(dados.getNome() != null ? dados.getNome() : pet.getNome());
            pet.setIdade(dados.getIdade() != null ? dados.getIdade() : pet.getIdade());
            pet.setGenero(dados.getGenero() != null ? dados.getGenero() : pet.getGenero());
            return petRepository.save(pet);
        }).orElseThrow(() -> new RuntimeException("Pet não encontrado!"));
    }
    public void deletarPet(UUID id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet não encontrado!");
        }
        petRepository.deleteById(id);
    }

    public List<Pet> buscarPetsPorUsuarioId(UUID usuarioId) {
        return petRepository.findByDonoId(usuarioId);
    }


}
