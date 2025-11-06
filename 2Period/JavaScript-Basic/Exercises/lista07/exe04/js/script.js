function atualizarContagem(segundos) {
    const mensagem = document.getElementById('mensagem');
    
    if (segundos > 0) {
        mensagem.textContent = 'Por favor, aguarde ' + segundos + ' segundos para carregar a página do Google';
        setTimeout(function() {
            atualizarContagem(segundos - 1);
        }, 1000);
    } else {
        window.location.href = 'https://www.google.com';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const btnIniciar = document.getElementById('btnIniciar');
    const inputSegundos = document.getElementById('segundos');
    
    btnIniciar.addEventListener('click', function() {
        const segundos = parseInt(inputSegundos.value);
        if (segundos > 0) {
            atualizarContagem(segundos);
        }
    });
});

