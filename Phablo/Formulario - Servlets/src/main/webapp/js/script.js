'use strict';
const urlBase = "http://localhost:8080/FormularioServlets/";
const urlEnviarFormulario = urlBase + 'AdicionarFormulario';
const urlListarFormularios = urlBase + 'ListarTodosFormularios';
const urlExcluirFormulario = urlBase + 'ExcluirFormulario';
const urlEditarFormulario = urlBase + 'EditarFormulario';

async function enviarFormulario(formulario,url){
  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "aplication/json;charset=UTF-8"
    },
    body: JSON.stringify(formulario)
  }
  await fetch(url,requestOptions);
}

let idFormularioAserEditado;
async function editarFormulario(formulario,url){
  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "aplication/json;charset=UTF-8"
    },
    body: JSON.stringify(formulario)
  }
  await fetch(url,requestOptions);
}

let formularios;
async function listarTodosFormularios(url) {
  const requestOptions = {
    method: 'GET',
    headers: {
      'Content-Type': 'aplication/json;charset=UTF-8'
    }
  };

  let response = await fetch(url, requestOptions);
  let data = await response.json();
  return data;
}
function construirATabela() {
  listarTodosFormularios(urlListarFormularios).then(function (result) {
    formularios = result;
    criarATabela(formularios);
  });
}
construirATabela();

async function excluirFormulario(id,url){
  const requestOptions = {
    method: "POST",
    body: JSON.stringify(id)
  }
  await fetch(url,requestOptions);
}
  


let quantidadeDeFormulariosNaTabela=0;
let nomeInput = document.getElementById('nome');
let emailInput = document.getElementById('e-mail');
let cpfInput = document.getElementById('cpf');
let cidadeInput = document.getElementById('cidade');
let bairroInput = document.getElementById('bairro');
let ruaInput = document.getElementById('rua');
let quadraInput = document.getElementById('quadra');
let casaInput = document.getElementById('casa');
let cepInput = document.getElementById('cep');
let loteInput = document.getElementById('lote');
let numeroInput = document.getElementById('numero');
let ufSelect = document.getElementById('uf');
let escolaridadeSelect = document.getElementById('escolaridade');
let contatoNomeInput = document.getElementById('contato-nome1');
let contatoTelefoneInput = document.getElementById('contato-telefone1');
let contatoEmailInput = document.getElementById('contato-email1');
const submitBtn = document.getElementById('submit');
let excluirBtns = document.querySelectorAll('.excluir');
let editarBtns = document.querySelectorAll('.editar');
let allInputs = document.querySelectorAll('.input');
let form = document.getElementById('form');

permitir_apenas_numeros_e_backspace_em_um_input(cpfInput);
permitir_apenas_numeros_e_backspace_em_um_input(quadraInput);
permitir_apenas_numeros_e_backspace_em_um_input(loteInput);
permitir_apenas_numeros_e_backspace_em_um_input(casaInput);
permitir_apenas_numeros_e_backspace_em_um_input(numeroInput);
permitir_apenas_numeros_e_backspace_em_um_input(contatoTelefoneInput);
permitir_apenas_numeros_e_backspace_em_um_input(cep);
let enviarOuEditar = 'enviar';



//enviar ou editar formulario
let quantidadeDeContatos = 1;
form.addEventListener("submit",function(e){
    e.preventDefault();

    const formData = new FormData(form);
    let formulario;
    if (quantidadeDeContatos < 2){
      alert('Informe pelo ao menos 2 contatos');
    }
    else if (cpfIsValid(formData.get('cpf'))){

      async function enviarFormularioParaOBackendEReconstruirATabela(){
        formulario = getFormularioObjAPartirDoFormData(formData);
        resetarInputsFormulario(form);
        if (enviarOuEditar === 'editar'){
          formulario.idFormulario = idFormularioAserEditado;
          await editarFormulario(formulario,urlEditarFormulario);
          enviarOuEditar = 'enviar';
        } else{
          await enviarFormulario(formulario,urlEnviarFormulario);
        }
        reconstruirTabela();
      }
      enviarFormularioParaOBackendEReconstruirATabela();

    }
});
function reconstruirTabela(){
  limparTabela();
  construirATabela();
}
function getFormularioObjAPartirDoFormData(formData){
  const formulario = {
    nome: formData.get('nome'),
    email: formData.get('email'),
    cpf: formData.get('cpf'),
    escolaridade: formData.get('escolaridade'),
    endereco:{
      cidade: formData.get('cidade'),
      bairro: formData.get('bairro'),
      rua: formData.get('rua'),
      quadra: formData.get('quadra'),
      lote: formData.get('lote'),
      casa: formData.get('casa'),
      numero: formData.get('numero'),
      cep: formData.get('cep'),
      uf: formData.get('uf')
    },
    contatos:[
    ]
  };
  let k=1;
  for(;k<=quantidadeDeContatos;k++){
    const contato = {
      nome: formData.get(`contato-nome${k}`),
      telefone: formData.get(`contato-telefone${k}`),
      email: formData.get(`contato-email${k}`),
    }
    formulario.contatos.push(contato);
  }

  return formulario;
}


//excluindo/editando um formulario
function adicionarEventListenersNosBotoesDeExcluirEEditar(){
  excluirBtns[excluirBtns.length-1].addEventListener("click",function(e){
    async function excluir(){
      await excluirFormulario(Number(e.target.id),urlExcluirFormulario);
      reconstruirTabela();
    }
    excluir();
  });

  editarBtns[editarBtns.length-1].addEventListener("click",function(e){
    copiarOsDadosASeremEditadosParaOsInputs(Number(e.target.id));
    enviarOuEditar = 'editar';
  });
}

function copiarOsDadosASeremEditadosParaOsInputs(idBotao){
  for(let i = 0 ; i < formularios.length ; i++){
    if( formularios[i].idFormulario === idBotao ){
      let formularioASerCopiadoParaOsInputs = formularios[i];
      nomeInput.value = formularioASerCopiadoParaOsInputs.nome;
      emailInput.value = formularioASerCopiadoParaOsInputs.email;
      cpfInput.value= formularioASerCopiadoParaOsInputs.cpf;
      cidadeInput.value= formularioASerCopiadoParaOsInputs.endereco.cidade;
      bairroInput.value= formularioASerCopiadoParaOsInputs.endereco.bairro;
      ruaInput.value= formularioASerCopiadoParaOsInputs.endereco.rua;
      quadraInput.value= formularioASerCopiadoParaOsInputs.endereco.quadra
      loteInput.value= formularioASerCopiadoParaOsInputs.endereco.lote
      casaInput.value= formularioASerCopiadoParaOsInputs.endereco.casa
      numeroInput.value= formularioASerCopiadoParaOsInputs.endereco.numero
      cepInput.value= formularioASerCopiadoParaOsInputs.endereco.cep

      for(let k = 1 ; k <= formularioASerCopiadoParaOsInputs.contatos.length ; k++){
        if(k!=1) adicionarContatoBtn.click()
        let contatoNome = document.getElementById(`contato-nome${k}`);
        let contatoTelefone = document.getElementById(`contato-telefone${k}`);
        let contatoEmail = document.getElementById(`contato-email${k}`);
        contatoNome.value = formularioASerCopiadoParaOsInputs.contatos[k-1].nome;
        contatoTelefone.value = formularioASerCopiadoParaOsInputs.contatos[k-1].telefone;
        contatoEmail.value = formularioASerCopiadoParaOsInputs.contatos[k-1].email;
      }

      idFormularioAserEditado = formularioASerCopiadoParaOsInputs.idFormulario;
      break;
    }
  }
}

//Adicionar Contatos
const adicionarContatoBtn = document.getElementById('adicionar-contato');
const contato1 = document.getElementById('contato-1');

function criarContato() {
  quantidadeDeContatos++;
  let contatoAserAdicionado = document.createElement('div');
  contatoAserAdicionado.setAttribute('id', `contato-${quantidadeDeContatos}`);
  const node = `<p id="${quantidadeDeContatos}°contato" class="nth-contato">Contato ${quantidadeDeContatos} </p>
    <div class="contato-nome-container flexbox">
      <label for="contato-nome${quantidadeDeContatos}" class="padding-left-1rem">Nome</label>
      <input type="text" name="contato-nome${quantidadeDeContatos}" id="contato-nome${quantidadeDeContatos}" class="input" />
      </div>
    <div class="contato-telefone-container flexbox">
    <label for="contato-telefone${quantidadeDeContatos}" class="padding-left-1rem"
        >Telefone + DDD</label
      >
      <input
        type="tel" name="contato-telefone${quantidadeDeContatos}" id="contato-telefone${quantidadeDeContatos}" class="input" maxlength="14"
      />
    </div>
    <div class="contato-email-container flexbox">
      <label for="contato-email${quantidadeDeContatos}" class="padding-left-1rem"
        >E-mail</label
      >
      <input type="email" name="contato-email${quantidadeDeContatos}" id="contato-email${quantidadeDeContatos}" class="input" />
    </div>
    `;
  contatoAserAdicionado.innerHTML = node;

  return contatoAserAdicionado;
}

adicionarContatoBtn.addEventListener('click', function () {
  if (quantidadeDeContatos < 5) {
    document.getElementById('contatos-serao-inseridos-antes-deste-div').before(criarContato());
  }
});

//removerContatos
const removerContatoBtn = document.getElementById('remover-contato');

function removerContato() {
  if (quantidadeDeContatos > 1) {
    const contatoAserRemovido = document.getElementById(`contato-${quantidadeDeContatos}`);
    contatoAserRemovido.parentNode.removeChild(contatoAserRemovido);
    quantidadeDeContatos--;
  }
}

removerContatoBtn.addEventListener('click', function () {
  removerContato();
});






function limparTabela(){
  for(let i = quantidadeDeFormulariosNaTabela; i >0; i--){
    let removerDaTabela = document.querySelectorAll(`.ref${i}`);
    removerDaTabela[0].remove();
    removerDaTabela[1].remove();
    removerDaTabela[2].remove();
    removerDaTabela[3].remove();
  }
  quantidadeDeFormulariosNaTabela=0;
}

function criarATabela(formularios) {
  for (let i = 0; i < formularios.length; i++) {
    let nomeAserAdicionado = document.createElement('div');
    let emailAserAdicionado = document.createElement('div');
    let escolaridadeAserAdicionado = document.createElement('div');
    let botoesAserAdicionado = document.createElement('div');

    nomeAserAdicionado.setAttribute('class', `table-item nome border-right border-top ref${i + 1}`);
    nomeAserAdicionado.textContent = formularios[i].nome;

    emailAserAdicionado.setAttribute('class', `table-item email border-right border-top ref${i + 1}`);
    emailAserAdicionado.textContent = formularios[i].email;

    escolaridadeAserAdicionado.setAttribute('class', `table-item escolaridade border-right border-top ref${i + 1}`);
    escolaridadeAserAdicionado.textContent = formularios[i].escolaridade;

    botoesAserAdicionado.setAttribute('class', `table-item ações border-top ref${i+1}`);
    botoesAserAdicionado.innerHTML = `
        <button class="editar ações-btn" id="${formularios[i].idFormulario}">Editar</button>
        <button class="excluir ações-btn" id="${formularios[i].idFormulario}">Excluir</button>
    `;
    let divReferenciaInsercao = document.querySelector('.dados-da-tabela-serao-inseridos-antes-deste-div');
    divReferenciaInsercao.before(nomeAserAdicionado);
    divReferenciaInsercao.before(emailAserAdicionado);
    divReferenciaInsercao.before(escolaridadeAserAdicionado);
    divReferenciaInsercao.before(botoesAserAdicionado);
    quantidadeDeFormulariosNaTabela++;
    excluirBtns = document.querySelectorAll('.excluir');
    editarBtns = document.querySelectorAll('.editar');
    adicionarEventListenersNosBotoesDeExcluirEEditar();
  }
}










function permitir_apenas_numeros_e_backspace_em_um_input(input) {
  input.addEventListener('keydown', function (e) {
    const condicao =
      !(e.key >= 0 && e.key <= 9) &&
      e.key !== 'Backspace' &&
      e.key !== 'ArrowRight' &&
      e.key !== 'ArrowLeft' &&
      e.key !== 'Shift' &&
      e.key !== 'Tab' &&
      e.key !== 'Delete';
    if (condicao) {
      e.preventDefault();
    }
  });
}

//mascara cpf
cpfInput.addEventListener('keypress', () => {
  if (cpfInput.value.length === 3 || cpfInput.value.length === 7) {
    cpfInput.value += '.';
  }
  if (cpfInput.value.length === 11) {
    cpfInput.value += '-';
  }
});

function cpfIsValid(cpf) {
  let cpfApenasNumeros = '';
  if (cpf.length !== 14) {
    alert('O cpf deve possuir 11 dígitos');
    return false;
  } else {
    cpfApenasNumeros = cpf.replace('.', ' ');
    cpfApenasNumeros = cpfApenasNumeros.replace('.', ' ');
    cpfApenasNumeros = cpfApenasNumeros.replace('.', ' ');
    cpfApenasNumeros = cpfApenasNumeros.replace('-', ' ');
    cpfApenasNumeros = cpfApenasNumeros.replace(/\s/g, '');

    let Soma = 0;
    let Resto;
    if (cpfApenasNumeros == '00000000000') {
      alert('Cpf Inválido');
      return false;
    }

    for (let i = 1; i <= 9; i++) Soma = Soma + parseInt(cpfApenasNumeros.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;

    if (Resto == 10 || Resto == 11) Resto = 0;
    if (Resto != parseInt(cpfApenasNumeros.substring(9, 10))) {
      alert('Cpf Inválido');
      return false;
    }

    Soma = 0;
    for (let i = 1; i <= 10; i++) Soma = Soma + parseInt(cpfApenasNumeros.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if (Resto == 10 || Resto == 11) Resto = 0;
    if (Resto != parseInt(cpfApenasNumeros.substring(10, 11))) {
      alert('Cpf Inválido');
      return false;
    }
    return true;
  }
}

function resetarInputsFormulario(form){
  form.reset();
  if (quantidadeDeContatos > 1) {
    for (let i = 2; i <= quantidadeDeContatos; i++) {
      let removerContato = document.getElementById(`contato-${i}`);
      removerContato.remove();
    }
    quantidadeDeContatos = 1;
  }
}