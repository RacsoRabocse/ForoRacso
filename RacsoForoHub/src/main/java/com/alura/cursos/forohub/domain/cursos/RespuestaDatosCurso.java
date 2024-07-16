package com.alura.cursos.forohub.domain.cursos;

public record RespuestaDatosCurso(
  Long id,
  String name,
  Categoria categoria,
  Boolean isDeleted

) {

  public RespuestaDatosCurso(Curso curso) {
    this(
      curso.getId(),
      curso.getName(),
      curso.getCategory(),
      curso.getIsDeleted()
    );
  }
}
