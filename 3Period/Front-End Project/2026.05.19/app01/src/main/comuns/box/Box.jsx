import React from 'react'
import style from './Box.module.css'

export default function Box({ texto }) {
    return (
        <div className={style.box}>
            <p>{texto}</p>
        </div>
    )
}
