import './App.css';
import Cabecalho from './components/Cabecalho';
import Card from './components/Card';

export function App() {
  return (
    <div className="app">
      <Cabecalho 
        titulo="Acesso à Informação" 
        subtitulo="Veja dados de transparência e governança" 
      />
      
      <div className="cards-grid">
        <Card titulo="PLANO DE DESENVOLVIMENTO INSTITUCIONAL" />
      </div>
    </div>
  );
}

