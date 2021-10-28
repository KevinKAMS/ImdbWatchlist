import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { SearchComponent } from './search/search.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    WatchlistComponent,
    FavoritesComponent,
    SearchComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    RouterModule.forRoot([
      {path: 'watchlist', component: WatchlistComponent},
      {path: 'favorites', component: FavoritesComponent},
      {path: 'search', component: SearchComponent},
      {path: 'navbar', component: NavbarComponent},
      {path: '', redirectTo: '/watchlist', pathMatch: 'full'},
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
