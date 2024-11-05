# Sistema Bibliotecário

**Sistema Bibliotecário** é uma aplicação de gerenciamento de biblioteca, projetada para atender tanto administradores (ou bibliotecários) quanto leitores. A aplicação permite que os leitores acompanhem seus empréstimos de livros e solicitem renovações, enquanto os administradores têm a responsabilidade de gerenciar as retiradas e devoluções dos livros, além de aceitar ou recusar as solicitações de renovação.

## Funcionalidades

### Leitores
- **Visualização de Empréstimos**: Os leitores podem visualizar todos os livros que estão emprestados, com detalhes como data de retirada e data de devolução.
- **Solicitação de Renovação**: Os leitores podem solicitar a renovação de um empréstimo, caso necessário.

### Administradores (Bibliotecários)
- **Registro de Retiradas de Livros**: Os administradores podem registrar as retiradas de livros feitas pelos leitores, gerenciando a disponibilidade do acervo.
- **Registro de Entregas de Livros**: Também é possível registrar a devolução dos livros emprestados, garantindo que o inventário da biblioteca seja atualizado corretamente.
- **Gerenciamento de Solicitações de Renovação**: Os administradores podem aceitar ou recusar as solicitações de renovação feitas pelos leitores.

## Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Java**: Linguagem principal utilizada para o desenvolvimento da aplicação, proporcionando robustez e escalabilidade.
- **JDBC (Java Database Connectivity)**: Utilizado para estabelecer a comunicação entre a aplicação Java e o banco de dados MySQL, permitindo operações de leitura e escrita de dados.
- **MySQL**: Banco de dados relacional utilizado para armazenar informações dos livros, empréstimos, usuários e solicitações de renovação.
- **SQL**: Linguagem utilizada para interagir diretamente com o banco de dados, realizando consultas, inserções, atualizações e exclusões dos dados.
