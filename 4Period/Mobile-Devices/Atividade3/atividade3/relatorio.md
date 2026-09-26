# Relatorio de decisao

## 1. Classificacao dos dados

| Dado | Categoria | Ferramenta | Justificativa |
| --- | --- | --- | --- |
| `busca` | Estado local | `useState` | E usado somente na tela de eventos e vem da digitacao do usuario. |
| `eventos` | Estado de servidor | `fetch` + `useReducer` | Vem de uma API externa e possui estados de carregamento, sucesso e falha. |
| `eventosFiltrados` | Nao e estado | Variavel calculada | E derivado de `eventos` e `busca`, portanto e calculado na renderizacao. |
| `inscricoes` | Estado global de cliente | Context API | E compartilhado entre eventos e minhas inscricoes; o contexto guarda apenas os IDs. |
| `totalInscricoes` | Nao e estado | `inscricoesIds.length` | E somente a quantidade de IDs inscritos. |
| `eventoSelecionadoId` | Estado local/de navegacao | `useState` + parametro de rota | Guarda apenas o identificador do evento selecionado, nao uma copia do objeto. |
| `temaEscuro` | Estado global de cliente | `TemaContexto` | Pode afetar varias telas e e fornecido pelo contexto de tema. |
| `usuario` | Estado global de cliente | `SessaoContexto` | Representa dados da sessao e pode ser usado por varias telas. |

## 2. Estados impossiveis e R4

Antes do R4, quatro variaveis correlacionadas (`eventos`, `carregando`, `erro` e `enviado`) podiam ser tratadas como tendo ou nao tendo valor. Isso produzia $2^4 = 16$ combinacoes possiveis, embora somente quatro fossem estados uteis: ocioso, carregando, sucesso e falha. Assim, havia 12 combinacoes invalidas, como carregamento e erro ao mesmo tempo.

Depois do R4, o ciclo da requisicao e representado por um unico `status` no reducer: `carregando`, `sucesso` ou `falha`. Portanto, existem tres estados nomeados e os estados impossiveis deixam de ser representaveis. A tela nao mostra mais o indicador de carregamento junto com a mensagem de erro.

## 3. Arvore de decisao para `inscricoes`

1. Precisa sobreviver ao fechamento do aplicativo? Sim, segundo o chamado C7. Portanto, a solucao completa deveria incluir persistencia local.
2. Enquanto o aplicativo esta aberto, varias telas precisam do dado? Sim: Eventos e Minhas inscricoes, alem dos componentes relacionados.
3. Como sao telas distantes, o dado deve ser global de cliente. A escolha foi Context API, com `InscricoesContexto` e o hook `useInscricoes()`.
4. Para evitar duas fontes de verdade, o contexto guarda somente `inscricoesIds`; os objetos exibidos sao derivados da lista de eventos.

A persistencia nao foi implementada nesta refatoracao porque o enunciado determina que AsyncStorage sera tratado em outro momento.

## 4. Por que Context API

A Context API foi escolhida porque o aplicativo tem poucos estados globais e poucas telas. Ela ja faz parte do React, nao adiciona dependencia e e suficiente para tema, sessao e inscricoes. O custo e que consumidores de um contexto podem renderizar novamente quando seu valor muda; por isso os contextos foram separados por responsabilidade e seus valores foram memorizados com `useMemo`.

Zustand ou Redux Toolkit seriam justificaveis se o aplicativo crescesse muito, tivesse muitas fatias de estado, regras complexas, middleware, historico de acoes ou grande quantidade de consumidores. Nesse cenario pequeno, adicionar uma biblioteca aumentaria a complexidade sem beneficio proporcional.

## 5. IDs em vez de objetos e o chamado C3

`inscricoesIds` guarda somente identificadores porque guardar o objeto inteiro criaria uma copia do evento. A lista original poderia ser atualizada pela API enquanto a copia permaneceria antiga. Isso cria duas fontes de verdade e explica o C3: a tela de detalhe recebia um retrato estatico pelo parametro de rota e podia mostrar vagas desatualizadas.

Ao navegar, a aplicacao envia somente o `id`. A tela de detalhe busca novamente o evento pela fonte de servidor, evitando uma copia envelhecida.

## 6. Quinta morada: persistencia

`inscricoes` e `temaEscuro` pertencem a dados persistidos, pois deveriam sobreviver ao fechamento do aplicativo. Nesta etapa eles permanecem apenas em memoria porque o enunciado proibe tratar C7 com codigo agora. Se fosse usado AsyncStorage, as inscricoes e o tema poderiam ser restaurados ao reabrir o aplicativo, resolvendo o chamado C7.

## 7. Estado de servidor

Ao guardar o resultado de um `fetch` em estado, temos um retrato local e temporario do servidor; por isso precisamos decidir como tratar carregamento, erro, cancelamento, atualizacao, cache e possiveira resposta desatualizada.

## Uso de assistente de IA

Foi utilizado o GitHub Copilot para auxiliar na leitura do estudo de caso, explicar os conceitos, sugerir e aplicar as refatoracoes R1 a R7, revisar os commits e orientar as validacoes. A equipe verificou por conta propria os diffs, os commits, a compilacao com `npx expo export --platform android` e os criterios de aceite. A API de eventos estava indisponivel durante parte dos testes, portanto os testes visuais com eventos reais ficaram limitados.
