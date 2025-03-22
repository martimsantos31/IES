import React, { useState, useEffect } from "react";
import { getRandomQuote, getAllMovies, getQuotesByMovie, createMovie, createQuote } from "./api";

export const QuotesManager = () => {
    const [randomQuote, setRandomQuote] = useState(null);
    const [movies, setMovies] = useState([]);
    const [selectedMovieId, setSelectedMovieId] = useState(null);
    const [quotes, setQuotes] = useState([]);
    const [newMovie, setNewMovie] = useState({ title: "", year: "" });
    const [newQuote, setNewQuote] = useState("");

    useEffect(() => {
        fetchMovies();
    }, []);

    const fetchMovies = async () => {
        try {
            const movies = await getAllMovies();
            setMovies(movies);
        } catch (error) {
            console.error("Error fetching movies:", error);
        }
    };

    const fetchRandomQuote = async () => {
        try {
            const quote = await getRandomQuote();
            setRandomQuote(quote);
        } catch (error) {
            console.error("Error fetching random quote:", error);
        }
    };

    const fetchQuotesByMovie = async (movieId) => {
        try {
            const quotes = await getQuotesByMovie(movieId);
            setQuotes(quotes);
            setSelectedMovieId(movieId);
        } catch (error) {
            console.error("Error fetching quotes:", error);
        }
    };

    const handleCreateMovie = async () => {
        try {
            const movie = await createMovie(newMovie);
            setMovies((prev) => [...prev, movie]);
            setNewMovie({ title: "", year: "" });
        } catch (error) {
            console.error("Error creating movie:", error);
        }
    };

    const handleCreateQuote = async () => {
        try {
            if (!selectedMovieId) {
                alert("Please select a movie first.");
                return;
            }
            const quote = await createQuote({ text: newQuote }, selectedMovieId);
            setQuotes((prev) => [...prev, quote]);
            setNewQuote("");
        } catch (error) {
            console.error("Error creating quote:", error);
        }
    };

    return (
        <div>
            <h1>Quotes Dashboard</h1>

            <section>
                <h2>Random Quote</h2>
                <button onClick={fetchRandomQuote}>Get Random Quote</button>
                {randomQuote && <p>{randomQuote.text}</p>}
            </section>

            <section>
                <h2>Movies</h2>
                <ul>
                    {movies.map((movie) => (
                        <li key={movie.id}>
                            {movie.title} ({movie.year})
                            <button onClick={() => fetchQuotesByMovie(movie.id)}>View Quotes</button>
                        </li>
                    ))}
                </ul>

                <div>
                    <h3>Add Movie</h3>
                    <input
                        type="text"
                        placeholder="Movie Title"
                        value={newMovie.title}
                        onChange={(e) => setNewMovie({ ...newMovie, title: e.target.value })}
                    />
                    <input
                        type="number"
                        placeholder="Year"
                        value={newMovie.year}
                        onChange={(e) => setNewMovie({ ...newMovie, year: parseInt(e.target.value) })}
                    />
                    <button onClick={handleCreateMovie}>Add Movie</button>
                </div>
            </section>

            {selectedMovieId && (
                <section>
                    <h2>Quotes for Movie ID: {selectedMovieId}</h2>
                    <ul>
                        {quotes.map((quote) => (
                            <li key={quote.id}>{quote.text}</li>
                        ))}
                    </ul>

                    <div>
                        <h3>Add Quote</h3>
                        <input
                            type="text"
                            placeholder="Quote Text"
                            value={newQuote}
                            onChange={(e) => setNewQuote(e.target.value)}
                        />
                        <button onClick={handleCreateQuote}>Add Quote</button>
                    </div>
                </section>
            )}
        </div>
    );
};


