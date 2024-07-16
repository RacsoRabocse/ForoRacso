package com.alura.cursos.forohub.domain.respuesta;

import com.alura.cursos.forohub.domain.temas.Tema;
import com.alura.cursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "answers")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String message;

  private Boolean solved;

  private LocalDateTime dateCreation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "topic_id")
  private Tema topics;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Usuario usuario;

  private Boolean isDeleted;

  public Respuesta(String message, Boolean solved, Tema topics, Usuario usuario) {
    this.message = message;
    this.solved = solved != null ? solved : false;
    this.dateCreation = LocalDateTime.now();
    this.topics = topics;
    this.usuario = usuario;
    this.isDeleted = false;
  }

  public void putData(ActualizacionDatosRespuesta data) {
    if (data.message() != null) {
      this.message = data.message();
      this.dateCreation = LocalDateTime.now();
    }
  }

  public void deletedAnswer() {this.isDeleted = true;}
}
