var welcomeMsg = 'Movie App';
var subHeading = 'search';
document.querySelector('h1').innerText = welcomeMsg;
document.querySelector('h3').innerText = subHeading;

function searchMovies(){
    fetch('/search').then(resp => resp.json()).then(movies => {
        document.querySelector('#movies').innerHTML = listMovies(movies);
    }
    );
}

function listMovies(json) {
    return `${json.map(listMovie).join('\n')}`;
};

let listMovie = function(movie) {
    return '<p>' + movie.movieID + ": " + movie.title + " : "'<button type=button>Select</button></p>';
};

function postMovie() {
    let movie = {
        "movieID": document.getElementById("movieID").value,
        "imdb_id": document.getElementById("imdb_id").value,
        "title": document.getElementById("title").value,
        "overview": document.getElementById("overview").value,
        "releaseDate": document.getElementById("releaseDate").value,
        "cost": document.getElementById("cost").value
    }

     console.log(movie);
     console.log(movie.movieID);
     console.log(movie.imdb_id);
     console.log(movie.title);
     console.log(movie.overview);
     console.log(movie.releaseDate);
     console.log(movie.cost);
     fetch('/movies', {
         method: "POST",
         headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json'
         },
         body: JSON.stringify(movie)
     }).then((result) => {
         if (result.status != 200) {
             throw new Error("Bad Server Response");
         }
         console.log("text sent");
         fetch('/movies').then(resp => resp.json()).then(movies => {
             document.querySelector('#movies').innerHTML = listMovies(movies);
         })}
     ).catch((error) => { console.log("I got error: " + error); })
}



