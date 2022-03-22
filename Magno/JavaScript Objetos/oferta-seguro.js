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

function oferecerSeguro(obj){
    const propClientes = Object.keys(obj)
    if(propClientes.includes("dependentes")){
        console.log(`Oferta de seguro para ${obj.nome}`)
    }
}
oferecerSeguro(cliente)
console.log(Object.values(cliente))
console.log(Object.entries(cliente))
