//Problema
//Um supermercado possuía apenas um caixa para atendimento dos clientes, representado inicialmente pela Fila 1.
//Com o aumento da quantidade de clientes, foi necessário abrir mais dois caixas:
//•	Fila 2 
//•	Fila 3 
//A partir deste momento:
//•	toda nova inserção continuará sendo realizada apenas na Fila 1; 
//•	porém, deverá existir uma opção no menu responsável por redistribuir os clientes da Fila 1 entre as Filas 2 e 3.
//Regras da redistribuição
//Ao selecionar a opção redistribuir
//o programa deverá:
//•	remover elementos da Fila 1; 
//•	distribuir alternadamente entre: 
//o	Fila 2 
//o	Fila 3 
//Exemplo:
//Antes:	Fila 1: 10 20 30 40 50 60
//                Fila 2:
//       		Fila 3:
//
//Depois: 	Fila 1:
// 		Fila 2: 10 30 50
// 		Fila 3: 20 40 60
//Observação
//•	A inserção sempre será na Fila1
//•	Ao imprimir, as três filas deverão ser apresentadas.***	

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <locale.h>

// -----------------------------------------------------------
// Estrutura do nó da fila (lista encadeada simples)
// Cada nó armazena um número inteiro e um ponteiro para o próximo.
// -----------------------------------------------------------
typedef struct apelido_no {
    int dado;
    struct apelido_no *proximo;
} no;

// -----------------------------------------------------------
// Ponteiros globais para controle de cada fila:
//   frente -> primeiro elemento (cabeça)
//   fim    -> último elemento (cauda)
// Quando a fila está vazia: frente = NULL e fim = NULL
// -----------------------------------------------------------

// FILA 1 - onde sempre entram novos clientes
no *frente1 = NULL;
no *fim1 = NULL;

// FILA 2 - recebe parte da redistribuição
no *frente2 = NULL;
no *fim2 = NULL;

// FILA 3 - recebe parte da redistribuição
no *frente3 = NULL;
no *fim3 = NULL;

// -----------------------------------------------------------
// Protótipos das funções (declarações antecipadas)
// -----------------------------------------------------------
void enqueue1(int item, int parada);  // enfileirar na fila 1
void enqueue2(int item, int parada);  // enfileirar na fila 2
void enqueue3(int item, int parada);  // enfileirar na fila 3

void dequeue1(int parada);            // desenfileirar da fila 1
void dequeue2(int parada);            // desenfileirar da fila 2
void dequeue3(int parada);            // desenfileirar da fila 3

void imprimir(no *fr, char nome[]);   // imprime os elementos de uma fila
void redistribuir();                  // redistribui da fila1 para fila2/fila3
int entrada_dados();                  // lê um valor do usuário

// -----------------------------------------------------------
// FUNÇÃO PRINCIPAL
// -----------------------------------------------------------
int main() {
    setlocale(LC_ALL, "Portuguese");

    int n, opcao, i;
    srand(time(NULL));   // semente para números aleatórios

    // Loop principal do menu: repete até que o usuário digite 6 (Sair)
    do {
        system("cls");   // limpa a tela (Windows)
        printf("\n=========== MENU DA FILA DO SUPERMERCADO ===========\n");
        printf("1. Enfileirar (Fila 1)\n");
        printf("2. Enfileirar 10 Números aleatórios (Fila 1)\n");
        printf("3. Desenfileirar\n");
        printf("4. Imprimir filas\n");
        printf("5. Redistribuir as filas\n");
        printf("6. Sair\n");

        printf("\nEscolha uma opcao: ");
        scanf("%d", &opcao);

        switch (opcao) {
            // -------------------------------------------------
            case 1:   // insere um único número digitado pelo usuário
                n = entrada_dados();
                enqueue1(n, 1);
                break;

            // -------------------------------------------------
            case 2:   // insere 10 números aleatórios (0 a 99) na Fila 1
                for (i = 0; i < 10; i++)
                    enqueue1(rand() % 100, 0);  // parada=0 -> não pausa
                system("pause");
                break;

            // -------------------------------------------------
            case 3:   // remove um elemento de qualquer fila (escolha do usuário)
            {
                int fila;
                printf("\nRemover de qual fila?");
                printf("\n1 - Fila 1");
                printf("\n2 - Fila 2");
                printf("\n3 - Fila 3");
                printf("\nOpcao: ");
                scanf("%d", &fila);
                switch (fila) {
                    case 1:
                        dequeue1(1);
                        break;
                    case 2:
                        dequeue2(1);
                        break;
                    case 3:
                        dequeue3(1);
                        break;
                    default:
                        printf("\nFila invalida\n");
                        system("pause");
                }
                break;
            }

            // -------------------------------------------------
            case 4:   // imprime o conteúdo das três filas
                system("cls");
                imprimir(frente1, "FILA 1");
                imprimir(frente2, "FILA 2");
                imprimir(frente3, "FILA 3");
                system("pause");
                break;

            // -------------------------------------------------
            case 5:   // redistribui os elementos da Fila 1 entre Fila 2 e Fila 3
                redistribuir();
                break;
        }
    } while (opcao != 6);  // repete até a opção 6 (Sair)

    // -----------------------------------------------------------
    // Antes de terminar, esvazia todas as filas para liberar memória
    // -----------------------------------------------------------
    while (frente1) dequeue1(0);   // parada=0 -> sem pausa
    while (frente2) dequeue2(0);
    while (frente3) dequeue3(0);

    return 0;
}

// ============================================================
// FUNÇÕES DE ENFILEIRAR (inserir no final)
// ============================================================

// -----------------------------------------------------------
// enqueue1: insere um elemento no final da Fila 1
// Parâmetro "parada": se for 1, pausa a execução; se 0, não pausa.
// -----------------------------------------------------------
void enqueue1(int item, int parada) {
    no *novo = malloc(sizeof(no));
    if (novo == NULL) {   // verifica se a alocação falhou
        printf("Erro de memoria\n");
        return;
    }
    novo->dado = item;
    novo->proximo = NULL;   // será o novo último, então próximo é NULL

    if (fim1 == NULL) {     // fila estava vazia
        frente1 = novo;     // novo nó é também o primeiro
        fim1 = novo;        // e também o último
    } else {                // fila já tem elementos
        fim1->proximo = novo;  // liga o antigo último ao novo
        fim1 = novo;           // atualiza o ponteiro fim
    }
    printf("\nValor %d enfileirado na fila 1\n", novo->dado);
    if (parada == 1) system("pause");
}

// enqueue2 e enqueue3 são idênticas, mas operam sobre fila2 e fila3.
void enqueue2(int item, int parada) {
    no *novo = malloc(sizeof(no));
    if (novo == NULL) {
        printf("Erro de memoria\n");
        return;
    }
    novo->dado = item;
    novo->proximo = NULL;
    if (fim2 == NULL) {
        frente2 = novo;
        fim2 = novo;
    } else {
        fim2->proximo = novo;
        fim2 = novo;
    }
    printf("\nValor %d enfileirado na fila 2\n", novo->dado);
    if (parada == 1) system("pause");
}

void enqueue3(int item, int parada) {
    no *novo = malloc(sizeof(no));
    if (novo == NULL) {
        printf("Erro de memoria, agora complicou :( \n");
        return;
    }
    novo->dado = item;
    novo->proximo = NULL;
    if (fim3 == NULL) {
        frente3 = novo;
        fim3 = novo;
    } else {
        fim3->proximo = novo;
        fim3 = novo;
    }
    printf("\nValor %d enfileirado na fila 3\n", novo->dado);
    if (parada == 1) system("pause");
}

// ============================================================
// FUNÇÕES DE DESENFILEIRAR (remover do início)
// ============================================================

void dequeue1(int parada) {
    printf("\nDESENFILEIRANDO A FILA 1...\n");
    if (frente1 == NULL) {
        printf("\nFila vazia\n");
    } else {
        no *temp = frente1;      // guarda o nó que será removido
        frente1 = frente1->proximo;  // avança o início para o próximo
        if (frente1 == NULL)     // se a fila ficou vazia, ajusta o fim
            fim1 = NULL;
        printf("\n%d desenfileirado!\n", temp->dado);
        free(temp);              // libera a memória do nó removido
    }
    if (parada == 1) system("pause");
}

void dequeue2(int parada) {
    printf("\nDESENFILEIRANDO  A FILA 2...\n");
    if (frente2 == NULL) {
        printf("\nFila vazia\n");
    } else {
        no *temp = frente2;
        frente2 = frente2->proximo;
        if (frente2 == NULL) fim2 = NULL;
        printf("\n%d desenfileirado!\n", temp->dado);
        free(temp);
    }
    if (parada == 1) system("pause");
}

void dequeue3(int parada) {
    printf("\nDESENFILEIRANDO A FILA 3...\n");
    if (frente3 == NULL) {
        printf("\nFila vazia\n");
    } else {
        no *temp = frente3;
        frente3 = frente3->proximo;
        if (frente3 == NULL) fim3 = NULL;
        printf("\n%d desenfileirado!\n", temp->dado);
        free(temp);
    }
    if (parada == 1) system("pause");
}

// ============================================================
// FUNÇÃO PARA IMPRIMIR UMA FILA
// Recebe o ponteiro para o primeiro nó e o nome da fila.
// Percorre a lista encadeada exibindo os valores.
// ============================================================
void imprimir(no *fr, char nome[]) {
    no *temp = fr;
    printf("\n%s\n", nome);
    while (temp != NULL) {
        printf("%d ", temp->dado);
        temp = temp->proximo;
    }
    printf("\n");
}

// ============================================================
// FUNÇÃO DE REDISTRIBUIÇÃO (principal para o problema)
// ============================================================
void redistribuir() {
    // contador para alternar entre as filas 2 e 3
    // início = 0 -> primeiro elemento vai para fila 2
    int cont = 0;

    // Enquanto a Fila 1 não estiver vazia...
    while (frente1 != NULL) {
        // 1. Lê o valor do primeiro elemento da Fila 1
        int valor = frente1->dado;

        // 2. Remove esse nó da Fila 1 (desenfileira manualmente)
        no *temp = frente1;        // guarda o nó a ser removido
        frente1 = frente1->proximo; // frente1 aponta para o próximo
        if (frente1 == NULL)       // se a fila ficou vazia, atualiza fim1
            fim1 = NULL;
        free(temp);                // libera a memória do nó removido

        // 3. Distribui alternadamente: par (0,2,4,...) vai para fila2;
        //    ímpar (1,3,5,...) vai para fila3.
        if (cont % 2 == 0)
            enqueue2(valor, 0);    // insere na fila 2 (sem pausa)
        else
            enqueue3(valor, 0);    // insere na fila 3 (sem pausa)

        // 4. Incrementa o contador para alternar na próxima vez
        cont++;
    }

    printf("\nFilas redistribuidas com sucesso!\n");
    system("pause");
}

// ============================================================
// FUNÇÃO PARA LER UM NÚMERO DO TECLADO (usada na opção 1)
// ============================================================
int entrada_dados() {
    int valor;
    printf("\nEntre com valor a enfileirar: ");
    scanf("%d", &valor);
    return valor;
}
