# 🎮 Jogo da Forca - Projeto Final JavaScript Básico

## 📋 Descrição
Jogo da Forca desenvolvido como projeto final da disciplina de JavaScript Básico.

## 🚀 Funcionalidades

### ✅ Login e Cadastro
- Sistema de autenticação com localStorage
- Validação de dados com mensagens de erro personalizadas
- Cadastro de novos usuários

### ✅ Jogo da Forca
- **Níveis de Dificuldade:**
  - Fácil: 8 tentativas, 5 minutos
  - Médio: 6 tentativas, 3 minutos
  - Difícil: 4 tentativas, 2 minutos

- **Controles do Jogo:**
  - ⏯️ Iniciar/Pausar
  - ⏹️ Parar
  - 🚪 Sair

- **Timer:** Contador regressivo por nível
- **Palavras:** Mais de 60 palavras em diferentes categorias

### ✅ Ranking
- Ranking separado por nível de dificuldade
- Top 10 melhores jogadores
- Exibe vitórias, tempo e tentativas

## 📁 Estrutura de Arquivos

```
projeto final/
├── login.html          # Página de login
├── cadastro.html       # Página de cadastro
├── jogo.html           # Página do jogo
├── ranking.html        # Página de ranking
├── index.html          # Página inicial (redireciona para login)
├── css/
│   └── style.css       # Estilos do jogo
├── js/
│   ├── login.js        # Lógica de login
│   ├── cadastro.js     # Lógica de cadastro
│   ├── script.js        # Lógica principal do jogo
│   └── ranking.js      # Lógica do ranking
└── img/
    └── [imagens da forca]
```

## 🎯 Como Usar

1. Abra `login.html` no navegador
2. Faça login ou cadastre-se
3. Escolha o nível de dificuldade
4. Clique em "Iniciar" para começar
5. Adivinhe a palavra antes do tempo acabar!

## 🛠️ Tecnologias Utilizadas

- HTML5
- CSS3
- JavaScript (ES6+)
- Bootstrap 4
- SweetAlert2 (para alertas)
- Boxicons (para ícones)
- localStorage (para armazenamento)

## 📝 Notas

- Todos os dados são armazenados localmente no navegador
- O ranking é mantido por nível de dificuldade
- O jogo funciona offline após o primeiro carregamento

