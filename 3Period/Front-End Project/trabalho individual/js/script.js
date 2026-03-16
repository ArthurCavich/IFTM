window.addEventListener("DOMContentLoaded", () => {
    //chama a função de leitura dos dados da requisição
    lerClientes();

});

const lerClientes = async () => {
    const url = "https://arthurcavich.github.io/IFTM/3Period/Front-End%20Project/trabalho%20individual/clientes.json";
    try {
        const resposta = await fetch(url);
        if (!resposta.ok) {
            throw new Error("Error exception")
        };
        const clientes = await resposta.json();
        exibirClientes(clientes);
    }
    catch (error) {
        console.log(error.message);
    }
}

const exibirClientes = (clientes) => {
    const lista = document.getElementById("lista-clientes");

    lista.innerHTML = clientes.map(cliente => `
        <div>
            <h2>${cliente.nome}</h2>
            <p>${cliente.depoimento}</p>
            <span>Nota: ${cliente.rating}/5</span>
        </div>
        `).join("");
};