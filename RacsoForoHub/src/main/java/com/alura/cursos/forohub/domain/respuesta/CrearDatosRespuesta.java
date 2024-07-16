package com.alura.cursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearDatosRespuesta(
  @NotBlank
  String message,
  @NotNull
  Long idUser,
  @NotNull
  Long idTopic,
  Boolean solved
) {
}
