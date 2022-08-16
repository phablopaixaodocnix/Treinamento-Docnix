// Declarações Dados Principais
let nome = document.getElementById('nomePrincipal')
let email = document.getElementById("emailPrincipal")
let cpf = document.getElementById("cpfPrincipal")
let cep = document.getElementById("cep")
let rua = document.getElementById("rua")
let estado = document.getElementById("estado")
let select = document.getElementById("select")

let contadorBotaoEditar = 0;
let idAux;
let valorBtnContato = 0;
let indexAux;
let index = 0;

//Declarações Contato Principal
let emailContato = document.getElementById("emailContatoPrincipal")
let nomeContato = document.getElementById("nomeContatoPrincipal")
let numeroContato = document.getElementById("numeroContatoPrincipal")



// Tabela

var tableForm = document.getElementById("table-form")
var dadoID = document.getElementsByClassName("acrescentarDadoID")
var dadoNome = document.getElementsByClassName("acrescentarDadoNome")
var dadoEmail = document.getElementsByClassName("acrescentarDadoEmail")
let contadorButtonExcluir = 0
const buttonsExcluir = document.getElementsByClassName('buttonExcluirLinha')
const buttonsEditar = document.getElementsByClassName('buttonEditarLinha')
let auxAlterar = 0


// Mascara para campos
$(document).ready(function () {
    $('#numeroContatoPrincipal').mask('(00) 00000-0000')
    $('#cep').mask('00000-000')
    $('#cpfPrincipal').mask('000.000.000-00')
});

// Validar Numero
var validarNumero = "0123456789"
let aux = 0

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

var dadosContatos = []

var ctdID = 0


// Submit

Formulario.addEventListener("submit", (e) => {
    e.preventDefault();


    // Declarações Dados Principais
    let nomePrincipal = document.getElementById('nomePrincipal').value
    let emailPrincipal = document.getElementById("emailPrincipal").value
    let cpfPrincipal = document.getElementById("cpfPrincipal").value
    let cepPrincipal = document.getElementById("cep").value
    let ruaPrincipal = document.getElementById("rua").value
    let estadoPrincipal = document.getElementById("estado").value

    //Declarações Contato Principal
    let emailContatoPrincipal = document.getElementById("emailContatoPrincipal").value
    let nomeContatoPrincipal = document.getElementById("nomeContatoPrincipal").value
    let numeroContatoPrincipal = document.getElementById("numeroContatoPrincipal").value

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

        //Pegar valor sem mascara
        if (isNaN(cpfPrincipal)) {
            $("#cpfPrincipal").unmask();
            cpfPrincipal = document.getElementById("cpfPrincipal").value
            $('#cpfPrincipal').mask('000.000.000-00')
        }

        let contadorAux = 10
        let calc = 0

        if (cpfPrincipal == "") {
            validacaoCPF.style.display = "block"
            validacaoCPF.innerHTML = "Campo Obrigatório"
        }
        else {
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
    }

    // Função Validar Numeros

    let validarNumeros = () => {
        if (numeroContatoPrincipal == "") {
            validacaoNumeroContato.style.display = "block"
            validacaoNumeroContato.innerHTML = "Campo Obrigatório"
        }
        else {
            validacaoNumeroContato.style.display = "none"
        }
    }

    // Função Gravar Dados no Objeto
    let gravarDadosP = () => {
        if (auxAlterar == 0) {
            let arrayInformacoesP = {}
            arrayInformacoesP.id = ctdID
            arrayInformacoesP.nome = nomePrincipal
            arrayInformacoesP.cpf = cpfPrincipal
            arrayInformacoesP.email = emailPrincipal
            arrayInformacoesP.cep = cepPrincipal
            arrayInformacoesP.rua = ruaPrincipal
            arrayInformacoesP.estado = estadoPrincipal
            arrayInformacoesP.formacao = select.options[select.selectedIndex].value;
            dadosPrincipais.push(arrayInformacoesP)
        }
    }

    // Função Gravar Dados Alterados
    let AlterarTabelaEArray = () => {
        if (auxAlterar == 1) {
            for (let j = 0; j < divIndex.length;) {
                for (let i = 0; i < dadosContatos.length; i++) {
                    if (idAux == dadosContatos[i].idDadosPrincipais) {
                        dadosContatos[i].nome = divIndex[j].children[1].value
                        dadosContatos[i].email = divIndex[j].children[2].value
                        dadosContatos[i].numero = divIndex[j].children[3].value
                        j++
                    }
                }
            }
            for (let i = 0; i < dadosPrincipais.length; i++) {
                if (idAux == dadosPrincipais[i].id) {

                    dadoID[idAux].innerHTML = idAux
                    dadosPrincipais[idAux].id = idAux
                    dadoEmail[idAux].innerHTML = email.value
                    dadosPrincipais[idAux].email = email.value
                    dadoNome[idAux].innerHTML = nome.value
                    dadosPrincipais[idAux].nome = nome.value
                    idAux = undefined
                    limparCampos()
                }
            }
        }
    }

    // Função Gravar Dados Contatos

    let gravarDadosC = () => {
        if (valorBtnContato == 0) {
            alert("Mínimo de 2 Contatos")
        }
        else {
            if (auxAlterar == 0) {
                let arrayInformacoesC = {}
                for (let i = 0; i < divIndex.length; i++) {
                    arrayInformacoesC.nomeContatoP = nomeContatoPrincipal
                    arrayInformacoesC.emailContatoP = emailContatoPrincipal
                    arrayInformacoesC.numeroContatoP = numeroContatoPrincipal
                    arrayInformacoesC.idDadosPrincipais = ctdID
                    arrayInformacoesC.idContato = contadorContatos - 1
                    arrayInformacoesC.nome = divIndex[i].children[1].value
                    arrayInformacoesC.email = divIndex[i].children[2].value
                    arrayInformacoesC.numero = divIndex[i].children[3].value
                    dadosContatos.push(arrayInformacoesC)
                    arrayInformacoesC = {}
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
    AlterarTabelaEArray()

    if (
        validacaoNome.style.display == 'none' && validacaoEmail.style.display == 'none'
        && validacaoCPF.style.display == 'none' && validacaoEmailContato.style.display == 'none'
        && validacaoNomeContato.style.display == 'none' && validacaoNumeroContato.style.display == 'none' &&
        auxAlterar == 0 && valorBtnContato != 0) {
        gravarDadosP()
        gravarDadosC()
        gravarTabela()
    }

    auxAlterar = 0
    btnNovoContato[0].style.display = "flex"

})

// Função Alterar

let editarLinha = () => {
    $(document).on('click', '.buttonEditarLinha', function () {
        index = $(this).attr("data-index")

        // Colocar Dados da Tabela no Input
        nome.value = dadosPrincipais[index].nome
        email.value = dadosPrincipais[index].email
        cpf.value = dadosPrincipais[index].cpf
        cep.value = dadosPrincipais[index].cep
        rua.value = dadosPrincipais[index].rua
        estado.value = dadosPrincipais[index].estado
        select.value = dadosPrincipais[index].formacao
        emailContato.value = dadosContatos[index].emailContatoP
        nomeContato.value = dadosContatos[index].nomeContatoP
        numeroContato.value = dadosContatos[index].numeroContatoP
        idAux = dadosPrincipais[index].id
        indexAux = index
        auxAlterar = 1

        for (let j = 0; j < (dadosContatos[index].idContato + 1);) {
            for (let i = 0; i < dadosContatos.length; i++) {
                if (idAux == dadosContatos[i].idDadosPrincipais) {
                    divIndex[j].children[1].value = dadosContatos[i].nome
                    divIndex[j].children[2].value = dadosContatos[i].email
                    divIndex[j].children[3].value = dadosContatos[i].numero
                    j++
                }
            }
        }
        console.log(dadosContatos)
    });
    btnNovoContato[0].style.display = "none"
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
        editar.setAttribute('onclick', 'editarLinha()')
        editar.setAttribute('data-index', contadorBotaoEditar)
        editar.innerHTML = 'Editar'

        // Excluir
        var acrescentarButtonExcluir = document.createElement('td')
        acrescentarButtonExcluir.setAttribute('class', 'tdAttribute')
        var excluir = document.createElement('button')
        excluir.setAttribute('id', 'buttonExcluir')
        excluir.setAttribute('data-index', contadorButtonExcluir)
        excluir.setAttribute('class', 'buttonExcluirLinha')
        excluir.setAttribute('onclick', 'excluirLinha()')
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
    contadorBotaoEditar++
    ctdID++
    limparCampos()
}

// Função Excluir Linha

let excluirLinha = () => {
    $(document).on('click', '.buttonExcluirLinha', function () {
        dadosPrincipais.splice($(this).attr("data-index"), 1)
        $(this).parent().parent().remove();
        for (let i = 0; i < buttonsEditar.length; i++) {
            buttonsEditar[i].setAttribute("data-index", i)
        }
        contadorBotaoEditar = buttonsEditar.length
    });
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
    novoContatoNome.setAttribute("placeholder", "Nome")
    novoContatoNome.setAttribute("data-name", "Nome")
    novaDiv.appendChild(novoContatoNome)

    // Input E-mail

    let novoContatoEmail = document.createElement('input')
    novoContatoEmail.setAttribute("type", "text")
    novoContatoEmail.setAttribute("placeholder", "Email")
    novoContatoEmail.setAttribute("data-name", "Email")
    novaDiv.appendChild(novoContatoEmail)

    // Input Numero

    let novoContatoNumero = document.createElement('input')
    novoContatoNumero.setAttribute("type", "text")
    novoContatoNumero.setAttribute("placeholder", "Numero de Telefone")
    novoContatoNumero.setAttribute("data-name", "Numero")
    novaDiv.appendChild(novoContatoNumero)
    valorBtnContato++


}

// Função para limpar campos

let limparCampos = () => {
    nome.value = ""
    email.value = ""
    cpf.value = ""
    cep.value = ""
    rua.value = ""
    estado.value = ""
    select.value = "Default"
    emailContato.value = ""
    nomeContato.value = ""
    numeroContato.value = ""

    for (let j = 0; j < divIndex.length; j++) {
        divIndex[j].children[1].value = ""
        divIndex[j].children[2].value = ""
        divIndex[j].children[3].value = ""
    }
}