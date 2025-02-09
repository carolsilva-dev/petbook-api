package com.oliveiradevs.petbook.controller;

import com.oliveiradevs.petbook.model.entity.Foto;
import com.oliveiradevs.petbook.service.FotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("fotos")
public class FotoController {

    private final FotoService fotoService;

    public FotoController(FotoService fotoService) {
        this.fotoService = fotoService;
    }

    @PostMapping
    public ResponseEntity<Foto> adicionarFoto(@RequestBody Foto foto) {
        Foto novaFoto = fotoService.adicionarFoto((foto));
        return ResponseEntity.ok(novaFoto);
    }
    @GetMapping
    public ResponseEntity<List<Foto>> listarFotos() {
        List<Foto> fotos = fotoService.listarFotos();
        return ResponseEntity.ok(fotos);
    }
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<Foto>> buscarFotosPorPet(@PathVariable UUID petId) {
        List<Foto> fotos = fotoService.buscarFotosPorPet(petId);
        return ResponseEntity.ok(fotos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirFoto(@PathVariable UUID id) {
        fotoService.excluirFoto(id);
        return ResponseEntity.ok("Foto deletada com sucesso!");
    }
}
