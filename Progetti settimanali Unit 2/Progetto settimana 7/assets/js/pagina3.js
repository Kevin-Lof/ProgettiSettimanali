const API_KEY = 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZDRkNjJkN2IxMTAwMTkwZTZkY2EiLCJpYXQiOjE3MDk4ODg3MjYsImV4cCI6MTcxMTA5ODMyNn0.Qv7lc3LfAXVWXsHIrjfVMmmQ4Fryoeg0Y4Ors3kyycM';
const API_URL = 'https://striveschool-api.herokuapp.com/api/product/';
const productForm = document.getElementById('productForm');
const deleteBtn = document.getElementById('deleteBtn');
const saveBtn = document.getElementById('saveBtn');
const resetBtn = document.getElementById('resetBtn');
const nomeProdotto = document.getElementById('nameProduct');
const brand = document.getElementById('brand');
const prezzo = document.getElementById('price');
const urlImg = document.getElementById('urlImg');
const descProduct = document.getElementById('descProduct');
const title = document.getElementById('title');
const message = document.getElementById('message');

window.onload = () => {
    init();
}

const checkValidator = () => {
  
    for ( let i = 0; i< productForm.elements.length; i++){
        
        productForm.elements[i].addEventListener('blur', () => {
            if(productForm.elements[i].value == ''){
                productForm.elements[i].classList.add('empty')
            } else{
                productForm.elements[i].classList.remove('empty')
            }
        })
    }
   
}


async function showProduct (id) {
    let newUrl = API_URL + id;
    let response = await fetch(newUrl, {
        headers: {
            "Authorization": API_KEY
            }
    })
    let data = await response.json();
   Form(data);
    
}

const Form = (data) => {
     nomeProdotto.value = data.name;
    brand.value = data.brand;
    prezzo.value = data.price;
    urlImg.value = data.imageUrl;
    descProduct.value = data.description;
}

async function aggiungiProdotto () {
    let nameVal = nomeProdotto.value;
    let brandVal = brand.value;
    let prezzoVal = prezzo.value;
    let urlImgVal = urlImg.value;
    let descProductVal = descProduct.value;

    if(nameVal == '' || brandVal == '' || prezzoVal == '' || urlImg == '' || descProductVal == ''){
        message.innerText = 'Aggiungi i campi mancanti';
        message.classList.add('text-danger')
        for ( let i = 0; i< productForm.elements.length; i++){
        
           
                if(productForm.elements[i].value == ''){
                    productForm.elements[i].classList.add('empty')
                } else{
                    productForm.elements[i].classList.remove('empty')
                }
            
        }
    }else{
        let data = {
            name : nameVal,
            brand : brandVal,
            imageUrl : urlImgVal,
            price : prezzoVal,
            description : descProductVal
        }
    
        try{
            let response = await fetch(API_URL, {
                method: 'POST', 
                headers: {
                    "Authorization": API_KEY,
                    'Content-Type': 'application/json' 
                },
                body: JSON.stringify(data) 
              });
            
              let responseData = await response.json();
              productForm.reset()
              message.innerText = '';
              
            
        } catch (error){
           
            console.log(error);
        }
    }

    
    

}

async function editProdotto (id){
    let finalUrl = API_URL+id;
    let nameVal = nomeProdotto.value;
    let brandVal = brand.value;
    let prezzoVal = prezzo.value;
    let urlImgVal = urlImg.value;
    let descProductVal = descProduct.value;
    let data = {
        name : nameVal,
        brand : brandVal,
        imageUrl : urlImgVal,
        price : prezzoVal,
        description : descProductVal
    }
    try{
        let response = await fetch(finalUrl, {
            method: 'PUT', 
            headers: {
                "Authorization": API_KEY,
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(data) 
          });
        
          let responseData = await response.json();
          message.innerText = '';
          if(message.classList.contains('d-none')){
            message.classList.remove('d-none')
          }
          message.classList.add('text-success');
          message.innerText = 'Modifiche salvate ';
          setTimeout(() => {
            message.classList.add('d-none');
          }, 3000);
          
        
    } catch (error){
        console.log(error);
    }
}


async function deleteProdotto (id) {
    let finalUrl = API_URL+id;
    try{
        let response = await fetch(finalUrl, {
            method: 'DELETE', 
            headers: {
                "Authorization": API_KEY,
                'Content-Type': 'application/json' 
            },
          });
        
          let responseData = await response.json();
         
          window.location.href = 'index.html';
          
        
    } catch (error){
        console.log(error);
    }
}



const init = () =>{

    const url = new URL(window.location.href); 
    const searchParams = url.searchParams;
    const idProduct = searchParams.get('id');
    checkValidator()
    if(idProduct){
        title.innerText = 'Edit Product'
        showProduct(idProduct);
        resetBtn.classList.add('d-none');
        deleteBtn.classList.remove('d-none');
        deleteBtn.addEventListener('click', function(e){
            e.preventDefault();
           if (confirm('Sicuro della tua scelta?') == true ){
            deleteProdotto(idProduct);
           }
           
        })
        saveBtn.addEventListener('click', function(e){
            e.preventDefault();
            editProdotto(idProduct);
        })
    }else{
        saveBtn.addEventListener('click', function(e){
            e.preventDefault();
            aggiungiProdotto();
        })
    }


    

    resetBtn.addEventListener('click', function(e){
        e.preventDefault();
        
        if (confirm('Sicuro della tua scelta?') == true ){
            productForm.reset();
            for ( let i = 0; i< productForm.elements.length; i++){
        
           
               
                    productForm.elements[i].classList.remove('empty')
               
            
        }
           }
    })
    
}

