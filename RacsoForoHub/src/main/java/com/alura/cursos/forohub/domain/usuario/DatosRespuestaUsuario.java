package com.alura.cursos.forohub.domain.usuario;

public record DatosRespuestaUsuario(
  Long id,
  String name,
  String email,
  String password
) {
  public DatosRespuestaUsuario(Usuario usuario) {
    this(
      usuario.getId(),
      usuario.getName(),
      usuario.getEmail(),
      usuario.getPassword()
    );
  }
}
