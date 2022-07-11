# RSA-criptografia

# Dupla: Luis Antonio Momm Duarte e Brian Klein

N3 Segurança de Informação

## Como usar: 
- clonar o repositório 
- colocar suas chaves públicas e privadas, além do texto a ser criptografado, na pasta rsa-generator/src/main/resources
- na pasta raiz, monte a imagem do projeto: __docker build -t scala-docker .__
- rode a imagem com __docker run -it --rm scala-docker__
- ao entrar no shell do container, digite __sbt run__ (essa parte demora uns 5 minutos, pegue um café)
- escolha a opcao para encriptar e digite os arquivos pedidos
- escolha a opcao para desencriptar e digite os arquivos pedidos 
- va ate __src/main/resources__ e verifique o conteúdo do arquivo. (Como a imagem não tem volume montado, não vai registrar no file system do host)


