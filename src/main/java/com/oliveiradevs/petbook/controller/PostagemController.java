package com.oliveiradevs.petbook.controller;

import com.oliveiradevs.petbook.dto.PostagemDTO;
import com.oliveiradevs.petbook.model.entity.Foto;
import com.oliveiradevs.petbook.model.entity.Pet;
import com.oliveiradevs.petbook.model.entity.Postagem;
import com.oliveiradevs.petbook.model.entity.Usuario;
import com.oliveiradevs.petbook.service.FotoService;
import com.oliveiradevs.petbook.service.PetService;
import com.oliveiradevs.petbook.service.PostagemService;
import com.oliveiradevs.petbook.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("postagens")
public class PostagemController {

    private final PostagemService postagemService;
    private final UsuarioService usuarioService;
    private final FotoService fotoService;
    private final PetService petService;

    public PostagemController(PostagemService postagemService, UsuarioService usuarioService, FotoService fotoService, PetService petService) {
        this.postagemService = postagemService;
        this.usuarioService = usuarioService;
        this.fotoService = fotoService;
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<?> adicionarPostagem(@RequestBody PostagemDTO postagemDTO) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(postagemDTO.getUsuarioId());
        Optional<Pet> petOptional = petService.buscarPetPeloID(postagemDTO.getPetId());

        if (usuarioOptional.isEmpty() || petOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário e pet são obrigatórios para criar uma postagem.");
        }

        Postagem postagem = new Postagem();
        postagem.setTitulo(postagemDTO.getTitulo());
        postagem.setDescricao(postagemDTO.getDescricao());
        postagem.setUsuario(usuarioOptional.get());
        postagem.setPet(petOptional.get());

        if (postagemDTO.getFotoId() != null) {
            Optional<Foto> fotoOptional = fotoService.buscarFotoPorId(postagemDTO.getFotoId());
            fotoOptional.ifPresent(postagem::setFoto);
        }

        Postagem novaPostagem = postagemService.adicionarPostagem(postagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPostagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPostagem(@PathVariable UUID id) {
        boolean deletado = postagemService.deletarPostagem(id);
        if (!deletado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Postagem deletada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPostagemPorId(@PathVariable UUID id) {
        Optional<Postagem> postagemOptional = postagemService.buscarPorId(id);
        return postagemOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Postagem não encontrada."));
    }

}

