window.addEventListener("DOMContentLoaded", () => {
    // lerArquivoTxt();
    lerArquivoJSON();

});

// const lerArquivoTxt = () => {
//     const url = "https://wilton-filho.github.io/PFJS-GitHub/APIs/filmes/filmes.txt";

//     fetch(url)
//         .then(resposta => resposta.text())
//         .then(texto => console.log(texto))
// }

// const lerArquivoJSON = () => {
//     const url = "https://wilton-filho.github.io/PFJS-GitHub/APIs/filmes/filmes.json";

//     fetch(url)
//         .then(resposta => resposta.json())
//         .then(filmes => exibirFilmes(filmes));
// }

// const exibirFilmes = (filmes) => {
//     filmes.forEach(filme =>
//         console.log(`${filme.nome}, com nota ${filme.imdb} no imdb`)
//     )
// };

const lerArquivoJSON = async () => {
    const url = "https://wilton-filho.github.io/PFJS-GitHub/APIs/filmes/filmes.json";
    try {
        const resposta = await fetch(url);
        if (!resposta.ok) throw new Error("Error exception")
        const filmes = await resposta.json();
        exibirFilmes(filmes);
    }
    catch (error) {
        console.log(error.message);
    }

}
//utilizar map pra manipular os vetores. o FOREACH varre e não retorna nada.
const exibirFilmes = (filmes) => {
    filmes.forEach(filme =>
        console.log(`${filme.nome}, com nota ${filme.imdb} no imdb`)
    )
};