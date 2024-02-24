
const navBar = document.getElementById('nav-bar')
const button = document.getElementsByClassName('btn')

window.addEventListener('scroll', function () {
    if (window.scrollY > 430) {
        navBar.style.backgroundColor = 'white';
        button[0].style.backgroundColor = '#538724';
    } else {
        navBar.style.backgroundColor = '#ffc017';
        button[0].style.backgroundColor = '#191919';
    }
});