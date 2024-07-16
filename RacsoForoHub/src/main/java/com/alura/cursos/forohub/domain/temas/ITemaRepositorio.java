package com.alura.cursos.forohub.domain.temas;

import com.alura.cursos.forohub.domain.cursos.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ITemaRepositorio extends JpaRepository<Tema, Long> {
  Optional<Tema> findByTitle(String title);
  Optional<Tema> findByMessage(String message);
  Page<Tema> findByIsDeletedFalse(Pageable pageable);
  Page<Tema> findByCourseAndDateCreationAfter(Curso curso, LocalDateTime dateCreation, Pageable pageable);
}

