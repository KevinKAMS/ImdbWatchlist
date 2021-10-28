import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Movie } from '../movie';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  
  public movies: Movie[];
  public page = 1;
  public pageSize = 8;
  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.getMovies();
  }

  public getMovies(): void {
    this.movieService.getMovies().subscribe(
      (response: Movie[]) => {
        this.movies = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  

  public setFavorite(movie: Movie): void{
    this.movieService.setFavorite(movie.id).subscribe(
    );
  }

}
