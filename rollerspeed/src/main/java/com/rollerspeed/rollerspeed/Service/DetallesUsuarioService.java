package com.rollerspeed.rollerspeed.Service;

import com.rollerspeed.rollerspeed.Model.UsuarioModel;
import com.rollerspeed.rollerspeed.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetallesUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UsuarioModel usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Correo no encontrado: " + correo));

        return User.builder()
                .username(usuario.getCorreo()) // ← Spring usará el correo como identificador
                .password(usuario.getPassword()) // ← Contraseña cifrada
                .roles(usuario.getRol()) // ← Rol: ADMIN, ESTUDIANTE, INSTRUCTOR
                .build();
    }
}
