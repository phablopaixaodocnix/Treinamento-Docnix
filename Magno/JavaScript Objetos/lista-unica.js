const clientes = [
    {
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
            parentesco: "filha"
            }],
        },
        {
        nome: "raissa",
        idade: 21,
        cpf: "67312098452",
        email: "raiss@newmail.com",
        fones: ["347029457240", "1934710293841"],
        dependentes: [
            {
            nome: "andressa suita",
            dataDeNascimento: "14/03/2006",
            parentesco: "filha"},
        ]
    },
    
    
]

const listaDependentes = [...clientes[0].dependentes,...clientes[1].dependentes]

console.table(listaDependentes)