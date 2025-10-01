personagem = document.getElementById("personagem");
mensagem = document.getElementById("mensagem");

imagens = {
    pensativo: "img/pensativo.png",
    assustado: "img/assustado.png",
    alegre: "img/alegre.png",
    nervoso: "img/nervoso.png"
}

function pensativo() {
    personagem.src = imagens.pensativo;
    mensagem.textContent = "ZZZZZZZ!";
}

function assustado() {
    personagem.src = imagens.assustado;
    mensagem.textContent = "O que você quer??";
}

function alegre(nome) {
    personagem.src = imagens.alegre;
    mensagem.textContent = `${nome}, seja bem-vindo!`;
}

function nervoso() {
    personagem.src = imagens.nervoso;
    mensagem.textContent = "Não me faça perder o meu tempo!";
}

function nervosoAlegre() {
    nome = prompt("Digite seu nome:");

    if (nome === null || nome.trim() === "") {
        nervoso();
    } else {
        alegre(nome);
    }
}

pensativo();

personagem.addEventListener("mouseover", assustado);

personagem.addEventListener("mouseout", pensativo);

personagem.addEventListener("click", nervosoAlegre);