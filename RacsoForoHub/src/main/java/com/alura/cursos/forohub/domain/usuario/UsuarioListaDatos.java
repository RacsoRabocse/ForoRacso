package com.alura.cursos.forohub.domain.usuario;

public record UsuarioListaDatos(
  Long id,
  String name,
  String email
) {

  public UsuarioListaDatos(Usuario usuario) {
    this(
      usuario.getId(),
      usuario.getName(),
      usuario.getEmail()
    );
  }
}
