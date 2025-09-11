opcoes = ["Pedra", "Papel", "Tesoura"];

imagens = {
    "Pedra": "img/pedra.png",
    "Papel": "img/papel.png",
    "Tesoura": "img/tesoura.png",
};

jogador1 = opcoes[parseInt(Math.random() * 3)];
jogador2 = opcoes[parseInt(Math.random() * 3)];


function determinarVencedor(j1, j2) {
    if (j1 === j2) return "Empate!";
    if (
        (j1 === "Pedra" && j2 === "Tesoura") ||
        (j1 === "Papel" && j2 === "Pedra") ||
        (j1 === "Tesoura" && j2 === "Papel")
    ) {
        return "Jogador 1 venceu!";
    } else {
        return "Jogador 2 venceu!";
    }
}

resultado = determinarVencedor(jogador1, jogador2);


document.write(`<p>Jogador 1 <br><img src="${imagens[jogador1]}" alt="${jogador1}" width="100"></p>`);
document.write(`<p>Jogador 2 <br><img src="${imagens[jogador2]}" alt="${jogador2}" width="100"></p>`);
document.write(`<h3>${resultado}</h3>`);