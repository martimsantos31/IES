package pt.ua.deti.ies.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.deti.ies.backend.entity.Movie;
import pt.ua.deti.ies.backend.entity.Quote;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByMovie(Movie movie);

}
