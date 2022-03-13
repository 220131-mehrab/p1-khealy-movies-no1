var welcomeMsg = 'Movie App';
document.querySelector('h1').innerText = welcomeMsg;

fetch('/movies').then(resp => resp.json()).then(Movie => {
        document.querySelector('#movies').innerHTML = listMovie(Movie);
    }
);

function listMovie(json) {
    return `${json.map(listMovie).join('\n')}`;
};

let listMovie = function(Movie) {
    return '<p>' + Movie.movieID + ": " + Movie.title + '</p>';
};

function postMovie() {
    let Movie = {
        "MovieID": document.getElementById("movieID").value,
        "title": document.getElementById("title").value
    }

    console.log(Movie);
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
            document.querySelector('#movies').innerHTML = listMovie(Movie);
        }
    );
}

let button = document.querySelector('button');
button.addEventListener('mouseenter', function() {
    button.textContent = "Go!";
})