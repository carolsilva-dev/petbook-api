package com.oliveiradevs.petbook.controller;

import com.oliveiradevs.petbook.dto.DadosCadastroPet;
import com.oliveiradevs.petbook.model.entity.Pet;
import com.oliveiradevs.petbook.model.entity.Usuario;
import com.oliveiradevs.petbook.service.PetService;
import com.oliveiradevs.petbook.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final UsuarioService usuarioService;

    public PetController(PetService petService, UsuarioService usuarioService) {
        this.petService = petService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPet(@RequestBody DadosCadastroPet dto) {
        Optional<Usuario> donoOptional = usuarioService.buscarPorId(dto.getDonoId());
        if (donoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        Usuario dono = donoOptional.get();

        Pet pet = new Pet();
        pet.setNome(dto.getNome());
        pet.setIdade(dto.getIdade());
        pet.setGenero(dto.getGenero());
        pet.setDono(dono);

        Pet petSalvo = petService.salvarPet(pet);

        return ResponseEntity.status(HttpStatus.CREATED).body(petSalvo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPet(@PathVariable UUID id, @RequestBody DadosCadastroPet dados) {
        try {
            Pet petAtualizado = petService.atualizarPet(id, dados);
            return ResponseEntity.ok(petAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Pet>> buscarPetPorNome(@PathVariable String nome) {
        List<Pet> pets = petService.buscarPetPorNome(nome);
        return pets.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(pets);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPet(@PathVariable UUID id) {
        petService.deletarPet(id);
        return ResponseEntity.ok("Pet deletado com sucesso!");
    }
}
