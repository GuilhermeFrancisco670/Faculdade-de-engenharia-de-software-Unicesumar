---
name: grill-me-impl
description: 'Sessão de entrevista focada em detalhes de implementação de código. Diferente da grill-me original (que discute plano/design de forma ampla), esta skill mantém o desenvolvedor no controle das decisões técnicas e de regra de negócio que a IA toma durante a codificação, pausando para confirmação sempre que uma decisão relevante para o domínio do sistema é criada. Só deve ser usada mediante invocação explícita do usuário, por exemplo /grill-me-impl, nunca automaticamente.'
metadata:
  invocation: explicit-only
---

# Grill Me — Implementação

Versão adaptada da skill `grill-me`, com foco estreito em **detalhes de implementação** em vez de plano/design amplo. O objetivo é manter o desenvolvedor no controle das decisões que a IA toma ao escrever código — sem exigir que ele leia linha por linha.

Use esta skill sempre que o usuário invocar `/grill-me-impl` (ou pedir explicitamente para "ativar o modo grill-me-impl") antes ou durante uma tarefa de codificação.

## Objetivo

Duas responsabilidades, nesta ordem:

1. **Entrevista de implementação** — antes de escrever código, questionar o usuário sobre decisões técnicas concretas (não sobre o problema de negócio em si, que já deve estar claro, mas sobre *como* ele será implementado).
2. **Checkpoint de regra de negócio** — durante a escrita do código, parar e confirmar com o usuário toda vez que uma linha ou bloco represente uma decisão de regra de negócio ou de domínio (condicional, validação, cálculo, tratamento de exceção específico do domínio, etc.).

O usuário quer manter domínio sobre o sistema sem precisar revisar cada linha gerada. Portanto: seja seletivo. Questionar demais é tão ruim quanto questionar de menos — vira ruído e o usuário para de prestar atenção.

## Fase 1 — Entrevista de implementação (antes de codificar)

Antes de começar a escrever o código, faça uma rodada curta e objetiva de perguntas sobre a implementação — não sobre o "o quê" (o requisito), mas sobre o "como". Exemplos do tipo de pergunta a fazer (adapte ao contexto real da tarefa):

- Estrutura de dados: qual formato/tipo deve representar essa entidade? (ex: enum vs string, objeto vs classe)
- Tratamento de erros: o que deve acontecer quando a operação falha ou recebe entrada inválida?
- Casos de borda: como lidar com valores nulos, vazios, limites (mínimo/máximo), duplicados?
- Integração: essa lógica deve viver em uma função nova, um serviço existente, um hook, etc.?
- Persistência/estado: o dado é local, precisa ser salvo, precisa sincronizar com algo?
- Convenções: há um padrão do projeto a seguir (nomenclatura, camadas, estilo) que deva ser respeitado?

Regras para essa fase:
- Faça perguntas objetivas, de múltipla escolha quando possível, para reduzir o esforço de resposta.
- Não pergunte sobre decisões triviais (formatação, nomes de variáveis internas, indentação) — isso não é implementação relevante.
- Não avance para o código até ter respostas suficientes para tomar as decisões de implementação com segurança. Se o usuário disser "decide você" ou "usa o padrão", registre isso como resposta válida e prossiga.
- Se a tarefa for pequena e sem ambiguidade real (ex: "corrige esse typo"), pule a entrevista e vá direto ao código — não force perguntas onde não há decisão a tomar.

## Fase 2 — Checkpoint de regra de negócio (durante a codificação)

Enquanto escreve o código, **pare e pergunte ao usuário** sempre que estiver prestes a criar algo que constitua uma **decisão de regra de negócio ou de domínio**. Isso inclui, por exemplo:

- Uma condicional (`if`/`switch`/ternário) que decide um comportamento do sistema baseado em uma regra do domínio (ex: "se o pedido tiver mais de 3 itens, aplica desconto").
- Uma validação que define o que é um dado "válido" ou "inválido" para o negócio (ex: idade mínima, limite de crédito, formato de documento).
- Um cálculo que produz um valor de negócio (preço final, prazo, pontuação, taxa).
- Uma decisão sobre o que acontece em um caminho de exceção específico do domínio (ex: "se o estoque zerar, cancela o pedido" vs "coloca em espera").
- Qualquer valor "mágico" ou limiar (threshold) que representa uma política do negócio (ex: `if (dias > 30)`).

**NÃO** pare para:
- Decisões puramente técnicas sem impacto de negócio (nome de variável, escolha entre `for` e `map`, formatação, imports, estrutura de arquivos).
- Código que apenas implementa o que já foi explicitamente decidido na Fase 1 ou dito diretamente pelo usuário na conversa.
- Repetições do mesmo tipo de decisão já confirmada anteriormente na mesma tarefa (ex: se o usuário já definiu a regra de desconto, não pergunte de novo para cada função que a usa — apenas na primeira vez que a regra é criada).

### Como perguntar

Quando identificar uma decisão de regra de negócio a ser criada:

1. Pare *antes* de escrever aquele trecho (não escreva primeiro e pergunte depois).
2. Explique em uma frase curta qual decisão está prestes a tomar e por quê.
3. Apresente a interpretação padrão que você pretende usar, e pergunte se está correta — evite perguntas abertas quando uma confirmação sim/não ou uma escolha entre 2-3 opções resolver.
4. Só depois de confirmado, escreva o trecho de código correspondente e continue.

Formato sugerido:

> ⚠️ **Checkpoint de regra de negócio**: vou implementar [descrição curta da regra] como [interpretação/decisão proposta]. Confirma ou ajusta?

Mantenha essas pausas curtas e específicas — uma pergunta por decisão, sem acumular várias perguntas não relacionadas na mesma pausa.

## Encerramento

Ao final da tarefa, se houver mais de uma decisão de regra de negócio confirmada ao longo da conversa, feche com um resumo curto em lista das decisões tomadas (regra → decisão), para que o usuário tenha um registro rápido sem precisar reler o código.
