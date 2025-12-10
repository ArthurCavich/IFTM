// Cadastra novo usuário
document.addEventListener("DOMContentLoaded", function() {
    const formularioCadastro = document.getElementById("formularioCadastro");
    
    // Lida com envio do formulário de cadastro
    formularioCadastro.addEventListener("submit", function(e) {
        e.preventDefault(); // previne o envio do formulário
        
        const nome = document.getElementById("nome").value.trim();
        const usuario = document.getElementById("usuario").value.trim();
        const senha = document.getElementById("senha").value.trim();
        const confirmarSenha = document.getElementById("confirmarSenha").value.trim();
        
        // Validação dos campos
        if (nome === "" || usuario === "" || senha === "" || confirmarSenha === "") {
            Swal.fire({
                icon: 'error',
                title: 'Erro!',
                text: 'Por favor, preencha todos os campos!',
                confirmButtonColor: '#d18b00'
            });
            return;
        }
        
        // Validação do tamanho mínimo
        if (usuario.length < 3) {
            Swal.fire({
                icon: 'error',
                title: 'Erro!',
                text: 'O nome de usuário deve ter pelo menos 3 caracteres!',
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
        const usuarioExiste = usuarios.find(u => u.usuario === usuario);
        
        if (usuarioExiste) {
            Swal.fire({ // exibe o alerta de erro
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
            usuario: usuario,
            senha: senha
        };
        
        usuarios.push(novoUsuario);
        localStorage.setItem('usuarios', JSON.stringify(usuarios)); // salva os usuários no localStorage
        
        Swal.fire({
            icon: 'success',
            title: 'Cadastro realizado!',
            text: 'Conta criada com sucesso!',
            confirmButtonColor: '#8b008b' // cor do botão de confirmação
        }).then(() => {
            window.location.href = 'login.html'; // redireciona para a página de login
        });
    });
});

