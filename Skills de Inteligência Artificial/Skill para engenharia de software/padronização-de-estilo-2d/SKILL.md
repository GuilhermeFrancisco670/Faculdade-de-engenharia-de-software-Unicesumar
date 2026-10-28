---
name: consistencia-estilo-2d
description: Padronização de estilo visual e transferência de estilo (Style Transfer) para ativos e animações 2D. Garante a manutenção da coesão de traço, iluminação, resolução e cores entre sprites, NPCs, cenários e itens utilizando imagens de referência e âncoras estilo ControlNet ou IP-Adapter.
---

# Consistência de Estilo e Style Transfer 2D

Habilidade para garantir a padronização e a coesão visual entre diferentes assets 2D de jogos (personagens, itens, cenários e NPCs), contornando limitações de IA relacionadas a variações não desejadas de traço, iluminação, resolução e sombreamento.

## Quando Usar

Use esta habilidade quando o usuário solicitar:

* Manutenção de consistência visual entre múltiplos assets ou quadros de animação 2D.

* Aplicação de transferência de estilo (Style Transfer) utilizando uma imagem de referência ou imagem âncora.

* Criação de novos elementos (armas, itens de inventário, cenários, NPCs) mantendo a mesma iluminação e traço do personagem principal.

* Definição de fluxos de trabalho e prompts com ControlNet, IP-Adapter ou Image-to-Image para preservação de estilo.

## Fluxo de Trabalho e Capacidades

### 1. Extração da Assinatura Visual (Imagem Âncora)
Aprenda e mapeie os atributos visuais da imagem âncora (ex: sprite do personagem principal):

* **Linha e Traço:** Espessura da linha de contorno (outline), estilo (pixel art, vetor, pintura).

* **Iluminação e Sombreamento:** Ângulo e fonte de luz, nível de contraste, tipo de sombreamento (cel-shading, gradiente suave, flat).

* **Paleta de Cores e Saturação:** Nível de saturação, paleta limitada, tom dominante.

* **Resolução e Detalhamento:** Densidade de pixels/detalhes e escala.

### 2. Transferência de Estilo para Novos Assets
Ao solicitar novos elementos por texto (ex: "um machado de ferro" ou "um novo inimigo"):

* **Uso de Imagem de Referência:** Defina o sprite principal como imagem âncora em algoritmos de Style Transfer, ControlNet ou IP-Adapter.

* **Transferência Estruturada:** Force o algoritmo a copiar a iluminação, profundidade, cores e textura da imagem âncora para o novo asset.

### 3. Estruturação de Prompts e Parâmetros
Crie prompts alinhados à assinatura visual extraída:

* Mantenha descritores fixos de estilo em todas as requisições (ex: `same art style as anchor image, matching line weight, identical cel-shading`).

* Utilize prompts negativos padronizados para evitar desvios visuais.

## Boas Práticas e Recomendações

* **Ancoragem Única:** Utilize sempre a mesma imagem base (ou conjunto reduzido e consistente) como referência principal para todo o projeto.

* **Ajuste de Pesos:** Em ferramentas como ControlNet ou IP-Adapter, balanceie o peso do estilo para preservar a estética sem desfigurar a forma do novo objeto.

* **Validação Cruzada:** Compare o novo asset lado a lado com a imagem âncora antes de integrá-lo ao jogo.
