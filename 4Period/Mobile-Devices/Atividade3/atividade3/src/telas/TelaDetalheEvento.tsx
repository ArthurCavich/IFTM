import { useEffect, useState } from 'react';
import { ActivityIndicator, StyleSheet, Text, View } from 'react-native';

export default function TelaDetalheEvento({ eventoId }) {
    const [evento, setEvento] = useState(null);
    const [erro, setErro] = useState(null);

    useEffect(() => {
        // Permite cancelar a busca se o usuário sair do detalhe.
        const controlador = new AbortController();

        fetch('https://api.campus.iftm.edu.br/eventos', {
            signal: controlador.signal,
        })
            .then((resposta) => {
                if (!resposta.ok) {
                    throw new Error(`HTTP ${resposta.status}`);
                }

                return resposta.json();
            })
            .then((eventos) => {
                const eventoEncontrado = eventos.find(
                    (item) => String(item.id) === eventoId
                );

                setEvento(eventoEncontrado ?? null);
            })
            .catch((e) => {
                // Cancelamento é esperado ao sair da tela, não é uma falha.
                if (e.name !== 'AbortError') {
                    setErro(e.message);
                }
            });

        // Cancela a requisição quando o componente é desmontado.
        return () => controlador.abort();
    }, [eventoId]);

    if (erro) {
        return <Text style={styles.erro}>Falha: {erro}</Text>;
    }

    if (!evento) {
        return <ActivityIndicator size="large" />;
    }

    return (
        <View style={styles.container}>
            <Text style={styles.titulo}>{evento.titulo}</Text>
            <Text style={styles.texto}>{evento.descricao}</Text>
            <Text style={styles.texto}>Vagas restantes: {evento.vagas}</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    container: { flex: 1, padding: 16, gap: 8 },
    titulo: { fontSize: 22, fontWeight: 'bold' },
    texto: { fontSize: 16 },
    erro: { color: '#B00020', padding: 16 },
});
