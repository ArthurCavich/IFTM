button = document.getElementById("btnExibir");
nome = document.getElementById("txtNome");

function exibirNome(){
    alert(nome.value.toUpperCase());
}

button.addEventListener("click", exibirNome);