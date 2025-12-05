package org.vinylshop.helpers;

public class GenreNotFoundException extends RuntimeException {

  public GenreNotFoundException(Long id) {
    super("Genre met ID " + id + " niet gevonden");
  }
}
