// Função para validar e fazer login
document.addEventListener("DOMContentLoaded", function() {
    const formLogin = document.getElementById("formLogin");
    
    formLogin.addEventListener("submit", function(e) {
        e.preventDefault();
        
        const username = document.getElementById("username").value.trim();
        const senha = document.getElementById("senha").value.trim();
        
        // Validação dos campos
        if (username === "" || senha === "") {
            Swal.fire({
                icon: 'error',
                title: 'Erro!',
                text: 'Por favor, preencha todos os campos!',
                confirmButtonColor: '#d18b00'
            });
            return;
        }
        
        // Buscar usuário no localStorage
        const usuarios = JSON.parse(localStorage.getItem('usuarios') || '[]');
        const usuarioEncontrado = usuarios.find(u => u.username === username && u.senha === senha);
        
        if (usuarioEncontrado) {
            // Salvar usuário logado
            localStorage.setItem('usuarioLogado', JSON.stringify({
                username: username,
                nome: usuarioEncontrado.nome
            }));
            
            Swal.fire({
                icon: 'success',
                title: 'Login realizado!',
                text: 'Bem-vindo, ' + username + '!',
                confirmButtonColor: '#d18b00',
                timer: 1500,
                showConfirmButton: true
            }).then(() => {
                window.location.href = 'jogo.html';
            });
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Erro de Login!',
                text: 'Usuário ou senha inválidos!',
                confirmButtonColor: '#d18b00'
            });
        }
    });
});

