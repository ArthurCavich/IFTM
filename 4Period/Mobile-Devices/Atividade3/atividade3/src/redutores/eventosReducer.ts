export type Evento = {
    id: number;
    titulo: string;
    [chave: string]: unknown;
};

export type EstadoEventos = {
    status: 'carregando' | 'sucesso' | 'falha';
    eventos: Evento[];
    erro: string | null;
};

type AcaoEventos =
    | { type: 'CARREGANDO' }
    | { type: 'SUCESSO'; eventos: Evento[] }
    | { type: 'FALHA'; erro: string };

export const estadoInicialEventos: EstadoEventos = {
    status: 'carregando',
    eventos: [],
    erro: null,
};

export function eventosReducer(
    estado: EstadoEventos,
    acao: AcaoEventos
): EstadoEventos {
    switch (acao.type) {
        case 'CARREGANDO':
            return {
                status: 'carregando',
                eventos: [],
                erro: null,
            };
        case 'SUCESSO':
            return {
                status: 'sucesso',
                eventos: acao.eventos,
                erro: null,
            };
        case 'FALHA':
            return {
                status: 'falha',
                eventos: [],
                erro: acao.erro,
            };
        default:
            return estado;
    }
}
