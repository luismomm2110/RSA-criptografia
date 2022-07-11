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

- Você pode também usar a chave contida em public.txt e descriptar o arquivo dest.txt, encriptado por nós 
- Além disso, pode gerar seu par de chaves com o GetKeys.java (aparece quando dá sbt run), usando o primelist.txt em resources

## Planejamento e operação: 

- O código foi organizado segundo o padrão scala, semelhante ao maven:
  - src/main/scala 
  - src/main/java
  - src/main/resources

  Arquivos diferentes foram usados para encriptar e descriptografar e outro para operações de IO no Scala
- Foi utilizado Scala por curiosidade de conhecer melhor a linguagem e também por permitir usar o TextChunk.java, já que Scala também roda na Java Virtual Machine e pode chamar API Java
- A maior dificuldade para implementação foi em debugar, já que os valores gerados não são inteligíveis para humanos. Assim, trocar a ordem dos paramêtros buga o código mas só podemos saber no resultado final. Além disso, Scala tem uma inferface fraca para IO. Mas no mais, é um algoritmo bastante simples quando o TextChunk já está implementado. 

## Teste:
- execução foi sequencial, comparando com resultados já testados. Não houve necessidade de teste unitário pela dificuldade de obter um exemplo intermediário garantido e as etapas do algoritmo serem bastantes simples. 
- Bugs foram encontrados especialmente ao ler o arquivo texto, já que utilizamos a quebra por linha, ao manter o arquivo inteiro, por documentação obscura do Scala para IO, como citamos acima. Mas trabalhando sequencialmente, foi possível verificar que era a quebra de linha que estava gerando diferenças com implementações testadas. 
- Sobre o que faltou implementar, fora do escopo, mas acreditamos que seria interessante implementar paralelismo para descriptografar textos grandes. Uma possível solução para problemas de concorrência seria usar um Map para cada chunk, atribuindo a ordem de cada como chave do Map. 

### Avaliação:
- bastante útil para aprender que criptografia não é mágica: é uma implementação relativamente trivial de uma engenhosa descoberta matemática
- Além disso, extremamente interessante descobrir o processo de criptografia assimétrica com o uso de nossas próprias chaves, tornando bem mais fácil entender esse processo ubíquo na Internet atual 
- Melhorar o compreendimento do Scala e sua interface funcional foi um grande bônus. 
