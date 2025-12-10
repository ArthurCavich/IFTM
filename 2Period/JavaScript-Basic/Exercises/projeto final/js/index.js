document.addEventListener('DOMContentLoaded', function () {

    const usuarioLogado = JSON.parse(localStorage.getItem('usuarioLogado') || 'null');
    const barraUsuario = document.getElementById('barraUsuario');
    const nomeUsuarioBarra = document.getElementById('nomeUsuarioBarra');
    const btnSair = document.getElementById('btnSair');
    const btnJogarInicio = document.getElementById('btnJogarInicio');
    const btnLoginInicio = document.getElementById('btnLoginInicio');
    const btnCadastroInicio = document.getElementById('btnCadastroInicio');

    // Atualiza a visibilidade da barra e dos botões conforme login
    function atualizarInterfaceInicio() {
        if (usuarioLogado) {
            if (barraUsuario) barraUsuario.style.display = 'flex';
            if (nomeUsuarioBarra) nomeUsuarioBarra.textContent = '👤 ' + (usuarioLogado.usuario || usuarioLogado.nome || 'Usuário');
            if (btnSair) btnSair.style.display = 'inline-flex';
            if (btnJogarInicio) btnJogarInicio.style.display = 'inline-flex';
            if (btnLoginInicio) btnLoginInicio.style.display = 'none';
            if (btnCadastroInicio) btnCadastroInicio.style.display = 'none';
        } else {
            if (barraUsuario) barraUsuario.style.display = 'none';
            if (btnSair) btnSair.style.display = 'none';
            if (btnJogarInicio) btnJogarInicio.style.display = 'none';
            if (btnLoginInicio) btnLoginInicio.style.display = 'inline-flex';
            if (btnCadastroInicio) btnCadastroInicio.style.display = 'inline-flex';
        }
    }

    if (btnSair) {
        btnSair.addEventListener('click', function () {
            localStorage.removeItem('usuarioLogado');
            window.location.reload();
        });
    }

    atualizarInterfaceInicio();
})();