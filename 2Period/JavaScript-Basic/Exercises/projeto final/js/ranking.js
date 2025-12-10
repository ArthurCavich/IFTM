// Função para mostrar ranking por nível
function mostrarRanking(nivel) {
    // Atualizar tabs
    document.querySelectorAll('.tab-nivel').forEach(tab => {
        tab.classList.remove('active');
    });
    event.target.classList.add('active');
    
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
        tdUsuario.textContent = resultado.username;
        
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
        tdData.textContent = data.toLocaleDateString('pt-BR') + ' ' + data.toLocaleTimeString('pt-BR', {hour: '2-digit', minute: '2-digit'});
        
        tr.appendChild(tdPosicao);
        tr.appendChild(tdUsuario);
        tr.appendChild(tdResultado);
        tr.appendChild(tdTempo);
        tr.appendChild(tdTentativas);
        tr.appendChild(tdData);
        
        tbody.appendChild(tr);
    });
}

// Função para formatar tempo
function formatarTempo(segundos) {
    const min = Math.floor(segundos / 60);
    const seg = segundos % 60;
    return `${String(min).padStart(2, '0')}:${String(seg).padStart(2, '0')}`;
}

// Carregar ranking ao carregar a página
document.addEventListener("DOMContentLoaded", function() {
    mostrarRanking('facil');
});

