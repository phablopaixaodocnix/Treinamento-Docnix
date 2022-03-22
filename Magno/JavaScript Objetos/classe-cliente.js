class Cliente{
    constructor(nome,idade,saldo){
        this.nome = nome
        this.idade = idade
        this.saldo = saldo
    }

    depositar(valor){
        this.saldo += valor
    }

    exibirSaldo(){
        console.log(this.saldo)
    }
}

class ClientePoupanca extends Cliente{
    constructor(nome, idade, saldo, saldoPoupanca){
        super (nome, idade, saldo)
        this.saldoPoupanca = saldoPoupanca
    }
    depositarPoupanca(valor){
        this.saldoPoupanca += valor
    }
}

const cliente = new ClientePoupanca("andre", 56, 100, 200)
cliente.depositar (50)
cliente.depositarPoupanca(50)

console.log(cliente)
