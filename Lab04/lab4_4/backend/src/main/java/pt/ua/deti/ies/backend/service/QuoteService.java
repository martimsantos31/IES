package pt.ua.deti.ies.backend.service;

import pt.ua.deti.ies.backend.entity.Movie;
import pt.ua.deti.ies.backend.entity.Quote;

import java.util.List;

public interface QuoteService {

    Quote getRandomQuote();

    List<Movie> getAllMovies();

    List<Quote> getQuotesByMovie(Long movieId);

    Movie createMovie(Movie movie);

    Quote createQuote(Quote quote, Long movieId);
}



