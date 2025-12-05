package org.vinylshop.services;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.vinylshop.dtos.GenreRequest;
import org.vinylshop.entities.Genre;
import org.vinylshop.helpers.GenreNotFoundException;

@Service
public class GenreService {

  private static final Logger log = LoggerFactory.getLogger(GenreService.class);
  private final ArrayList<Genre> genreRepository;

  public GenreService() {
    genreRepository = new ArrayList<>();
  }

  public List<Genre> findAllGenres() {
    return genreRepository;
  }

  public Genre findGenreById(Long id) {
    return genreRepository.stream().filter(genre -> genre.getId().equals(id)).findFirst()
        .orElseThrow(() -> new GenreNotFoundException(id));
  }

  public Genre createGenre(Genre genre) {
    genre.setId(findNextId(genreRepository));
    genreRepository.add(genre);
    return genre;
  }


  public void updateGenre(Long id, GenreRequest genreInput) {
    Genre existingGenreEntity = findGenreById(id);

    existingGenreEntity.setName(genreInput.getName());
    existingGenreEntity.setDescription(genreInput.getDescription());
  }

  public void deleteGenre(long id) {
    try {
      Genre existingGenreEntity = findGenreById(id);
      genreRepository.remove(existingGenreEntity);
    } catch (IndexOutOfBoundsException e) {
      log.error("e: ", e);
    }
  }

  private Long findNextId(ArrayList<Genre> genreRepository) {
    Long highest = 0L;

    if (!genreRepository.isEmpty()) {
      for (Genre genre : genreRepository) {
        if (genre.getId() > highest) {
          highest = genre.getId();
        }
      }
    }
    return highest + 1;
  }
}


