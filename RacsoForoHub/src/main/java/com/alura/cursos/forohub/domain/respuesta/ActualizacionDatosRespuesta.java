package com.alura.cursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizacionDatosRespuesta(
  @NotNull
  Long id,
  @NotBlank
  String message,
  @NotNull
  Long idTopic,
  @NotNull
  Long idUser

) {
}
