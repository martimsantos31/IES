package pt.ua.deti.ies.lab3_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.deti.ies.lab3_3.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

