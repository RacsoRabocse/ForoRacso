package com.alura.cursos.forohub.domain.temas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizacionDatosTema(
  @NotNull
  Long id,
  @NotBlank
  String title,
  @NotBlank
  String message
) {
}
