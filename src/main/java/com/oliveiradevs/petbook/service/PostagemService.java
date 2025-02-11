package com.oliveiradevs.petbook.service;


import com.oliveiradevs.petbook.model.entity.Postagem;
import com.oliveiradevs.petbook.repository.PostagemRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;

    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }
    public Optional<Postagem> buscarPorId(UUID id) {
        return postagemRepository.findById(id);
    }
    public Postagem adicionarPostagem(Postagem postagem) {
        return postagemRepository.save(postagem);
    }
    public boolean deletarPostagem(UUID id) {
        Optional<Postagem> postagemOptional = postagemRepository.findById(id);
        if (postagemOptional.isPresent()) {
            postagemRepository.deleteById(id);
            return true;
        }
        return false;
    }



}



