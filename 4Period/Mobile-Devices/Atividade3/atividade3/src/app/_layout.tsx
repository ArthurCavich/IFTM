import { Tabs } from 'expo-router';
import { AppProvedor } from '../contextos/AppContexto';
import { InscricoesProvedor } from '../contextos/InscricoesContexto';

export default function RootLayout() {
  return (
    <AppProvedor>
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
    </AppProvedor>
  );
}
