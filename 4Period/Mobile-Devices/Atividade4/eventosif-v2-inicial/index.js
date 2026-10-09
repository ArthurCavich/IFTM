import { registerRootComponent } from 'expo';
import Aplicativo from './src/Aplicativo';
import { RecuperacaoDev } from './src/dev/RecuperacaoDev';

// Em desenvolvimento (npm start), um erro de renderização mostra uma tela de
// recuperação com o botão "Limpar armazenamento". Em produção, nada muda.
function AplicativoDev() {
  return (
    <RecuperacaoDev>
      <Aplicativo />
    </RecuperacaoDev>
  );
}

registerRootComponent(__DEV__ ? AplicativoDev : Aplicativo);
