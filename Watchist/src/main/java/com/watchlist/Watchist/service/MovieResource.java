package com.watchlist.Watchist.service;

import java.util.ArrayList;
import java.util.List;

import com.watchlist.Watchist.model.Movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/movie")
public class MovieResource {
    private final MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.findMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        Movie updateMovie = movieService.updateMovie(movie);
        return new ResponseEntity<>(updateMovie, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<Movie> search(@RequestParam("movieTitle") String movieTitle) {
        return movieService.search(movieTitle);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/favorites")
    public List<Movie> watchlist(){
        try{
            return movieService.getFavorites();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Movie>();
        }
    }
    
    @RequestMapping("/{movieId}/favorite")
    public Movie setFavorite(@PathVariable("movieId") long id){
        try {
            return movieService.favoriteToggle(id);        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
