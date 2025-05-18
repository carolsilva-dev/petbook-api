package com.oliveiradevs.petbook.controller;

import com.oliveiradevs.petbook.config.Jwt;
import com.oliveiradevs.petbook.dto.DadosCadastroUsuario;
import com.oliveiradevs.petbook.dto.LoginDto;
import com.oliveiradevs.petbook.model.entity.Usuario;
import com.oliveiradevs.petbook.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody DadosCadastroUsuario dados) {
        Usuario usuario = new Usuario();
        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());

        Usuario usuarioSalvo = usuarioService.criarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable String email) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorEmail(email);
        return usuarioOptional.map(usuario -> ResponseEntity.ok().body(usuario))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody LoginDto loginDTO) {
        String token = usuarioService.autenticarUsuario(loginDTO.getEmail(), loginDTO.getSenha());
        if ( Objects.nonNull( token ) ) {
            return ResponseEntity.ok(usuarioService.geraRetornoAutenticado(loginDTO, token));
        } else {
            return ResponseEntity.status(401).body("Usuário não encontrado ou senha incorreta");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable UUID id, @RequestBody DadosCadastroUsuario dados) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, dados);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }
}


