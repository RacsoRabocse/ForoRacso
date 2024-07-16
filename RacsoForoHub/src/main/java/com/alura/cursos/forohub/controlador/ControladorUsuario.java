package com.alura.cursos.forohub.controlador;

import com.alura.cursos.forohub.domain.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/user")
public class ControladorUsuario {

  @Autowired
  private ServicioUsuario service;

  @Operation(
    summary = "Create a new user",
    description = "This endpoint allows users to create a new account.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = RegistroDatosUsuario.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "201", description = "Usuario created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    }
  )
  @PostMapping
  @Transactional
  public ResponseEntity<DatosRespuestaUsuario> createUser(
    @RequestBody @Valid RegistroDatosUsuario registroDatosUsuario,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    DatosRespuestaUsuario response = service.createUser(registroDatosUsuario);
    URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(url).body(response);
  }

  @Operation(
    summary = "List users",
    description = "This endpoint allows authorized users to view a list of all registered users."
  )
  @GetMapping
  public ResponseEntity<List<UsuarioListaDatos>> listUser() {
    List<UsuarioListaDatos> usuarioListaDatos = service.getUsers();
    return ResponseEntity.ok(usuarioListaDatos);
  }

  @Operation(
    summary = "Update a user",
    description = "This endpoint allows authorized users to update their own user information.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = DataUpdateUser.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "200", description = "Usuario updated successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    }
  )
  @PutMapping
  @Transactional
  public ResponseEntity<DatosRespuestaUsuario> updateUser(@RequestBody @Valid DataUpdateUser dataUpdateUser) {
    var user = service.updateUser(dataUpdateUser);
    return ResponseEntity.ok(user);
  }

  @Operation(
    summary = "Delete a user",
    description = "This endpoint allows authorized users to delete their own user account."
  )
  @DeleteMapping("/{id}")
  public ResponseEntity deletedUser(@PathVariable Long id) {
    service.deletedUser(id);
    return ResponseEntity.noContent().build();
  }
}