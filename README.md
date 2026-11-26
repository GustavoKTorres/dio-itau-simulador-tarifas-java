# Simulador de tarifas

Projeto didático de Design Patterns do bootcamp Itaú em parceria com a DIO. Calcula cotações de transferências fictícias com dois planos. As tarifas são exemplos de programação.

**Strategy** permite trocar a regra de cálculo, representada pela interface `Tarifa`. Os planos Essencial e Digital usam regras diferentes. O teste comprova que uma terceira regra pode ser introduzida sem editar a central.

**Facade** concentra validação, cálculo e arredondamento em `CentralDeCotacoes.simular`. A interface de linha de comando apenas lê os argumentos e apresenta a cotação.

## Executar e testar

Com JDK 21, rode na raiz do projeto:

```shell
javac -encoding UTF-8 -d out src/Simulador.java src/SimuladorTeste.java
java -cp out Simulador DIGITAL 100.00
java -cp out SimuladorTeste
```

O primeiro exemplo imprime uma tarifa de R$ 0,50 e um total de R$ 100,50. O teste verifica os dois planos, a substituição da estratégia, o arredondamento e entradas inválidas.

Referência do desafio: [laboratório de padrões em Java puro da DIO](https://github.com/digitalinnovationone/lab-padroes-projeto-java).
