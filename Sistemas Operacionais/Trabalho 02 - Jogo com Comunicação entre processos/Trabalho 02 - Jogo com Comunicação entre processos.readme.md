SISTEMAS OPERACIONAIS PROF. MAURILIO CAMPANO JR ESOFT-4S
TRABALHO 02 – 2º BIMESTRE – JOGO COM TROCA DE MENSAGENS E GERENCIAMENTO DE MEMÓRIA 
IMPLEMENTAÇÃO DE UM JOGO COM TROCA DE MENSAGENS E GERENCIAMENTO DE MEMÓRIA 
A comunicação entre dois processos é feita com base em um socket, que é um canal de comunicação entre os processos. Por meio de um socket pode ser criada a ideia de uma aplicação cliente/servidor em um computador localmente. 

A comunicação entre dois processos em C pode ser feita utilizando as bibliotecas winsock2.h e pthreads.h. Com base nos exemplos vistos em sala de aula e no código disponibilizado pelo professor, FAÇA um jogo qualquer para ser jogado entre pelo menos dois usuários implementado troca de mensagens e memória compartilhada entre os usuários.

Com base no código fornecido, a ideia é que por meio das mensagens trocadas entre os processos, o controle das ações do jogo seja realizada por meio de códigos nas mensagens, por exemplo, se temos um jogo de turnos (xadrez, dama, alguns jogos de baralho), uma variável inteira “vez” pode ser utilizada, sendo que se o valor de “vez” é igual a 1, a vez é do jogador 1 e se “vez” é igual a 2, quem joga é o jogador 2. No entanto essa variável deve ser criada em ambos os processos, sendo controlada por meio das trocas de mensagens.
