document.addEventListener('DOMContentLoaded', function () {

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
    // (chamada uma vez no final deste arquivo)
    function inicializarPaginaJogo() {
        // Verificar se o usuário está logado (apenas na página jogo.html ou index)
        if (window.location.pathname.includes('jogo.html') || window.location.pathname.endsWith('index.html')) {
            const usuarioLogado = JSON.parse(localStorage.getItem('usuarioLogado') || 'null');
            if (!usuarioLogado) {
                window.location.href = 'login.html';
                return;
            }

            // Exibir nome do usuário
            const nomeJogadorEl = document.getElementById('nomeJogador');
            if (nomeJogadorEl) {
                nomeJogadorEl.textContent = '👤 ' + usuarioLogado.usuario;
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
            atualizarTentativasUI();
        }

        inicializarEventos();
    }

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

    // Cria palavra secreta respeitando o nível
    function criarPalavraSecreta() {
        if (palavras.length === 0) {
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

    // Monta a palavra secreta na tela
    function montarPalavraNaTela() {
        if (!palavraSecretaSorteada) {
            return;
        }

        const categoria = document.getElementById("categoria");
        categoria.innerHTML = palavraSecretaCategoria;

        const palavraTela = document.getElementById("palavra-secreta");
        palavraTela.innerHTML = "";

        for (let i = 0; i < palavraSecretaSorteada.length; i++) {
            if (listaDinamica[i] === undefined) {
                if (palavraSecretaSorteada[i] === " ") {
                    listaDinamica[i] = " ";
                    palavraTela.innerHTML = palavraTela.innerHTML + "<div class='letrasEspaco'>" + listaDinamica[i] + "</div>";
                }
                else {
                    listaDinamica[i] = "&nbsp;";
                    palavraTela.innerHTML = palavraTela.innerHTML + "<div class='letras'>" + listaDinamica[i] + "</div>";
                }
            }
            else {
                if (palavraSecretaSorteada[i] === " ") {
                    listaDinamica[i] = " ";
                    palavraTela.innerHTML = palavraTela.innerHTML + "<div class='letrasEspaco'>" + listaDinamica[i] + "</div>";
                }
                else {
                    palavraTela.innerHTML = palavraTela.innerHTML + "<div class='letras'>" + listaDinamica[i] + "</div>";
                }
            }
        }
    }

    // Verifica a letra escolhida pelo jogador
    function verificaLetraEscolhida(letra) {
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
        if (tentativas > 0) {
            mudarStyleLetra("tecla-" + letra, false);
            compararListas(letra);
            montarPalavraNaTela();
        }
    }

    // Muda o estilo da tecla (errada = rosa, certa = verde)
    function mudarStyleLetra(tecla, condicao) {
        if (condicao === false) {
            document.getElementById(tecla).style.background = "#C71585";
            document.getElementById(tecla).style.color = "#ffffff";
        }
        else {
            document.getElementById(tecla).style.background = "#008000";
            document.getElementById(tecla).style.color = "#ffffff";
        }
    }

    // Compara a letra escolhida com a palavra secreta
    function compararListas(letra) {
        let letraEncontrada = false;

        // Verifica todas as ocorrências da letra na palavra
        for (let i = 0; i < palavraSecretaSorteada.length; i++) {
            if (palavraSecretaSorteada[i] === letra) {
                listaDinamica[i] = letra;
                letraEncontrada = true;
            }
        }

        if (!letraEncontrada) {
            // Letra não encontrada - perde uma tentativa
            tentativas--;
            atualizarTentativasUI();
            carregaImagemForca();

            if (tentativas === 0) {
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
        else {
            // Letra encontrada - marca como correta
            mudarStyleLetra("tecla-" + letra, true);
        }

        // Verifica se o jogador venceu
        let vitoria = true;
        for (let i = 0; i < palavraSecretaSorteada.length; i++) {
            if (palavraSecretaSorteada[i] !== listaDinamica[i]) {
                vitoria = false;
                break;
            }
        }

        if (vitoria === true) {
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
            atualizarTentativasUI();
            piscarBotaoJogarNovamente(true);

            // Salvar resultado no ranking
            salvarResultado(true, tempoUsado);
        }
    }

    // Função auxiliar para criar atraso (delay)
    async function atraso(tempo) {
        return new Promise(x => setTimeout(x, tempo));
    }

    // Função para carregar a imagem da forca baseada nas tentativas restantes
    function carregaImagemForca() {
        switch (tentativas) {
            case 5:
                document.getElementById("imagem").style.background = "url('./img/forca01.png')";
                break;
            case 4:
                document.getElementById("imagem").style.background = "url('./img/forca02.png')";
                break;
            case 3:
                document.getElementById("imagem").style.background = "url('./img/forca03.png')";
                break;
            case 2:
                document.getElementById("imagem").style.background = "url('./img/forca04.png')";
                break;
            case 1:
                document.getElementById("imagem").style.background = "url('./img/forca05.png')";
                break;
            case 0:
                document.getElementById("imagem").style.background = "url('./img/forca06.png')";
                break;
            default:
                document.getElementById("imagem").style.background = "url('./img/forca.png')";
                break;
        }
    }

    // Função para abrir o modal com título e mensagem
    function abreModal(titulo, mensagem) {
        Swal.fire({
            title: titulo,
            html: mensagem,
            icon: 'info',
            confirmButtonColor: obterCorTemaAtual()
        });
    }

    // Função para inicializar todos os eventos do jogo
    function inicializarEventos() {
        let botaoReiniciar = document.querySelector("#btnReiniciar");
        if (botaoReiniciar) {
            botaoReiniciar.addEventListener("click", function () {
                jogarNovamente = false;
                location.reload();
            });
        }

        const modal = document.getElementById("modal-alerta");
        if (modal) {
            const btnAbreModal = document.getElementById("abreModalAddPalavra");
            if (btnAbreModal) {
                btnAbreModal.onclick = function () {
                    modal.style.display = "block";
                };
            }

            const btnFechaModal = document.getElementById("fechaModal");
            if (btnFechaModal) {
                btnFechaModal.onclick = function () {
                    modal.style.display = "none";
                    document.getElementById("addPalavra").value = "";
                    document.getElementById("addCategoria").value = "";
                };
            }

            window.onclick = function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                    document.getElementById("addPalavra").value = "";
                    document.getElementById("addCategoria").value = "";
                }
            };
        }
    }

    // Define o nível de dificuldade selecionado
    function definirNivelDificuldade(nivel, botao) {
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
        const botoesNivel = document.querySelectorAll('.btn-nivel');
        botoesNivel.forEach(btn => btn.classList.remove('active'));
        if (botao) {
            botao.classList.add('active');
        } else {
            const alvo = Array.from(botoesNivel).find(btn => btn.dataset.nivel === nivel);
            if (alvo) alvo.classList.add('active');
        }

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
        switch (nivelDificuldade) {
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
        atualizarTentativasUI();
    }

    // Atualiza o display do timer
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

    // Atualiza visual das tentativas restantes
    function atualizarTentativasUI() {
        const el = document.getElementById('tentativas-info');
        if (!el) return;
        el.textContent = `Tentativas: ${tentativas}`;
    }

    // Aplica tema visual conforme dificuldade
    function aplicarTemaDificuldade(nivel) {
        const body = document.body;
        body.classList.remove('tema-facil', 'tema-medio', 'tema-dificil');
        switch (nivel) {
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
    function obterCorTemaAtual() {
        const body = document.body;
        if (body.classList.contains('tema-dificil')) return '#b30021';
        if (body.classList.contains('tema-medio')) return '#ff7a00';
        if (body.classList.contains('tema-facil')) return '#7a5cf0';
        return '#7a5cf0';
    }

    // Inicia o jogo
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
        timerInterval = setInterval(function () {
            if (!jogoPausado) {
                tempoRestante--;
                atualizarTimer();

                if (tempoRestante <= 0) {
                    tempoEsgotado();
                }
            }
        }, 1000);
    }

    // Pausa o jogo
    function pausarJogo() {
        if (!jogoIniciado || jogoPausado) {
            return;
        }

        jogoPausado = true;
        document.getElementById('btnPause').disabled = true;
        document.getElementById('btnPlay').disabled = false;
    }

    // Para o jogo e reseta estado
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
        atualizarTentativasUI();
    }

    // Confirma e sai do jogo
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

    // Dispara quando o tempo esgota
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

    // Carrega a lista automática de palavras
    function carregaListaAutomatica() {
        palavras = [
            {
                nome: "IRLANDA",
                categoria: "LUGARES"
            },
            {
                nome: "EQUADOR",
                categoria: "LUGARES"
            },
            {
                nome: "CHILE",
                categoria: "LUGARES"
            },
            {
                nome: "INDONESIA",
                categoria: "LUGARES"
            },
            {
                nome: "MALDIVAS",
                categoria: "LUGARES"
            },
            {
                nome: "BRASIL",
                categoria: "PAISES"
            },
            {
                nome: "HOLANDA",
                categoria: "PAISES"
            },
            {
                nome: "ESTADOS UNIDOS",
                categoria: "PAISES"
            },
            {
                nome: "CANADA",
                categoria: "PAISES"
            },
            {
                nome: "ITALIA",
                categoria: "PAISES"
            },
            {
                nome: "FRANCA",
                categoria: "PAISES"
            },
            {
                nome: "BICICLETA",
                categoria: "TRANSPORTE"
            },
            {
                nome: "LANCHA",
                categoria: "TRANSPORTE"
            },
            {
                nome: "NAVIO",
                categoria: "TRANSPORTE"
            },
            {
                nome: "TELEFERICO",
                categoria: "TRANSPORTE"
            },
            {
                nome: "MOTOCICLETA",
                categoria: "TRANSPORTE"
            },
            {
                nome: "BARCO",
                categoria: "TRANSPORTE"
            },
            {
                nome: "AERONAVE",
                categoria: "TRANSPORTE"
            },
            {
                nome: "TREM",
                categoria: "TRANSPORTE"
            },
            {
                nome: "CAIAQUE",
                categoria: "TRANSPORTE"
            },
            {
                nome: "XICARA",
                categoria: "OBJETOS"
            },
            {
                nome: "MOEDA",
                categoria: "OBJETOS"
            },
            {
                nome: "ESPARADRAPO",
                categoria: "OBJETOS"
            },
            {
                nome: "SINO",
                categoria: "OBJETOS"
            },
            {
                nome: "CHUVEIRO",
                categoria: "OBJETOS"
            },
            {
                nome: "TAMBORETE",
                categoria: "OBJETOS"
            },
            {
                nome: "LAMPADA",
                categoria: "OBJETOS"
            },
            {
                nome: "CORTINA",
                categoria: "OBJETOS"
            },
            {
                nome: "LAPIS",
                categoria: "OBJETOS"
            },
            {
                nome: "MELANCIA",
                categoria: "ALIMENTOS"
            },
            {
                nome: "AMENDOIM",
                categoria: "ALIMENTOS"
            },
            {
                nome: "ESFIRRA",
                categoria: "ALIMENTOS"
            },
            {
                nome: "JANTAR",
                categoria: "ALIMENTOS"
            },
            {
                nome: "SABOROSO",
                categoria: "ALIMENTOS"
            },
            {
                nome: "DESJEJUM",
                categoria: "ALIMENTOS"
            },
            {
                nome: "MASTIGAR",
                categoria: "ALIMENTOS"
            },
            {
                nome: "ENGOLIR",
                categoria: "ALIMENTOS"
            },
            {
                nome: "DRAGAO",
                categoria: "ANIMAIS"
            },
            {
                nome: "GALINHA",
                categoria: "ANIMAIS"
            },
            {
                nome: "PAVAO",
                categoria: "ANIMAIS"
            },
            {
                nome: "CAMELO",
                categoria: "ANIMAIS"
            },
            {
                nome: "PERU",
                categoria: "ANIMAIS"
            },
            {
                nome: "ZEBRA",
                categoria: "ANIMAIS"
            },
            {
                nome: "DROMEDARIO",
                categoria: "ANIMAIS"
            },
            {
                nome: "CALANGO",
                categoria: "ANIMAIS"
            },
            {
                nome: "SAGUI",
                categoria: "ANIMAIS"
            },
            {
                nome: "LAGARTIXA",
                categoria: "ANIMAIS"
            },
            {
                nome: "HIPOPOTAMO",
                categoria: "ANIMAIS"
            },
            {
                nome: "A ERA DO GELO",
                categoria: "TV E CINEMA"
            },
            {
                nome: "HOMEM ARANHA",
                categoria: "TV E CINEMA"
            },
            {
                nome: "CASA MONSTRO",
                categoria: "TV E CINEMA"
            },
            {
                nome: "TELA QUENTE",
                categoria: "TV E CINEMA"
            },
            {
                nome: "STRANGER THINGS",
                categoria: "TV E CINEMA"
            },
            {
                nome: "O REI DO GADO",
                categoria: "TV E CINEMA"
            },
            {
                nome: "MULHER MARAVILHA",
                categoria: "TV E CINEMA"
            },
            {
                nome: "O INCRIVEL HULK",
                categoria: "TV E CINEMA"
            },
            {
                nome: "BOB ESPONJA",
                categoria: "TV E CINEMA"
            }
        ];
    }

    // Sorteia uma nova palavra e reinicia o jogo
    function sortear() {
        if (palavras.length > 0) {
            pararJogo();
            iniciarJogo();
        }
    }

    // Reseta todas as teclas do teclado
    function resetaTeclas() {
        let teclas = document.querySelectorAll(".teclas > button");
        teclas.forEach((tecla) => {
            tecla.style.background = "#FFFFFF";
            tecla.style.color = "#8B008B";
            tecla.disabled = false;
        });
    }

    // Mostra ou esconde o botão de jogar novamente
    async function piscarBotaoJogarNovamente(querJogar) {
        const btnJogarNovamente = document.getElementById("jogarNovamente");
        if (btnJogarNovamente) {
            if (querJogar) {
                btnJogarNovamente.style.display = "block";
            }
            else {
                btnJogarNovamente.style.display = "none";
            }
        }
    }

    // Calcula o tempo usado
    function calcularTempoUsado() {
        let tempoInicial = 0;
        switch (nivelDificuldade) {
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

    // Formata tempo em mm:ss
    function formatarTempo(segundos) {
        const min = Math.floor(segundos / 60);
        const seg = segundos % 60;
        return `${String(min).padStart(2, '0')}:${String(seg).padStart(2, '0')}`;
    }

    // Salva resultado no ranking
    function salvarResultado(venceu, tempoUsado = 0) {
        const usuarioLogado = JSON.parse(localStorage.getItem('usuarioLogado') || 'null');
        if (!usuarioLogado) return;

        const ranking = JSON.parse(localStorage.getItem('ranking') || '{}');

        if (!ranking[nivelDificuldade]) {
            ranking[nivelDificuldade] = [];
        }

        const resultado = {
            usuario: usuarioLogado.usuario,
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

    // Expor funções para os handlers inline no HTML
    window.definirNivelDificuldade = definirNivelDificuldade;
    window.iniciarJogo = iniciarJogo;
    window.pausarJogo = pausarJogo;
    window.pararJogo = pararJogo;
    window.sairJogo = sairJogo;
    window.verificaLetraEscolhida = verificaLetraEscolhida;

    // Chamada principal quando o DOM estiver pronto
    inicializarPaginaJogo();

}); 
