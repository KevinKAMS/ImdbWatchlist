import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Movie } from '../movie';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  public movies: Movie[];
  
  public page = 1;
  public pageSize = 8;
  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.getFavoriteMovies();
  }

  public getFavoriteMovies(): void {
    this.movieService.getFavoriteMovies().subscribe(
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
