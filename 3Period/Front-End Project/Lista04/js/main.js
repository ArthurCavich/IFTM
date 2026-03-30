import { obterMaisPopulares, obterGeneros } from "./requisicoes.js";

// variáveis globais
let paginaAtual = 1;
let filmesExibidos = 10;
let listaFilmes = [];
let generosFilmes = [];

window.addEventListener('DOMContentLoaded', () => {
    requisicao(); // aqui que faz tudo acontecer
    
    // configura o evento do botão de carregar mais
    const btnCarregar = document.getElementById('btn-carregar');
    if (btnCarregar) {
        btnCarregar.addEventListener('click', carregarMais);
    }
})

// Requisição dos filmes com promisse.all
const requisicao = async () => {
    const [lista, generos] = await Promise.all([
        obterMaisPopulares(paginaAtual),
        obterGeneros()
    ]);

    listaFilmes = lista;
    generosFilmes = generos;
    mostrarDados();
}

const carregarMais = async () => {
    filmesExibidos += 10;

    const btn = document.getElementById('btn-carregar');
    btn.disabled = true; // controla o estado do botão para não gerar bug nos cliques

    if (filmesExibidos > listaFilmes.length) {
        paginaAtual++;
        const novaLista = await obterMaisPopulares(paginaAtual);

        listaFilmes = listaFilmes.concat(novaLista);
    }

    mostrarDados();

    // Restaura o botão
    btn.innerText = "Carregar +10 filmes";
    btn.disabled = false; // controla o estado do botão para não gerar bug nos cliques
}

const mostrarDados = () => {
    const filmesParaMostrar = listaFilmes.slice(0, filmesExibidos);

    const mapaGeneros = {};
    generosFilmes.forEach(g => {
        mapaGeneros[g.id] = g.name;
    });

    const dados = document.getElementById('filmes');

    // Ferramenta nativa do JS para traduzir idiomas
    const tradutorIdioma = new Intl.DisplayNames(['pt-BR'], { type: 'language' });

    const info = filmesParaMostrar
        .map(item => {
            const nomeGeneros = item.genre_ids
                .map(id => mapaGeneros[id])
                .join(", ");

            let idiomaTraduzido = tradutorIdioma.of(item.original_language);
            idiomaTraduzido = idiomaTraduzido.charAt(0).toUpperCase() + idiomaTraduzido.slice(1);

            return `
                <div class="caixa-filme">
                    <img src="https://image.tmdb.org/t/p/w500${item.poster_path}" alt="${item.title}">
                    <div class="info-filme">
                        <h2>${item.title}</h2>
                        <p class="sinopse"><strong>Sinopse:</strong> ${item.overview || "Não informada"}</p>
                        <p><strong>Gênero:</strong> ${nomeGeneros}</p>
                        <p><strong>Título original:</strong> ${item.original_title}</p>
                        <p><strong>Idioma:</strong> ${idiomaTraduzido}</p>
                        <p><strong>Avaliações:</strong> ⭐ ${item.vote_average.toFixed(1)} <span>(${item.vote_count} votos)</span></p>
                    </div>
                </div>
            `;
        })
        .join('');

    dados.innerHTML = info;
}