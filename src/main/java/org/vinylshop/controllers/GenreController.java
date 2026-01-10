package org.vinylshop.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.vinylshop.dtos.GenreRequest;
import org.vinylshop.entities.Genre;
import org.vinylshop.helpers.GenreNotFoundException;
import org.vinylshop.services.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {

  private final GenreService genreService;
  // constructor injection

  public GenreController(GenreService genreService) {
    this.genreService = genreService;
  }

  // GET REQUESTS

  @GetMapping
  public ResponseEntity<List<Genre>> getAllGenres() {
    System.out.println("All genres endpoint and function");
    return ResponseEntity.ok(genreService.findAllGenres());

  }

  // @PathVariable is gekoppeld aan de {id} path in url
  @GetMapping("/{id}")
  public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
    System.out.println("Get genre by id function, ID: " + id);
    try {
      return ResponseEntity.ok(genreService.findGenreById(id));
    } catch (GenreNotFoundException exception) {
      System.out.println(exception.getMessage());
      return ResponseEntity.notFound().build();
    }
  }

  // POST REQUESTS

  @PostMapping
  public ResponseEntity<Genre> createGenre(@RequestBody GenreRequest request) {
    Genre genre = new Genre();
    genre.setName(request.getName());
    genre.setDescription(request.getDescription());

    System.out.println("Genre created");

    // opslaan via service
    Genre created = genreService.createGenre(genre);

    // location headers maken
    URI location = URI.create("/genre/" + created.getId());

    return ResponseEntity.created(location).body(created);
  }

  // PUT REQUESTS

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateGenre(@PathVariable Long id,
      @RequestBody GenreRequest request) {

    try {
      genreService.updateGenre(id, request);
      System.out.println("Genre updated");
      return ResponseEntity.noContent().build();
    } catch (GenreNotFoundException exception) {
      System.out.println(exception.getMessage());
      return ResponseEntity.notFound().build();
    }


  }

  // DELETE REQUESTS

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
    try {
      genreService.deleteGenre(id);
      System.out.println("ID: " + id + " deleted");
      return ResponseEntity.noContent().build();
    } catch (GenreNotFoundException exception) {
      System.out.println(exception.getMessage());
      return ResponseEntity.notFound().build();
    }
  }
}
