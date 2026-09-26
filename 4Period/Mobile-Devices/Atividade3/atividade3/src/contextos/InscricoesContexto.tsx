import { createContext, useContext, useMemo, useState } from 'react';

type InscricoesContextoValor = {
    inscricoesIds: number[];
    inscrever: (id: number) => void;
    cancelar: (id: number) => void;
    estaInscrito: (id: number) => boolean;
};

const InscricoesContexto = createContext<InscricoesContextoValor | undefined>(
    undefined
);

export function InscricoesProvedor({ children }) {
    const [inscricoesIds, setInscricoesIds] = useState<number[]>([]);

    function inscrever(id: number) {
        setInscricoesIds((idsAtuais) => {
            if (idsAtuais.includes(id)) {
                return idsAtuais;
            }

            return [...idsAtuais, id];
        });
    }

    function cancelar(id: number) {
        setInscricoesIds((idsAtuais) =>
            idsAtuais.filter((idAtual) => idAtual !== id)
        );
    }

    const valor = useMemo(
        () => ({
            inscricoesIds,
            inscrever,
            cancelar,
            estaInscrito: (id: number) => inscricoesIds.includes(id),
        }),
        [inscricoesIds]
    );

    return (
        <InscricoesContexto.Provider value={valor}>
            {children}
        </InscricoesContexto.Provider>
    );
}

export function useInscricoes() {
    const contexto = useContext(InscricoesContexto);

    if (!contexto) {
        throw new Error(
            'useInscricoes deve ser usado dentro de InscricoesProvedor.'
        );
    }

    return contexto;
}
