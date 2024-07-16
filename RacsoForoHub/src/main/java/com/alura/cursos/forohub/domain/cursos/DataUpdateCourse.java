package com.alura.cursos.forohub.domain.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateCourse(
  @NotNull
  Long id,
  @NotBlank
  String name,
  @NotNull
  Categoria categoria
) {
}
