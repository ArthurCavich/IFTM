import { useEffect, useState } from 'react';
import { Button, FlatList, StyleSheet, Text, View } from 'react-native';
import { useInscricoes } from '../contextos/InscricoesContexto';

export default function TelaMinhasInscricoes() {
    const { inscricoesIds, cancelar } = useInscricoes();
    const [eventos, setEventos] = useState([]);

    useEffect(() => {
        const controlador = new AbortController();

        fetch('https://api.campus.iftm.edu.br/eventos', {
            signal: controlador.signal,
        })
            .then((resposta) => resposta.json())
            .then((dados) => setEventos(dados))
            .catch((e) => {
                if (e.name !== 'AbortError') {
                    console.error('[inscricoes] Falha ao buscar eventos', e);
                }
            });

        return () => controlador.abort();
    }, []);

    // Deriva os eventos exibidos a partir dos ids compartilhados.
    const inscricoes = eventos.filter((evento) =>
        inscricoesIds.includes(evento.id)
    );

    console.log('[render] TelaMinhasInscricoes');

    return (
        <View style={styles.container}>
            <Text style={styles.titulo}>
                Minhas inscrições ({inscricoes.length})
            </Text>
            <FlatList
                data={inscricoes}
                keyExtractor={(item) => String(item.id)}
                renderItem={({ item }) => (
                    <View style={styles.linha}>
                        <Text>{item.titulo}</Text>
                        <Button title="Cancelar"
                            onPress={() => cancelar(item.id)} />
                    </View>
                )}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    container: { flex: 1, padding: 16 },
    titulo: { fontSize: 20, fontWeight: 'bold', marginBottom: 12 },
    linha: {
        flexDirection: 'row', alignItems: 'center',
        justifyContent: 'space-between', paddingVertical: 8
    },
});
