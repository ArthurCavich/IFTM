button = document.getElementById("btnDobro");
a = document.getElementById("numberA");
dobro = document.getElementById("doubleA")

function dobrar(){
    valor = a.value;
    dobro.value = valor * 2;
}

button.addEventListener("click", dobrar);