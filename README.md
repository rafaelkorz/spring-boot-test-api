
## 👨🏻‍💻 Siga os passos abaixo para executar o projeto

Realize o clone do projeto 

```bash
git clone https://github.com/rafaelkorz/spring-boot-test-api.git
```

Abra o projeto em uma IDE de sua preferência.

## Aplicação

- Execute as intalações de dependência do Maven.
- Execute o arquivo MoviesApplication.
- Para carregar o arquivo CSV utilize a rota http://localhost:8080/movies/load.
- Após fazer inserção do arquivo no banco de dados é possível usar as outras rotas.

OBS: a coluna winner foi alterado no CSV para as celulas em branco inserido o valor "no" para não dar problema na abertura dos dados dentro do array.


## Testes

- Os testes estão nos arquivos MoviesApplicationTest e MoviesTest.
- Recomendo executar somente o arquivo MoviesTest para executar os testes todos corretamente.
- Motivo disso que não consegui usar o @Order para dar uma sequência correta aos testes.

## Rotas
POST http://localhost:8080/movies/load import o arquivo CSV no banco.
GET http://localhost:8080/movies/api -> gera o JSON final que é solicitado no desafio max {} e o min {} dos vencedires.
POST http://localhost:8080/movies/save -> faz a inclusão de um filme e passa o objeto completo do filme no corpo da requisição.
{
  "year": 1980,
  "title": "Stop the Music",
  "producer": "Allan Carr",
  "winner": "yes",
  "studios": "Associated Film Distribution"
}

GET http://localhost:8080/movies/{id} ->  recupera um filme

DELETE http://localhost:8080/movies/{id} -> delete um filme

PUT http://localhost:8080/movies/ atualizada um filme passando no corpo da requisição todas as info necessárias
{
  "id": 1,
  "year": 1980,
  "title": "Stop the Music",
  "producer": "Allan Carr",
  "winner": "yes",
  "studios": "Associated Film Distribution"
}




