'use strict';
const urlBase = "http://localhost:8080/FormularioServlets/";
const urlEnviarFormulario = urlBase + "AdicionarFormulario";
const urlListarFormularios = urlBase + "ListarTodosFormularios";

async function enviarFormulario(formulario,url){
  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "aplication/json;charset=UTF-8",
      "Access-Control-Allow-Origin":"*"
    },
    body: JSON.stringify(formulario)
  }
  fetch(url,requestOptions);
}

async function listarTodosFormularios(url){
  const requestOptions = {
    method: "GET",
    headers: {
      "Content-Type": "aplication/json;charset=UTF-8",
      "Access-Control-Allow-Origin":"http://127.0.0.1:5500"
    }
  }
  let response = await fetch(url,requestOptions);
  let data = await response.json();
  return data;
}
let formularios = listarTodosFormularios(urlListarFormularios);


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

let allInputs = document.querySelectorAll('.input');
let form = document.getElementById('form');

permitir_apenas_numeros_e_backspace_em_um_input(cpfInput);
permitir_apenas_numeros_e_backspace_em_um_input(quadraInput);
permitir_apenas_numeros_e_backspace_em_um_input(loteInput);
permitir_apenas_numeros_e_backspace_em_um_input(casaInput);
permitir_apenas_numeros_e_backspace_em_um_input(numeroInput);
permitir_apenas_numeros_e_backspace_em_um_input(contatoTelefoneInput);
permitir_apenas_numeros_e_backspace_em_um_input(cep);


//enviando o formulario pro backend
let quantidadeDeContatos = 1;
form.addEventListener("submit",function(e){
    e.preventDefault();

    const formData = new FormData(form);
    let formulario;
    if (quantidadeDeContatos < 2){
      alert('Informe pelo ao menos 2 contatos');
    }
    else if (cpfIsValid(formData.get('cpf'))){
      formulario = getFormularioObjAPartirDoFormData(formData);
      enviarFormulario(formulario,urlEnviarFormulario);
      resetarInputsFormulario(form);
    }
});



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