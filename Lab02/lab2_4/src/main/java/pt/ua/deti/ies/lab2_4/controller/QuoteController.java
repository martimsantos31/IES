package pt.ua.deti.ies.lab2_4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.deti.ies.lab2_4.model.Quote;
import pt.ua.deti.ies.lab2_4.model.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class QuoteController {

    private static final List<Quote> quotes = new ArrayList<>();
    private static final List<Show> shows = new ArrayList<>();

    static {

        shows.add(new Show("1", "Breaking Bad"));
        shows.add(new Show("2", "Game of Thrones"));
        shows.add(new Show("3", "The Office"));

        quotes.add(new Quote("I am the one who knocks!", "Breaking Bad"));
        quotes.add(new Quote("Say my name.", "Breaking Bad"));
        quotes.add(new Quote("Winter is coming.", "Game of Thrones"));
        quotes.add(new Quote("I drink and I know things.", "Game of Thrones"));
        quotes.add(new Quote("That's what she said.", "The Office"));
        quotes.add(new Quote("Bears. Beets. Battlestar Galactica.", "The Office"));
    }

    @GetMapping("/api/quote")
    public Quote getRandomQuote() {
        Random rand = new Random();
        return quotes.get(rand.nextInt(quotes.size()));
    }

    @GetMapping("/api/shows")
    public List<Show> getAllShows() {
        return shows;
    }


    @GetMapping("/api/quotes")
    public List<Quote> getQuotesByShow(@RequestParam("show") String showId) {

        Show show = shows.stream()
                .filter(s -> s.id().equals(showId))
                .findFirst()
                .orElse(null);

        if (show != null) {
            return quotes.stream()
                    .filter(q -> q.show().equals(show.name()))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}

