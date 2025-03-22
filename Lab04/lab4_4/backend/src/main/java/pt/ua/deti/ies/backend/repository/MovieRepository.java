package pt.ua.deti.ies.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.deti.ies.backend.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

