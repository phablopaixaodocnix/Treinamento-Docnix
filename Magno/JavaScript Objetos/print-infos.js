const cliente = {
    nome: "magno",
    idade: 19,
    cpf: "67312098452",
    email: "magno@newmail.com",
}

console.log(`meu nome é ${cliente.nome}, e tenho ${cliente.idade} anos`)
console.log(`email: ${cliente.email}`)
console.log(`os três primeiros dígitos do cpf são: ${cliente.cpf.substring(0,3)}`)