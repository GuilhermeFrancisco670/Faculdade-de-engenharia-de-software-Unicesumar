//4) Desenvolva um programa em linguagem C que aloque dinamicamente um vetor de números
//inteiros, inicialmente com capacidade para armazenar 5 elementos.
//O programa deverá solicitar ao usuário a inserção de valores inteiros, armazenando-os no vetor.
//Sempre que a capacidade máxima for atingida, o vetor deverá ser redimensionado
//dinamicamente, dobrando seu tamanho por meio da função realloc.
//A inserção de dados deverá continuar até que o usuário informe um valor negativo, que indicará o
//encerramento da entrada de dados.
//Ao final, o programa deverá:
//• Apresentar todos os elementos armazenados
//• Informar a quantidade total de elementos inseridos
//• Informar o tamanho final do vetor alocado
//• Informar se houve mudança de endereçamento na realocação.
//Observações:
//• A alocação inicial deve ser realizada com malloc
//• O redimensionamento deve utilizar obrigatoriamente realloc
//• O programa deve garantir que não haja perda de dados durante a realocação
//• Recomenda-se o uso de uma variável auxiliar ao utilizar realloc

#include<stdio.h>
#include<locale.h>
#include<stdlib.h>

int main(){
	setlocale(LC_ALL,"Portuguese");
	int *vent1,n,i;
	
	vent1=malloc(5*sizeof(int));
	
	for(i=0;i<4;i++){
	printf("Insira um valor ao vetor: ");
	scanf("%d",&n);
	
	
		vent1[i] = n;
	}
	for(i=0;i<4;i++){
	printf("valor em %d ao vetor: %d \n", &i+1,vent1[i]);
		 
	}
}
