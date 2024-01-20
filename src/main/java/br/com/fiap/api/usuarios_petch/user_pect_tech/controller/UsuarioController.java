package br.com.fiap.api.usuarios_petch.user_pect_tech.controller;

import br.com.fiap.api.usuarios_petch.user_pect_tech.dto.UsuarioDTO;
import br.com.fiap.api.usuarios_petch.user_pect_tech.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = "nome")Pageable pageable
    ){
        Page<UsuarioDTO> usuarioDTOS = usuarioService.findAll(pageable);
        return ResponseEntity.ok(usuarioDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        UsuarioDTO usuarioDTO = usuarioService.findById((id));
        return ResponseEntity.ok((usuarioDTO));
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO savedUsuario = usuarioService.save(usuarioDTO);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<UsuarioDTO> update(@PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO updateUsuario = usuarioService.update(id, usuarioDTO);
        return  ResponseEntity.ok(updateUsuario);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
