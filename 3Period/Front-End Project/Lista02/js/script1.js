window.addEventListener("DOMContentLoaded", () => {
    const idades = [20, 23, 25, 29, 30, 33, 40, 50];
  
    // mostrar vetor
    document.getElementById("vetor-idades").textContent = idades.join(", ");
  
    // a) soma
    document.getElementById("somaIdades").textContent =
      idades.reduce((contador, idade) => contador + idade, 0);
  
    // b) média
    document.getElementById("mediaAritmetica").textContent =
      (idades.reduce((contador, idade) => contador + idade, 0) / idades.length).toFixed(2);//deixa duas casas decimais
  
    // c) maior idade
    document.getElementById("maiorIdade").textContent =
      idades.reduce(
        (maior, idade) => (idade > maior ? idade : maior),
        idades[0]
      );
  
    // d) ímpares
    document.getElementById("idadesImpares").textContent =
      idades.filter((idade) => idade % 2 !== 0).join(", ");
  
    // e) todos >= 18?
    document.getElementById("maioresDe18").textContent =
      idades.every((idade) => idade >= 18);
  
    const form = document.getElementById("form-comparacao");
  
    form.addEventListener("submit", (e) => {
      e.preventDefault(); //evita recarregar a página
  
      const valor = Number(document.getElementById("valorComparacao").value);
  
      document.getElementById("valor-f").textContent = valor;
      document.getElementById("valor-g").textContent = valor;
      document.getElementById("valor-h").textContent = valor;
  
      const todosMaioresOuIguais = idades.every((idade) => idade >= valor);
      document.getElementById("resultado-f").textContent = todosMaioresOuIguais;
  
      const filtradas = idades.filter((idade) => idade >= valor);
      document.getElementById("resultado-g").textContent =
        filtradas.length ? filtradas.join(", ") : "Nenhuma";
  
      const somaFiltradas = filtradas.reduce(
        (contador, idade) => contador + idade,
        0
      );
      const mediaFiltradas =
        filtradas.length ? somaFiltradas / filtradas.length : 0;
  
      document.getElementById("resultado-h").textContent =
        filtradas.length
          ? mediaFiltradas.toFixed(2)
          : "Não há idades nesse critério";
    });
  });