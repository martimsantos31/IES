package pt.ua.deti.ies.lab3_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.deti.ies.lab3_3.entity.Movie;
import pt.ua.deti.ies.lab3_3.entity.Quote;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByMovie(Movie movie);

}
