const botaoTopo = document.querySelector('#botao-topo');
const tipoBusca = document.querySelector('#tipo');
const campoConsulta = document.querySelector('#consulta');
const ajudaBusca = document.querySelector('#busca-ajuda');
const erroBusca = document.querySelector('#erro-busca');
const botaoLimpar = document.querySelector('#botao-limpar');
const confirmacaoLimpeza = document.querySelector('#confirmacao-limpeza');
const confirmarLimpeza = document.querySelector('#confirmar-limpeza');
const cancelarLimpeza = document.querySelector('#cancelar-limpeza');
const formularioBusca = document.querySelector('#formulario-busca');
const mensagemResultados = document.querySelector('#mensagem-resultados');
const quantidadeResultados = document.querySelector('#quantidade-resultados');
const listaLivros = document.querySelector('#lista-livros');
const resultadosModal = document.querySelector('#resultados');
const carregandoResultados = document.querySelector('#carregando-resultados');
const conteudoResultados = document.querySelector('#conteudo-resultados');
const fecharResultados = document.querySelector('#fechar-resultados');
let temporizadorBusca;

const acervo = [
  { titulo: 'Introdução à programação', autor: 'M. A. Weiss', isbn: '9788535902778', ano: 2012 },
  { titulo: 'Lógica de programação: a construção de algoritmos e estruturas de dados', autor: 'André Luiz Villar Forbellone', isbn: '9788576050247', ano: 2005 },
  { titulo: 'Dom Casmurro', autor: 'Machado de Assis', isbn: '9788535911664', ano: 1899 },
  { titulo: 'O alienista', autor: 'Machado de Assis', isbn: '9788520925932', ano: 1882 },
  { titulo: 'Algoritmos: teoria e prática', autor: 'Thomas H. Cormen', isbn: '9788535236996', ano: 2012 },
  { titulo: 'A arte da pesquisa', autor: 'Wayne C. Booth', isbn: '9788536314921', ano: 2005 },
  { titulo: 'Banco de dados', autor: 'Ramez Elmasri', isbn: '9788576050885', ano: 2011 },
  { titulo: 'Redes de computadores', autor: 'Andrew S. Tanenbaum', isbn: '9788543021456', ano: 2021 },
  { titulo: 'O pequeno príncipe', autor: 'Antoine de Saint-Exupéry', isbn: '9788522031440', ano: 1943 },
  { titulo: 'Metodologia científica', autor: 'Antonio Carlos Gil', isbn: '9788597020886', ano: 2017 },
  { titulo: 'JavaScript: o guia definitivo', autor: 'David Flanagan', isbn: '9780596805524', ano: 2011 }
];

const orientacoesBusca = {
  todos: {
    placeholder: 'Digite título, autor ou ISBN...',
    ajuda: 'Exemplo: introdução à programação, Machado de Assis ou 9788535902778.'
  },
  titulo: {
    placeholder: 'Digite o título do livro...',
    ajuda: 'Exemplo: introdução à programação.'
  },
  autor: {
    placeholder: 'Digite o nome do autor...',
    ajuda: 'Exemplo: Machado de Assis.'
  },
  isbn: {
    placeholder: 'Digite o ISBN somente com números...',
    ajuda: 'Informe somente números, sem pontos ou hífens. Exemplo: 9788535902778.'
  }
};

function atualizarOrientacaoBusca() {
  const orientacao = orientacoesBusca[tipoBusca.value];
  campoConsulta.placeholder = orientacao.placeholder;
  ajudaBusca.textContent = orientacao.ajuda;
  campoConsulta.inputMode = tipoBusca.value === 'isbn' ? 'numeric' : 'search';
  campoConsulta.setAttribute('aria-describedby', tipoBusca.value === 'isbn' ? 'busca-ajuda erro-busca' : 'busca-ajuda');
}

function mostrarErroBusca(texto) {
  erroBusca.textContent = texto;
  erroBusca.hidden = false;
  campoConsulta.setAttribute('aria-invalid', 'true');
}

function limparErroBusca() {
  erroBusca.textContent = '';
  erroBusca.hidden = true;
  campoConsulta.removeAttribute('aria-invalid');
}

tipoBusca.addEventListener('change', () => {
  limparErroBusca();
  atualizarOrientacaoBusca();
});

campoConsulta.addEventListener('input', () => {
  if (tipoBusca.value === 'isbn') {
    campoConsulta.value = campoConsulta.value.replace(/\D/g, '');
  }
  limparErroBusca();
});

atualizarOrientacaoBusca();

botaoLimpar.addEventListener('click', () => {
  confirmacaoLimpeza.classList.add('aberta');
  confirmarLimpeza.focus();
});

function fecharConfirmacao() {
  confirmacaoLimpeza.classList.remove('aberta');
  botaoLimpar.focus();
}

confirmarLimpeza.addEventListener('click', () => {
  tipoBusca.value = 'todos';
  campoConsulta.value = '';
  atualizarOrientacaoBusca();
  fecharConfirmacao();
  campoConsulta.focus();
});

cancelarLimpeza.addEventListener('click', fecharConfirmacao);

function normalizar(texto) {
  return texto
    .normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .toLowerCase()
    .trim();
}

function mostrarMensagem(texto, tipo = '') {
  mensagemResultados.hidden = false;
  mensagemResultados.className = `empty-state ${tipo}`.trim();
  mensagemResultados.innerHTML = `<span class="empty-icon" aria-hidden="true">${tipo === 'erro' ? '!' : '⌕'}</span><p>${texto}</p>`;
}

function renderizarLivros(livros) {
  listaLivros.innerHTML = livros.map((livro) => `
    <article class="book-card">
      <div class="book-icon" aria-hidden="true">▤</div>
      <div>
        <h3>${livro.titulo}</h3>
        <p>${livro.autor}</p>
        <small>ISBN: ${livro.isbn} · ${livro.ano}</small>
      </div>
    </article>
  `).join('');
}

function abrirResultados() {
  resultadosModal.classList.add('aberto');
  document.body.classList.add('modal-aberto');
  carregandoResultados.hidden = false;
  conteudoResultados.hidden = true;
  fecharResultados.focus();
}

function fecharTelaResultados() {
  resultadosModal.classList.remove('aberto');
  document.body.classList.remove('modal-aberto');
  campoConsulta.focus();
}

formularioBusca.addEventListener('submit', (evento) => {
  evento.preventDefault();

  const termo = campoConsulta.value.trim();
  const tipo = tipoBusca.value;

  if (!termo) {
    mostrarErroBusca('Digite um termo antes de clicar em Buscar.');
    campoConsulta.focus();
    return;
  }

  if (tipo === 'isbn' && !/^\d+$/.test(termo)) {
    mostrarErroBusca('Para pesquisar por ISBN, informe somente números.');
    campoConsulta.focus();
    return;
  }

  abrirResultados();
  clearTimeout(temporizadorBusca);
  temporizadorBusca = setTimeout(() => {
    const busca = normalizar(termo);
    const resultados = acervo.filter((livro) => {
      if (tipo === 'titulo') return normalizar(livro.titulo).includes(busca);
      if (tipo === 'autor') return normalizar(livro.autor).includes(busca);
      if (tipo === 'isbn') return livro.isbn.includes(termo);
      return [livro.titulo, livro.autor, livro.isbn].some((campo) => normalizar(campo).includes(busca));
    });

    if (resultados.length === 0) {
      listaLivros.innerHTML = '';
      mostrarMensagem('Nenhum livro foi encontrado. Tente outro termo ou escolha outro tipo de busca.', 'erro');
      quantidadeResultados.hidden = true;
    } else {
      renderizarLivros(resultados);
      mensagemResultados.hidden = true;
      quantidadeResultados.hidden = false;
      quantidadeResultados.textContent = `${resultados.length} resultado${resultados.length === 1 ? '' : 's'}`;
    }

    carregandoResultados.hidden = true;
    conteudoResultados.hidden = false;
  }, 650);
});

fecharResultados.addEventListener('click', fecharTelaResultados);

function atualizarBotaoTopo() {
  botaoTopo.classList.toggle('visivel', window.scrollY > 300);
}

window.addEventListener('scroll', atualizarBotaoTopo, { passive: true });
atualizarBotaoTopo();
