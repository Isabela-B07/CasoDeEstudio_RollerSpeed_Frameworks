package com.rollerspeed.rollerspeed.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.Model.userModel;
import com.rollerspeed.rollerspeed.Repository.UserRepository;




@Service // Para que el sepa que es un servicio.
public class userService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<userModel> listarUser(){
        return userRepository.findAll();
    } 
    
    public void guardar(userModel usuario){
        //Antes de guardar codifica la contraseña
        String encodePassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodePassword);
        //Ahora guarda el usuario con la contraseña codificada
        userRepository.save(usuario);
    }

    public userModel buscarPorId(Long id){
        return userRepository.findById(id).orElse(null);    }

    // Acá luego se hace lo necesario, el delete, etc
}
