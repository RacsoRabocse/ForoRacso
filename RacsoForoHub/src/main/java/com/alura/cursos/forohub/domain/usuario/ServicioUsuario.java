package com.alura.cursos.forohub.domain.usuario;

import com.alura.cursos.forohub.racso.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioUsuario {
  @Autowired
  private IUsuarioRepositorio IUsuarioRepositorio;
  private BCryptPasswordEncoder passwordEncoder;

  public ServicioUsuario(IUsuarioRepositorio IUsuarioRepositorio) {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }
  public DatosRespuestaUsuario createUser(RegistroDatosUsuario registroDatosUsuario) {
    String hashedPassword = passwordEncoder.encode(registroDatosUsuario.password());

    var user = new Usuario(
      registroDatosUsuario.name(),
      registroDatosUsuario.email(),
      hashedPassword
    );

    IUsuarioRepositorio.save(user);
    return new DatosRespuestaUsuario(user);
  }

  public List<UsuarioListaDatos> getUsers() {
    List<Usuario> activeUsuarios = IUsuarioRepositorio.findByIsActiveTrue();
    return activeUsuarios.stream()
      .map(UsuarioListaDatos::new)
      .collect(Collectors.toList());
  }

  public DatosRespuestaUsuario updateUser(DataUpdateUser dataUpdateUser) {
    if (!IUsuarioRepositorio.findById(dataUpdateUser.id()).isPresent()) {
      throw new ValidacionIntegridad("id usuario not found");
    }

    Usuario usuario = IUsuarioRepositorio.getReferenceById(dataUpdateUser.id());

    if (dataUpdateUser.password() != null) {
      String hashedPassword = passwordEncoder.encode(dataUpdateUser.password());
      usuario.setPassword(hashedPassword);
    }

    usuario.setName(dataUpdateUser.name());
    usuario.setEmail(dataUpdateUser.email());
    IUsuarioRepositorio.save(usuario);

    return new DatosRespuestaUsuario(usuario);
  }

  public void deletedUser(Long id) {
    Usuario usuario = IUsuarioRepositorio.findById(id)
      .orElseThrow(() -> new ValidacionIntegridad("Usuario not found with id"));

    usuario.deletedUser();
    IUsuarioRepositorio.save(usuario);
  }
}
