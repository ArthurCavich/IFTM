function exibirNomes(nomes, indice) {
    const resultado = document.getElementById('resultado');
    
    if (indice < nomes.length) {
        const p = document.createElement('p');
        p.textContent = nomes[indice];
        resultado.appendChild(p);
        
        setTimeout(function() {
            exibirNomes(nomes, indice + 1);
        }, 1000);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const btnGerar = document.getElementById('btnGerar');
    const btnExibir = document.getElementById('btnExibir');
    const inputQuantidade = document.getElementById('quantidade');
    const camposNomes = document.getElementById('camposNomes');
    
    btnGerar.addEventListener('click', function() {
        const quantidade = parseInt(inputQuantidade.value);
        if (quantidade > 0) {
            camposNomes.innerHTML = '';
            
            for (let i = 0; i < quantidade; i++) {
                const label = document.createElement('label');
                label.textContent = 'Nome ' + (i + 1) + ':';
                label.setAttribute('for', 'nome' + i);
                
                const input = document.createElement('input');
                input.type = 'text';
                input.id = 'nome' + i;
                input.name = 'nome' + i;
                
                const br = document.createElement('br');
                
                camposNomes.appendChild(label);
                camposNomes.appendChild(input);
                camposNomes.appendChild(br);
            }
            
            btnExibir.style.display = 'block';
        }
    });
    
    btnExibir.addEventListener('click', function() {
        const quantidade = parseInt(inputQuantidade.value);
        const nomes = [];
        
        for (let i = 0; i < quantidade; i++) {
            const nome = document.getElementById('nome' + i).value;
            if (nome.trim() !== '') {
                nomes.push(nome);
            }
        }
        
        const resultado = document.getElementById('resultado');
        resultado.innerHTML = '';
        
        if (nomes.length > 0) {
            exibirNomes(nomes, 0);
        }
    });
});

