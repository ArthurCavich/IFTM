function main (){
    x = parseFloat(prompt("Digite o valor de X: "));
    y = parseFloat(prompt("Digite o valor de Y: "));    
    op = prompt("Qual operação? +,-,/,*");

    resultado = calculadora(x, y, op);
}

/*function calculadora (x, y, op){ 
    switch (op){
        case 1:
            alert(`A soma é ${x + y}`);
            break;
        case 2:
            alert(`A subtração é ${x - y}`);
            break;
        case 3:
            if (y <= 0){
                alert("Divisão incorreta");
            }
            else {
                alert(`A divisão é ${x / y}`);
            }
            break;
        case 4:
            alert(`A multiplicação é ${x + y}`);
            break;
    }
}*/

function calculadora (x, y, op){ 
    alert(`${x} ${op} ${y} = ${eval(x+op+y)}`)
}


main();