# Forca Distribuida TCP
Projeto de jogo de forca distribuído utilizando TCP e a IDE Netbeans para edição do Swing.
## Get Start
Clone ou faça o Download do projeto em .zip (via terminal/manual) para sua maquina.
- Clone:
  - Execute o camando no Terminal e acessa a pasta do projeto:
    ```
    git clone https://github.com/itsgnegrao/ForcaDistribuidaTCP.git
    ```
- Download:
  - Execute no Terminal de comando:
    ```
    wget https://github.com/itsgnegrao/ForcaDistribuidaTCP/archive/master.zip
    ```
  - Descompacte e abra a pasta do projeto:
    ```
    unzip ForcaDistribuidaTCP-master.zip -d ForcaDistribuidaTCP
    ```
## Passos Para Execução:
  Abra a pasta do codigo fonte do projeto via Terminal.
  ```
  cd ForcaDistribuidaTCP/src
  ```
  Compile o cliente e o servidor.
  ```
  javac ForcaGUI.java TCPClient.java
  javac TCPServer.java Forca.java GerenciaPar.java PalavraDica.java ListaPalavrasDicas.java
  ```
  Execute o Servidor.
  ```
  java TCPServidor
  ```
  Abra outro terminal e execute o Cliente atravéz da view
  ```
  java ForcaGUI
  ```

## Autor
[Gabriel Negrão Silva](https://github.com/itsgnegrao), [Jennifer Izabel](https://github.com/JenniferIzabel).
