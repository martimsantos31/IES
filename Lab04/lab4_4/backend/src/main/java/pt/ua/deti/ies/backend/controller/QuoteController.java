package pt.ua.deti.ies.backend.controller;

import org.springframework.web.bind.annotation.*;
import pt.ua.deti.ies.backend.entity.Quote;
import pt.ua.deti.ies.backend.entity.Movie;
import pt.ua.deti.ies.backend.service.QuoteService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quotes/random")
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return quoteService.getAllMovies();
    }

    @GetMapping("/quotes/by-movie")
    public List<Quote> getQuotesByMovie(@RequestParam("movieId") Long movieId) {
        return quoteService.getQuotesByMovie(movieId);
    }

    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        return quoteService.createMovie(movie);
    }

    @PostMapping("/quotes")
    public Quote createQuote(@RequestBody Quote quote, @RequestParam("movieId") Long movieId) {
        return quoteService.createQuote(quote, movieId);
    }
}



