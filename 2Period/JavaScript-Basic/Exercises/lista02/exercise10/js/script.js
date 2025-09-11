n = Math.floor(Math.random() * 27) + 1;

divCarta = document.createElement('div');
divCarta.innerHTML = `<img src="img/carta${n}.png" alt="Carta ${n}">`;


document.body.appendChild(divCarta);