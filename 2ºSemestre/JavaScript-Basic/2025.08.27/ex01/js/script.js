nome = prompt("Digite seu nome completo:", "Arthur Santana Cavichioli");

opcao = prompt("[MENU]"+
     "\n[1] Letras Maísculas" +
     "\n[2] Letras Minúsculas" +
     "\n[3] Primeira Letra Maiúscula do Primeiro Nome" +
     "\n[4] Primeiro Nome" +
     "\n[5] Primeiro Nome Maiúsculo e Último Nome Minúsculo" +
     "\n[6] Substitua o Primeiro Nome Por Um Novo Nome" +
     "\n[7] Primeira Letra do Primeiro Nome e Ultimo Nome Maiúsculo Concatenadas" +
     "\n[8] Exiba As 3 Primeiras Letras Do Nome");

     switch(opcao){
        case '1':
            document.write(`<h1> ${nome.toUpperCase()} </h1>`);
            break;
        case '2':
            document.write(`<h1> ${nome.toLowerCase()} </h1>`);
            break;
        case '3':
            document.write(`<h1> ${nome.charAt(0).toUpperCase() + nome.slice(1).toLowerCase()} </h1>`);
            // document.write(`<h1> ${nome.substring(0,1).toUpperCase() + nome.slice(1).toLowerCase()}<\h1>`);
            break;
        case '4':
            document.write(`<h1> ${nome.split(' ')[0]} </h1>`);
            break;
        case '5':
            vetorNome = nome.split(' ');
            document.write(`<h1> ${vetorNome[0].toUpperCase()} ${vetorNome[vetorNome.lenght-1].toLowerCase()} </h1>`);
            break;
        case '6':
            novoNome = prompt("Coloque o segundo nome");
            vetorNome = nome.split(' ');
            vetorNome[0] = novoNome;
            document.write(`<h1> ${vetorNome.join(' ')} </h1>`);
            break;
        case '7':
            vetorNome = nome.split(' ');
            document.write(`<h1> ${vetorNome[0].charAt(0)} ${vetorNome[vetorNome.lenght-1].charAt(0)} </h1>`);
            break;
        case '8':
            document.write(`<h1> ${nome.substring(0,3)} </h1>`)
            break;
        default:
            document.write("Opção inválida!");
     }