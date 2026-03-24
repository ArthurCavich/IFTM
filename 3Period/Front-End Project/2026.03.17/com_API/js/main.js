import getCats from "./theCatAPI.js";

window.addEventListener("DOMContentLoaded", () => {

    mostrarGatos();

});

// const mostrarGatos = async () => {
//     const gatos = await getCats();
//     console.log(gatos[0].breeds[0].description)
//     console.log(gatos);
// }

/*const mostrarGatos = async () => {
    const gatos = await getCats();
    let boxGatos = document.getElementById("boxGatos");
    gatos.forEach(gato => {
        let imagem = document.createElement("img");
        imagem.src = gato.url;
        imagem.width = 200;
        // imagem.height = gato.height;
        boxGatos.appendChild(imagem);
    });
}*/

const mostrarGatos = async () => {
    const gatos = await getCats();
    let boxGatos = document.getElementById("boxGatos");
    boxGatos.innerHTML = gatos.map(gato => `
        <figure>
            <img src=${gato.url} width=200>
            <details>
                <summary>Saiba mais clicando aqui!</summary>
                <p>${gato.breeds?.[0]?.name}</p>
                <p>${gato.breeds?.[0]?.origin}</p>
                <p>${gato.breeds?.[0]?.description}</p>
                <p>${gato.breeds?.[0]?.temperament}</p>
            </details>
        </figure>
        `).join("<br>");

}