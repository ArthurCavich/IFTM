const baralho = Array.from({ length: 27 }, (_, i) => i + 1);

function sortearCarta() {
    const indice = Math.floor(Math.random() * baralho.length);
    return baralho.splice(indice, 1)[0];
}

for (let i = 1; i <= 4; i++) {
    const container = document.querySelector(`#jogador${i} .cartas`);
    for (let j = 0; j < 3; j++) {
        const carta = sortearCarta();
        container.innerHTML += `<img src="img/carta${carta}.png" alt="Carta ${carta}">`;
    }
}