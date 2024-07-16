package com.alura.cursos.forohub.domain.cursos;

import com.alura.cursos.forohub.racso.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoServicio {

  @Autowired
  private ICursoRepositorio ICursoRepositorio;

  public RespuestaDatosCurso createCourse(CursoCrearDatos cursoCrearDatos) {
    Optional<Curso> existingCourse = Optional.ofNullable(ICursoRepositorio.findByName(cursoCrearDatos.name()));

    if (existingCourse.isPresent()) {
      throw new ValidacionIntegridad("Curso already exists");
    }

    Curso newCurso = new Curso(cursoCrearDatos.name(), cursoCrearDatos.categoria());
    ICursoRepositorio.save(newCurso);
    return new RespuestaDatosCurso(newCurso);
  }

  public Page<ListaDatosCurso> getCourse(Pageable pageable) {
    Page<Curso> coursePage = ICursoRepositorio.findByIsDeletedFalse(pageable);
    return coursePage.map(ListaDatosCurso::new);
  }

  public ListaDatosCurso getCourseByName(String courseName) {
    Optional<Curso> existingCourse = ICursoRepositorio.findByNameIgnoreCase(courseName);

    if (existingCourse.isEmpty()) {
      throw new ValidacionIntegridad("Curso does not exist");
    }

    return new ListaDatosCurso(existingCourse.get());
  }

  public RespuestaDatosCurso updateCourse(DataUpdateCourse dataUpdateCourse) {
    if (!ICursoRepositorio.findById(dataUpdateCourse.id()).isPresent()) {
      throw new ValidacionIntegridad("id curso not found");
    }

    Curso curso = ICursoRepositorio.getReferenceById(dataUpdateCourse.id());
    curso.putData(dataUpdateCourse);

    return new RespuestaDatosCurso(curso);
  }

  public void deletedCourse(Long id) {
    Curso curso = ICursoRepositorio.findById(id)
      .orElseThrow(() -> new ValidacionIntegridad("Curso not found with id"));

    curso.deletedCourse();
    ICursoRepositorio.save(curso);
  }
}
