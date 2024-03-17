
const navBar = document.getElementById('nav-bar')
const button = document.getElementsByClassName('btn')

window.addEventListener('scroll', function () {
    if (window.scrollY > 390) {
        navBar.style.backgroundColor = 'white';
        button[0].style.backgroundColor = '#538724';
    } else {
        navBar.style.backgroundColor = '#ffc017';
        button[0].style.backgroundColor = '#191919';
    }
})

const emme = document.querySelectorAll("g[stroke-linecap=butt]");
function magiaEmme() {
    let ammassoEmme = Math.floor(Math.random() * emme.length);
    if (emme[ammassoEmme].style.opacity === '0') {
      emme[ammassoEmme].style.opacity = '1'
    }
    else {
        emme[ammassoEmme].style.opacity = '0'
    }
  }
  
  setInterval(magiaEmme, 32);

  /*aiuto con internet anche se alla fine potevo fare da solo*/


