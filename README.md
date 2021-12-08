<h1>Chat em Java - Socket and Threads</h1>

<br>
<p align="center">
    <img src="https://focanocliente.com.br/wp-content/uploads/2020/03/chat-bot.png" width="500">
</p>
<br>

**Atividade realizada em grupo:** Cássio Mariano de Freitas, Diego Keidi Shimizu, Guilherme Augusto Costa
Bressanim, Matheus Konrad Xavier e Lucas Henrique Hoffmann.

<h2>Responsabilidade do Server e do Cliente:</h2>
<h3><b>Server.java:</b><h3>
<p>O servidor deverá ser iniciado por primeiro, ela é a unidade centralizadora de todas as conexões recebidas via socket e terá como responsabilidade o envio de uma mensagem onde é rebebida de algum cliente, o server envia para todos os cliente conectados</p>
<h3><b>Cliente.java:</b><h3>
<p>Cada usuário criará uma instância do cliente e fará uma conexão com o servidor socket.</p>

<h2>Utilização do BufferedWriter</h2>
<p>Responsável pela gravação de strings em um fluxo de saída de caracteres, armazenando caracteres em buffer para fornecer uma gravação eficiente de caracteres únicos, matrizes e strings. O tamanho do buffer pode ser especificado ou o tamanho padrão pode ser aceito. O padrão é grande o suficiente para a maioria das finalidades. </p>

## Features
- ✅ **Enviar mensagens para todos os clientes conectados**
- ✅ **Server que roda em uma porta escolhida**
- ✅ **Utilização de buffer para armazenamento rápido**
- ✅ **Utilização de sockets e threads**

## Run
> Example: **127.0.0.1:4030/** or **localhost:4030**

- **Don't forget to always run server.java before any other run**
- **Sempre rodar primeiro o server.java**
- **Run the files `Server.java` later `Cliente.java`**
- **Rodar os arquivos `Server.java` depois `Cliente.java`**
- **Run Cliente.java for more customers in the chat**
- **Rode o Cliente.java para o maior número de clientes no chat**