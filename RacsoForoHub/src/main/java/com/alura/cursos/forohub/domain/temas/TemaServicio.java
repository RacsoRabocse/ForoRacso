package com.alura.cursos.forohub.domain.temas;

import com.alura.cursos.forohub.domain.cursos.ICursoRepositorio;
import com.alura.cursos.forohub.domain.usuario.IUsuarioRepositorio;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.alura.cursos.forohub.racso.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class TemaServicio {
  @Autowired
  private ITemaRepositorio ITemaRepositorio;

  @Autowired
  private IUsuarioRepositorio IUsuarioRepositorio;

  @Autowired
  private ICursoRepositorio ICursoRepositorio;

  public TemasDatosRespuesta createTopic(TemasCrearTema temasCrearTema) {

    if (!IUsuarioRepositorio.findById(temasCrearTema.idUser()).isPresent()) {
      throw new ValidacionIntegridad("id user not found");
    }

    if (!ICursoRepositorio.findById(temasCrearTema.idCourse()).isPresent()) {
      throw new ValidacionIntegridad("id course not found");
    }

    if (ITemaRepositorio.findByTitle(temasCrearTema.title()).isPresent()) {
      throw new ValidacionIntegridad("There already exists a topic with the same title");
    }

    if (ITemaRepositorio.findByMessage(temasCrearTema.message()).isPresent()) {
      throw new ValidacionIntegridad("There already exists a topic with the same message");
    }

    var user = IUsuarioRepositorio.findById(temasCrearTema.idUser()).get();
    var course = ICursoRepositorio.findById(temasCrearTema.idCourse()).get();

    var topic = new Tema(
      temasCrearTema.title(),
      temasCrearTema.message(),
      user,
      course
    );

    ITemaRepositorio.save(topic);
    return new TemasDatosRespuesta(topic);
  }

  public Page<ListaDatosTemas> getTopics(Pageable pageable) {
    Page<Tema> topicPage = ITemaRepositorio.findByIsDeletedFalse(pageable);
    return topicPage.map(ListaDatosTemas::new);
  }

  public Page<ListaDatosTemas> getTopicsByCourseNameAndDateCreation(String courseName, String year, Pageable pageable) {
    var course = ICursoRepositorio.findByName(courseName);
    if (course == null) {
      throw new ValidacionIntegridad("Curso does not exist");
    }

    LocalDateTime startDate;
    try {
      int yearNumber = Integer.parseInt(year);
      startDate = LocalDateTime.of(yearNumber, 1, 1, 0, 0);
    } catch (NumberFormatException e) {
      throw new ValidacionIntegridad("Invalid year format");
    }

    var topics = ITemaRepositorio.findByCourseAndDateCreationAfter(course, startDate, pageable);

    var dataListTopics = topics.getContent().stream()
      .map(ListaDatosTemas::new)
      .collect(Collectors.toList());

    return new PageImpl<>(dataListTopics, pageable, topics.getTotalElements());
  }

  public TemasDatosRespuesta detailsTopic(@PathVariable Long id) {
    Tema tema = ITemaRepositorio.getReferenceById(id);
    return new TemasDatosRespuesta(tema);
  }

  public TemasDatosRespuesta updateTopic(ActualizacionDatosTema actualizacionDatosTema) {
    if (!ITemaRepositorio.findById(actualizacionDatosTema.id()).isPresent()) {
      throw new ValidacionIntegridad("id tema not found");
    }

    Tema tema = ITemaRepositorio.getReferenceById(actualizacionDatosTema.id());
    tema.putData(actualizacionDatosTema);

    return new TemasDatosRespuesta(tema);
  }

  public void deletedTopic(Long id) {
    Tema tema = ITemaRepositorio.findById(id)
      .orElseThrow(() -> new ValidacionIntegridad("Tema not found with id"));

    tema.deletedTopic();
    ITemaRepositorio.save(tema);
  }
}

