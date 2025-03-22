package pt.ua.deti.ies.lab3_3.service;

import pt.ua.deti.ies.lab3_3.entity.Movie;
import pt.ua.deti.ies.lab3_3.entity.Quote;

import java.util.List;

public interface QuoteService {

    Quote getRandomQuote();

    List<Movie> getAllMovies();

    List<Quote> getQuotesByMovie(Long movieId);

    Movie createMovie(Movie movie);

    Quote createQuote(Quote quote, Long movieId);
}



