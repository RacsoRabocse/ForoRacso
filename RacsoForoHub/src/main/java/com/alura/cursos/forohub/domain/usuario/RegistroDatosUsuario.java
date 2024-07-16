package com.alura.cursos.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroDatosUsuario(
  @NotBlank
  String name,
  @NotBlank
  @Email
  String email,
  @NotBlank
  String password
) {
}
