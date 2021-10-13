# ImdbWatchlist
A Watchlist using IMDB Api for training Springboot, Maven, Angular, SQL and Typescript

To run this application you need a MySQL database, Springboot and Angular.
For now this runs only on localhost, with in code configuration.

You can configure the database and user info you will use on *application.properties* that's on *Watchlist/src/resources/application.properties*
After that you can run the backend of the application

To run the front end, go to *watchlistapp/* and run:

    ng serve
The application should be running in your *http://localhost:4200/*

From there you can:

 - Search movies from the search bar, those will be show by year in descending order.
 - Mark movies as favorite
 - See favorited movies
