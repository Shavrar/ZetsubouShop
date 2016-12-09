if (sessionStorage.getItem("Authorization") === null) {
    window.location.replace('http://localhost:60305/account/login');
}