import React from 'react'
import Animacao from './Animacao';

export default function Home() {
    return (

        <>
            <h1>Título Principal</h1>
            <ul>
                <li><a href="#sub5">Subtítulo 5</a></li>
                <li><a href="#sub10">Subtítulo 10</a></li>
            </ul>

            <h2 className="paragrafo1">Subtítulo 1</h2>
            <p className="paragrafo1">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>

            <h2 id='sub2'>Subtítulo 2</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>

            <h2>Subtítulo 3</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>


            <h2>Subtítulo 4</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <h2 id='sub5'>Subtítulo 5</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae adipisci quos maxime accusamus nesciunt totam, itaque dolor incidunt quas possimus? Tempore expedita numquam dolorem hic animi? Id, voluptates. Consequuntur, ratione.</p>

            <h2>Subtítulo 6</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <h2>Subtítulo 7</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <h2>Subtítulo 8</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <h2>Subtítulo 9</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>
            <h2 id='sub10' className='paragrafo10'>Subtítulo 10</h2>
            <p className='paragrafo10'>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum minus deserunt praesentium ipsam incidunt ea temporibus totam reiciendis corrupti fuga exercitationem, minima perferendis iure saepe, consequatur magni. Nihil, possimus ullam!</p>

            <Animacao nomeClasse=".paragrafo1" delay={1000} />
            <Animacao nomeClasse=".paragrafo10" delay={5000}/>
        </>

    )
}
