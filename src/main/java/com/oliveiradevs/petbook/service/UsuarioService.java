package com.oliveiradevs.petbook.service;

import com.oliveiradevs.petbook.config.Jwt;
import com.oliveiradevs.petbook.dto.DadosCadastroUsuario;
import com.oliveiradevs.petbook.dto.LoginDto;
import com.oliveiradevs.petbook.model.entity.Usuario;
import com.oliveiradevs.petbook.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Map<String, String> geraRetornoAutenticado(LoginDto loginDto, String token) {
        Map<String, String> response = new HashMap<>();
        response.put("user", loginDto.getEmail());
        response.put("expirationDate", String.valueOf(Jwt.generateExpiration()));
        response.put("token", token);
        return response;
    }

    public String autenticarUsuario(String email, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            boolean usuarioValido = passwordEncoder.matches(senha, usuario.getSenha());
            if ( usuarioValido ) {
                Jwt jwtUtil = new Jwt();
                jwtUtil.init();
                return jwtUtil.generateToken(email);
            }
        }
        return null;
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(UUID id, DadosCadastroUsuario dados) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(dados.getNome() != null ? dados.getNome() : usuario.getNome());
            usuario.setEmail(dados.getEmail() != null ? dados.getEmail() : usuario.getEmail());
            if (dados.getSenha() != null) {
                String senhaCriptografada = passwordEncoder.encode(dados.getSenha());
                usuario.setSenha(senhaCriptografada);
            }


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
