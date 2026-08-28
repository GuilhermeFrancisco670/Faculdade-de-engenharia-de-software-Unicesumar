# ReUse+ — AEP Engenharia de Software

Sistema acadêmico para organização de doações e redistribuição de itens, alinhado aos ODS 1, 10 e 12.

## Integrantes

- Guilherme Francisco Moreira Martinelli
- Luiz Eduardo dos Santos Corrêa

## Documentação

A documentação da Parte 1 está em [`docs/PARTE_1_AEP_REUSE.md`](docs/PARTE_1_AEP_REUSE.md).

Os diagramas estão em [`docs/diagramas/`](docs/diagramas/):

- `diagrama-classes.mmd`: classes, herança, polimorfismo e composição 1:N.
- `diagrama-der.mmd`: entidades, chaves e relacionamentos do banco.

O esquema SQL inicial para **MySQL 8.x** está em [`database/001_schema.sql`](database/001_schema.sql).

## Escopo da Parte 1

O ReUse+ deverá cadastrar usuários, categorias, itens, doações, solicitações e entregas; permitir consultas e filtros; controlar o ciclo de status; registrar histórico; e emitir um resumo das doações. O contrato completo está descrito no documento da Parte 1.

## Cronograma simulado

| Sprint | Período sugerido | Entrega principal | Responsável |
|---|---|---|---|
| 1 | 31/08–11/09/2026 | Fundação, conexão SQL e usuários | Guilherme |
| 2 | 14/09–25/09/2026 | Catálogo e CRUD de doações | Luiz |
| 3 | 28/09–09/10/2026 | Solicitações e regras de aprovação | Guilherme e Luiz |
| 4 | 12/10–23/10/2026 | Entregas, histórico, relatórios e testes | Guilherme e Luiz |
| Reserva | 26/10–30/10/2026 | Correções e preparação final | Guilherme e Luiz |

As datas são simuladas porque o calendário oficial da turma ainda não foi informado. O grupo deve ajustá-las quando o professor divulgar o cronograma.

## Estrutura planejada

```text
.
├── README.md
├── src/
├── docs/
│   ├── PARTE_1_AEP_REUSE.md
│   ├── diagramas/
│   │   ├── diagrama-classes.mmd
│   │   └── diagrama-der.mmd
│   └── pdf/
└── database/
    └── 001_schema.sql
```

## Tecnologias planejadas para a Parte 2

A implementação deverá usar Java, JDBC e **MySQL 8.x**, com arquitetura em camadas: apresentação, aplicação/serviços, domínio e persistência.

## Execução do banco na Parte 2

Com o MySQL 8.x instalado, execute o arquivo `database/001_schema.sql` no cliente MySQL ou MySQL Workbench. O script cria o banco `reuse_plus`, as tabelas, as chaves estrangeiras, os índices e as categorias iniciais. A aplicação Java deverá usar o driver JDBC do MySQL e variáveis de configuração para endereço, porta, banco, usuário e senha.

## Parte 2 — implementação

A implementação Java da Parte 2 está documentada em [`docs/parte2/PARTE_2_IMPLEMENTACAO.md`](docs/parte2/PARTE_2_IMPLEMENTACAO.md). O projeto Maven utiliza Java 17, JDBC e MySQL 8.x. O núcleo implementado contém cadastro, listagem, alteração de status e exclusão lógica de doações, além de demonstrar classe abstrata, herança e polimorfismo com `@Override`.

Para compilar, execute `mvn clean package`. Para iniciar o menu de terminal, execute `mvn exec:java` após configurar `REUSE_DB_URL`, `REUSE_DB_USER` e `REUSE_DB_PASSWORD`.

## Fonte institucional

O projeto segue o guia, o exemplo e as diretrizes gerais da AEP fornecidos pela disciplina. O alinhamento aos ODS é fundamentado nas páginas oficiais da [ONU Brasil](https://brasil.un.org/pt-br/sdgs) e da [ONU](https://sdgs.un.org/goals).
