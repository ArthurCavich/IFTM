// Função para cadastrar novo usuário
document.addEventListener("DOMContentLoaded", function() {
    const formCadastro = document.getElementById("formCadastro");
    
    formCadastro.addEventListener("submit", function(e) {
        e.preventDefault();
        
        const nome = document.getElementById("nome").value.trim();
        const username = document.getElementById("username").value.trim();
        const senha = document.getElementById("senha").value.trim();
        const confirmarSenha = document.getElementById("confirmarSenha").value.trim();
        
        // Validação dos campos
        if (nome === "" || username === "" || senha === "" || confirmarSenha === "") {
            Swal.fire({
                icon: 'error',
                title: 'Erro!',
                text: 'Por favor, preencha todos os campos!',
                confirmButtonColor: '#d18b00'
            });
            return;
        }
        
        // Validação do tamanho mínimo
        if (username.length < 3) {
            Swal.fire({
                icon: 'error',
                title: 'Erro!',
                text: 'O nome de usuário deve ter pelo menos 3 caracteres!',
                confirmButtonColor: '#d18b00'
            });
            return;
        }
        
        // Requisitos de senha: mínimo 6 chars, pelo menos 1 letra e 1 número
        const senhaRegex = /^(?=.*[A-Za-z])(?=.*\d).{6,}$/;
        if (!senhaRegex.test(senha)) {
            Swal.fire({
                icon: 'error',
                title: 'Senha fraca!',
                text: 'Use pelo menos 6 caracteres, com letras e números.',
                confirmButtonColor: '#d18b00'
            });
            return;
        }
        
        // Validação se as senhas coincidem
        if (senha !== confirmarSenha) {
            Swal.fire({
                icon: 'error',
                title: 'Erro!',
                text: 'As senhas não coincidem!',
                confirmButtonColor: '#d18b00'
            });
            return;
        }
        
        // Buscar usuários existentes
        const usuarios = JSON.parse(localStorage.getItem('usuarios') || '[]');
        
        // Verificar se o usuário já existe
        const usuarioExiste = usuarios.find(u => u.username === username);
        
        if (usuarioExiste) {
            Swal.fire({
                icon: 'error',
                title: 'Erro!',
                text: 'Este nome de usuário já está em uso!',
                confirmButtonColor: '#d18b00'
            });
            return;
        }
        
        // Adicionar novo usuário
        const novoUsuario = {
            nome: nome,
            username: username,
            senha: senha
        };
        
        usuarios.push(novoUsuario);
        localStorage.setItem('usuarios', JSON.stringify(usuarios));
        
        Swal.fire({
            icon: 'success',
            title: 'Cadastro realizado!',
            text: 'Conta criada com sucesso!',
            confirmButtonColor: '#8b008b'
        }).then(() => {
            window.location.href = 'login.html';
        });
    });
});

