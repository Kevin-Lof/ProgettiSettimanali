const cardContainer = document.getElementById('card');
const myKey = 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZGNlMjJkN2IxMTAwMTkwZTZlMDQiLCJpYXQiOjE3MDk4OTEyNDEsImV4cCI6MTcxMTEwMDg0MX0.yMGLZ0AUI-8VOiWP4D785JUzrn4_zGLcZxQttST-duc';
const dataURL = "https://striveschool-api.herokuapp.com/api/product/";
const image = document.getElementById('image');
const product = document.getElementById('product');



const params = new URLSearchParams(location.search)
const id = params.get('id')


window.addEventListener('load', init);

function init() {
    loadDetails();
    console.log(id)
}

async function loadDetails() {
    try {
        let response = await fetch((dataURL + id), {
            headers: {
                "Authorization": myKey
            }
        })
        data = await response.json();
        prodotto = data;
        printDetails();
    }
    catch (error) {
        console.log(error)
    }
}

function printDetails() {
    let img = document.createElement('img');
    img.classList.add('img-fluid');
    img.setAttribute('src', prodotto.imageUrl);
    image.appendChild(img);

    product.innerHTML=`
                    <p>${prodotto.brand}</p>
                    <h3 class="fs-2 my-3">${prodotto.name}</h3>
                    <p><span><samp class="btn btn-dark text-warning">${prodotto.price}€</samp></span></p>
                    <p>${prodotto.description}</p>`

}