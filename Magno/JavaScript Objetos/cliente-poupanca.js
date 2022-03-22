function Cliente (nome, cpf, email, saldo){
    this.nome = nome
    this.cpf = cpf
    this.email = email
    this.saldo = saldo
    this.depositar = function(valor){
        this.saldo += valor
    }
}

function ClientePoupanca (nome, cpf,email,saldo,saldoPoup){
    Cliente.call(this, nome, cpf, email,saldo)
    this.saldoPoup = saldoPoup
}

const baiano = new ClientePoupanca ("Baiano", "572349087234", "baiano@newmail.com", 100,200)

console.log(baiano)

ClientePoupanca.prototype.depositarPoup = function(valor){
    this.saldoPoup += valor
}

baiano.depositarPoup(30)

console.log(baiano.saldoPoup)