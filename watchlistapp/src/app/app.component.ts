import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Movie } from './movie';
import { MovieService } from './movie.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  
  public movies: Movie[];
  eventEmitterService: any;

  constructor(private movieService: MovieService) { }

  ngOnInit() {
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

  public searchMovies(key: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if(key == "") {
      console.log("No Results!");
      button.setAttribute('data-target', '#myModal');
      container.appendChild(button);
      button.click();
    }
    else {
      this.movieService.searchMovie(key).subscribe();
      setTimeout(() => 
      {   
        window.location.reload();
      },
      500);
    }
    
  }
  

}
