package com.oliveiradevs.petbook.repository;

import com.oliveiradevs.petbook.model.entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FotoRepository extends JpaRepository<Foto, UUID> {
    List<Foto> findByPetId(UUID petId);
}
