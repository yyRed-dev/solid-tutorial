# Revisão final da atividade

Nome:Lucas Fidelis da Silva
Data:22/09/2026

## Como validei a solução

Registre os comandos executados e os fluxos testados:

- [X] compilação do código inicial;
- [ ] compilação da versão refatorada;
- [ ] início de uma missão;
- [ ] movimentação, embarque e conclusão da missão;
- [ ] consulta e reset do ranking;
- [X] outro teste:

### Testes realizados no pacote `<span>model</span>`

**Nave**

* Posição inicial: `<span>(0, 0)</span>`
* Posição após mover: `<span>(1, 0)</span>`
* Resultado: aprovado.

**Passageiros**

* Professor: 10 pontos
* Engenheiro: 15 pontos
* Astronauta: 20 pontos
* Resultado: aprovado.

**Missão**

* Passageiros na missão: 3
* `<span>todosEmbarcados()</span>`: `<span>false</span>`
* Resultado: aprovado.

**Asteroide**

* Posição: `<span>(4, 4)</span>`
* Asteroides na missão: 1
* Resultado: aprovado.

**Inimigo**

* Posição inicial: `<span>(0, 0)</span>`
* Posição após mover: `<span>(1, 1)</span>`
* Inimigos na missão: 1
* Resultado: aprovado.

**Vida da Nave**

* Vidas iniciais: 3
* Vidas após perder uma: 2
* Resultado: aprovado.

**Embarque**

* Passageiros na nave antes: 0
* Passageiros na nave depois: 1
* Resultado: aprovado.

**Dificuldade**

* Fácil
* Médio
* Difícil
* Resultado: aprovado.

> Os testes do fluxo completo da versão refatorada ainda serão executados após a integração das camadas `<span>service</span>`, `<span>presentation</span>` e `<span>repository</span>`.
>

## Achados da revisão

Copie este bloco para cada ponto analisado:

```text
Achado 1

Local: model/EntidadeMapa.java
Princípio relacionado: SRP
Observação: A classe EntidadeMapa concentra os atributos de posição x e y e os métodos comuns de acesso a essas coordenadas, evitando repetição em Nave, Passageiro, Asteroide e Inimigo.
Impacto: Reduz duplicação e facilita alterações relacionadas à posição das entidades.
Proposta: Manter EntidadeMapa como classe base das entidades posicionáveis.
Prioridade: Baixa

Achado 2

Local: model/Passageiro.java, Professor.java, Engenheiro.java e Astronauta.java
Princípio relacionado: OCP
Observação: Passageiro foi definido como classe abstrata e cada subclasse implementa sua própria pontuação e tipo.
Impacto: Novos tipos de passageiros podem ser criados por extensão sem alterar as classes já existentes.
Proposta: Manter a hierarquia atual e adicionar novos passageiros por meio de subclasses quando necessário.
Prioridade: Baixa

Achado 3

Local: model/Professor.java, Engenheiro.java e Astronauta.java
Princípio relacionado: LSP
Observação: As três subclasses podem ser usadas em qualquer ponto que espera um Passageiro e mantêm o contrato da classe base.
Impacto: Permite utilizar polimorfismo e evita condicionais específicas para cada tipo de passageiro na lógica principal.
Proposta: Manter as subclasses respeitando o contrato definido por Passageiro.
Prioridade: Baixa

Achado 4

Local: model/Posicionavel.java e model/Movel.java
Princípio relacionado: ISP
Observação: As responsabilidades de possuir posição e possuir movimentação foram separadas em duas interfaces pequenas.
Impacto: Classes como Asteroide não precisam implementar métodos de movimento que não utilizam, enquanto Nave e Inimigo podem implementar Movel.
Proposta: Manter Posicionavel e Movel como interfaces separadas.
Prioridade: Baixa

Achado 5

Local: integração entre service e repository
Princípio relacionado: DIP
Observação: A camada de serviço deve depender da abstração RankingRepository, e não diretamente da implementação concreta responsável pelo arquivo.
Impacto: Permite substituir futuramente a persistência por outra implementação sem alterar a lógica principal do jogo.
Proposta: Conferir durante a integração final se JogoService recebe e utiliza RankingRepository como dependência.
Prioridade: Alta

Achado 6

Local: model/Inimigo.java
Princípio relacionado: SRP
Observação: Inimigo ainda possui o método colideCom(Nave), enquanto Asteroide não possui mais um método equivalente.
Impacto: A verificação de colisões pode ficar distribuída de forma inconsistente entre as entidades e aumentar o acoplamento.
Proposta: Avaliar a centralização da detecção de colisões em Missao ou na camada service.
Prioridade: Média

Achado 7

Local: model/Missao.java
Princípio relacionado: SRP
Observação: Nossa implementação de Missao foi mantida mais enxuta do que a referência do tutorial, concentrando principalmente o estado da missão, as coleções de entidades e a verificação de todosEmbarcados().
Impacto: A classe possui menos responsabilidades, mas parte da lógica de orquestração deverá ser assumida pelo service.
Proposta: Avaliar após a integração se essa divisão mantém JogoService coeso ou se alguma regra pertence melhor a Missao.
Prioridade: Média
```

## Decisões com as quais concordo



Concordo com a separação do projeto em pacotes como `<span>model</span>`, `<span>service</span>`, `<span>presentation</span>` e `<span>repository</span>`.

Essa organização deixa mais claro qual parte do sistema é responsável por cada tarefa. O `<span>model</span>` representa as entidades e regras básicas do domínio, o `<span>service</span>` coordena o fluxo da aplicação, a camada `<span>presentation</span>` cuida da exibição e o `<span>repository</span>` fica responsável pela persistência.

Também concordo com o uso da interface `<span>RankingRepository</span>`. Essa decisão aplica o DIP porque permite que `<span>JogoService</span>` dependa de uma abstração em vez de depender diretamente de uma implementação baseada em arquivo. Isso facilita testes e futuras alterações na forma de persistência.

## Decisões com as quais não concordo


Uma decisão da implementação de referência que mantivemos diferente foi a inclusão de `<span>getSimbolo()</span>` em `<span>EntidadeMapa</span>`.

Na nossa implementação, `<span>EntidadeMapa</span>` não possui esse método. Consideramos que símbolos como `<span>P</span>`, `<span>E</span>`, `<span>T</span>`, `<span>X</span>` e `<span>A</span>` representam a forma como as entidades são exibidas no console e, portanto, podem ser responsabilidade da camada `<span>presentation</span>`.

Essa escolha evita que o domínio conheça detalhes específicos da interface atual. Se no futuro o jogo utilizar uma interface gráfica, as entidades não precisarão ser alteradas apenas porque a representação visual mudou.

Outra diferença é que nossa classe `<span>Passageiro</span>` não armazena uma `<span>String tipo</span>`. Cada subclasse implementa `<span>getTipo()</span>` e informa seu próprio tipo. Como o tipo já é determinado pela classe concreta, essa abordagem evita manter duas representações para a mesma informação.

## Melhoria implementada (opcional)


Durante a refatoração, foi criada a classe abstrata `<span>EntidadeMapa</span>` e as interfaces `<span>Posicionavel</span>` e `<span>Movel</span>`.

Antes, classes diferentes armazenavam separadamente suas coordenadas `<span>x</span>` e `<span>y</span>` e repetiam métodos como `<span>getX()</span>` e `<span>getY()</span>`.

Depois da refatoração, `<span>EntidadeMapa</span>` passou a concentrar a posição comum, enquanto `<span>Posicionavel</span>` define o contrato para objetos que possuem coordenadas e `<span>Movel</span>` define apenas o comportamento de objetos que podem se movimentar.

Exemplo da nova estrutura:

```
Posicionavel
     ^
     |
EntidadeMapa
     ^
     |
--------------------------------
|           |          |       |
Nave    Passageiro  Asteroide Inimigo

Nave e Inimigo também implementam Movel.
```

Essa alteração reduziu duplicação e tornou mais explícitas as responsabilidades das entidades.
