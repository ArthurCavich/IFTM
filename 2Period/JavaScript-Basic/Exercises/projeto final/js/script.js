// Variáveis globais do jogo
let jogarNovamente = true;
let tentativas = 6;
let listaDinamica = [];
let palavraSecretaCategoria;
let palavraSecretaSorteada;
let palavras = [];
let nivelDificuldade = 'facil'; // facil, medio, dificil
let tempoRestante = 0;
let timerInterval = null;
let jogoPausado = false;
let jogoIniciado = false;

// Inicializa o jogo quando o DOM estiver pronto
document.addEventListener("DOMContentLoaded", function() {
    // Verificar se o usuário está logado (apenas na página jogo.html)
    if (window.location.pathname.includes('jogo.html') || window.location.pathname.endsWith('index.html')) {
        const usuarioLogado = JSON.parse(localStorage.getItem('usuarioLogado') || 'null');
        if (!usuarioLogado) {
            window.location.href = 'login.html';
            return;
        }
        
        // Exibir nome do usuário
        const nomeUsuarioEl = document.getElementById('nomeUsuario');
        if (nomeUsuarioEl) {
            nomeUsuarioEl.textContent = '👤 ' + usuarioLogado.username;
        }
    }
    
    carregaListaAutomatica();
    
    if (document.getElementById('palavra-secreta')) {
        configurarDificuldade();
        atualizarTimer();
        aplicarTemaDificuldade(nivelDificuldade);
        criarPalavraSecreta();
        montarPalavraNaTela();
        carregaImagemForca();
    }
    
    inicializarEventos();
});

// Filtra palavras pelo nível de dificuldade com base no tamanho do texto (desconsidera espaços)
function filtrarPalavrasPorNivel(lista, nivel) {
    const removerEspacos = (texto) => texto.replace(/\s+/g, '');
    return lista.filter(p => {
        const tamanho = removerEspacos(p.nome).length;
        if (nivel === 'facil') return tamanho <= 6;
        if (nivel === 'medio') return tamanho >= 7 && tamanho <= 9;
        if (nivel === 'dificil') return tamanho >= 10;
        return true;
    });
}

// Função para criar uma palavra secreta aleatória respeitando o nível
function criarPalavraSecreta(){
    if(palavras.length === 0) {
        console.warn("Nenhuma palavra disponível!");
        return;
    }

    // Filtra por nível; se ficar vazio, usa lista completa como fallback
    let pool = filtrarPalavrasPorNivel(palavras, nivelDificuldade);
    if (pool.length === 0) {
        pool = [...palavras];
    }

    const indicePalavra = Math.floor(Math.random() * pool.length);
    
    palavraSecretaSorteada = pool[indicePalavra].nome;
    palavraSecretaCategoria = pool[indicePalavra].categoria;
    
    // Reinicia a lista dinâmica para a nova palavra
    listaDinamica = [];
}

// Função para montar a palavra secreta na tela
function montarPalavraNaTela(){
    if(!palavraSecretaSorteada) {
        return;
    }
    
    const categoria = document.getElementById("categoria");
    categoria.innerHTML = palavraSecretaCategoria;

    const palavraTela = document.getElementById("palavra-secreta");
    palavraTela.innerHTML = "";
    
    for(let i = 0; i < palavraSecretaSorteada.length; i++){  
        if(listaDinamica[i] === undefined){
            if (palavraSecretaSorteada[i] === " ") {
                listaDinamica[i] = " ";
                palavraTela.innerHTML = palavraTela.innerHTML + "<div class='letrasEspaco'>" + listaDinamica[i] + "</div>";
            }
            else{
                listaDinamica[i] = "&nbsp;";
                palavraTela.innerHTML = palavraTela.innerHTML + "<div class='letras'>" + listaDinamica[i] + "</div>";
            }     
        }
        else{
            if (palavraSecretaSorteada[i] === " ") {
                listaDinamica[i] = " ";
                palavraTela.innerHTML = palavraTela.innerHTML + "<div class='letrasEspaco'>" + listaDinamica[i] + "</div>";
            }
            else{
                palavraTela.innerHTML = palavraTela.innerHTML + "<div class='letras'>" + listaDinamica[i] + "</div>";
            }    
        }
    }   
}

// Função para verificar a letra escolhida pelo jogador
function verificaLetraEscolhida(letra){
    // Impede jogar sem ter iniciado
    if (!jogoIniciado) {
        if (typeof Swal !== 'undefined') {
            Swal.fire({
                icon: 'info',
                title: 'Inicie o jogo',
                text: 'Clique em "Iniciar" para começar.',
                confirmButtonColor: obterCorTemaAtual()
            });
        } else {
            alert('Clique em "Iniciar" para começar.');
        }
        return;
    }

    document.getElementById("tecla-" + letra).disabled = true;
    if(tentativas > 0)
    {
        mudarStyleLetra("tecla-" + letra, false);
        compararListas(letra);
        montarPalavraNaTela();
    }    
}

// Função para mudar o estilo da tecla (errada = rosa, certa = verde)
function mudarStyleLetra(tecla, condicao){
    if(condicao === false)
    {
        document.getElementById(tecla).style.background = "#C71585";
        document.getElementById(tecla).style.color = "#ffffff";
    }
    else{
        document.getElementById(tecla).style.background = "#008000";
        document.getElementById(tecla).style.color = "#ffffff";
    }
}

// Função para comparar a letra escolhida com a palavra secreta
function compararListas(letra){
    let letraEncontrada = false;
    
    // Verifica todas as ocorrências da letra na palavra
    for(let i = 0; i < palavraSecretaSorteada.length; i++){
        if(palavraSecretaSorteada[i] === letra){
            listaDinamica[i] = letra;
            letraEncontrada = true;
        }
    }
    
    if(!letraEncontrada){
        // Letra não encontrada - perde uma tentativa
        tentativas--;
        carregaImagemForca();

        if(tentativas === 0){
            if (timerInterval) {
                clearInterval(timerInterval);
                timerInterval = null;
            }
            
            jogoIniciado = false;
            jogoPausado = false;
            
            document.getElementById('btnPlay').disabled = false;
            document.getElementById('btnPause').disabled = true;
            document.getElementById('btnStop').disabled = true;
            
            abreModal("OPS!", "Não foi dessa vez ... A palavra secreta era <br><strong>" + palavraSecretaSorteada + "</strong>");
            piscarBotaoJogarNovamente(true);
            
            // Salvar resultado no ranking
            salvarResultado(false);
        }
    }
    else{
        // Letra encontrada - marca como correta
        mudarStyleLetra("tecla-" + letra, true);
    }
    
    // Verifica se o jogador venceu
    let vitoria = true;
    for(let i = 0; i < palavraSecretaSorteada.length; i++){
        if(palavraSecretaSorteada[i] !== listaDinamica[i]){
            vitoria = false;
            break;
        }
    }

    if(vitoria === true)
    {
        if (timerInterval) {
            clearInterval(timerInterval);
            timerInterval = null;
        }
        
        jogoIniciado = false;
        jogoPausado = false;
        
        document.getElementById('btnPlay').disabled = false;
        document.getElementById('btnPause').disabled = true;
        document.getElementById('btnStop').disabled = true;
        
        const tempoUsado = calcularTempoUsado();
        abreModal("PARABÉNS!", "Você venceu! A palavra era <strong>" + palavraSecretaSorteada + "</strong><br>Tempo: " + formatarTempo(tempoUsado));
        tentativas = 0;
        piscarBotaoJogarNovamente(true);
        
        // Salvar resultado no ranking
        salvarResultado(true, tempoUsado);
    }
}

// async function piscarBotaoJogarNovamente(){
//     while (jogarNovamente == true) {
//         document.getElementById("btnReiniciar").style.backgroundColor = 'red';
//         document.getElementById("btnReiniciar").style.scale = 1.3;
//         await atraso(500)
//         document.getElementById("btnReiniciar").style.backgroundColor = 'yellow';
//         document.getElementById("btnReiniciar").style.scale = 1;
//         await atraso(500)
//     }
// }

// Função auxiliar para criar atraso (delay)
async function atraso(tempo){
    return new Promise(x => setTimeout(x, tempo));     
}

// Função para carregar a imagem da forca baseada nas tentativas restantes
function carregaImagemForca(){
    switch(tentativas){
        case 5:
            document.getElementById("imagem").style.background  = "url('./img/forca01.png')";
            break;
        case 4:
            document.getElementById("imagem").style.background  = "url('./img/forca02.png')";
            break;
        case 3:
            document.getElementById("imagem").style.background  = "url('./img/forca03.png')";
            break;
        case 2:
            document.getElementById("imagem").style.background  = "url('./img/forca04.png')";
            break;
        case 1:
            document.getElementById("imagem").style.background  = "url('./img/forca05.png')";
            break;
        case 0:
            document.getElementById("imagem").style.background  = "url('./img/forca06.png')";
            break;
        default:
            document.getElementById("imagem").style.background  = "url('./img/forca.png')";
            break;
    }
}

// Função para abrir o modal com título e mensagem
function abreModal(titulo, mensagem){
    let modalTitulo = document.getElementById("exampleModalLabel");
    modalTitulo.innerText = titulo;

    let modalBody = document.getElementById("modalBody");
    modalBody.innerHTML = mensagem;

    $("#myModal").modal({
        show: true
    });
}

// Função para inicializar todos os eventos do jogo
function inicializarEventos(){
    let botaoReiniciar = document.querySelector("#btnReiniciar");
    if(botaoReiniciar) {
        botaoReiniciar.addEventListener("click", function(){
            jogarNovamente = false;
            location.reload();
        });
    }

    const modal = document.getElementById("modal-alerta");
    if(modal) {
        const btnAbreModal = document.getElementById("abreModalAddPalavra");
        if(btnAbreModal) {
            btnAbreModal.onclick = function(){
                modal.style.display = "block";
            };
        }

        const btnFechaModal = document.getElementById("fechaModal");
        if(btnFechaModal) {
            btnFechaModal.onclick = function(){ 
                modal.style.display = "none";
                document.getElementById("addPalavra").value = "";
                document.getElementById("addCategoria").value = ""; 
            };
        }

        window.onclick = function(event){ 
            if (event.target === modal) {
                modal.style.display = "none";
                document.getElementById("addPalavra").value = "";
                document.getElementById("addCategoria").value = ""; 
            }  
        };
    }
}

// Função para definir o nível de dificuldade
function definirNivelDificuldade(nivel) {
    if (jogoIniciado && !jogoPausado) {
        Swal.fire({
            icon: 'warning',
            title: 'Atenção!',
            text: 'Pare o jogo antes de mudar o nível!',
            confirmButtonColor: obterCorTemaAtual()
        });
        return;
    }
    
    nivelDificuldade = nivel;
    
    aplicarTemaDificuldade(nivelDificuldade);

    // Atualizar botões de nível
    document.querySelectorAll('.btn-nivel').forEach(btn => {
        btn.classList.remove('active');
    });
    event.target.classList.add('active');
    
    configurarDificuldade();
    atualizarTimer();
    
    if (!jogoIniciado) {
        criarPalavraSecreta();
        montarPalavraNaTela();
        carregaImagemForca();
        resetaTeclas();
    }
}

// Função para configurar tentativas e tempo baseado no nível
function configurarDificuldade() {
    switch(nivelDificuldade) {
        case 'facil':
            tentativas = 8;
            tempoRestante = 300; // 5 minutos
            break;
        case 'medio':
            tentativas = 6;
            tempoRestante = 180; // 3 minutos
            break;
        case 'dificil':
            tentativas = 4;
            tempoRestante = 120; // 2 minutos
            break;
    }
}

// Função para atualizar o display do timer
function atualizarTimer() {
    const minutos = Math.floor(tempoRestante / 60);
    const segundos = tempoRestante % 60;
    const timerEl = document.getElementById('timer');
    if (timerEl) {
        timerEl.textContent = `${String(minutos).padStart(2, '0')}:${String(segundos).padStart(2, '0')}`;
        
        // Mudar cor quando tempo está acabando
        if (tempoRestante <= 30) {
            timerEl.style.color = '#dc3545';
        } else {
            timerEl.style.color = '#8b008b';
        }
    }
}

// Aplica tema visual conforme dificuldade
function aplicarTemaDificuldade(nivel) {
    const body = document.body;
    body.classList.remove('tema-facil', 'tema-medio', 'tema-dificil');
    switch(nivel){
        case 'facil':
            body.classList.add('tema-facil');
            break;
        case 'medio':
            body.classList.add('tema-medio');
            break;
        case 'dificil':
            body.classList.add('tema-dificil');
            break;
        default:
            break;
    }
}

// Cor padrão de alert por tema
function obterCorTemaAtual(){
    const body = document.body;
    if (body.classList.contains('tema-dificil')) return '#b30021';
    if (body.classList.contains('tema-medio')) return '#ff7a00';
    if (body.classList.contains('tema-facil')) return '#7a5cf0';
    return '#7a5cf0';
}

// Função para iniciar o jogo
function iniciarJogo() {
    if (jogoIniciado && !jogoPausado) {
        return;
    }
    
    if (!jogoIniciado) {
        configurarDificuldade();
        criarPalavraSecreta();
        montarPalavraNaTela();
        carregaImagemForca();
        resetaTeclas();
        piscarBotaoJogarNovamente(false);
    }
    
    jogoIniciado = true;
    jogoPausado = false;
    
    // Atualizar botões
    document.getElementById('btnPlay').disabled = true;
    document.getElementById('btnPause').disabled = false;
    document.getElementById('btnStop').disabled = false;
    
    // Iniciar timer
    timerInterval = setInterval(function() {
        if (!jogoPausado) {
            tempoRestante--;
            atualizarTimer();
            
            if (tempoRestante <= 0) {
                tempoEsgotado();
            }
        }
    }, 1000);
}

// Função para pausar o jogo
function pausarJogo() {
    if (!jogoIniciado || jogoPausado) {
        return;
    }
    
    jogoPausado = true;
    document.getElementById('btnPause').disabled = true;
    document.getElementById('btnPlay').disabled = false;
}

// Função para parar o jogo
function pararJogo() {
    if (timerInterval) {
        clearInterval(timerInterval);
        timerInterval = null;
    }
    
    jogoIniciado = false;
    jogoPausado = false;
    
    document.getElementById('btnPlay').disabled = false;
    document.getElementById('btnPause').disabled = true;
    document.getElementById('btnStop').disabled = true;
    
    configurarDificuldade();
    atualizarTimer();
    resetaTeclas();
    listaDinamica = [];
    tentativas = 6;
    carregaImagemForca();
    criarPalavraSecreta();
    montarPalavraNaTela();
}

// Função para sair do jogo
function sairJogo() {
    Swal.fire({
        icon: 'question',
        title: 'Sair do jogo?',
        text: 'Tem certeza que deseja sair?',
        showCancelButton: true,
        confirmButtonColor: '#dc3545',
        cancelButtonColor: '#6c757d',
        confirmButtonText: 'Sim, sair',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            localStorage.removeItem('usuarioLogado');
            window.location.href = 'index.html';
        }
    });
}

// Função quando o tempo esgota
function tempoEsgotado() {
    if (timerInterval) {
        clearInterval(timerInterval);
        timerInterval = null;
    }
    
    jogoIniciado = false;
    jogoPausado = false;
    
    document.getElementById('btnPlay').disabled = false;
    document.getElementById('btnPause').disabled = true;
    document.getElementById('btnStop').disabled = true;
    
    abreModal("TEMPO ESGOTADO!", "O tempo acabou! A palavra secreta era <br><strong>" + palavraSecretaSorteada + "</strong>");
    piscarBotaoJogarNovamente(true);
    
    // Salvar resultado no ranking
    salvarResultado(false);
}

// Função para carregar a lista automática de palavras
function carregaListaAutomatica(){
    palavras = [
        {
            nome: "IRLANDA",
            categoria:"LUGARES"
        },
        {
            nome: "EQUADOR",
            categoria:"LUGARES"
        },
        {
            nome: "CHILE",
            categoria:"LUGARES"
        },
        {
            nome: "INDONESIA",
            categoria:"LUGARES"
        },
        {
            nome: "MALDIVAS",
            categoria:"LUGARES"
        },
        {
            nome: "BRASIL",
            categoria:"PAISES"
        },
        {
            nome: "HOLANDA",
            categoria:"PAISES"
        },
        {
            nome: "ESTADOS UNIDOS",
            categoria:"PAISES"
        },
        {
            nome: "CANADA",
            categoria:"PAISES"
        },
        {
            nome: "ITALIA",
            categoria:"PAISES"
        },
        {
            nome: "FRANCA",
            categoria:"PAISES"
        },
        {
            nome: "BICICLETA",
            categoria:"TRANSPORTE"
        },
        {
            nome: "LANCHA",
            categoria:"TRANSPORTE"
        },
        {
            nome: "NAVIO",
            categoria:"TRANSPORTE"
        },
        {
            nome: "TELEFERICO",
            categoria:"TRANSPORTE"
        },
        {
            nome: "MOTOCICLETA",
            categoria:"TRANSPORTE"
        },
        {
            nome: "BARCO",
            categoria:"TRANSPORTE"
        },
        {
            nome: "AERONAVE",
            categoria:"TRANSPORTE"
        },
        {
            nome: "TREM",
            categoria:"TRANSPORTE"
        },
        {
            nome: "CAIAQUE",
            categoria:"TRANSPORTE"
        },
        {
            nome: "FUNICULAR",
            categoria:"TRANSPORTE"
        },
        {
            nome: "XICARA",
            categoria:"OBJETOS"
        },
        {
            nome: "MOEDA",
            categoria:"OBJETOS"
        },
        {
            nome: "ESPARADRAPO",
            categoria:"OBJETOS"
        },
        {
            nome: "SINO",
            categoria:"OBJETOS"
        },
        {
            nome: "CHUVEIRO",
            categoria:"OBJETOS"
        },
        {
            nome: "TAMBORETE",
            categoria:"OBJETOS"
        },
        {
            nome: "LAMPADA",
            categoria:"OBJETOS"
        },
        {
            nome: "BOCAL",
            categoria:"OBJETOS"
        },
        {
            nome: "CORTINA",
            categoria:"OBJETOS"
        },
        {
            nome: "LAPIS",
            categoria:"OBJETOS"
        },
        {
            nome: "MELANCIA",
            categoria:"ALIMENTOS"
        },
        {
            nome: "AMENDOIM",
            categoria:"ALIMENTOS"
        },
        {
            nome: "ESFIRRA",
            categoria:"ALIMENTOS"
        },
        {
            nome: "GOROROBA",
            categoria:"ALIMENTOS"
        },
        {
            nome: "JANTAR",
            categoria:"ALIMENTOS"
        },
        {
            nome: "SABOROSO",
            categoria:"ALIMENTOS"
        },
        {
            nome: "DESJEJUM",
            categoria:"ALIMENTOS"
        },
        {
            nome: "MASTIGAR",
            categoria:"ALIMENTOS"
        },
        {
            nome: "ENGOLIR",
            categoria:"ALIMENTOS"
        },
        {
            nome: "DOCERIA",
            categoria:"ALIMENTOS"
        },
        {
            nome: "DRAGAO",
            categoria:"ANIMAIS"
        },
        {
            nome: "GALINHA",
            categoria:"ANIMAIS"
        },
        {
            nome: "PAVAO",
            categoria:"ANIMAIS"
        },
        {
            nome: "CAMELO",
            categoria:"ANIMAIS"
        },
        {
            nome: "PERU",
            categoria:"ANIMAIS"
        },
        {
            nome: "ZEBRA",
            categoria:"ANIMAIS"
        },
        {
            nome: "DROMEDARIO",
            categoria:"ANIMAIS"
        },
        {
            nome: "CALANGO",
            categoria:"ANIMAIS"
        },
        {
            nome: "SAGUI",
            categoria:"ANIMAIS"
        },
        {
            nome: "LAGARTIXA",
            categoria:"ANIMAIS"
        },
        {
            nome: "HIPOPOTAMO",
            categoria:"ANIMAIS"
        },
        {
            nome: "A ERA DO GELO",
            categoria:"TV E CINEMA"
        },
        {
            nome: "HOMEM ARANHA",
            categoria:"TV E CINEMA"
        },
        {
            nome: "CASA MONSTRO",
            categoria:"TV E CINEMA"
        },
        {
            nome: "TELA QUENTE",
            categoria:"TV E CINEMA"
        },
        {
            nome: "STRANGER THINGS",
            categoria:"TV E CINEMA"
        },
        {
            nome: "O REI DO GADO",
            categoria:"TV E CINEMA"
        },
        {
            nome: "MULHER MARAVILHA",
            categoria:"TV E CINEMA"
        },
        {
            nome: "O INCRIVEL HULK",
            categoria:"TV E CINEMA"
        },
        {
            nome: "BOB ESPONJA",
            categoria:"TV E CINEMA"
        },
        {
            nome: "PANICO NA TV",
            categoria:"TV E CINEMA"
        }
    ];
}

// Função para adicionar uma nova palavra ao jogo (modo manual)
function adicionarPalavra(){
    let palavraAdicionar = document.getElementById("addPalavra").value.toUpperCase().trim();
    let categoriaAdicionar = document.getElementById("addCategoria").value.toUpperCase().trim();

    // Validação dos dados
    if (isNullOrWhiteSpace(palavraAdicionar) || isNullOrWhiteSpace(categoriaAdicionar)) {
        abreModal("ATENÇÃO"," Por favor, preencha todos os campos!");
        return;
    }
    
    if (palavraAdicionar.length < 3 || categoriaAdicionar.length < 3) {
        abreModal("ATENÇÃO"," Palavra e Categoria devem ter pelo menos 3 caracteres!");
        return;
    }
    
    // Verifica se a palavra contém apenas letras e espaços
    if (!/^[A-ZÁÉÍÓÚÂÊÔÇÃÕ\s]+$/.test(palavraAdicionar)) {
        abreModal("ATENÇÃO"," A palavra deve conter apenas letras!");
        return;
    }

    let palavra = {
        nome: palavraAdicionar,
        categoria: categoriaAdicionar
    };

    palavras.push(palavra);  
    abreModal("SUCESSO", "Palavra adicionada com sucesso!");
    sortear();
    
    document.getElementById("addPalavra").value = "";
    document.getElementById("addCategoria").value = "";
}

// Função para verificar se uma string está vazia ou contém apenas espaços
function isNullOrWhiteSpace(input){
    return !input || !input.trim();
}

// Função para sortear uma nova palavra e reiniciar o jogo
function sortear(){
    if(palavras.length > 0){
        pararJogo();
        iniciarJogo();
    }
}

// Função para resetar todas as teclas do teclado
function resetaTeclas(){
    let teclas = document.querySelectorAll(".teclas > button");
    teclas.forEach((tecla) => {
        tecla.style.background = "#FFFFFF";
        tecla.style.color = "#8B008B";
        tecla.disabled = false;
    });
}

// Função para mostrar ou esconder o botão de jogar novamente
async function piscarBotaoJogarNovamente(querJogar){
    const btnJogarNovamente = document.getElementById("jogarNovamente");
    if(btnJogarNovamente) {
        if(querJogar){
            btnJogarNovamente.style.display = "block";
        }
        else{
            btnJogarNovamente.style.display = "none";
        }
    }
}

// Função para calcular o tempo usado
function calcularTempoUsado() {
    let tempoInicial = 0;
    switch(nivelDificuldade) {
        case 'facil':
            tempoInicial = 300;
            break;
        case 'medio':
            tempoInicial = 180;
            break;
        case 'dificil':
            tempoInicial = 120;
            break;
    }
    return tempoInicial - tempoRestante;
}

// Função para formatar tempo
function formatarTempo(segundos) {
    const min = Math.floor(segundos / 60);
    const seg = segundos % 60;
    return `${String(min).padStart(2, '0')}:${String(seg).padStart(2, '0')}`;
}

// Função para salvar resultado no ranking
function salvarResultado(venceu, tempoUsado = 0) {
    const usuarioLogado = JSON.parse(localStorage.getItem('usuarioLogado') || 'null');
    if (!usuarioLogado) return;
    
    const ranking = JSON.parse(localStorage.getItem('ranking') || '{}');
    
    if (!ranking[nivelDificuldade]) {
        ranking[nivelDificuldade] = [];
    }
    
    const resultado = {
        username: usuarioLogado.username,
        venceu: venceu,
        tempo: tempoUsado,
        tentativas: tentativas,
        data: new Date().toISOString()
    };
    
    ranking[nivelDificuldade].push(resultado);
    
    // Ordenar ranking: primeiro por vitória, depois por tempo
    ranking[nivelDificuldade].sort((a, b) => {
        if (a.venceu && !b.venceu) return -1;
        if (!a.venceu && b.venceu) return 1;
        if (a.venceu && b.venceu) {
            return a.tempo - b.tempo; // Menor tempo primeiro
        }
        return 0;
    });
    
    // Manter apenas os 10 melhores
    ranking[nivelDificuldade] = ranking[nivelDificuldade].slice(0, 10);
    
    localStorage.setItem('ranking', JSON.stringify(ranking));
}


