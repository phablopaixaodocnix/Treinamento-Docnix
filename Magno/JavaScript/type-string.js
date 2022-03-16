//type string

const citacao = "Fulano disse: 'oi!'";

console.log(citacao)

//concatenação

const primeiroNome = "Magno";
const segundoNome = "   Levi";

console.log(primeiroNome +segundoNome)


//caracteres especiais

const cifrao = '\u0024';
const aMaiusculo = '\u0041';
const tique = '\u2705';
const hiragana = '\u3041';

console.log(cifrao)
console.log(aMaiusculo)
console.log(tique)
console.log(hiragana)

//Trabalhando com strings - toLowerCase

const cidade = "goiânia";
const input = "GOIÂNIA";

const letraMinuscula = input.toLowerCase();

console.log(cidade === letraMinuscula);

//Trabalhando com strings - lenght

const tamanho = "Magno";

console.log(tamanho.length)