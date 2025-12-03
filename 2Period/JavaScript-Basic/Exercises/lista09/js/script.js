document.addEventListener('DOMContentLoaded', function () {
    //Botão de preenchimento automático da data de nascimento:
    document.getElementById("btnPreencherNasc").addEventListener("click", function () {
        nascimento.value = "01/01/2000";
    })

    //Botão de validação da Data de Nascimento:
    document.getElementById("btnValidarNasc").addEventListener("click", function () {
        let validacaoNasc = /^(0[1-9]|[12]\d|3[01])[/][0-1]\d[/]\d{2,4}$/;
        (validacaoNasc.test(nascimento.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático do CPF:
    document.getElementById("btnPreencherCPF").addEventListener("click", function () {
        cpf.value = "123.456.789-00";
    })

    //Botão de validação do CPF (com Jwifi):
    document.getElementById("btnValidarCPF").addEventListener("click", function () {
        let validacaoCpf = /^\d{3}[.]\d{3}[.]\d{3}[-]\d{2}$/;

        (validacaoCpf.test(cpf.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático da matrícula:
    document.getElementById("btnPreencherMatricula").addEventListener("click", function () {
        matricula.value = "IFTM-123/456-AB";
    })

    //Botão de validação da matrículo:
    document.getElementById("btnValidarMatricula").addEventListener("click", function () {
        let validacaoMatricula = /^IFTM-\d{3}[/]\d{3}[-]\w{2}$/i;

        (validacaoMatricula.test(matricula.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático da disciplina:
    document.getElementById("btnPreencherDisciplina").addEventListener("click", function () {
        disciplina.value = "MT-12.456-IFTM";
    })

    //Botão de validação da disciplina (com Jwifi):

    document.getElementById("btnValidarDisciplina").addEventListener("click", function () {
        let validacaoDisciplina = /^MT-\d{2}[.]\d{3}-IFTM$/i;

        (validacaoDisciplina.test(disciplina.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático da disciplina 2:
    document.getElementById("btnPreencherDisciplina2").addEventListener("click", function () {
        disciplina2.value = "MT-12.456-IFTM";
    })

    //Botão de validação da disciplina (com Jwifi) 2:
    document.getElementById("btnValidarDisciplina2").addEventListener("click", function () {
        let validacaoDisciplina2 = /^MT-\d{2}[.]\d{3}-(I|i)(F|f)(T|t)(M|m)$/;

        (validacaoDisciplina2.test(disciplina2.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático da disciplina 3:
    document.getElementById("btnPreencherDisciplina3").addEventListener("click", function () {
        disciplina3.value = "MT-12.456-IFTM";
    })

    //Botão de validação da disciplina (com Jwifi) 3:
    document.getElementById("btnValidarDisciplina3").addEventListener("click", function () {
        let validacaoDisciplina3 = /^M\s?T-\d{2}[.]\d{3}-I\s?F\s?T\s?M$/i;

        (validacaoDisciplina3.test(disciplina3.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático da disciplina 4:
    document.getElementById("btnPreencherDisciplina4").addEventListener("click", function () {
        disciplina4.value = "MT-12.456-IFTM Uberlândia Centro";
    })

    //Botão de validação da disciplina (com Jwifi) 4:
    document.getElementById("btnValidarDisciplina4").addEventListener("click", function () {
        let validacaoDisciplina4 = /^(M|m)\s?(T|t)-\d{2}[.]\d{3}-(I|i)\s?(F|f)\s?(T|t)\s?(M|m)\s(Uberlândia Centro|Uberlândia)$/;

        (validacaoDisciplina4.test(disciplina4.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático do campus:
    document.getElementById("btnPreencherCampus").addEventListener("click", function () {
        campus.value = "IFTM campus Uberlândia Centro";
    })

    //Botão que valida o nome do campus:
    document.getElementById("btnValidarCampus").addEventListener("click", function () {
        let validacaoCampus = /^IFTM campus Uberlândia( Centro)?$/;

        (validacaoCampus.test(campus.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático do telefone:    
    document.getElementById("btnPreencherTelefone").addEventListener("click", function () {
        telefone.value = "+55(11)99999-9999";
    })

    //Botão de validação do telefone:
    document.getElementById("btnValidarTelefone").addEventListener("click", function () {
        let validacaoTelefone = /^\+55\(\d{2}\)\d{5}-\d{4}$/;

        (validacaoTelefone.test(telefone.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático do telefone 2:
    document.getElementById("btnPreencherTelefone2").addEventListener("click", function () {
        telefone2.value = "(11)99999-9999";
    })

    //Botão de validação do telefone 2:
    document.getElementById("btnValidarTelefone2").addEventListener("click", function () {
        let validacaoTelefone2 = /^\(\d{2}\)\d{5}-\d{4}$|^\(\d{3}\)\d{5}-\d{4}$/;

        (validacaoTelefone2.test(telefone2.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático da altura:
    document.getElementById("btnPreencherAltura").addEventListener("click", function () {
        altura.value = "1,3";
    })

    //Botão de validação da altura:
    document.getElementById("btnValidarAltura").addEventListener("click", function () {
        let altura = document.getElementById("altura").value;

        // Regex: aceita x,xz ou x.xz (onde z é opcional)
        let validacaoAltura = /^[1-2][.,]\d\d?$/;

        if (validacaoAltura.test(altura)) {
            // Converte vírgula para ponto e transforma em número
            let alturaNum = parseFloat(altura.replace(',', '.'));

            // Valida o intervalo [1.3, 2.5]
            if (alturaNum >= 1.3 && alturaNum <= 2.5) {
                alert("Válido");
            } else {
                alert("Inválido - Altura fora do intervalo permitido (1.3 a 2.5)");
            }
        } else {
            alert("Inválido - Formato incorreto");
        }
    });

    //Botão de preenchimento automático do faturamento:
    document.getElementById("btnPreencherFaturamento").addEventListener("click", function () {
        faturamento.value = "R$999.999.999.999,99";
    })

    //Botão de validação do faturamento:
    document.getElementById("btnValidarFaturamento").addEventListener("click", function () {
        let validacaoFaturamento = /^R\$\d{1,3}(\.\d{1,3})*,\d{1,2}$/;

        (validacaoFaturamento.test(faturamento.value) ? alert("Válido") : alert("Inválido"));
    })

    //Botão de preenchimento automático do cronômetro:
    document.getElementById("btnPreencherCrono").addEventListener("click", function () {
        cronometro.value = "14:35:47:89";
    })

    //Botão de validação do cronômetro:
    document.getElementById("btnValidarCrono").addEventListener("click", function () {
        
        let validacaoCrono = /^([01]\d|2[0-3]):([0-5]\d):([0-5]\d):(\d{2})$/;

        if (validacaoCrono.test(cronometro.value)) {
            // Valida os valores individualmente
            let partes = cronometro.value.split(':');
            let hh = parseInt(partes[0]);
            let mm = parseInt(partes[1]);
            let ss = parseInt(partes[2]);
            let cc = parseInt(partes[3]);

            if (hh >= 0 && hh <= 23 && mm >= 0 && mm <= 59 && ss >= 0 && ss <= 59 && cc >= 0 && cc <= 99) {
                alert("Válido");
            } else {
                alert("Inválido");
            }
        } else {
            alert("Inválido");
        }
    })

    //Botão de preenchimento automático da senha:
    document.getElementById("btnPreencherSenha").addEventListener("click", function () {
        senha.value = "321a.&apMb.aei.Ab0-asf+_,/*.cq";
    })

    //Botão de validação da senha:
    document.getElementById("btnValidarSenha").addEventListener("click", function () {
        
        // Regex com Z (opcional)
        let validacaoSenha1 = /^[a-zA-Z0-9._-]{5,}&[a-pA-P]+\.[aeiou]+\.[a-zA-Z0-5]+-[^0-9]+,[^\w]{2}\.[^ab01]+$/;

        // Regex sem Z
        let validacaoSenha2 = /^[a-zA-Z0-9._-]{5,}&[a-pA-P]+\.[aeiou]+-[^0-9]+,[^\w]{2}\.[^ab01]+$/;

        if (validacaoSenha1.test(senha.value) || validacaoSenha2.test(senha.value)) {
            alert("Válido");
        } else {
            alert("Inválido");
        }
    })
})
