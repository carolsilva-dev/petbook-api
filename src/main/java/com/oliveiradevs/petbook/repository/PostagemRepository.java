package com.oliveiradevs.petbook.repository;


import com.oliveiradevs.petbook.model.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostagemRepository extends JpaRepository<Postagem, UUID> {
}

