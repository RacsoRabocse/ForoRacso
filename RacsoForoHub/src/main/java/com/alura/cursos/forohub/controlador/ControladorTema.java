package com.alura.cursos.forohub.controlador;

import com.alura.cursos.forohub.domain.temas.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
@SecurityRequirement(name = "bearer-key")
public class ControladorTema {

  @Autowired
  private TemaServicio service;

  @Operation(
    summary = "Create a new topic",
    description = "This endpoint allows authorized users to create a new topic.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = TemasCrearTema.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "201", description = "Tema created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    }
  )
  @PostMapping
  @Transactional
  public ResponseEntity<TemasDatosRespuesta> createTopic(
    @RequestBody @Valid TemasCrearTema temasCrearTema,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    TemasDatosRespuesta response = service.createTopic(temasCrearTema);
    URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(response.id()).toUri();

    return ResponseEntity.created(url).body(response);
  }

  @Operation(
    summary = "List topics",
    description = "This endpoint allows users to list all available topics."
  )
  @GetMapping
  public ResponseEntity<Page<ListaDatosTemas>> listTopics(
    @Parameter(description = "The page number to retrieve")
    @PageableDefault(size = 10, sort = "dateCreation")
    Pageable pageable
  ) {
    Page<ListaDatosTemas> dataListTopicPage = service.getTopics(pageable);
    return ResponseEntity.ok(dataListTopicPage);
  }

  @Operation(
    summary = "Search for topics",
    description = "This endpoint allows users to search for topics by course name and creation date.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = SolicitudBusquedaTema.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "200", description = "Topics found"),
      @ApiResponse(responseCode = "404", description = "No topics found")
    }
  )
  @GetMapping("/search")
  public ResponseEntity<Page<ListaDatosTemas>> searchTopics(
    @RequestBody SolicitudBusquedaTema searchRequest,
    @PageableDefault(size = 10, sort = "dateCreation") Pageable pageable
  ) {
    Page<ListaDatosTemas> dataListTopicPage = service.getTopicsByCourseNameAndDateCreation(
      searchRequest.courseName(),
      searchRequest.dateCreation(),
      pageable
    );
    return ResponseEntity.ok(dataListTopicPage);
  }
  @Operation(
    summary = "Update a topic",
    description = "This endpoint allows authorized users to update an existing topic.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = ActualizacionDatosTema.class)
      )
    ),
    responses = {
      @ApiResponse(responseCode = "200", description = "Tema updated successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    }
  )
  @PutMapping
  @Transactional
  public ResponseEntity<TemasDatosRespuesta> updateTopic(@RequestBody @Valid ActualizacionDatosTema actualizacionDatosTema) {
    var topic = service.updateTopic(actualizacionDatosTema);
    return ResponseEntity.ok(topic);
  }

  @Operation(
    summary = "Delete a topic",
    description = "This endpoint allows authorized users to delete an existing topic."
  )
  @DeleteMapping("/{id}")
  public ResponseEntity deletedTopic(@PathVariable Long id) {
    service.deletedTopic(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(
    summary = "Get topic details",
    description = "This endpoint allows users to retrieve the details of a specific topic."
  )
  @GetMapping("/{id}")
  @Transactional
  public ResponseEntity<TemasDatosRespuesta> detailsTopic(@PathVariable Long id) {
    TemasDatosRespuesta temasDatosRespuesta = service.detailsTopic(id);
    return ResponseEntity.ok(temasDatosRespuesta);
  }
}
