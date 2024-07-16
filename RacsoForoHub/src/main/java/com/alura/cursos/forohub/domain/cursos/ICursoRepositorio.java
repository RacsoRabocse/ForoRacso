package com.alura.cursos.forohub.domain.cursos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICursoRepositorio extends JpaRepository<Curso, Long> {
  Curso findByName(String name);
  Page<Curso> findByIsDeletedFalse(Pageable pageable);
  Optional<Curso> findByNameIgnoreCase(String name);
}
