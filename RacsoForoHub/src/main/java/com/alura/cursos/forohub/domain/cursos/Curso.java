package com.alura.cursos.forohub.domain.cursos;

import com.alura.cursos.forohub.domain.temas.Tema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "courses")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "categoria")
  @Enumerated(EnumType.STRING)
  private Categoria categoria;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Tema> temas;

  private Boolean isDeleted;

  public Curso(String name, Categoria categoria) {
    this.name = name;
    this.categoria = categoria;
    this.isDeleted = false;
  }

  public void putData(DataUpdateCourse data) {
    if (data.name() != null) {
      this.name = data.name();
    }
    if (data.categoria() != null) {
      this.categoria = data.categoria();
    }
  }

  public void deletedCourse() {this.isDeleted = true;}
}
