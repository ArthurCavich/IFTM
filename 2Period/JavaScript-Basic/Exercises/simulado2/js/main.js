window.addEventListener("DOMContentLoaded", function () {

const campoEntrada = document.getElementById("campoEntrada");
const btnProcessar = document.getElementById("btnProcessar");

btnProcessar.addEventListener("click", function () {
    localStorage.setItem("informacao", campoEntrada.value);
    window.open("analisarTexto.html","_self");
});

})
