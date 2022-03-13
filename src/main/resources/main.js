var welcomeMsg = 'Music App';
document.querySelector('h1').innerText = welcomeMsg;

fetch('/movies').then(resp => resp.json()).then(artists => {
        document.querySelector('#movies').innerHTML = listArtists(artists);
    }
);

function listArtists(json) {
    return `${json.map(listArtist).join('\n')}`;
};

let listMovie = function(Movie) {
    return '<p>' + Movie.movieID + ": " + movie.title + '</p>';
};

function postMovie() {
    let Movie = {
        "MovieID": document.getElementById("movieID").value,
        "name": document.getElementById("title").value
    }
    console.log(Movie);
    fetch('/movies', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(artist)
    }).then((result) => {
        if (result.status != 200) {
            throw new Error("Bad Server Response");
        }
        console.log(result.text());
    }).catch((error) => { console.log(error); })
    fetch('/movies').then(resp => resp.json()).then(movies => {
            document.querySelector('#movies').innerHTML = listMovie(movie);
        }
    );
}

let button = document.querySelector('button');
button.addEventListener('mouseenter', function() {
    button.textContent = "Go!";
})