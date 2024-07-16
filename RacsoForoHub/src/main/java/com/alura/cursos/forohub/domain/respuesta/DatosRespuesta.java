package com.alura.cursos.forohub.domain.respuesta;

public record DatosRespuesta(
  Long id,
  String message,
  Boolean solved,
  Long idTopic,
  Long idUser
) {
  public DatosRespuesta(Respuesta respuesta) {
    this(
      respuesta.getId(),
      respuesta.getMessage(),
      respuesta.getSolved(),
      respuesta.getUser().getId(),
      respuesta.getTopics().getId()
    );
  }
}
