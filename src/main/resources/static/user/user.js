var welcomeMsg = 'Movie App';
document.querySelector('h1').innerText = welcomeMsg;

function Login() {
    let user = {

        "userName": document.getElementById("userName").value,
        "userEmail": document.getElementById("userEmail").value,
        "userPassword": document.getElementById("userPassword").value,
    }

    console.log(movie);
    console.log(movie.movieID);
    console.log(movie.imdb_id);
    console.log(movie.title);
    console.log(movie.overview);
    console.log(movie.releaseDate);
    console.log(movie.cost);

    const handleClick = (e) => {
          console.log("click")
          window.location.pathname = ('/userSearch.html')
          console.log(window.location)
    }
