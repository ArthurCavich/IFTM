nota1 = document.getElementById("nota1");
nota2 = document.getElementById("nota2");
result = document.getElementById("btnResult");

function resultado() {
    n1 = Number(nota1.value);
    n2 = Number(nota2.value);
    if (n1 < 0 || n1 > 50 || n2 < 0 || n2 > 50) {
        alert("Cada nota deve estar entre 0 e 50!");
        return;
    }
    soma = n1 + n2;
    restante = 60 - soma;
    soma >= 60
        ? alert(`APROVADO em JavaScript!\nNota final: ${soma}`)
        : alert(`REPROVADO em JavaScript!\nFaltaram ${restante} pontos`);
}

result.addEventListener("click", resultado)