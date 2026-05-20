import React from 'react'
import TituloSecao from '../comuns/tituloSecao/TituloSecao'
import Depoimentos from './depoimentos/Depoimentos'

export default function Secao01() {
  return (
    <section>
      <TituloSecao subtitulo="Subtítulo 1" descricao="Sou a descrição do subtítulo 1" />
      <Depoimentos/>
    </section>
  )
}
