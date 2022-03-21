var welcomeMsg = 'Movie App';
var subHeading = 'Search';
document.querySelector('h1').innerText = welcomeMsg;
document.querySelector('h3').innerText = subHeading;


function searchMovies(){
    let imdb_id = document.getElementById("imdb_id").value;
    fetch('/search', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(imdb_id)
    }).then((result) => {
        if (result.status != 200) {
            throw new Error("Bad Server Response");
        }
        console.log("request sent");
        fetch('/search').then(resp => resp.json()).then(movies => {
            document.querySelector('#movies').innerHTML = listMovies(movies);
        })}).catch((error) => { console.log("I got error: " + error); }
    );
}

function listMovies(json) {
    return `${json.map(listMovie).join('\n')}`;
};

let listMovie = function(movie) {
    console.log('<p>' + movie.movieID + ': ' + movie.title + ' : ' + '<button type=button onclick=\"postSelection(\'' + movie.imdb_id + '\')\">Select</button></p>');
    return '<p>' + movie.movieID + ': ' + movie.title + ' : ' + '<button type=button onclick=\"postSelection(\'' + movie.imdb_id + '\')\">Select</button></p>'};

function postSelection(imdb_id) {
    /*
    let movie = {
        "movieID": document.getElementById("movieID").value,
        "imdb_id": document.getElementById("imdb_id").value,
        "title": document.getElementById("title").value,
        "overview": document.getElementById("overview").value,
        "releaseDate": document.getElementById("releaseDate").value,
        "cost": document.getElementById("cost").value
    }
    */

    fetch('/search', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(imdb_id)
    }).then((result) => {
        if (result.status != 200) {
            throw new Error("Bad Server Response");
        }
        console.log("request sent");
        fetch('/search').then(resp => resp.json()).then(movie => {
            console.log(listMovies(movie));
            document.querySelector('#movies').innerHTML = listMovies(movie)
        })}
    ).catch((error) => { console.log("I got error: " + error); }
    );
};
function listMovies(json) {
    return `${json.map(listMyMovie(movie)).join('\n')}`;
};
function listMyMovie(movie){
        return '<p>You have selected: </br> ID: ' + movie.movieID + '</br>Title: ' + movie.title + '</br>ID: ' + movie.imdb_id + '<p>'
};



