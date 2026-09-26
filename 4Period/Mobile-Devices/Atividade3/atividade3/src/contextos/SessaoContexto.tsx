import { createContext, useContext, useMemo, useState } from 'react';

type Usuario = {
    nome: string;
    matricula: string | null;
};

type SessaoContextoValor = {
    usuario: Usuario;
    setUsuario: (usuario: Usuario) => void;
    notificacoes: unknown[];
    setNotificacoes: (notificacoes: unknown[]) => void;
    ultimaBusca: string;
    setUltimaBusca: (busca: string) => void;
};

const SessaoContexto = createContext<SessaoContextoValor | undefined>(
    undefined
);

export function SessaoProvedor({ children }) {
    const [usuario, setUsuario] = useState<Usuario>({
        nome: 'Visitante',
        matricula: null,
    });
    const [notificacoes, setNotificacoes] = useState<unknown[]>([]);
    const [ultimaBusca, setUltimaBusca] = useState('');

    const valor = useMemo(
        () => ({
            usuario,
            setUsuario,
            notificacoes,
            setNotificacoes,
            ultimaBusca,
            setUltimaBusca,
        }),
        [usuario, notificacoes, ultimaBusca]
    );

    return (
        <SessaoContexto.Provider value={valor}>
            {children}
        </SessaoContexto.Provider>
    );
}

export function useSessao() {
    const contexto = useContext(SessaoContexto);

    if (!contexto) {
        throw new Error('useSessao deve ser usado dentro de SessaoProvedor.');
    }

    return contexto;
}
