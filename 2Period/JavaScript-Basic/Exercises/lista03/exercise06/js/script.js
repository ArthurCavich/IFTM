valor1 = document.getElementById("valor1");
valor2 = document.getElementById("valor2");
resultado = document.getElementById("resultado");

btnSoma = document.getElementById("soma");
btnSubtrai = document.getElementById("subtrai");
btnProduto = document.getElementById("produto");
btnDivide = document.getElementById("divide");

function validarCampos() {
    a = Number(valor1.value);
    b = Number(valor2.value);

    if (valor1.value === "" || valor2.value === "") {
        alert("Digite números nos dois campos!");
        return false;
    }

    if (isNaN(a) || isNaN(b)) {
        alert("Digite apenas números válidos!");
        return false;
    }

    return true;
}

function soma() {
    if (!validarCampos()) return;
    resultado.value = a + b;
}

function subtrai() {
    if (!validarCampos()) return;
    resultado.value = a - b;
}

function produto() {
    if (!validarCampos()) return;
    resultado.value = a * b;
}

function divide() {
    if (!validarCampos()) return;
    if (b === 0) {
        alert("Não é possível dividir por zero.");
        return;
    }
    resultado.value = a / b;
}

btnSoma.addEventListener("click", soma);
btnSubtrai.addEventListener("click", subtrai);
btnProduto.addEventListener("click", produto);
btnDivide.addEventListener("click", divide);
