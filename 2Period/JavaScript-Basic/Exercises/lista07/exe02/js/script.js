function transferirTexto(tempo) {
    const caixa1 = document.getElementById('caixa1');
    const caixa2 = document.getElementById('caixa2');
    const texto = caixa1.value;
    
    setTimeout(function() {
        caixa1.value = '';
        caixa2.value = texto;
    }, tempo);
}

document.addEventListener('DOMContentLoaded', function() {
    const btnTransferir = document.getElementById('btnTransferir');
    const selectTempo = document.getElementById('tempo');
    
    btnTransferir.addEventListener('click', function() {
        const segundos = parseInt(selectTempo.value);
        const tempoMilissegundos = segundos * 1000;
        transferirTexto(tempoMilissegundos);
    });
});

