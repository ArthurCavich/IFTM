window.addEventListener("DOMContentLoaded", function () {
    document.getElementById("btnNroFalas").addEventListener("click", function () {
        let nroFalas = parseInt(document.getElementById("txtFalas").value);
        if (isNaN(nroFalas)) alert("Número de falas inválido. Informe um número!!!");
        console.log(nroFalas)
        let paragrafo;
        for (i=0; i<nroFalas; i++) {
            paragrafo = document.createElement("p");
            paragrafo.textContent = "Ho Ho Ho Feliz Natal!";
            document.getElementById("boxFalasPPN").appendChild(paragrafo);
        }
    });
});

