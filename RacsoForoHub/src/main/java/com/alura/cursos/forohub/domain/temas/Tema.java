package com.alura.cursos.forohub.domain.temas;

import com.alura.cursos.forohub.domain.cursos.Curso;
import com.alura.cursos.forohub.domain.respuesta.Respuesta;
import com.alura.cursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Tema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tema {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String message;

  private LocalDateTime dateCreation;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private EstadoTema status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Usuario usuario;

  @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Respuesta> respuestas = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  private Curso curso;

  private Boolean isDeleted;
  public Tema(
    String title,
    String message,
    Usuario usuario,
    Curso curso)
  {
    this.title = title;
    this.message = message;
    this.dateCreation = LocalDateTime.now();
    this.status = EstadoTema.UNANSWERED;
    this.usuario = usuario;
    this.curso = curso;
    this.isDeleted = false;
  }

  public void setStatus(EstadoTema status) {
    this.status = status;
  }

  public void putData(ActualizacionDatosTema data) {
    if (data.title() != null) {
      this.title = data.title();
    }
    if (data.message() != null) {
      this.message = data.message();
    }
  }

  public void deletedTopic() {this.isDeleted = true;}
}
