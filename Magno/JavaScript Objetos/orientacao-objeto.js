class Pessoa {
    constructor (nome, sobrenome, idade){
        this.nome = nome
        this.sobrenome = sobrenome
        this.idade = idade
    }

    exibirNomeCompleto(){
        console.log(`${this.nome} ${this.sobrenome}`)
    }
}

class Programador extends Pessoa{
    constructor(nome,sobrenome,idade,linguagem){
        super (nome,sobrenome,idade)
        this.linguagem = linguagem
    }
}

const programmer = new Programador("baiano", "de mauá", 19,"HTML")


console.log(programmer)