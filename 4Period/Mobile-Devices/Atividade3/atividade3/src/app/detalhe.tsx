import { useLocalSearchParams } from 'expo-router';
import TelaDetalheEvento from '../telas/TelaDetalheEvento';

export default function DetalheRoute() {
  const { id } = useLocalSearchParams();

  return <TelaDetalheEvento eventoId={String(id)} />;
}
