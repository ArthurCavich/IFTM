import './App.css';
import Cabecalho from './components/Cabecalho';
import Card from './components/Card';
import Botao from './components/Botao';

export function App() {
  return (
    <div className="app">
      <Cabecalho 
        titulo="Acesso à Informação" 
        subtitulo="Veja dados de transparência e governança" 
      />
      
      <div className="cards-grid">
        <Card titulo="PLANO DE DESENVOLVIMENTO INSTITUCIONAL" />
        <Card titulo="PESQUISA PÚBLICA PROCESSOS IFTM" />
        <Card titulo="LICITAÇÕES E CONTRATOS" />
        <Card titulo="RECEITAS E DESPESAS" />
        <Card titulo="DADOS ABERTOS" />
        <Card titulo="TRANSPARÊNCIA E PRESTAÇÃO DE CONTAS" />
        <Card titulo="SERVIDORES" />
        <Card titulo="PERGUNTAS FREQUENTES" />
      </div>

      <div className="button-container">
        <Botao texto="Mais informações"/>
      </div>
    </div>
  );
}

