numerosMegaSena = [];


while (numerosMegaSena.length < 6){
nro = parseInt(Math.random() * 60) + 1;

if(!numerosMegaSena.includes(nro)){
    numerosMegaSena.push(nro);
}
}
document.write(`Números sugeridos: ${numerosMegaSena.join(", ")}.`);