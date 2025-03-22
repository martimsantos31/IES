package pt.ua.deti.ies.backend.service;

import org.springframework.stereotype.Service;
import pt.ua.deti.ies.backend.entity.Movie;
import pt.ua.deti.ies.backend.entity.Quote;
import pt.ua.deti.ies.backend.repository.MovieRepository;
import pt.ua.deti.ies.backend.repository.QuoteRepository;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final MovieRepository movieRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository, MovieRepository movieRepository) {
        this.quoteRepository = quoteRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        Random rand = new Random();
        return quotes.get(rand.nextInt(quotes.size()));
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Quote> getQuotesByMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .map(quoteRepository::findByMovie)
                .orElse(List.of());
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Quote createQuote(Quote quote, Long movieId) {
        return movieRepository.findById(movieId)
                .map(movie -> {
                    quote.setMovie(movie);
                    return quoteRepository.save(quote);
                })
                .orElseThrow(() -> new RuntimeException("Movie with id " + movieId + " not found"));
    }
}
