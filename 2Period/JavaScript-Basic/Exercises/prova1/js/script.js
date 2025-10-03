texto = document.getElementById("userText");
texts = document.getElementById("textoo");
imagem = document.getElementById("imagem");
botao1 = document.getElementById("botao1");
botao2 = document.getElementById("botao2");
botao3 = document.getElementById("botao3");
botao4 = document.getElementById("botao4");
botao5 = document.getElementById("botao5");
botao6 = document.getElementById("botao6");

imagens = {
    personagem1: "img/imagem01.png",
    personagem2: "img/imagem02.png",
    personagem3: "img/imagem03.png",
    personagem4: "img/imagem04.png",
    personagem5: "img/imagem05.png",
    personagem6: "img/imagem06.png"
};

botao1.addEventListener("click", char1);
botao2.addEventListener("click", char2);
botao3.addEventListener("click", char3);
botao4.addEventListener("click", char4);
botao5.addEventListener("click", char5);
botao6.addEventListener("mouseover", char6);

function mostrarImagem(indice) {
    chaves = Object.keys(imagens);
    chave = chaves[indice];
    imagem.src = imagens[chave];
    imagem.alt = chave;
}

function char1() {
    mostrarImagem(0);
    if (texto.value.trim() === '') {
        alert("Caixa de texto vazia! Por favor, inserir o dado!");
        return;
    }
    texts.textContent = texto.value;
}

function char2() {
    mostrarImagem(1);
    if (texto.value.trim() === '') {
        alert("Caixa de texto vazia! Por favor, inserir o dado!");
        return;
    }
    texts.textContent = texto.value.toUpperCase();
}

function char3() {
    mostrarImagem(2);
    if (texto.value.trim() === '') {
        alert("Caixa de texto vazia! Por favor, inserir o dado!");
        return;
    }
    texts.textContent = texto.value.split(' ')[0];
}

function char4() {
    mostrarImagem(3);
    if (texto.value.trim() === '') {
        alert("Caixa de texto vazia! Por favor, inserir o dado!");
        return;
    }
    texts.textContent = Number(texto.value) + 1;
}

function char5() {
    mostrarImagem(4);
    if (texto.value.trim() === '') {
        alert("Caixa de texto vazia! Por favor, inserir o dado!");
        return;
    }

    texts.textContent = Number(texto.value);
    //não consegui fazer =(
}

function char6() {
    mostrarImagem(5);

    
    if (texto.value.trim() === '') {
        alert("Caixa de texto vazia! Por favor, inserir o dado!");
        return;
    }

    nome = prompt("Qual é seu nome?")
    texts.textContent = texto.value;
    alert(nome);

}

