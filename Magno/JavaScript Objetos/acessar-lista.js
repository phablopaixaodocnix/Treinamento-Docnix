const cliente = {
    nome: "magno",
    idade: 19,
    cpf: "67312098452",
    email: "magno@newmail.com",
}

const chaves = ["nome", "idade", "cpf", "email"]

//console.log(cliente[chaves[2]])

//com o método forEach, é possível imprimir todas as informações
//contidas no objeto

chaves.forEach(info => console.log(cliente[info]))

//console.log(cliente["cpf"])