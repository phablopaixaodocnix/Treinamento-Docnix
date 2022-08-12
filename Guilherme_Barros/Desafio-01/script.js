// Validar Numero
var validarNumero = "0123456789"
let aux = 0

//Contatos

var contatosAlternativos = document.getElementById("contatosAlternativos")
var contadorContatos = 0

// Mensagem de Validação

const validacaoNome = document.getElementById("validacao-nome")
const validacaoEmail = document.getElementById("validacao-email")
const validacaoCPF = document.getElementById("validacao-cpf")
const validacaoEmailContato = document.getElementById("validacaoEmailContato")
const validacaoNomeContato = document.getElementById("validacaoNomeContato")
const validacaoNumeroContato = document.getElementById("validacaoNumeroContato")

// Formulario

const Formulario = document.getElementById("form-cadastro")

// Adicionar Novo Contato
const btnNovoContato = document.getElementById("btnNovoContato")

// Submit

Formulario.addEventListener("submit", (e) => {
    e.preventDefault();

    // Declarações Dados Principais

    var nomePrincipal = document.getElementById('nomePrincipal').value
    var emailPrincipal = document.getElementById("emailPrincipal").value
    var cpfPrincipal = document.getElementById("cpfPrincipal").value

    //Declarações Contato Principal
    var emailContatoPrincipal = document.getElementById("emailContatoPrincipal").value
    var nomeContatoPrincipal = document.getElementById("nomeContatoPrincipal").value
    var numeroContatoPrincipal = document.getElementById("numeroContatoPrincipal").value




    // Função Validar Nome
    let validarNome = () => {

        // Dados Principais (Nome)
        if (nomePrincipal == "") {

            validacaoNome.style.display = "block"

        }
        else {
            validacaoNome.style.display = "none"

        }

        // Contato Principal (Nome)

        if (nomeContatoPrincipal == "") {

            validacaoNomeContato.style.display = "block"

        }
        else {
            validacaoNomeContato.style.display = "none"

        }
    }

    // Função Validar E-Mail
    let validarEmail = () => {


        // Dados Principais (E-mail)
        if (emailPrincipal == "") {
            validacaoEmail.style.display = "block"
            validacaoEmail.innerHTML = "Campo Obrigatório"
        }
        else {
            validacaoEmail.style.display = "none"
            if (emailPrincipal.includes('@') && emailPrincipal.includes('.com')) {
                while (emailPrincipal.indexOf('@') > emailPrincipal.indexOf('.com')) {
                    emailPrincipal = emailPrincipal.replace(".com", "test")
                }
            }
            else {
                validacaoEmail.style.display = "block"
                validacaoEmail.innerHTML = "E-mail Inválido"
            }
        }

        //Contato Principal (E-mail)

        if (emailContatoPrincipal == "") {
            validacaoEmailContato.style.display = "block"
            validacaoEmailContato.innerHTML = "Campo Obrigatório"
        }
        else {
            validacaoEmailContato.style.display = "none"
            if (emailContatoPrincipal.includes('@') && emailContatoPrincipal.includes('.com')) {
                while (emailContatoPrincipal.indexOf('@') > emailContatoPrincipal.indexOf('.com')) {
                    emailContatoPrincipal = emailContatoPrincipal.replace(".com", "test")
                }
            }
            else {
                validacaoEmailContato.style.display = "block"
                validacaoEmailContato.innerHTML = "E-mail Inválido"
            }
        }
    }

    // Função Validar CPF
    let validarCPF = () => {
        let contadorAux = 10
        let calc = 0

        if (cpfPrincipal == "") {
            validacaoCPF.style.display = "block"
            validacaoCPF.innerHTML = "Campo Obrigatório"
        }
        else
            // Calculando Primeiro Digito Verificador
            for (let i = 0; i < 9; i++) {
                calc += cpfPrincipal[i] * contadorAux
                contadorAux--
            }
        calc = (calc * 10) % 11

        // Se calc for igual a primeiro digito verificador, primeiro digito valido
        if (calc >= 10) calc = 0
        if (calc == cpfPrincipal[9]) {
            //Calculando Segundo Digito Verificador
            contadorAux = 11
            calc = 0
            for (let i = 0; i < 10; i++) {
                calc += cpfPrincipal[i] * contadorAux
                contadorAux--
            }
            calc = (calc * 10) % 11

            if (calc == cpfPrincipal[10]) {
                validacaoCPF.style.display = "none"
            }
            else {
                validacaoCPF.style.display = "block"
                validacaoCPF.innerHTML = "CPF Incorreto"
            }
        }
        else {
            validacaoCPF.style.display = "block"
            validacaoCPF.innerHTML = "CPF Incorreto"
        }
    }

    // Função Validar Numero

    let validarNumero = ()=>{
        if(numeroContatoPrincipal == ""){
            validacaoNumeroContato.style.display = "block"
            validacaoNumeroContato.innerHTML = "Campo Obrigatório"
        }
        else{
            validacaoNumeroContato.style.display = "none"
        }
    }


    // Funções Globais

    let validacaoDadosPrincipais = () => {
        validarNome()
        validarEmail()
        validarCPF()

    }

    let validacaoContatoPrincipal = () => {
        validarNome()
        validarEmail()
        validarNumero()
    }

    validacaoDadosPrincipais()
    validacaoContatoPrincipal()

})




// Função Criar Contato Alternativo
let criarContato = () => {

    // Titulo
    let novoContatoTitulo = document.createElement('p')
    novoContatoTitulo.innerHTML = "Contato Alternativo " + contadorContatos
    contatosAlternativos.appendChild(novoContatoTitulo)
    contadorContatos++

    //Input Nome

    let novoContatoNome = document.createElement('input')
    novoContatoNome.setAttribute("type", "text")
    novoContatoNome.setAttribute("placeholder", "Nome")
    contatosAlternativos.appendChild(novoContatoNome)

    // Input E-mail

    let novoContatoEmail = document.createElement('input')
    novoContatoEmail.setAttribute("type", "text")
    novoContatoEmail.setAttribute("placeholder", "Email")
    contatosAlternativos.appendChild(novoContatoEmail)

    // Input Numero

    let novoContatoNumero = document.createElement('input')
    novoContatoNumero.setAttribute("type", "text")
    novoContatoNumero.setAttribute("placeholder", "Numero de Telefone")
    contatosAlternativos.appendChild(novoContatoNumero)

}

// Mascara para campos

$(document).ready(function () {
    $('#numeroContatoPrincipal').mask('(00) 00000-0000');
    $('#maskCep').mask('00000-00')
    $('#cpfPrincipal').mask('000.000.000-00')
});