import { createContext, useContext, useMemo, useState } from 'react';

type TemaContextoValor = {
    temaEscuro: boolean;
    setTemaEscuro: (temaEscuro: boolean) => void;
};

const TemaContexto = createContext<TemaContextoValor | undefined>(undefined);

export function TemaProvedor({ children }) {
    const [temaEscuro, setTemaEscuro] = useState(false);

    const valor = useMemo(
        () => ({ temaEscuro, setTemaEscuro }),
        [temaEscuro]
    );

    return (
        <TemaContexto.Provider value={valor}>
            {children}
        </TemaContexto.Provider>
    );
}

export function useTema() {
    const contexto = useContext(TemaContexto);

    if (!contexto) {
        throw new Error('useTema deve ser usado dentro de TemaProvedor.');
    }

    return contexto;
}
