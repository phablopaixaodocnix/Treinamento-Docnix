//Contatos
var contatosAlternativos = document.getElementById("contatosAlternativos")
var contadorContatos = 0
// Mensagem de Validação

const validacaoNome = document.getElementById("validacao-nome")
const validacaoEmail = document.getElementById("validacao-email")
const validacaoCPF = document.getElementById("validacao-cpf")
const validacaoEmailContato = document.getElementById("validacaoEmailContato")
const validacaoNomeContato = document.getElementById("validacaoNomeContato")

// Formulario

const Formulario = document.getElementById("form-cadastro")

// Adicionar Novo Contato
const btnNovoContato = document.getElementById("btnNovoContato")

// Submit

Formulario.addEventListener("submit", (e) => {
    e.preventDefault();

    // Declarações

    var nomePrincipal = document.getElementById('nomePrincipal').value
    var emailPrincipal = document.getElementById("emailPrincipal").value
    var cpfPrincipal = document.getElementById("cpfPrincipal").value
    var emailContatoPrincipal = document.getElementById("emailContatoPrincipal").value
    var nomeContatoPrincipal = document.getElementById("nomeContatoPrincipal").value


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
        else if (cpfPrincipal.length == 11) {
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
        else {
            validacaoCPF.style.display = "block"
            validacaoCPF.innerHTML = "CPF Incorreto"
        }
    }

    // Função Validar Numero

    let regexNumero = [(][0-9]{2}[)][9][0-9]{4}[-][0-9]{4}


    // Funções Globais

    let validacaoDadosPrincipais = () => {
        validarNome()
        validarEmail()
        validarCPF()
    }

    let validacaoContatoPrincipal = () => {
        validarNome()
        validarEmail()
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

    //Input Name

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
