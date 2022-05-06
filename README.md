# Projeto - Fase Backend

### Desafio:
O projeto consiste na construção de uma aplicação cujo domínio é composto por fazendas que podem ter de 0 a N talhões
(marcação de uma subárea da fazenda), cada. Os talhões devem conter um atributo numérico de área (em hectares) e deve
ser possível fazer N registros de produção (em KG) para cada talhão. A aplicação deve ser capaz de calcular a produtividade
de cada fazenda e de cada talhão, sendo a produtividade a quantidade de produção (em KG) por área (em hectares).

### Objetivos:
- Cadastro, edição e remoção de uma fazenda;
- Cadastro, edição e remoção de um talhão (área da fazenda);
- Cadastro, edição e remoção da produção de um talhão;
- Calcular produtividade de uma fazenda;
- Calcular produtividade de um talhão;
- A API deve atender às especificações básicas previamente descritas;
- A API deve ser construída em Java com Spring;
- Os dados devem ser persistidos em um banco de dados local (H2 ou MongoDB);
- A aplicação deve possuir um coverage maior do que 50%;
- É importante que o código seja construído por partes e revisado por pares.

### Modelo entidade de Dominio


<div align="center">
<img src="https://user-images.githubusercontent.com/87953006/167046355-b683f5da-e1a0-4f09-8e22-5d130fa0a33c.jpg"/>
</div>

### Tecnologias utilizadas:
- JDK 16;
- Gradle;
- H2 Database Engine;
- Spring Boot 2.6.7.

