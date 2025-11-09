window.addEventListener("DOMContentLoaded", function () {
    let informacaoLS = localStorage.getItem("informacao");
    document.getElementById("txtHome").innerHTML = informacaoLS;

    document.getElementById("btnPapaiNoel").addEventListener("click", function () {
        window.open('felizNatal.html', '_self')
    });

    document.getElementById("btnAnalisarTexto").addEventListener("click", function () {

        let txtAnalisar;
        let vetPalavrasAnalisar;

        let selOpcao = document.getElementById("selOpcao").value;
        if (selOpcao == "sim") {
            txtAnalisar = informacaoLS;
        } else {
            txtAnalisar = document.getElementById("campoTexto").value;
        }
        vetPalavrasAnalisar = txtAnalisar.split(" ");

        // 3) Total de palavras no texto
        document.getElementById("txtTotalPal").innerHTML = vetPalavrasAnalisar.length;

        // 4) Palavras iniciadas com a letra 'm' (ou 'M')
        let palavrasM = [];
        let letter = 'M';
        for (let i = 0; i < vetPalavrasAnalisar.length; i++) {
            if (vetPalavrasAnalisar[i].charAt(0).toUpperCase() == letter.toUpperCase()) {
                palavrasM.push(vetPalavrasAnalisar[i]);
            }
        }
        document.getElementById("txtPalLetraM").value = palavrasM.join('');

        // 5) Primeira palavra do texto
        document.getElementById("primPalavra").value = vetPalavrasAnalisar[0];

        // 6) Segunda palavra do texto
        document.getElementById("segPalavra").value = vetPalavrasAnalisar[1];

        // 7) Ultima palavra do texto
        document.getElementById("ultPalavra").value = vetPalavrasAnalisar[vetPalavrasAnalisar.length - 1];
    });
});

