// Declarações Dados Principais
let nomePrincipal = document.getElementById('nomePrincipal')
let emailPrincipal = document.getElementById("emailPrincipal")
let cpfPrincipal = document.getElementById("cpfPrincipal")
let cepPrincipal = document.getElementById("cep")
let ruaPrincipal = document.getElementById("rua")
let estadoPrincipal = document.getElementById("estado")
let emailContatoPrincipal = document.getElementById("emailContatoPrincipal")
let nomeContatoPrincipal = document.getElementById("nomeContatoPrincipal")
let numeroContatoPrincipal = document.getElementById("numeroContatoPrincipal")
let contatosAlternativos = document.getElementById("contatosAlternativos")
let select = document.getElementById("select")


let contadorButtonEditar = 0;
let idAux = -1;
let valorBtnContato = 0;
let index = 0;
let ctdAuxId = 0
let aux = 0




// Tabela

var tableForm = document.getElementById("table-form")
var dadoID = document.getElementsByClassName("acrescentarDadoID")
var dadoNome = document.getElementsByClassName("acrescentarDadoNome")
var dadoEmail = document.getElementsByClassName("acrescentarDadoEmail")
let contadorButtonExcluir = 0
const buttonsExcluir = document.getElementsByClassName('buttonExcluirLinha')
const buttonsEditar = document.getElementsByClassName('buttonEditarLinha')
let auxAlterar = 0



$(document).ready(function () {
    $('#numeroContatoPrincipal').mask('(00) 00000-0000')
    $('#cep').mask('00000-000')
    $('#cpfPrincipal').mask('000.000.000-00')
});

$(document).on('click', '.buttonEditarLinha', function () {
    index = $(this).attr("data-index")
    editarLinha(index)
});
$(document).on('click', '.buttonExcluirLinha', function () {
    dadosPrincipais.splice($(this).attr("data-index"), 1)
    $(this).parent().parent().remove();
    excluirLinha()
});



//Contatos

var divIndex = document.getElementsByClassName("divIndex")
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
const btnNovoContato = document.getElementsByClassName("btn")



//Array de Dados

var dadosPrincipais = []

var ctdID = 0

// Função Remover Campos Dinamicos

let removerCampos = () => {
    while (contatosAlternativos.firstChild) {
        contatosAlternativos.removeChild(contatosAlternativos.firstChild);
    }
}


// Submit

Formulario.addEventListener("submit", (e) => {
    e.preventDefault();

    // Declarações Dados Principais
    let nome = nomePrincipal.value
    let email = emailPrincipal.value
    let cpf = cpfPrincipal.value
    let cep = cepPrincipal.value
    let rua = ruaPrincipal.value
    let estado = estadoPrincipal.value
    let emailContato = emailContatoPrincipal.value
    let nomeContato = nomeContatoPrincipal.value
    let numeroContato = numeroContatoPrincipal.value

    // Função Validar Nome
    let validarNome = () => {
        if (nome == "") {

            validacaoNome.style.display = "block"

        }
        else {
            validacaoNome.style.display = "none"

        }

        // Contato Principal (Nome)
        if (nomeContato == "") {

            validacaoNomeContato.style.display = "block"

        }
        else {
            validacaoNomeContato.style.display = "none"

        }
    }

    // Função Validar E-Mail
    let validarEmail = () => {


        // Dados Principais (E-mail)
        if (email == "") {
            validacaoEmail.style.display = "block"
            validacaoEmail.innerHTML = "Campo Obrigatório"
        }
        else {
            validacaoEmail.style.display = "none"
            if (email.includes('@') && email.includes('.com')) {
                while (email.indexOf('@') > email.indexOf('.com')) {
                    email = email.replace(".com", "test")
                }
            }
            else {
                validacaoEmail.style.display = "block"
                validacaoEmail.innerHTML = "E-mail Inválido"
            }
        }

        //Contato Principal (E-mail)

        if (emailContato == "") {
            validacaoEmailContato.style.display = "block"
            validacaoEmailContato.innerHTML = "Campo Obrigatório"
        }
        else {
            validacaoEmailContato.style.display = "none"
            if (emailContato.includes('@') && emailContato.includes('.com')) {
                while (emailContato.indexOf('@') > emailContato.indexOf('.com')) {
                    emailContato = emailContato.replace(".com", "test")
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

        //Pegar valor sem mascara
        if (isNaN(cpf)) {
            $("#cpfPrincipal").unmask();
            cpf = document.getElementById("cpfPrincipal").value
            $('#cpfPrincipal').mask('000.000.000-00')
        }

        let contadorAux = 10
        let calc = 0

        if (cpf == "") {
            validacaoCPF.style.display = "block"
            validacaoCPF.innerHTML = "Campo Obrigatório"
        }
        else {
            // Calculando Primeiro Digito Verificador
            for (let i = 0; i < 9; i++) {
                calc += cpf[i] * contadorAux
                contadorAux--
            }
            calc = (calc * 10) % 11

            // Se calc for igual a primeiro digito verificador, primeiro digito valido
            if (calc >= 10) calc = 0
            if (calc == cpf[9]) {
                //Calculando Segundo Digito Verificador
                contadorAux = 11
                calc = 0
                for (let i = 0; i < 10; i++) {
                    calc += cpf[i] * contadorAux
                    contadorAux--
                }
                calc = (calc * 10) % 11

                if (calc == cpf[10]) {
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
    }

    // Função Validar Numeros

    let validarNumeros = () => {
        if (numeroContato == "") {
            validacaoNumeroContato.style.display = "block"
            validacaoNumeroContato.innerHTML = "Campo Obrigatório"
        }
        else {
            validacaoNumeroContato.style.display = "none"
        }
    }

    // Função Gravar Dados
    let gravarDados = () => {
        if (auxAlterar === 0) {
            if (valorBtnContato === 0) {
                alert("Mínimo de 2 Contatos")
            }

            else {

                var contatoInformacoesPrincipais = {}
                let contatoInformacoesDinamicas = []
                let objetoInformacoesDinamicas = {}

                contatoInformacoesPrincipais.id = ctdAuxId
                contatoInformacoesPrincipais.idFixo = ctdID
                contatoInformacoesPrincipais.nome = nome
                contatoInformacoesPrincipais.cpf = cpf
                contatoInformacoesPrincipais.email = email
                contatoInformacoesPrincipais.cep = cep
                contatoInformacoesPrincipais.rua = rua
                contatoInformacoesPrincipais.estado = estado
                contatoInformacoesPrincipais.formacao = select.options[select.selectedIndex].value;
                contatoInformacoesPrincipais.nomeContato = nomeContato
                contatoInformacoesPrincipais.emailContato = emailContato
                contatoInformacoesPrincipais.numeroContato = numeroContato

                for (let i = 0; i < divIndex.length; i++) {
                    objetoInformacoesDinamicas.idContato = contadorContatos - 1
                    objetoInformacoesDinamicas.nome = divIndex[i].children[1].value
                    objetoInformacoesDinamicas.email = divIndex[i].children[2].value
                    objetoInformacoesDinamicas.numero = divIndex[i].children[3].value
                    contatoInformacoesDinamicas.push(objetoInformacoesDinamicas)
                    objetoInformacoesDinamicas = {}
                }

                contatoInformacoesPrincipais.contatos = contatoInformacoesDinamicas
                dadosPrincipais.push(contatoInformacoesPrincipais)
                ctdID++
                ctdAuxId++
                gravarTabela()
            }
        }
    }

    // Função Gravar Dados Alterados
    let alterarTabelaEArray = () => {
        if (auxAlterar == 1) {
            for (let i = 0; i < dadosPrincipais[idAux].contatos.length; i++) {
                dadosPrincipais[idAux].contatos[i].nome = divIndex[i].children[1].value
                dadosPrincipais[idAux].contatos[i].email = divIndex[i].children[2].value
                dadosPrincipais[idAux].contatos[i].numero = divIndex[i].children[3].value
            }
            for (let i = 0; i < dadosPrincipais.length; i++) {
                if (idAux == dadosPrincipais[i].id) {
                    dadosPrincipais[idAux].idFixo = idAux
                    dadoEmail[idAux].innerHTML = email
                    dadosPrincipais[idAux].email = email
                    dadoNome[idAux].innerHTML = nome
                    dadosPrincipais[idAux].nome = nome
                    dadosPrincipais[idAux].nomeContato = nomeContato
                    dadosPrincipais[idAux].emailContato = emailContato
                    dadosPrincipais[idAux].numeroContato = numeroContato
                    idAux = undefined
                    limparCampos()
                }
            }
        }
    }



    // Chamada de Funções

    let validacaoDadosPrincipais = () => {
        validarNome()
        validarEmail()
        validarCPF()

    }

    let validacaoContatoPrincipal = () => {
        validarNome()
        validarEmail()
        validarNumeros()
    }
    validacaoDadosPrincipais()
    validacaoContatoPrincipal()
    if (
        validacaoNome.style.display == 'none' && validacaoEmail.style.display == 'none'
        && validacaoCPF.style.display == 'none' && validacaoEmailContato.style.display == 'none'
        && validacaoNomeContato.style.display == 'none' && validacaoNumeroContato.style.display == 'none' &&
        auxAlterar == 0) {
        gravarDados()
    }
    alterarTabelaEArray()


    removerCampos()
    auxAlterar = 0
    contadorContatos = 0
    btnNovoContato[0].style.display = "flex"
})

// // Função Alterar

let editarLinha = (idx) => {
    limparCampos()
    removerCampos()
    contadorContatos = 0

    // Colocar Dados da Tabela no Input
    nomePrincipal.value = dadosPrincipais[idx].nome
    emailPrincipal.value = dadosPrincipais[idx].email
    cpfPrincipal.value = dadosPrincipais[idx].cpf
    cepPrincipal.value = dadosPrincipais[idx].cep
    ruaPrincipal.value = dadosPrincipais[idx].rua
    estadoPrincipal.value = dadosPrincipais[idx].estado
    select.value = dadosPrincipais[idx].formacao
    emailContatoPrincipal.value = dadosPrincipais[idx].emailContato
    nomeContatoPrincipal.value = dadosPrincipais[idx].nomeContato
    numeroContatoPrincipal.value = dadosPrincipais[idx].numeroContato
    idAux = idx

    for (let i = 0; i < dadosPrincipais[index].contatos.length; i++) {
        criarContato()
        divIndex[i].children[1].value = dadosPrincipais[index].contatos[i].nome
        divIndex[i].children[2].value = dadosPrincipais[index].contatos[i].email
        divIndex[i].children[3].value = dadosPrincipais[index].contatos[i].numero
    }

    auxAlterar = 1
    btnNovoContato[0].style.display = "none"
}

// Função Excluir Linha

let excluirLinha = () => {
    for (let i = 0; i < buttonsEditar.length; i++) {
        buttonsEditar[i].setAttribute("data-index", i)
    }
    for (let i = 0; i < buttonsExcluir.length; i++) {
        buttonsExcluir[i].setAttribute("data-index", i)
    }
    contadorButtonEditar = buttonsEditar.length
    contadorBotaoExcluir = buttonsExcluir.length
    for (let i = 0; i < dadosPrincipais.length; i++) {
        dadosPrincipais[i].id = i
        dadoID[i].innerHTML = dadosPrincipais[i].id
    }
    ctdAuxId = dadosPrincipais.length
    limparCampos()
    removerCampos()
    btnNovoContato[0].style.display = "flex"
    auxAlterar = 0
    contadorContatos = 0
}


// Função Gravar dados na Tabela
let gravarTabela = () => {
    
    var novaLinha = document.createElement('tr')
    novaLinha.setAttribute("id", "novaLinhaTR")
    tableForm.appendChild(novaLinha)
    for (let i = 0; i < dadosPrincipais.length; i++) {
        // ID
        var acrescentarDadoID = document.createElement('td')
        acrescentarDadoID.setAttribute('class', 'acrescentarDadoID')
        acrescentarDadoID.innerHTML = dadosPrincipais[i].id
        // Nome
        var acrescentarDadoNome = document.createElement('td')
        acrescentarDadoNome.setAttribute('class', 'acrescentarDadoNome')
        acrescentarDadoNome.innerHTML = dadosPrincipais[i].nome
        // E-mail
        var acrescentarDadoEmail = document.createElement('td')
        acrescentarDadoEmail.setAttribute('class', 'acrescentarDadoEmail')
        acrescentarDadoEmail.innerHTML = dadosPrincipais[i].email


        // Editar

        var acrescentarButtonEditar = document.createElement('td')
        acrescentarButtonEditar.setAttribute('class', 'tdAttribute')
        var editar = document.createElement('button')
        editar.setAttribute('id', 'buttonEditar')
        editar.setAttribute('class', 'buttonEditarLinha')
        editar.setAttribute('data-index', contadorButtonEditar)
        editar.innerHTML = 'Editar'

        // Excluir
        var acrescentarButtonExcluir = document.createElement('td')
        acrescentarButtonExcluir.setAttribute('class', 'tdAttribute')
        var excluir = document.createElement('button')
        excluir.setAttribute('id', 'buttonExcluir')
        excluir.setAttribute('data-index', contadorButtonExcluir)
        excluir.setAttribute('class', 'buttonExcluirLinha')
        excluir.innerHTML = 'Excluir'

    }
    novaLinha.appendChild(acrescentarDadoID)
    novaLinha.appendChild(acrescentarDadoNome)
    novaLinha.appendChild(acrescentarDadoEmail)
    novaLinha.appendChild(acrescentarButtonEditar)
    acrescentarButtonEditar.appendChild(editar)
    novaLinha.appendChild(acrescentarButtonExcluir)
    acrescentarButtonExcluir.appendChild(excluir)
    contadorButtonExcluir++
    contadorButtonEditar++
    limparCampos()
}


// Função Criar Contato Alternativo
let criarContato = () => {

    // Criar DIV
    let novaDiv = document.createElement('div')
    novaDiv.setAttribute("class", "divIndex")
    contatosAlternativos.appendChild(novaDiv)

    // Titulo
    let novoContatoTitulo = document.createElement('p')
    novoContatoTitulo.innerHTML = "Contato Alternativo " + contadorContatos
    novaDiv.appendChild(novoContatoTitulo)
    contadorContatos++

    //Input Nome

    let novoContatoNome = document.createElement('input')
    novoContatoNome.setAttribute("type", "text")
    novoContatoNome.setAttribute("required", '')
    novoContatoNome.setAttribute("placeholder", "Nome *")
    novoContatoNome.setAttribute("data-name", "Nome")
    novaDiv.appendChild(novoContatoNome)

    // Input E-mail

    let novoContatoEmail = document.createElement('input')
    novoContatoEmail.setAttribute("type", "text")
    novoContatoEmail.setAttribute("required", '')
    novoContatoEmail.setAttribute("placeholder", "Email *")
    novoContatoEmail.setAttribute("data-name", "Email")
    novaDiv.appendChild(novoContatoEmail)

    // Input Numero

    let novoContatoNumero = document.createElement('input')
    novoContatoNumero.setAttribute("type", "text")
    novoContatoNumero.setAttribute("class", "numeroAlternativo")
    novoContatoNumero.setAttribute("required", '')
    novoContatoNumero.setAttribute("placeholder", "Numero de Telefone *")
    novoContatoNumero.setAttribute("data-name", "Numero")
    novaDiv.appendChild(novoContatoNumero)

    // Aplicando Mascara no Numero
    $(document).ready(function () {
        $('.numeroAlternativo').mask('(00) 00000-0000')
    });

    valorBtnContato++
}

// Função para limpar campos

let limparCampos = () => {
    nomePrincipal.value = ""
    emailPrincipal.value = ""
    cpfPrincipal.value = ""
    cepPrincipal.value = ""
    ruaPrincipal.value = ""
    estadoPrincipal.value = ""
    select.value = "Default"
    emailContatoPrincipal.value = ""
    nomeContatoPrincipal.value = ""
    numeroContatoPrincipal.value = ""

    for (let j = 0; j < divIndex.length; j++) {
        divIndex[j].children[1].value = ""
        divIndex[j].children[2].value = ""
        divIndex[j].children[3].value = ""
    }
}