### Introdução
Este projeto é o modelo de apis que iram compor o nosso projeto, sendo responsavel pelo fluxo de informação 
e integração com o banco de dados e até mesmo gerenciar authenticações de segurança. 
Em outras palavras o sistema de API's que gerenciaram os dados de toda plataforma

## requisitos basicos
- docker: que vamos utlizar para simular o servidor no desenvolvimento, sem a necessidade de baixar um banco de dados novo ou criar um artemis do zero para cada 
desenvolverdo, sendo responsavel por padronizar o ambiente de integração a todos os outros usuarios

- docker compose: apenas uma implementação simples do docker
- java 17+ : é a limguagem de programação que iremos usar no nosso projeto na versão 17 porem qualquer versão acima desta pode ser utilizado
- maven 3.X.X +: da mesma forma que o java, qualquer vesão acima de 3 ou até mesmo a 3 pode ser utilizada neste projeto.

O mavem é responsavel por gerenciar as dependencias do java, criar os builds, targets e iniciar o spring boot


# recursos 

<h2>linux</h2> 
para instalr qualquer um dos elementos sitado a cima em um ambiente linux basta digitar no terminal
sudo apt install [nome do programa] -y, com excessão ao docker e o docker-composer que são instalados ao mesmo 
tepo com o nome do programa docker-compose


<h2>windows</h2>
para installar o [maven](https://maven.apache.org/guides/getting-started/windows-prerequisites.html) no windonws

para installar o [java](https://www.java.com/download/ie_manual.jsp) no windowns 

para installar o [docker](https://docs.docker.com/desktop/install/windows-install/)

# Rodando o programa

- o primeiro passo é baixar as dependencias do mavem usando o comando mvn install -U -DskipTests
- o segundo passo é subir seu arquivo docker-compose.yaml que se encontra na raiz do projeto
(se você esta em um ambiente linux basta digitar docker-compose up -d) no terminal 
- O ultimo passo é rodar o projeto com a sua ide

aqui esta um quick start para que é iniciante em docker e tem um windonws como plataforma de desenvolvimento
usando o
[docker hub](https://docs.docker.com/docker-hub/quickstart/)

# GOOD Hacking !
