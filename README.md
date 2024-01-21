# README

## Objetivo:

Este projeto é uma aplicação Java que utiliza o Maven como ferramenta de gerenciamento de projeto. A aplicação é
empacotada em um contêiner Docker para facilitar a implantação e o gerenciamento. A aplicação se conecta a um banco de
dados PostgreSQL e realiza operações de atualização em intervalos regulares.

Toda esta tecnologia é responsável por atualizar o IP público de um domínio no NextDNS, de forma que os computadores
conectados a rede possam acessar a internet com as regras definidas no NextDNS.

## Compilando a aplicação:

Para compilar a aplicação, você precisa ter o Maven e o JDK 17 instalados em seu sistema. Navegue até o diretório raiz
do projeto e execute o seguinte comando:

```bash
mvn clean install compile package
```

Este comando irá compilar o código, executar os testes e gerar um arquivo JAR no diretório `docker`.

## Criando a imagem Docker:

Para criar a imagem Docker, você precisa ter o Docker instalado em seu sistema. Navegue até o diretório raiz do projeto
e execute o seguinte comando:

```bash
docker build -t sbatch_nextdns .
```

Este comando irá criar uma imagem Docker chamada `sbatch_nextdns` a partir do Dockerfile presente no diretório `docker`.

## Executando a aplicação com Docker:

Para executar a aplicação usando Docker, você pode usar o arquivo `docker-compose.yml` fornecido. Execute o seguinte
comando no diretório raiz do projeto:

```bash
docker-compose up -d
```

Este comando irá iniciar um contêiner a partir da imagem `sbatch_nextdns`, com todas as variáveis de ambiente
necessárias já configuradas.

> Consulte o conteúdo do arquivo `docker-compose.yml` para mais detalhes para configurar as variáveis de ambiente
> necessárias para a correta execução da aplicação.

## Mais informações:

Feito com &hearts; por Ivan Wilhelm.

Codificado no [IntelliJ IDEA](https://www.jetbrains.com/idea/) da [JetBrains](https://www.jetbrains.com)
