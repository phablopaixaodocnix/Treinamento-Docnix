//uso da arrow function

const somaNumerosPequenos = (num1, num2) => {
    if (num1 > 10 || num2 > 10){
        return "Somente números de 1 a 9";  
    } else{
        return num1+num2;
    }
}

console.log(somaNumerosPequenos(1,1))