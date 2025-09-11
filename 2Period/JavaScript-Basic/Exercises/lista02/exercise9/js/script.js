nomes = ["Arthur", "Brenda", "Caio", "Danilo", "Eliane"];
ordem = [];

while (nomes.length > 0) {
    
    indiceAleat = parseInt(Math.random() * nomes.length);

    
    ordem.push(nomes[indiceAleat]);

    
    nomes.splice(indiceAleat, 1);
}
ordem.forEach((nome, i) => document.write(`<div class="container">${i + 1}º ${nome}</div>`));