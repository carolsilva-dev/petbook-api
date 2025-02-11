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

    public Optional<Foto> buscarFotoPorId(UUID id) {
        return fotoRepository.findById(id);
    }
    public List<Foto> buscarFotosPorPet(UUID petId) {
        return fotoRepository.findByPetId(petId);
    }

    public void deletarFoto(UUID id) {
        fotoRepository.deleteById(id);
    }

}
