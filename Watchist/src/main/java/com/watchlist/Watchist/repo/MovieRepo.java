package com.watchlist.Watchist.repo;


import com.watchlist.Watchist.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long>{

    void deleteMovieById(Long id);

    Movie findMovieById(long id);

    
    
}
