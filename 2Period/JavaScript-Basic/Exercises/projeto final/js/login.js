document.addEventListener("DOMContentLoaded", function() {
    const formularioLogin = document.getElementById("formularioLogin");
    
    // Lida com o envio do formulário de login
    formularioLogin.addEventListener("submit", function(e) {
        e.preventDefault();
        
        const usuario = document.getElementById("usuario").value.trim();
        const senha = document.getElementById("senha").value.trim();
        
        // Validação dos campos
        if (usuario === "" || senha === "") {
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
        const usuarioEncontrado = usuarios.find(u => u.usuario === usuario && u.senha === senha);
        
        if (usuarioEncontrado) {
            // Salvar usuário logado
            localStorage.setItem('usuarioLogado', JSON.stringify({
                usuario: usuario,
                nome: usuarioEncontrado.nome
            }));
            
            Swal.fire({
                icon: 'success',
                title: 'Login realizado!',
                text: 'Bem-vindo, ' + usuario + '!',
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

