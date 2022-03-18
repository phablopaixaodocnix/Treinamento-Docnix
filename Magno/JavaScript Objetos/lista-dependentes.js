const cliente = {
    nome: "magno",
    idade: 19,
    cpf: "67312098452",
    email: "magno@newmail.com",
    fones: ["347029457240", "1934710293841"],
    dependentes: [{
        nome: "juliana dias",
        dataDeNascimento: "14/03/2006",
        parentesco: "filha"
    }]
}

cliente.dependentes.push({
    nome: "andressa",
    dataDeNascimento: "17/02/2009",
    parentesco: "filha"
})

console.log(cliente)

const filhaMaisVelha = cliente.dependentes.filter(dependente => 
                dependente.dataDeNascimento === "14/03/2006")

console.log(filhaMaisVelha[0].nome)