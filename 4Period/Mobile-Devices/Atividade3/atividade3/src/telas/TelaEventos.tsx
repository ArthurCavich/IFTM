// Importa o hook para acessar dados compartilhados entre telas.
import { useRouter } from 'expo-router';
import { useContext, useEffect, useReducer, useState } from 'react';

// Importa os componentes visuais usados nesta tela.
import {
    ActivityIndicator,
    FlatList,
    StyleSheet,
    Text, TextInput,
    View,
} from 'react-native';

// Importa o componente que desenha cada evento da lista.
import CartaoEvento from '../componentes/CartaoEvento';

// Importa o contexto global da aplicação.
import { AppContexto } from '../contextos/AppContexto';

// Importa a máquina de estados responsável pela busca dos eventos.
import {
    estadoInicialEventos,
    eventosReducer,
} from '../redutores/eventosReducer';

// Declara o componente principal da tela de eventos.
export default function TelaEventos() {
    // Obtém do contexto o tema e as inscrições atuais.
    const { temaEscuro, inscricoes, setInscricoes } = useContext(AppContexto);

    // Obtém o controlador de navegação do Expo Router.
    const router = useRouter();

    // Controla carregamento, sucesso, eventos e falha em um único estado.
    const [estadoEventos, dispatch] = useReducer(
        eventosReducer,
        estadoInicialEventos
    );

    // Extrai os dados atuais da máquina de estados.
    const { status, eventos, erro } = estadoEventos;

    // Guarda o texto digitado no campo de busca.
    const [busca, setBusca] = useState('');

    // Guarda somente o id do evento usado na mensagem de confirmação.
    const [eventoSelecionadoId, setEventoSelecionadoId] = useState(null);

    // Calcula a lista filtrada durante a renderização.
    // Como ela depende de eventos e busca, não precisa ser guardada em estado.
    const eventosFiltrados = eventos.filter((evento) =>
        evento.titulo.toLowerCase().includes(busca.toLowerCase())
    );

    // Localiza o evento selecionado a partir da fonte principal dos eventos.
    const eventoSelecionado = eventos.find(
        (evento) => evento.id === eventoSelecionadoId
    );

    // Calcula o total diretamente a partir da lista de inscrições.
    // Isso evita manter um segundo estado com a mesma informação.
    const totalInscricoes = inscricoes.length;

    // Executa a busca dos eventos uma vez, quando a tela é montada.
    useEffect(() => {
        // Informa ao redutor que a busca começou.
        dispatch({ type: 'CARREGANDO' });

        // Faz uma requisição para obter os eventos disponíveis.
        fetch('https://api.campus.iftm.edu.br/eventos')
            // Trata respostas HTTP que representam erro.
            .then((resposta) => {
                if (!resposta.ok) {
                    throw new Error(`HTTP ${resposta.status}`);
                }

                return resposta.json();
            })
            // Converte a resposta para JSON.
            // Atualiza a tela com os dados recebidos.
            .then((dados) => {
                dispatch({ type: 'SUCESSO', eventos: dados });
            })
            // Guarda a mensagem caso a requisição falhe.
            .catch((e) => {
                dispatch({ type: 'FALHA', erro: e.message });
            });
        // O array vazio faz este efeito executar apenas uma vez.
    }, []);

    // Executa esta função quando o usuário se inscreve em um evento.
    function inscrever(evento) {
        // Verifica se o usuário já está inscrito nesse evento.
        const jaInscrito = inscricoes.some(
            (inscricao) => inscricao.id === evento.id
        );

        // Impede que o mesmo evento seja adicionado novamente.
        if (jaInscrito) {
            return;
        }

        // Usa o estado mais atual e cria um novo array sem mutar o anterior.
        setInscricoes((inscricoesAtuais) => {
            // Confere novamente dentro do setter para tratar toques muito rápidos.
            if (inscricoesAtuais.some((inscricao) => inscricao.id === evento.id)) {
                return inscricoesAtuais;
            }

            // Adiciona o evento somente quando ele ainda não está inscrito.
            return [...inscricoesAtuais, evento];
        });

        // Guarda somente o id, evitando duplicar o objeto inteiro no estado.
        setEventoSelecionadoId(evento.id);

        // Ativa a mensagem de confirmação.
    }

    // Mostra no console toda vez que a tela é renderizada.
    console.log('[render] TelaEventos');

    // Retorna a interface visual da tela.
    return (
        // Define o container principal e sua cor de fundo.
        <View style={[styles.container,
        { backgroundColor: temaEscuro ? '#121212' : '#FFFFFF' }]}>
            {/* Mostra a quantidade atual de inscrições. */}
            <Text style={styles.contador}>Inscrições: {totalInscricoes}</Text>

            {/* Campo usado para pesquisar eventos pelo título. */}
            <TextInput
                style={styles.campo}
                value={busca}
                onChangeText={setBusca}
                placeholder="Buscar evento"
            />

            {/* Mostra o carregamento enquanto os eventos são buscados. */}
            {status === 'carregando' && <ActivityIndicator size="large" />}

            {/* Mostra o erro caso a busca dos eventos falhe. */}
            {status === 'falha' && erro && (
                <Text style={styles.erro}>Falha: {erro}</Text>
            )}

            {/* Mostra a confirmação depois de uma inscrição. */}
            {eventoSelecionado && (
                <Text style={styles.aviso}>
                    Inscrição confirmada em {eventoSelecionado.titulo}
                </Text>
            )}

            {/* Renderiza a lista de eventos filtrados. */}
            <FlatList
                data={eventosFiltrados}
                // Usa o id do evento como chave única da lista.
                keyExtractor={(itemLista) => String(itemLista.id)}
                // Define como cada evento deve ser renderizado.
                renderItem={({ item }) => (
                    <CartaoEvento
                        evento={item}
                        // Inscreve o usuário no evento selecionado.
                        aoInscrever={() => inscrever(item)}
                        // Abre a tela com os detalhes do evento.
                        aoAbrir={() =>
                            router.push({
                                pathname: '/detalhe',
                                params: { id: String(item.id) },
                            })}
                    />
                )}

            />
        </View>
    );
}

// Cria os estilos usados pelos componentes da tela.
const styles = StyleSheet.create({
    // Ocupa a tela inteira e adiciona espaçamento interno.
    container: { flex: 1, padding: 16 },

    // Formata o contador de inscrições.
    contador: { fontSize: 18, fontWeight: 'bold', marginBottom: 8 },

    // Formata o campo de busca.
    campo: {
        borderWidth: 1, borderColor: '#CCCCCC', borderRadius: 8,
        padding: 10, marginBottom: 12
    },

    // Define a aparência da mensagem de erro.
    erro: { color: '#B00020', marginBottom: 8 },

    // Define a aparência da mensagem de confirmação.
    aviso: { color: '#2E7D32', marginBottom: 8 },
});