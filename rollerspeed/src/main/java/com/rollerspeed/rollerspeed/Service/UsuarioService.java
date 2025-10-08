package com.rollerspeed.rollerspeed.Service;

import com.rollerspeed.rollerspeed.Model.UsuarioModel;
import com.rollerspeed.rollerspeed.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void crearUsuario(String nombre, String correo, String password, String rol) {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRol(rol);
        usuarioRepository.save(usuario);
    }
}
