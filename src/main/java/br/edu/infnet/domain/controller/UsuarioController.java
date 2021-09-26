package br.edu.infnet.domain.controller;

import br.edu.infnet.domain.model.Usuario;
import br.edu.infnet.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/usuarios"})
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(path = {"/{id}"})
    public ResponseEntity getById(@PathVariable Integer id) {
        ResponseEntity response = ResponseEntity.notFound().build();
        Usuario usuario = this.findById(id);
        if (usuario != null) {
            response = ResponseEntity.ok().body(usuario);
        }
        return response;
    }

    private Usuario findById(Integer id) {

        Usuario response = null;
        try {
            response = usuarioRepository.findById(id).get();
        } catch (Exception e) {
        }
        return response;
    }

    @GetMapping(path = {"/email/{email}"})
    public ResponseEntity findByEmail(@PathVariable String email) {
        ResponseEntity response = ResponseEntity.notFound().build();
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario != null) {
                response = ResponseEntity.ok().body(usuario);

            }
        } catch (Exception e) {
        }

        return response;
    }

    @PostMapping
    public ResponseEntity criaUsuario(@RequestBody Usuario usuario) {
        ResponseEntity response = ResponseEntity.badRequest().build();
        if (usuario != null && usuario.getId() == null) {
            Usuario registrado = usuarioRepository.save(usuario);
            response = ResponseEntity.status(HttpStatus.CREATED).body(registrado);

        }
        return response;
    }
        @PutMapping
        public ResponseEntity editaUsuario (@RequestBody Usuario usuario){
            ResponseEntity response = ResponseEntity.badRequest().build();
            if (usuario != null && usuario.getId() == null) {
                Usuario registrado = this.findById(usuario.getId());

                if (registrado != null) {
                    try {
                        registrado = usuarioRepository.save(usuario);
                        response = ResponseEntity.ok().body(registrado);
                    } catch (Exception e) {
                    }
                }
            }
            return response;
        }
    }