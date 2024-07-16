package com.alura.cursos.forohub.domain.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoCrearDatos(
  @NotBlank
  String name,
  @NotNull
  Categoria categoria
) {
}
