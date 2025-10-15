document.addEventListener("DOMContentLoaded", () => {
    // ETAPA 1
    vetorCartas = [];
    for (let i = 1; i <= 27; i++) {
        vetorCartas.push(i);
    }

    // ETAPA 2 
    embaralhado = vetorCartas.sort(() => Math.random() - 0.5);
    selecionadas = embaralhado.slice(0, 4);
    vetorPares = [];

    selecionadas.forEach(carta => {
        vetorPares.push(carta);
        vetorPares.push(carta);
    });

    // ETAPA 3
    vetorParesEmbaralhados = vetorPares.sort(() => Math.random() - 0.5);
    console.log("Etapa 3 - Vetor embaralhado:", vetorParesEmbaralhados);

    // ETAPA 4
    tabuleiro = document.getElementById("tabuleiro");

    vetorParesEmbaralhados.forEach(num => {
        img = document.createElement("img");
        img.src = `./img/carta${num}.png`; // caminho relativo à pasta /js
        img.alt = `Carta ${num}`;
        img.classList.add("carta");
        tabuleiro.appendChild(img);
    });
});
