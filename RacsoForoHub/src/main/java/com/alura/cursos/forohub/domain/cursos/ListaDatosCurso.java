package com.alura.cursos.forohub.domain.cursos;

public record ListaDatosCurso(
  Long id,
  String name,
  Categoria categoria
) {
  public ListaDatosCurso(Curso curso) {
    this(
      curso.getId(),
      curso.getName(),
      curso.getCategory()
    );
  }
}
