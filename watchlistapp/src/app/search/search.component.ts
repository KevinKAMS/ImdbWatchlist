import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from '../movie';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public movies: Movie[];

  constructor(private movieService: MovieService, private router: Router) { }

  ngOnInit(): void {
  }

  //Search movie by calling the .searchMovie function
  //If it doesn't return anything, open a modal showing a warning
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
        this.router.navigateByUrl('/search', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/watchlist']);
        }); 
      },
      1000);
    }
    
  }

}
