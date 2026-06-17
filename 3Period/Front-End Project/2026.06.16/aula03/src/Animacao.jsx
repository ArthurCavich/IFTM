import React, { useEffect } from 'react'
import ScrollReveal from 'scrollreveal';

export default function Animacao({nomeClasse, delay}) {

    useEffect(() => {
        ScrollReveal({ reset: false }).reveal(`${nomeClasse}`, {
            duration: 1000,
            scale: 0.1,
            easing: 'ease-in',
            delay: delay
        });
    }, []);

    return (


        <>
        </>
    );
}
