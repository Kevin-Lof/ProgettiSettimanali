/*
REGOLE
- Tutte le risposte devono essere scritte in JavaScript
- Puoi usare Google / StackOverflow ma solo quanto ritieni di aver bisogno di qualcosa che non è stato spiegato a lezione
- Puoi testare il tuo codice in un file separato, o de-commentando un esercizio alla volta
- Per visualizzare l'output, lancia il file HTML a cui è collegato e apri la console dagli strumenti di sviluppo del browser. 
- Utilizza dei console.log() per testare le tue variabili e/o i risultati delle espressioni che stai creando.
*/

/* ESERCIZIO 1
    Dato il seguente array, scrivi del codice per stampare ogni elemento dell'array in console.
*/
const pets = ['dog', 'cat', 'hamster', 'redfish']
for (const pet of pets)

  console.log([pet])

/* ESERCIZIO 2
    Scrivi del codice per ordinare alfabeticamente gli elementi dell'array "pets".
*/
pets.sort();
console.log(pets)

/* ESERCIZIO 3
    Scrivi del codice per stampare nuovamente in console gli elementi dell'array "pets", questa volta in ordine invertito.
*/
pets.reverse();
console.log(pets)
/* ESERCIZIO 4
    Scrivi del codice per spostare il primo elemento dall'array "pets" in ultima posizione.
*/
const primoElemento = pets.shift();
pets.push(primoElemento);
console.log(pets)
/* ESERCIZIO 5
    Dato il seguente array di oggetti, scrivi del codice per aggiungere ad ognuno di essi una proprietà "licensePlate" con valore a tua scelta.
*/
const cars = [
  {
    brand: 'Ford',
    model: 'Fiesta',
    color: 'red',
    trims: ['titanium', 'st', 'active'],
  },
  {
    brand: 'Peugeot',
    model: '208',
    color: 'blue',
    trims: ['allure', 'GT'],
  },
  {
    brand: 'Volkswagen',
    model: 'Polo',
    color: 'black',
    trims: ['life', 'style', 'r-line'],
  },
]
const ford = cars.filter(obj => {
  return (obj.brand == "Ford")
})
ford.licensePlate = "RT567OP"
console.log(ford)

const peugeot = cars.filter(obj => {
  return (obj.brand == "peugeot")
})
peugeot.licensePlate = "ST597LM"
console.log(peugeot)

const volkswagen = cars.filter(obj => {
  return (obj.brand == "volkswagen")
})
volkswagen.licensePlate = "AC009HY"
console.log(volkswagen)



/* ESERCIZIO 6
    Scrivi del codice per aggiungere un nuovo oggetto in ultima posizione nell'array "cars", rispettando la struttura degli altri elementi.
    Successivamente, rimuovi l'ultimo elemento della proprietà "trims" da ogni auto.
*/
const nuovoOggetto = {
  brand: 'Toyota',
  model: 'Aygo',
  color: 'yellow',
  trims: ['life', 'style', 'active'],
}
cars.push(nuovoOggetto);



cars.forEach(car => delete car.trims[car.trims.length - 1]);

console.log(cars)
/* ESERCIZIO 7
    Scrivi del codice per salvare il primo elemento della proprietà "trims" di ogni auto nel nuovo array "justTrims", sotto definito.
*/
const justTrims = [];
for (const car of cars) {
  justTrims.push(car.trims[0]);
}
console.log(justTrims)

/* ESERCIZIO 8
    Cicla l'array "cars" e costruisci un if/else statament per mostrare due diversi messaggi in console. Se la prima lettera della proprietà
    "color" ha valore "b", mostra in console "Fizz". Altrimenti, mostra in console "Buzz".
*/
for (const car of cars) {
  const firstLetter = car.color.charAt(0);
  if (firstLetter === "b") {
    console.log("Fizz");
  } else {
    console.log("Buzz")
  }

}
console.log(cars)


console.log(cars)
/* ESERCIZIO 9
    Utilizza un ciclo while per stampare in console i valori del seguente array numerico fino al raggiungimento del numero 32.
*/
const numericArray = [
  6, 90, 45, 75, 84, 98, 35, 74, 31, 2, 8, 23, 100, 32, 66, 313, 321, 105,
]
let i = 0;
while (i < numericArray.length && numericArray[i] !== 66) {
  console.log(numericArray[i]);
  i++;
}
/* ESERCIZIO 10
    Partendo dall'array fornito e utilizzando un costrutto switch, genera un nuovo array composto dalle posizioni di ogni carattere all'interno
    dell'alfabeto italiano.
    es. [f, b, e] --> [6, 2, 5]
*/
const charactersArray = ['g', 'n', 'u', 'z', 'd'];
const alphabet = 'abcdefghijklmnopqrstuvwxyz';

const positionsArray = charactersArray.map(char => {
  switch (char) {
    case 'g':
      return alphabet.indexOf(char) + 1;
    case 'n':
      return alphabet.indexOf(char) + 1;
    case 'u':
      return alphabet.indexOf(char) + 1;
    case 'z':
      return alphabet.indexOf(char) + 1;
    case 'd':
      return alphabet.indexOf(char) + 1;
    default:
      return -1;
  }
});

console.log(positionsArray);
