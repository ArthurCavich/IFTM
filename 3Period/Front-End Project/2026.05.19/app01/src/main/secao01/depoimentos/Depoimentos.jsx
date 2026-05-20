import React from 'react'
import Box from '../../comuns/box/Box'
import style from './Depoimentos.module.css'

export default function Depoimentos() {
    return (
        <div className={style.depoimentos}>
            <Box texto="sou o texto do cliente 1" />
            <Box texto="sou o texto do cliente 2" />
            <Box texto="sou o texto do cliente 3" />
        </div>
    )
}
