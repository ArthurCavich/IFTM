btn = document.getElementById("btnDobrar");
valor1 = document.getElementById("txtValor1");
valor2 = document.getElementById("txtValor2");

btn.addEventListener("click", dobrarValor);

function dobrarValor(){
    valor2.value = valor1.value * 2;
}