const cliente = {
    nome: "magno",
    idade: 19,
    cpf: "67312098452",
    email: "magno@newmail.com",
    fones: ["347029457240", "1934710293841"]
}

cliente.dependentes = {
    nome: "Sara",
    parentesco: "filha",
    dataDeNascimento: "02/10/2003",
}

console.log(cliente)

cliente.dependentes.nome = "Sara Luiza"

console.log(cliente)