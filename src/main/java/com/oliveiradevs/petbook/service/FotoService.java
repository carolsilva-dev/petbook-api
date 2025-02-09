package com.oliveiradevs.petbook.service;

import com.oliveiradevs.petbook.model.entity.Foto;
import com.oliveiradevs.petbook.repository.FotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FotoService {

    private final FotoRepository fotoRepository;

    public FotoService(FotoRepository fotoRepository) {
        this.fotoRepository = fotoRepository;
    }
    public Foto adicionarFoto(Foto foto) {
        return fotoRepository.save(foto);
    }
    public void excluirFoto(UUID id) {
        Optional<Foto> foto = fotoRepository.findById(id);
        if (foto.isPresent()) {
            fotoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Foto não encontrada!");
        }
    }
    public List<Foto> listarFotos() {
        return fotoRepository.findAll();
    }
    public List<Foto> buscarFotosPorPet(UUID petId) {
        return fotoRepository.findByPetId(petId);
    }
}
