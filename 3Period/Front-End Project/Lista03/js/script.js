window.addEventListener("DOMContentLoaded", () => {

    let opcao = document.getElementById("opcao");
    let btnResultado = document.getElementById("btnResultado");
    let resultado = document.getElementById("resultado");

    btnResultado.addEventListener("click", () => {
        if (opcao.value === "1") { //opcao 1
            const texto = funcionarios
                .filter(user => user.idade >= 18)
                .map(user => (`Nome: ${user.nome} | Idade:${user.idade}`))
                .join("<br>");

            resultado.innerHTML = texto;
        }
        if (opcao.value === "2"){ //opcao 2
            const texto = funcionarios
            .filter(user => user.sexo === "m")
            .map(user => user.nome)
            .join(", ");

            resultado.innerHTML = texto;
        }
        if (opcao.value === "3"){
            const maiorSalario = funcionarios.reduce((maior, user) => Math.max(maior, user.salario), 0);
            const texto = funcionarios
            .filter(user => user.salario === maiorSalario)
            .map(user => (`Nome: ${user.nome} | Idade:${user.idade} | Sexo:${user.sexo} | Salário:${user.salario}`))

            resultado.innerHTML = texto;
        }
        if(opcao.value === "4"){
            const texto = funcionarios
            const pessoas = .filter(user => user.salario >= 5000 && user.sexo === "f")
            .map(user => (`Nome: ${user.nome} | Salário:${user.salario}`))
            .join("<br>");

            resultado.innerHTML = texto;
        }
    })

    //01


});