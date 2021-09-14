package br.edu.infnet.domain.controller;

import br.edu.infnet.domain.model.Usuario;
import br.edu.infnet.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Retorna o objeto e os dados do objeto são gravados diretamente na resposta HTTP como JSON ou XML.
@RequestMapping(path = {"/usuarios"})
public class UsuarioController {

    @Autowired//
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
    public ResponseEntity getByEmail(@PathVariable String email) {
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
    public ResponseEntity createUsuario(@RequestBody Usuario usuario) {
        ResponseEntity response = ResponseEntity.badRequest().build();
        if (usuario != null && usuario.getId() == null) {
            Usuario resgistered = usuarioRepository.save(usuario);
            response = ResponseEntity.status(HttpStatus.CREATED).body(resgistered);

        }
        return response;
    }
        @PutMapping
        public ResponseEntity editUser (@RequestBody Usuario usuario){
            ResponseEntity response = ResponseEntity.badRequest().build();
            if (usuario != null && usuario.getId() == null) {
                Usuario registered = this.findById(usuario.getId());

                if (registered != null) {
                    try {
                        registered = usuarioRepository.save(usuario);
                        response = ResponseEntity.ok().body(registered);
                    } catch (Exception e) {
                    }
                }
            }
            return response;
        }
    }