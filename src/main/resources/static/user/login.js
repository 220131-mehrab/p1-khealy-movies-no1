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

    const handleClick = (e) => {
          console.log("click")
          window.location.pathname = ('/userSearch.html')
          console.log(window.location)
    }
