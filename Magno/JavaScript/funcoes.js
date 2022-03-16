function imprimeTexto(texto){
    console.log(texto)
}

imprimeTexto ("Um texto")
imprimeTexto ("Outro texto")

imprimeTexto(soma());

//outras formas de escrever

function soma(){
    return 10+20;
}

console.log(soma())
