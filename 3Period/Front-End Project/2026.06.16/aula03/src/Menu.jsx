import React from 'react'
import style from './Menu.module.css'
import { Link } from 'react-router-dom';

export default function Menu() {
  return (

    <nav>
      <Link to="/">Home</Link>
      <Link to="/contatos">Contatos</Link>
    </nav>

  )
}
