//definir um modelo

function Cliente(nome, cpf, email, saldo){
    this.nome = nome
    this.cpf = cpf
    this.email = email
    this.saldo = saldo
    this.depositar = function(valor){
        this.saldo += valor
    }
}

const magno = new Cliente("Magno", "09470293857", "magno@newmail.com", "100")
console.log(magno)
