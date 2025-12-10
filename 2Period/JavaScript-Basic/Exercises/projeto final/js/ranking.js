document.addEventListener('DOMContentLoaded', function () {

    // Aplica tema visual conforme nível
    function aplicarTemaRanking(nivel) {
        const container = document.querySelector('.ranking-container');
        if (!container) return;
        container.classList.remove('nivel-facil', 'nivel-medio', 'nivel-dificil');
        container.classList.add(`nivel-${nivel}`);
    }

    // Mostra ranking por nível
    function mostrarRanking(nivel, tabEl) {
        // Atualizar tabs
        const tabs = document.querySelectorAll('.tab-nivel');
        tabs.forEach(tab => tab.classList.remove('active'));
        if (tabEl) {
            tabEl.classList.add('active');
        } else {
            const alvo = Array.from(tabs).find(t => t.dataset.nivel === nivel);
            if (alvo) alvo.classList.add('active');
        }

        aplicarTemaRanking(nivel);

        // Buscar ranking do localStorage (somente vencedores)
        const ranking = JSON.parse(localStorage.getItem('ranking') || '{}');
        const rankingNivel = (ranking[nivel] || []).filter(r => r.venceu);

        const tbody = document.getElementById('ranking-body');
        tbody.innerHTML = '';

        if (rankingNivel.length === 0) {
            tbody.innerHTML = '<tr><td colspan="6" class="sem-dados">Ainda não há vencedores neste nível.</td></tr>';
            return;
        }

        rankingNivel.forEach((resultado, index) => {
            const tr = document.createElement('tr');

            // Posição
            const tdPosicao = document.createElement('td');
            tdPosicao.className = 'posicao';
            if (index === 0) tdPosicao.classList.add('ouro');
            else if (index === 1) tdPosicao.classList.add('prata');
            else if (index === 2) tdPosicao.classList.add('bronze');
            tdPosicao.textContent = index + 1;

            // Usuário
            const tdUsuario = document.createElement('td');
            tdUsuario.textContent = resultado.usuario;

            // Resultado
            const tdResultado = document.createElement('td');
            if (resultado.venceu) {
                tdResultado.textContent = '✅ Vitória';
                tdResultado.className = 'venceu';
            } else {
                tdResultado.textContent = '❌ Derrota';
                tdResultado.className = 'perdeu';
            }

            // Tempo
            const tdTempo = document.createElement('td');
            tdTempo.textContent = formatarTempo(resultado.tempo || 0);

            // Tentativas
            const tdTentativas = document.createElement('td');
            tdTentativas.textContent = resultado.tentativas || '-';

            // Data
            const tdData = document.createElement('td');
            const data = new Date(resultado.data);
            tdData.textContent = data.toLocaleDateString('pt-BR') + ' ' + data.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });

            tr.appendChild(tdPosicao);
            tr.appendChild(tdUsuario);
            tr.appendChild(tdResultado);
            tr.appendChild(tdTempo);
            tr.appendChild(tdTentativas);
            tr.appendChild(tdData);

            tbody.appendChild(tr);
        });
    }

    // Formata tempo em mm:ss
    function formatarTempo(segundos) {
        const min = Math.floor(segundos / 60);
        const seg = segundos % 60;
        return `${String(min).padStart(2, '0')}:${String(seg).padStart(2, '0')}`;
    }

    // Expor para os botões inline
    window.mostrarRanking = mostrarRanking;

    // Carregar ranking inicial
    mostrarRanking('facil');

}); 