package com.oliveiradevs.petbook.service;

import com.oliveiradevs.petbook.dto.DadosCadastroUsuario;
import com.oliveiradevs.petbook.model.entity.Usuario;
import com.oliveiradevs.petbook.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean autenticarUsuario(String email, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario.getSenha().equals(senha);
        }
        return false;
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(UUID id, DadosCadastroUsuario dados) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(dados.getNome() != null ? dados.getNome() : usuario.getNome());
            usuario.setEmail(dados.getEmail() != null ? dados.getEmail() : usuario.getEmail());
            usuario.setSenha(dados.getSenha() != null ? dados.getSenha() : usuario.getSenha());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
    public void deletarUsuario(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        usuarioRepository.deleteById(id);
    }
}
