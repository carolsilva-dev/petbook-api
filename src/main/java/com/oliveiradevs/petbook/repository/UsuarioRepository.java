package com.oliveiradevs.petbook.repository;

import com.oliveiradevs.petbook.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.*;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

Optional<Usuario> findByEmail(String email);

Optional<Usuario> findById(UUID id);

boolean existsByEmail(String email);
}
