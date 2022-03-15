var welcomeMsg = 'Movie App';
document.querySelector('h1').innerText = welcomeMsg;

fetch('/movies').then(resp => resp.json()).then(movies => {
        document.querySelector('#movies').innerHTML = listMovies(movies);
    }
);

function listMovies(json) {
    return `${json.map(listMovie).join('\n')}`;
};

let listMovie = function(movie) {
    return '<p>' + movie.movieID + ": " + movie.title + '</p>';
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
<<<<<<< HEAD
        fetch('/movies').then(resp => resp.json()).then(movies => {
=======
    }).catch((error) => { console.log(error); })
    
    fetch('/movies').then(resp => resp.json()).then(movies => {
>>>>>>> 71363de72f3275e9a362c9643f6bf38ac943cb28
            document.querySelector('#movies').innerHTML = listMovies(movies);
        })}
    ).catch((error) => { console.log(error); })
}

let button = document.querySelector('button');
button.addEventListener('mouseenter', function() {
    button.textContent = "Go!";
})