usuario = document.getElementById("login");
senha = document.getElementById("senha");
confirma = document.getElementById("confirmar");
btnEntrar = document.getElementById("btnEntrar");
btnLimpar = document.getElementById("btnLimpar")

btnEntrar.addEventListener("click", validarDados);
btnLimpar.addEventListener("click", limpar);


function validarDados() {

    login = usuario.value;
    password = senha.value;
    confirma = confirma.value;

    if (login.trim() == "" && password.trim() == "" && confirma.trim() == "") 
        alert("Os campos estão vazios!\nDigite login e senha!");
    
    else if (password != confirma) 
        alert("As senhas não são iguais. Digite novamente!");
    
    else 
        alert("Todos os campos foram digitados corretamente!");
    
    limpar();
}

function limpar() {
    usuario.value = "";
    senha.value = "";
    confirmar.value = "";
}