btnGerarResultado = document.getElementById("btnGerarResultado");
btnGerarResultado.addEventListener("click", gerarSorteio);

function gerarSorteio() {
    candidatos = [
        { nome: "Jair Bolsonaro", partido: "PL", imagem: "candidato1.png" },
        { nome: "Luiz Inácio Lula da Silva", partido: "PT", imagem: "candidato2.png" },
        { nome: "Ciro Gomes", partido: "PDT", imagem: "candidato3.png" },
        { nome: "Simone Tebet", partido: "MDB", imagem: "candidato4.png" }
    ];

    indices = [0, 1, 2, 3];

    nroVotos = Math.floor(Math.random() * 100);
    vetVotos = [nroVotos, 100 - nroVotos];
    indicesSorteados = [];

    for (i = 1; i <= 2; i++) {
        indCand = indices.splice(Math.floor(Math.random() * indices.length), 1)[0];
        indicesSorteados[i - 1] = indCand;
        document.getElementById(`candSorteado${i}`).innerHTML = candidatos[indCand].nome;
        document.getElementById(`partSorteado${i}`).innerHTML = candidatos[indCand].partido;
        document.getElementById(`imagemCand${i}`).src = `img/${candidatos[indCand].imagem}`;
        document.getElementById(`votosCand${i}`).innerHTML = vetVotos[i - 1].toFixed(1);
    }

    if (vetVotos[0] == vetVotos[1]) {
        document.getElementById("candVencedor").innerHTML = "Empate";
        document.getElementById("percVencedor").innerHTML = "-"
    }
    else if (vetVotos[0] > vetVotos[1]) {
        document.getElementById("candVencedor").innerHTML = candidatos[indicesSorteados[0]].nome;
        document.getElementById("percVencedor").innerHTML = vetVotos[0].toFixed(1);
    }
    else {
        document.getElementById("candVencedor").innerHTML = candidatos[indicesSorteados[1]].nome;
        document.getElementById("percVencedor").innerHTML = vetVotos[1].toFixed(1);
    }
}

gerarSorteio();
// indices = [0, 1, 2, 3];

// cand1 = Math.floor(Math.random() * indices.length);
// indCand1 = indices[cand1];

// indices.splice(cand1, 1);

// cand2 = Math.floor(Math.random() * indices.length);
// indCand2 = indices[cand2];


// // indCand1 = Math.floor(Math.random()*4);
// document.getElementById("candSorteado1").innerHTML = candidatos[indCand1].nome;
// document.getElementById("partSorteado1").innerHTML = candidatos[indCand1].partido;
// document.getElementById("imagemCand1").src = `img/${candidatos[indCand1].imagem}`;


// // indCand2 = Math.floor(Math.random()*4);
// document.getElementById("candSorteado2").innerHTML = candidatos[indCand2].nome;
// document.getElementById("partSorteado2").innerHTML = candidatos[indCand2].partido;
// document.getElementById("imagemCand2").src = `img/${candidatos[indCand2].imagem}`;