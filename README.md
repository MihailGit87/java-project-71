### Hexlet tests and linter status:
[![Actions Status](https://github.com/MihailGit87/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/MihailGit87/java-project-71/actions)

### CI status:
[![Java-CI](https://github.com/MihailGit87/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/MihailGit87/java-project-71/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/e57dbe07772414a7871e/maintainability)](https://codeclimate.com/github/MihailGit87/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e57dbe07772414a7871e/test_coverage)](https://codeclimate.com/github/MihailGit87/java-project-71/test_coverage)

# Описание

Вычислитель отличий – программа, определяющая разницу между двумя структурами данных.

Подобный механизм используется при выводе тестов или при автоматическом отслеживании изменении в конфигурационных файлах.

# Возможности утилиты:

    Поддержка разных входных форматов: yaml и json
    Генерация отчета в виде plain text, stylish и json

# Пример использования:
## Output by plain:
```sh
./app --format plain path/to/file.yml another/path/file.json
Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed
```

## Output by stylish:
```sh
./app filepath1.json filepath2.json
{
  + follow: false
  + numbers: [1, 2, 3]
    setting1: Value 1
  - setting2: 200
  - setting3: true
  + setting3: {key=value}
  + setting4: blah blah
}
```

