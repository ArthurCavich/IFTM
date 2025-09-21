btn = document.getElementById("btnDobrar");
txtValor = document.getElementById("txtValor");

btnDobrar.addEventListener("click", dobraValor);

function dobrarValor(){
    
    if (txtValor.value.trim() == ""){
        return alert("Campo em branco! Informe um número válido:")
    } else if (isNaN(txtValor.value)){
        return alert("Você não informou um número. Informe apenas números")
    } else{
        return alert(txtValor.value*2);
    }
}

/*
function dobrarValor(){
    valor = prompt("Digite um valor:");
    alert(valor*2);
}
*/