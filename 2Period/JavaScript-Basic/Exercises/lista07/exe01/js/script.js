// Função para transferir texto após um tempo determinado
function transferirTexto(tempo) {
    const caixa1 = document.getElementById('caixa1');
    const caixa2 = document.getElementById('caixa2');
    const texto = caixa1.value;
    
    setTimeout(function() {
        caixa1.value = '';
        caixa2.value = texto;
    }, tempo);
}

// Aguarda o carregamento completo da página
document.addEventListener('DOMContentLoaded', function() {
    const btnTransferir = document.getElementById('btnTransferir');
    
    btnTransferir.addEventListener('click', function() {
        transferirTexto(2000);
    });
});

