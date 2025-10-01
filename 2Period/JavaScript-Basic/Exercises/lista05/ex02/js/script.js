mickey = document.getElementById("mickey");
minnie = document.getElementById("minnie");
pluto = document.getElementById("pluto");
pateta = document.getElementById("pateta");

votoMickey = document.getElementById("votoMickey");
votoMinnie = document.getElementById("votoMinnie");
votoPluto = document.getElementById("votoPluto");
votoPateta = document.getElementById("votoPateta");

votos = { mickey: 0, minnie: 0, pluto: 0, pateta: 0 };

function destacar(candidato) {
    if (candidato.style.backgroundColor !== "gold") {
        candidato.style.backgroundColor = "lightblue";
    }
}

function removerDestaque(candidato) {
    if (candidato.style.backgroundColor !== "gold") {
        candidato.style.backgroundColor = "";
    }
}

function registrarVoto(nome, span) {
    votos[nome]++;
    span.textContent = votos[nome];

    maxVotos = Math.max(...Object.values(votos));

    for (c in votos) {
        elemento = document.getElementById(c);
        if (votos[c] === maxVotos && maxVotos > 0) {
            elemento.style.backgroundColor = "gold";
        } else {
            elemento.style.backgroundColor = "";
        }
    }
}

[mickey, minnie, pluto, pateta].forEach((candidato, i) => {
    candidato.addEventListener("mouseenter", () => destacar(candidato));
    candidato.addEventListener("mouseleave", () => removerDestaque(candidato));
});

mickey.querySelector("img").addEventListener("click", () => registrarVoto("mickey", votoMickey));
minnie.querySelector("img").addEventListener("click", () => registrarVoto("minnie", votoMinnie));
pluto.querySelector("img").addEventListener("click", () => registrarVoto("pluto", votoPluto));
pateta.querySelector("img").addEventListener("click", () => registrarVoto("pateta", votoPateta));
