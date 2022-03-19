var welcomeMsg = 'Movie App';
var subHeading = 'Login';
document.querySelector('h1').innerText = welcomeMsg;
document.querySelector('h3').innerText = subHeading;

function login() {
    console.log("im starting login")
    let User1 = {
        "userID": document.getElementById("userID").value,
        "userName": document.getElementById("userName").value,
        "email": document.getElementById("email").value,
        "password": document.getElementById("password").value,
        "imdb_id": "X"
    }

    console.log(User1);
    console.log(User1.userName);
    console.log(User1.email);
    console.log(User1.password);
    fetch('/login', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(User1)
    }).then((result) => {
        if (result.status != 200) {
            throw new Error("Bad Server Response");
        }
        console.log("text sent");
        window.location.pathname = 'search/search.html';
    }).catch((error) => { console.log(error); })
}