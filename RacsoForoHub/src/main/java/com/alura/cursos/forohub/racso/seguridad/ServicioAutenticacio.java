package com.alura.cursos.forohub.racso.seguridad;

import com.alura.cursos.forohub.domain.usuario.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioAutenticacio implements UserDetailsService {

  @Autowired
  private IUsuarioRepositorio IUsuarioRepositorio;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return IUsuarioRepositorio.findByEmail(username);
  }
}
