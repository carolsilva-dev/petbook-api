package com.oliveiradevs.petbook.repository;

import com.oliveiradevs.petbook.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository <Pet, UUID> {
    List<Pet> findByNomeContainingIgnoreCase(String nome);
}
