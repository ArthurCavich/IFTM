// Importa o hook para acessar dados compartilhados entre telas.
import { useRouter } from 'expo-router';
import { useContext, useEffect, useState } from 'react';

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

// Declara o componente principal da tela de eventos.
export default function TelaEventos() {
    // Obtém do contexto o tema e as inscrições atuais.
    const { temaEscuro, inscricoes, setInscricoes } = useContext(AppContexto);

    // Obtém o controlador de navegação do Expo Router.
    const router = useRouter();

    // Guarda a lista completa de eventos recebida da API.
    const [eventos, setEventos] = useState([]);

    // Controla a exibição do indicador de carregamento.
    const [carregando, setCarregando] = useState(true);

    // Guarda uma possível mensagem de erro da requisição.
    const [erro, setErro] = useState(null);

    // Indica se uma inscrição foi enviada.
    const [enviado, setEnviado] = useState(false);

    // Guarda o texto digitado no campo de busca.
    const [busca, setBusca] = useState('');

    // Guarda o evento usado na mensagem de confirmação.
    const [eventoSelecionado, setEventoSelecionado] = useState(null);

    // Calcula a lista filtrada durante a renderização.
    // Como ela depende de eventos e busca, não precisa ser guardada em estado.
    const eventosFiltrados = eventos.filter((evento) =>
        evento.titulo.toLowerCase().includes(busca.toLowerCase())
    );

    // Calcula o total diretamente a partir da lista de inscrições.
    // Isso evita manter um segundo estado com a mesma informação.
    const totalInscricoes = inscricoes.length;

    // Executa a busca dos eventos uma vez, quando a tela é montada.
    useEffect(() => {
        // Faz uma requisição para obter os eventos disponíveis.
        fetch('https://api.campus.iftm.edu.br/eventos')
            // Converte a resposta para JSON.
            .then((resposta) => resposta.json())
            // Atualiza a tela com os dados recebidos.
            .then((dados) => {
                setEventos(dados);
                setCarregando(false);
            })
            // Guarda a mensagem caso a requisição falhe.
            .catch((e) => {
                setErro(e.message);
            });
        // O array vazio faz este efeito executar apenas uma vez.
    }, []);

    // Executa esta função quando o usuário se inscreve em um evento.
    function inscrever(evento) {
        // Cria um novo array para o React detectar a alteração imediatamente.
        setInscricoes([...inscricoes, evento]);

        // Guarda o evento que acabou de receber a inscrição.
        setEventoSelecionado(evento);

        // Ativa a mensagem de confirmação.
        setEnviado(true);
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
            {carregando && <ActivityIndicator size="large" />}

            {/* Mostra o erro caso a busca dos eventos falhe. */}
            {erro && <Text style={styles.erro}>Falha: {erro}</Text>}

            {/* Mostra a confirmação depois de uma inscrição. */}
            {enviado && eventoSelecionado && (
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
                                params: { evento: JSON.stringify(item) },
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