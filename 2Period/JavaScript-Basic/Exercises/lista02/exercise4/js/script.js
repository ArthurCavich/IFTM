n = parseInt(prompt("Digite o primeiro número: "));
m = parseInt(prompt("Digite o segundo número: "));

if (n>m){
    controle = n;
    n = m;
    m = controle;
}

nroAleatorio = parseInt(Math.random() * (m - n + 1)) + n;

document.write(nroAleatorio);