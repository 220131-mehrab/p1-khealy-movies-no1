var welcomeMsg = 'Movie App';
document.querySelector('h1').innerText = welcomeMsg;

fetch('/movies').then(resp => resp.json()).then(Movie => {
        document.querySelector('#movies').innerHTML = listMovie(Movie);
    }
);

function listMovies(json) {
    return `${json.map(listMovie).join('\n')}`;
};

let listMovie = function(Movie) {
    return '<p>' + Movie.movieID + ": " + Movie.title + '</p>';
};

function postMovie() {
    let Movie = {
        "movieID": document.getElementById("movieID").value,
        "title": document.getElementById("title").value,
        "overview": document.getElementById("overview").value,
        "releaseDate": document.getElementById("releaseDate").value,
        "cost": document.getElementById("cost").value
    }

    console.log(Movie);
    console.log(Movie.movieID);
    console.log(Movie.title);
    console.log(Movie.overview);
    console.log(Movie.releaseDate);
    console.log(Movie.cost);
    fetch('/movies', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(Movie)
    }).then((result) => {
        if (result.status != 200) {
            throw new Error("Bad Server Response");
        }
        console.log(result.text());
    }).catch((error) => { console.log(error); })
    fetch('/movies').then(resp => resp.json()).then(movies => {
            document.querySelector('#movies').innerHTML = listMovies(Movie);
        }
    );
}

let button = document.querySelector('button');
button.addEventListener('mouseenter', function() {
    button.textContent = "Go!";
})