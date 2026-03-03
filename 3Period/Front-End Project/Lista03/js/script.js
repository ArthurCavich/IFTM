window.addEventListener("DOMContentLoaded", () => {

    let opcao = document.getElementById("opcao");
    let btnResultado = document.getElementById("btnResultado");
    let resultado = document.getElementById("resultado");

    btnResultado.addEventListener("click", () => {
        //1. Exibir o nome e a idade de todas as pessoas maiores de idade
        if (opcao.value === "1") {
            const texto = funcionarios
                .filter(user => user.idade >= 18)
                .map(user => (`Nome: ${user.nome} | Idade:${user.idade}`))
                .join("<br>");

            resultado.innerHTML = texto;
        }
        // 2. Exibir os nomes de todas as pessoas do sexo masculino
        if (opcao.value === "2") { //opcao 2
            const texto = funcionarios
                .filter(user => user.sexo === "m")
                .map(user => user.nome)
                .join(", ");

            resultado.innerHTML = texto;
        }
        // 3. Exibir os dados da pessoa com o maior salário
        if (opcao.value === "3") {
            const maiorSalario = funcionarios.reduce((maior, user) => Math.max(maior, user.salario), 0);
            const texto = funcionarios
                .filter(user => user.salario === maiorSalario)
                .map(user => (`Nome: ${user.nome} | Idade:${user.idade} | Sexo:${user.sexo} | Salário:${user.salario}`))

            resultado.innerHTML = texto;
        }
        // 4. Há alguma mulher que ganha acima de 5000,00?
        if (opcao.value === "4") {
            const qtd5k = funcionarios
                .filter(user => user.salario >= 5_000 && user.sexo === "f")
            let qtdTotal = qtd5k.length;

            if (qtdTotal === 0) {
                const texto = "Nenhuma mulher recebe acima de R$5.000,00"
                resultado.innerHTML = texto;
            }

            if (qtdTotal > 0) {
                const lista = qtd5k
                    .map(user => (`Nome: ${user.nome} | Salário:${user.salario}`))
                    .join("<br>");
                const texto = `Existe(m) ${qtdTotal} mulher(es) que recebe(m) acima de 5k<br>${lista}`;

                resultado.innerHTML = texto;
            }
        }
        // 5. Media dos salarios dos homens e das mulheres
        if (opcao.value === "5") {
            const homens = funcionarios.filter(user => user.sexo === "m");
            const mulheres = funcionarios.filter(user => user.sexo === "f");

            let qtdHomens = homens.length;
            let qtdMulheres = mulheres.length;

            let somaHomens = homens.reduce((soma, user) => soma + user.salario, 0);
            let somaMulheres = mulheres.reduce((soma, user) => soma + user.salario, 0);

            let mediaHomens = somaHomens / qtdHomens;
            let mediaMulheres = somaMulheres / qtdMulheres;

            const texto = `
            Média dos salários dos homens: R$ ${mediaHomens.toFixed(2)}<br>
            Média dos salários das mulheres: R$ ${mediaMulheres.toFixed(2)}`;
            resultado.innerHTML = texto;
        }
    });
});