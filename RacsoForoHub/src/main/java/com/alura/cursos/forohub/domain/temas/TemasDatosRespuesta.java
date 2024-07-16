package com.alura.cursos.forohub.domain.temas;

import java.time.LocalDateTime;

public record TemasDatosRespuesta(
  Long id,
  String title,
  String message,
  LocalDateTime dateCreation,
  EstadoTema status,
  Long idUser,
  Long idCourse,
  Boolean isDeleted
) {

  public TemasDatosRespuesta(Tema tema) {
    this(
      tema.getId(),
      tema.getTitle(),
      tema.getMessage(),
      tema.getDateCreation(),
      tema.getStatus(),
      tema.getUser().getId(),
      tema.getCourse().getId(),
      tema.getIsDeleted()
    );

  }
}
