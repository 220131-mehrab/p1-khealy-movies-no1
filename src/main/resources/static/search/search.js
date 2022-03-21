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

     let movie = {
         movieID: 0,
         imdb_id: "X",
         title: "x",
         overview: "x",
         releaseDate: "x",
         cost: 0
     }


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
            document.querySelector('#movies').innerHTML = listMyMovies(movie)
        })}
    ).catch((error) => { console.log("I got error: " + error); }
    );
};
function listMyMovies(json) {
    return `${json.map(listMyMovie).join('\n')}`;
};

function listMyMovie(movie){
        return '<p>Thank you for your order.  You have selected: </br> ID: ' + movie.movieID + '</br>Title: ' + movie.title + '</br>IMDB_ID: ' + movie.imdb_id +  '</br>' +
        'overview: ' + movie.overview + '</br>' + 'Releasedate: ' + movie.releaseDate + '</br>'+ movie.cost + '<p>'
};




