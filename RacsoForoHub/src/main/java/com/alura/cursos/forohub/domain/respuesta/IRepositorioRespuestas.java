package com.alura.cursos.forohub.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioRespuestas extends JpaRepository<Respuesta, Long> {
  Page<Respuesta> findByIsDeletedFalse(Pageable pageable);
}
