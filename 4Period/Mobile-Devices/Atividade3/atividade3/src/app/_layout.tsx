import { Tabs } from 'expo-router';
import { InscricoesProvedor } from '../contextos/InscricoesContexto';
import { SessaoProvedor } from '../contextos/SessaoContexto';
import { TemaProvedor } from '../contextos/TemaContexto';

export default function RootLayout() {
  return (
    <TemaProvedor>
      <SessaoProvedor>
        <InscricoesProvedor>
          <Tabs>
            <Tabs.Screen name="index" options={{ title: 'Eventos' }} />
            <Tabs.Screen name="inscricoes" options={{ title: 'Inscrições' }} />
            <Tabs.Screen
              name="detalhe"
              options={{ href: null, title: 'Detalhe' }}
            />
          </Tabs>
        </InscricoesProvedor>
      </SessaoProvedor>
    </TemaProvedor>
  );
}
