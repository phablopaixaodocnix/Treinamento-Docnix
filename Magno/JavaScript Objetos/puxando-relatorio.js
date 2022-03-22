const cliente = {
    nome: "magno",
    idade: 19,
    cpf: "67312098452",
    email: "magno@newmail.com",
    fones: ["347029457240", "1934710293841"],
    dependentes: [
        {
        nome: "juliana dias",
        dataDeNascimento: "14/03/2006",
        parentesco: "filha"},
        {
        nome: "leticia castro",
        dataDeNascimento: "02/10/2009",
        parentesco: "filha"}
    ],
    saldo:100,
    depositar: function (valor){
        this.saldo += valor
    }
}

let relatorio = "";

for (let info in cliente){
    if (typeof cliente[info] === "object" || typeof cliente[info] === "function"){
        continue
    }else{
        relatorio += `${cliente[info]
        }`
    }
}
console.log(relatorio)