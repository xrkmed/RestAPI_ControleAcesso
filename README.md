
# Sistema de controle de acesso em RestAPI

O sistema foi desenvolvido em Springboot with Maven e funciona da seguinte maneira: Atraves de uma aplicacao front-end faz a requisicao com o Cracha do usuario que por padrao foi desenvolvido no sistema "FLN9999999BR" sendo FLN a Filial e BR, os numeros o id do cadastro do funcionario e BR o pais.
ao fazer a requisicao, retorna-se um Json informando se o usuario iniciou a jornada de servico, se foi para o descanso, se terminou o descanso ou se terminou a jornada de servico.
Tambem tem tratamento de excecoes para caso o usuario nao cumpra a jornada de servico minima de oito horas/dia.
Cada usuario contem, alem de suas informacoes e cargos, uma lista de advertencia e uma lista de "atitudes positivas" onde estas duas formam uma media da nota do funcionario.
Todo o resumo do funcionario, bem como suas faltas e atrasos, podem ser verificados via RestAPI tambem utilizando a requisicao /worker/{cracha}


## Autores

- [@xrkmed](https://www.github.com/xrkmed)


## Screenshots

![Log do sistema](https://i.imgur.com/NchGnRX.png)

![Log do sistema](https://i.imgur.com/jM2tdZ0.png)

![Log do sistema](https://i.imgur.com/rr0EiBT.png)

![Log do sistema](https://i.imgur.com/YnII4nW.png)

![Log do sistema](https://i.imgur.com/8axQD9k.png)

![Log do sistema](https://i.imgur.com/r2xcrf9.png)

## Exemplos

!['Funcionamento'](https://i.imgur.com/5PyImlY.png)
