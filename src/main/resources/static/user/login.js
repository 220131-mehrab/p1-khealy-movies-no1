var welcomeMsg = 'Movie App';
var subHeading = 'Login';
document.querySelector('h1').innerText = welcomeMsg;
document.querySelector('h3').innerText = subHeading;



function login() {
    let user = {
        "userName": document.getElementById("userName").value,
        "userEmail": document.getElementById("userEmail").value,
        "userPassword": document.getElementById("userPassword").value,
    }

    console.log(user);
    console.log(user.userName);
    console.log(user.userEmail);
    console.log(user.userPassword);

    /*
    function toLogin(){
       const handleClick = (e) => {
              console.log("click")
              window.location.pathname = ('user/login.html')
              console.log(window.location)
        }
    }*/

    let button = document.querySelector('button');
    button.addEventListener('click', function() {
        button.textContent = "Go!";
        window.location.pathname = ('search/search.html')
        })
}