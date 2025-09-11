function verificarMegaSena(nroSort, nroUser){

    /*if (nroSort.indexOf(nroUser) == -1)
        return false;
    else
        return true;*/

    // return nroSort.includes(nroUser);

    return (nroSort.indexOf(nroUser) == -1)?false:true;

}

nroSort = [10,58,7,33,14,9];

nroUser = parseInt(prompt("Qual número deseja verificar?"));

alert(verificarMegaSena(nroSort, nroUser));