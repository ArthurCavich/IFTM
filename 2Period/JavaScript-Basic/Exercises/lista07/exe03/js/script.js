function aumentarFonte(elemento, tamanhoAtual, tamanhoFinal) {
    elemento.style.fontSize = tamanhoAtual + 'px';
    
    if (tamanhoAtual < tamanhoFinal) {
        setTimeout(function() {
            aumentarFonte(elemento, tamanhoAtual + 2, tamanhoFinal);
        }, 500);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const btnExibir = document.getElementById('btnExibir');
    const inputNome = document.getElementById('nome');
    const resultado = document.getElementById('resultado');
    
    btnExibir.addEventListener('click', function() {
        const nome = inputNome.value;
        if (nome.trim() !== '') {
            resultado.textContent = nome;
            resultado.style.fontSize = '14px';
            aumentarFonte(resultado, 14, 40);
        }
    });
});

