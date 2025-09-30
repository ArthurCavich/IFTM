nota1 = document.getElementById("nota1");
nota2 = document.getElementById("nota2");
result = document.getElementById("btnResult");

function resultado(){
n1 = Number(nota1.value);
n2 = Number(nota2.value);

if (n1 < 0 || n1 > 50 || n2 < 0 || n2 > 50) {
  return alert("Cada nota deve estar entre 0 e 50!");
}
    
soma = n1+n2;
soma >= 60? alert("Aprovado em JavaScript"): alert("Reprovado em JavaScript");
}

result.addEventListener("click", resultado)