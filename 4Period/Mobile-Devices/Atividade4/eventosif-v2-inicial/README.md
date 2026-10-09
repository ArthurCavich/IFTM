# EventosIF 2.1 — estudo de caso

Aplicativo de eventos do campus usado no estudo de caso **Persistência de Dados · Otimização e Desempenho · Testes e Depuração** (Desenvolvimento para Dispositivos Móveis — IFTM). Leia o enunciado completo antes de começar.

## Primeiros passos

```bash
npm install
# 1) preencha nome e matrícula em aluno.config.js
npm test            # roda a suíte de testes deixada pelo estagiário
npm run bancada     # mede a aba Eventos e grava medicoes/ultima-bancada.md
npm start           # abre no Expo Go (opcional, mas recomendado para reproduzir os chamados)
```

Requisitos: Node.js 22.13 ou superior. O app roda no **Expo Go** (não há login com Google nem módulo nativo extra).

## Scripts

| Comando | O que faz |
|---|---|
| `npm test` | roda todos os testes de `__tests__/` |
| `npm run test:aleatorio` | roda os testes em ordem aleatória (`--randomize`); use `-- --seed=123` para repetir uma ordem |
| `npx jest -t "trecho do nome"` | roda só os testes cujo nome contém o trecho |
| `npm run bancada` | bancada de desempenho: tempo e número de renderizações de três interações |

No app, em modo de desenvolvimento, a tela **Início** mostra **Ferramentas de desenvolvimento**: simular o cache da versão 2.0, listar o conteúdo do armazenamento no console e limpar o armazenamento.

### Se o app travar num erro (por exemplo, depois de simular o cache da 2.0)

Em modo de desenvolvimento, depois de fechar a tela vermelha (**Dismiss**), aparece a tela **Recuperação (somente em desenvolvimento)** com o botão **Limpar armazenamento e recarregar**. Ela é uma ferramenta, não a correção do chamado C3: no app de produção ela não existe e o erro continua virando tela branca.

Se mesmo assim não conseguir sair, limpe os dados do Expo Go:

- Terminal (emulador ou celular via USB): `adb shell pm clear host.exp.exponent`
- Android: Configurações › Apps › Expo Go › Armazenamento › Limpar armazenamento
- iPhone: apague e reinstale o Expo Go

Depois, `npm start` e abra o app de novo.

## Estrutura

```
aluno.config.js            ← seu nome e matrícula (define a SUA massa de dados)
src/
  Aplicativo.js            ← provedores e abas
  api/eventosApi.js        ← API simulada (devolve objetos novos a cada chamada)
  armazenamento/           ← chaves, cache, sessão (token), migração de chaves
  contextos/               ← Eventos, Favoritos, Sessão
  hooks/useEstadoPersistido.js
  telas/Inicio.js, telas/ListaEventos.js
  dev/RecuperacaoDev.js    ← NÃO ALTERE (tela de recuperação, só em desenvolvimento)
  componentes/ItemEvento.js, componentes/FerramentasDev.js
  dados/gerarEventos.js    ← NÃO ALTERE
  utils/medicao.js         ← NÃO ALTERE (usado pela bancada)
__tests__/                 ← suíte do estagiário (você vai auditá-la)
bancada/bancada.test.js    ← NÃO ALTERE
RELATORIO.md               ← seu relatório (modelo pronto)
DIARIO-IA.md               ← seu diário de uso de IA (modelo pronto)
PROMPT-TUTOR.md            ← prompt para usar IA como tutora
AGENTS.md, CLAUDE.md, GEMINI.md, .github/copilot-instructions.md  ← NÃO ALTERE
```

## Contrato de interface (o professor roda testes próprios no seu repositório)

Você pode reescrever o código à vontade, **desde que mantenha**:

- os textos visíveis: `Início`, `Eventos`, `Buscar evento` (placeholder), `N eventos`, `Nenhum evento encontrado`, `Próximos eventos`, `Você tem N favoritos`, `Entrar`, `Sair`, `Sessão ativa`, e os nomes das categorias;
- os papéis de acessibilidade: abas com `accessibilityRole="tab"`, categorias e botões com `accessibilityRole="button"` e `accessibilityState.selected`;
- os rótulos da estrela: `Adicionar aos favoritos` / `Remover dos favoritos`;
- os `testID`: `evento-<id>`, `expandir-<id>`, `descricao-<id>`, `favoritar-<id>`, `proximo-<id>`;
- os nomes exportados em `src/armazenamento/` (`CHAVES`, `lerCache`, `salvarCache`, `guardarToken`, `lerToken`, `apagarToken`);
- as chamadas `contarRender('ItemEvento')` e `contarRender('ListaEventos')`.

## Convenção de commits

| Prefixo | Uso |
|---|---|
| `chore:` | preparação, medição inicial |
| `diag:` | hipóteses da Parte A (antes de qualquer correção) |
| `test(red):` | teste de regressão que **falha** antes da correção |
| `fix:` | correção de defeito (o teste vermelho fica verde) |
| `perf:` | uma otimização por commit, com a medição no relatório |
| `revert:` | desfaz um experimento que não melhorou |
| `docs:` | relatório e diário |
