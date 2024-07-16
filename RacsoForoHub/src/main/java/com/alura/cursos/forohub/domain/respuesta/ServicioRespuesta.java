package com.alura.cursos.forohub.domain.respuesta;

import com.alura.cursos.forohub.domain.temas.ITemaRepositorio;
import com.alura.cursos.forohub.domain.temas.EstadoTema;
import com.alura.cursos.forohub.domain.usuario.IUsuarioRepositorio;
import com.alura.cursos.forohub.racso.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ServicioRespuesta {

  @Autowired
  private ITemaRepositorio ITemaRepositorio;

  @Autowired
  private IUsuarioRepositorio IUsuarioRepositorio;

  @Autowired
  private IRepositorioRespuestas IRepositorioRespuestas;

  public DatosRespuesta createAnswer(CrearDatosRespuesta crearDatosRespuesta) {

    if (!IUsuarioRepositorio.findById(crearDatosRespuesta.idUser()).isPresent()) {
      throw new ValidacionIntegridad("id user not found");
    }

    if (!ITemaRepositorio.findById(crearDatosRespuesta.idTopic()).isPresent()) {
      throw new ValidacionIntegridad("id topic not found");
    }

    var user = IUsuarioRepositorio.findById(crearDatosRespuesta.idUser()).get();
    var topic = ITemaRepositorio.findById(crearDatosRespuesta.idTopic()).get();

    var answer = new Respuesta(
      crearDatosRespuesta.message(),
      crearDatosRespuesta.solved(),
      topic,
      user
    );
    IRepositorioRespuestas.save(answer);

    topic.setStatus(EstadoTema.ANSWERED);
    ITemaRepositorio.save(topic);

    return new DatosRespuesta(answer);
  }

  public Page<ListaDatosRespuesta> getAnswer(Pageable pageable) {
    Page<Respuesta> answerPage = IRepositorioRespuestas.findByIsDeletedFalse(pageable);
    return answerPage.map(ListaDatosRespuesta::new);
  }

  public DatosRespuesta detailsAnswer(@PathVariable Long id) {
    Respuesta respuesta = IRepositorioRespuestas.getReferenceById(id);
    return new DatosRespuesta(respuesta);
  }

  public DatosRespuesta updateAnswer(ActualizacionDatosRespuesta actualizacionDatosRespuesta) {
    if(!IRepositorioRespuestas.findById(actualizacionDatosRespuesta.id()).isPresent()) {
      throw new ValidacionIntegridad("id respuesta not found");
    }

    Respuesta respuesta = IRepositorioRespuestas.getReferenceById(actualizacionDatosRespuesta.id());
    respuesta.putData(actualizacionDatosRespuesta);

    return new DatosRespuesta(respuesta);
  }

  public void deletedAnswer(Long id) {
    Respuesta respuesta = IRepositorioRespuestas.findById(id)
      .orElseThrow(() -> new ValidacionIntegridad("Tema not found with id"));

    respuesta.deletedAnswer();
    IRepositorioRespuestas.save(respuesta);
  }

}
