nome = document.getElementById("txtNome");
button = document.getElementById("buttonExibir");

function exibirNome(){
    alert(nome.value);
}


button.addEventListener("click", exibirNome);