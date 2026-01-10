package org.vinylshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vinylshop.entities.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}