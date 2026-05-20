import React from 'react'
import style from './Titulo.module.css'

function TituloSecao({ subtitulo = "", descricao = "" }) {

    function txtToUpperCase(txt) {
        return txt.toUpperCase();
    }

    // se for necessário, podemos criar functions

    return (
        <div className={style.tituloSecao}>
            <h2 className={style.subtitulo}>{txtToUpperCase(subtitulo)}</h2>
            <p>{descricao}</p>
        </div>
    )
}

export default TituloSecao