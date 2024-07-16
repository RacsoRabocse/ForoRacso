package com.alura.cursos.forohub.racso.seguridad;

import com.alura.cursos.forohub.domain.usuario.IUsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguridad extends OncePerRequestFilter {

  @Autowired
  private ServicioFichas servicioFichas;

  @Autowired
  private IUsuarioRepositorio IUsuarioRepositorio;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
    var authHeader = request.getHeader("Authorization");
    if (authHeader != null) {
      var token = authHeader.replace("Bearer ", "");
      var subject = servicioFichas.getSubject(token);
      if (subject != null) {
        var user = IUsuarioRepositorio.findByEmail(subject);
        var authentication = new UsernamePasswordAuthenticationToken(user, null,
          user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }
}
