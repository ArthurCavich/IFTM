import getCats from "./theCatAPI.js";

window.addEventListener("DOMContentLoaded", () => {

    mostrarGatos();

});

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
    boxGatos.innerHTML = gatos.map(gato => `<img src=${gato.url} width=200>`).join("<br>");

}