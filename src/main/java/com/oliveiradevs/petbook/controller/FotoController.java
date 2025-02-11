package com.oliveiradevs.petbook.controller;

import com.oliveiradevs.petbook.dto.DadosCadastroFoto;
import com.oliveiradevs.petbook.model.entity.Foto;
import com.oliveiradevs.petbook.model.entity.Pet;
import com.oliveiradevs.petbook.model.entity.Usuario;
import com.oliveiradevs.petbook.service.FotoService;
import com.oliveiradevs.petbook.service.PetService;
import com.oliveiradevs.petbook.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("fotos")
public class FotoController {

    private final FotoService fotoService;
    private final UsuarioService usuarioService;
    private final PetService petService;

    public FotoController(FotoService fotoService, UsuarioService usuarioService, PetService petService) {
        this.fotoService = fotoService;
        this.usuarioService = usuarioService;
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<?> adicionarFoto(@RequestBody DadosCadastroFoto dto) {
        Foto foto = new Foto();
        foto.setUrl(dto.getUrl());
        foto.setTipo(dto.getTipo());
        foto.setCompartilhada(dto.getCompartilhado());

        Optional<Usuario> donoOptional = usuarioService.buscarPorId(dto.getDonoID());
        Optional<Pet> petOptional = petService.buscarPetPeloID(dto.getPetID());

        if (donoOptional.isEmpty() || petOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("É necessário ter um usuário e um pet cadastrados para adicionar a foto.");
        }

        foto.setUsuario(donoOptional.get());
        foto.setPet(petOptional.get());

        Foto novaFoto = fotoService.adicionarFoto(foto);
        return ResponseEntity.ok(novaFoto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarFotoPorId(@PathVariable UUID id) {
        Optional<Foto> fotoOptional = fotoService.buscarFotoPorId(id);
        if (fotoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada.");
        }
        return ResponseEntity.ok(fotoOptional.get());
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<?> buscarFotosPorPet(@PathVariable UUID petId) {
        List<Foto> fotos = fotoService.buscarFotosPorPet(petId);
        if (fotos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma foto encontrada para este pet.");
        }
        return ResponseEntity.ok(fotos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFoto(@PathVariable UUID id) {
        Optional<Foto> fotoOptional = fotoService.buscarFotoPorId(id);
        if (fotoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada.");
        }
        fotoService.deletarFoto(id);
        return ResponseEntity.ok("Foto deletada com sucesso.");
    }

}
