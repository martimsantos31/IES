import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/api",
    withCredentials: true,
});

export default api;


export const getRandomQuote = async () => {
    const response = await api.get("/quotes/random");
    return response.data;
};

export const getAllMovies = async () => {
    const response = await api.get("/movies");
    return response.data;
};

export const getQuotesByMovie = async (movieId) => {
    const response = await api.get("/quotes/by-movie", {
        params: { movieId },
    });
    return response.data;
};

export const createMovie = async (movie) => {
    const response = await api.post("/movies", movie);
    return response.data;
};

export const createQuote = async (quote, movieId) => {
    const response = await api.post(`/quotes?movieId=${movieId}`, quote);
    return response.data;
};


