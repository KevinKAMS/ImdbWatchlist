package com.watchlist.Watchist.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.watchlist.Watchist.api.ImdbApi;
import com.watchlist.Watchist.model.Movie;
import com.watchlist.Watchist.repo.MovieRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieService{
    private final MovieRepo movieRepo;
    private ImdbApi imdbApi;

    @Autowired
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Autowired
    public void ImdbApi(ImdbApi imdbApi) {
        this.imdbApi = imdbApi;
    }

    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> findAllMovies() {
        List<Movie> movieList = movieRepo.findAll();
        Collections.sort(movieList);
        Collections.reverse(movieList);
        return movieList;
    }

    public Movie updateMovie(Movie movie){
        return movieRepo.save(movie);
    }

    public void deleteMovie(Long id){
        movieRepo.deleteMovieById(id);
    }

    public Movie findMovieById(long id){
        return movieRepo.findMovieById(id);
    }

    
    public List<Movie> search(String movieTitle) {
        if (movieTitle.equals("")) {
        ArrayList<Movie> movieList = new ArrayList();
        return movieList;
    }
        ArrayList<Movie> movieList = imdbApi.search(movieTitle);
        movieList.stream().filter((movie) -> (findMovieById(movie.getId())==null)).forEachOrdered((movie) -> {
            addMovie(movie);
        });
        return movieList;
    }

    public ArrayList<Movie> getFavorites(){
        ArrayList<Movie> favorites = new ArrayList<>();
        findAllMovies().stream().filter((movie) -> (movie.isFavorite())).forEachOrdered((movie) -> {
            favorites.add(movie);
        });
        return favorites;
    }

    public Movie favoriteToggle(long id) {
        Movie movie = findMovieById(id);
        movie.setFavorite(!movie.isFavorite());
        updateMovie(movie);
        return movie;
    }



}
