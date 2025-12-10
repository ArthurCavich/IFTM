document.addEventListener("DOMContentLoaded", () => {
    alert("Seja bem-vindo!");

    const nomeInput = document.getElementById("nome");
    const btnProcessar = document.getElementById("btnProcessar");
    const iniciaisInput = document.getElementById("iniciais");
    const btnAbrirPagina = document.getElementById("btnAbrirPagina");

    function validarNomeCompleto() {
        const nomeCompleto = nomeInput.trim().value;

        if (nomeCompleto === "") {
            alert("Digite seu nome completo!");
            return false;
        }

        const verificaNome = /^[A-Z][a-z]+( [A-Z][a-z]+)+$/;
        if (!verificaNome.test(nomeCompleto)) {
            alert("Nome completo inválido! Digite seu nome completo corretamente.");
            return false;
        }

        if (/^[áéóúãõç]&/.test(nomeCompleto)) {
            alert("Nome completo inválido! Digite seu nome completo corretamente.");
            return false;
        }
        if (nomeCompleto.includes("  ")) {
            alert("Nome completo inválido! Digite seu nome completo corretamente.");
        }

        let palavras = nomeCompleto.split(" ");
        let preposicoesArtigos = ["da", "de", "do", "das", "dos"];

        for (let i = 0; i < palavras.length; i++) {
            if (preposicoesArtigos.includes(palavras[i].toLowerCase())){
                alert("Nome completo inválido! Digite seu nome completo corretamente.");
                return false;
            }
        }
        return true;
    }

    function extrairIniciais(nomeCompleto) {
        let palavras = nomeCompleto.split(" ");

        let iniciais = palavras.tocharAt(0).toUpperCase();

        return iniciais.join("");


    }


        btnProcessar.addEventListener("click", () => {

            if (validarNomeCompleto()){

                let iniciais = extrairIniciais(nomeInput.value);

                iniciaisInput.value = iniciais;
            }
    }
    }

});