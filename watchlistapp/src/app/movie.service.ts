import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from './movie';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})

export class MovieService {
    private apiServerUrl = environment.apiBaseUrl ;

    constructor(private http: HttpClient) { }

    public getMovies(): Observable<Movie[]> {
        return this.http.get<Movie[]>(`${this.apiServerUrl}/movie/all`);
    }

    public getFavoriteMovies(): Observable<Movie[]> {
        return this.http.get<Movie[]>(`${this.apiServerUrl}/movie/favorites`);
    }

    public addMovie(movie: Movie): Observable<Movie> {
        return this.http.post<Movie>(`${this.apiServerUrl}/movie/add`, movie);
    }

    public updateMovie(movie: Movie): Observable<Movie> {
        return this.http.put<Movie>(`${this.apiServerUrl}/movie/update`, movie);
    }

    public deleteMovie(movieId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/movie/delete/${movieId}`);
    }

    public setFavorite(movieId: number): Observable<Object> {
        return this.http.get(`${this.apiServerUrl}/movie/${movieId}/favorite`);
    }

    public searchMovie(movieTitle: String): Observable<Object> {
        return this.http.post<String>(`${this.apiServerUrl}/movie/search?movieTitle=${movieTitle}`, movieTitle);
    }

}