// ─────────────────────────────────────────────────────────────
//  FERRAMENTA DE DESENVOLVIMENTO — NÃO É A CORREÇÃO DE NENHUM CHAMADO
//  Só é usada quando __DEV__ é verdadeiro (npm start). No app de
//  produção ela não existe: um erro de renderização continua virando
//  tela branca, exatamente como relatado no chamado C3.
//  Serve para sair do travamento depois de "Simular cache da versão 2.0"
//  sem precisar reinstalar o Expo Go. Não altere este arquivo.
// ─────────────────────────────────────────────────────────────
import { Component } from 'react';
import { DevSettings, Pressable, ScrollView, Text, StyleSheet } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export class RecuperacaoDev extends Component {
  state = { erro: null };

  static getDerivedStateFromError(erro) {
    return { erro };
  }

  async limparERecarregar() {
    await AsyncStorage.clear();
    console.log('[dev] AsyncStorage limpo. Recarregando o app...');
    DevSettings.reload();
  }

  render() {
    if (!this.state.erro) return this.props.children;
    return (
      <ScrollView contentContainerStyle={estilos.container}>
        <Text style={estilos.rotulo}>Recuperação (somente em desenvolvimento)</Text>
        <Text style={estilos.titulo}>O app quebrou ao renderizar</Text>
        <Text style={estilos.erro}>{String(this.state.erro.message)}</Text>
        <Text style={estilos.texto}>
          Em produção, o usuário veria só uma tela branca. Investigue a causa pela mensagem acima
          (ou pela tela vermelha). Se o problema estiver no armazenamento e você quiser voltar ao
          estado de primeira instalação, use o botão abaixo.
        </Text>
        <Pressable style={estilos.botao} onPress={() => this.limparERecarregar()}>
          <Text style={estilos.textoBotao}>Limpar armazenamento e recarregar</Text>
        </Pressable>
        <Pressable style={estilos.botaoSecundario} onPress={() => DevSettings.reload()}>
          <Text style={estilos.textoBotaoSecundario}>Recarregar sem limpar</Text>
        </Pressable>
      </ScrollView>
    );
  }
}

const estilos = StyleSheet.create({
  container: { flexGrow: 1, justifyContent: 'center', padding: 24, paddingTop: 64, gap: 12, backgroundColor: '#fff' },
  rotulo: { color: '#b45309', fontWeight: 'bold' },
  titulo: { fontSize: 20, fontWeight: 'bold', color: '#1e3a5f' },
  erro: { fontFamily: 'monospace', color: '#b91c1c', backgroundColor: '#fef2f2', padding: 12, borderRadius: 8 },
  texto: { color: '#334155' },
  botao: { backgroundColor: '#1e3a5f', padding: 14, borderRadius: 8, alignItems: 'center', marginTop: 8 },
  textoBotao: { color: '#fff', fontWeight: 'bold' },
  botaoSecundario: { padding: 14, borderRadius: 8, alignItems: 'center', borderWidth: 1, borderColor: '#cbd5e1' },
  textoBotaoSecundario: { color: '#1e3a5f' },
});
