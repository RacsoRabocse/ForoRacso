package com.alura.cursos.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioActualizacionDatos(
  @NotNull
  Long id,
  @NotBlank
  String name,
  @NotBlank
  String email,
  @NotBlank
  String password
) {
}
