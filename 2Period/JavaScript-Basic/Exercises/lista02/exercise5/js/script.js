nomes = ["Arthur", "Brenda", "Caio", "Danilo", "Eliane"];
idades = [30, 28, 19, 25, 40];

soma = 0;

for (i = 0; i < idades.length; i++){
    soma += idades[i];
}

media = soma / idades.length;

sorteado = nomes[parseInt(Math.random() * nomes.length)];


document.write("<h1>Passageiros:</h1>");
    for (let i = 0; i < nomes.length; i++) {
        document.write(`${nomes[i]} - ${idades[i]} anos<br>`);
    }

document.write("<h2>Resultados:</h2>");
document.write(`Quantidade de passageiros: ${nomes.length}<br>`);
document.write(`Idade média dos passageiros: ${parseInt(media)} anos<br>`);
document.write(`Sorteado para ganhar o almoço: ${sorteado}`);
