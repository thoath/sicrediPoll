# Sicredi enquetes


No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Essa api serve para você gerenciar essas sessões de votação.

Com essa API REST é possível:
● Cadastrar uma nova pauta
● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um
tempo determinado na chamada de abertura ou 1 minuto por default)
● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada
associado é identificado por um id único e pode votar apenas uma vez por pauta)
● Contabilizar os votos e dar o resultado da votação na pauta

! Qualquer chamada para as interfaces pode ser considerada como autorizada !

# Tecnologias utilizadas
 - Spring boot
 - Jnuit
 - Mockito
 - Java 8
 - Postgres
 - JMS + activeMQ
 - Docker
 - Swagger2

# Configuração docker

Com o docker rodando, executar os seguintes comandos : 

docker run --name dbsicredi -e POSTGRES_PASSWORD=sicredi -e POSTGRES_DB=sicredipoll -p 5432:5432 -d postgres:11

docker pull rmohr/activemq

docker run -p 61616:61616 -p 8161:8161 rmohr/activemq

