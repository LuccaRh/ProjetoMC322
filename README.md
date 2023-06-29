# ProjetoMC322

O programa possui uma estrutura hierárquica de classes e interfaces relacionadas a frutas e verduras.

A classe abstrata "Fruta" define um método "calculaValor" que recebe o peso da fruta como parâmetro e retorna o valor calculado em Reais. As classes "Uva", "Banana" e "Maçã" estendem a classe "Fruta" e implementam o método "calculaValor" de acordo com as características e cálculos específicos de cada fruta.

A interface "Verdura" declara um método "calculaValor" que recebe o peso da verdura como parâmetro e retorna o valor calculado. As classes "Couve", "Alface", "Acelga" e "Brócolis" implementam a interface "Verdura" e atualizam o método "calculaValor" com a lógica de cálculo correspondente a cada tipo de verdura.

Para definir os valores de cada item, foi utilizada a classe enumeração "Tabela de Valores". Essa classe enumeração possui valores fixos para cada fruta e verdura, representados por constantes com seus respectivos preços.

O programa também apresenta uma classe chamada "GuiaComAbas" que estende a classe "JFrame". Essa classe é responsável pela implementação da interface gráfica e possui funcionalidades como carregar dados de um arquivo CSV em uma tabela, adicionar, atualizar e remover registros do arquivo.

Essa é uma visão geral do funcionamento do programa, onde as classes e interfaces estão interconectadas para fornecer as funcionalidades de cálculo de valor das frutas e verduras, além da interação com a interface gráfica.
