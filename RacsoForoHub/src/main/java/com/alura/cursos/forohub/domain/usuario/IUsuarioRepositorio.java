package com.alura.cursos.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {
  UserDetails findByEmail(String email);
  List<Usuario> findByIsActiveTrue();
}
