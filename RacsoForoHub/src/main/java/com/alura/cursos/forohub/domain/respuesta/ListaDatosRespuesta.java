package com.alura.cursos.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record ListaDatosRespuesta(
  Long id,
  String message,
  Boolean solved,
  Long idTopic,
  Long idUser,
  LocalDateTime dateCreation
) {
  public ListaDatosRespuesta(Respuesta respuesta) {
    this(
      respuesta.getId(),
      respuesta.getMessage(),
      respuesta.getSolved(),
      respuesta.getUser().getId(),
      respuesta.getTopics().getId(),
      respuesta.getDateCreation()
    );
  }
}
