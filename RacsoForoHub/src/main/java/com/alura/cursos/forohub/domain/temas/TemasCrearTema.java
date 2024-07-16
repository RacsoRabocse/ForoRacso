package com.alura.cursos.forohub.domain.temas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TemasCrearTema(
  @NotBlank
  String title,
  @NotBlank
  String message,
  @NotNull
  Long idUser,
  @NotNull
  Long idCourse
) {
}
