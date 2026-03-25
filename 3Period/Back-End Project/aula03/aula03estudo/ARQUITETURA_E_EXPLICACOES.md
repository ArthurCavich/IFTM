# 📚 Explicação do Projeto REST API - Guia Completo

## 🎯 Resumo das Mudanças Realizadas

O projeto foi refatorado para seguir **rigorosamente** os conceitos apresentados nos slides (44 páginas sobre APIs REST). Abaixo está a explicação de cada arquivo e sua relação com os slides.

---

## 📂 Estrutura de Arquivos e Responsabilidades

### 1. **Contato.java** (Slide 7: Recursos)
- **Localização**: `src/main/java/br/edu/iftm/aula03estudo/domain/`
- **Responsabilidade**: Entidade que representa um contato
- **Atributos**:
  - `codigo` (Integer): Identificador único (Chave Primária)
  - `nome` (String): Nome do contato
- **Anotações**:
  - `@Data`: Gera getters, setters, toString, equals, hashCode (Lombok)
  - `@AllArgsConstructor`: Construtor com todos os parâmetros
  - `@NoArgsConstructor`: Construtor vazio (necessário para frameworks)
- **Por quê?** Cada recurso REST precisa de um identificador único

---

### 2. **ErroDTO.java** (Slides 31-34: DTO - Padrão de Resposta)
- **Localização**: `src/main/java/br/edu/iftm/aula03estudo/dto/`
- **Responsabilidade**: Padronizar resposta de erro em JSON
- **Atributos**:
  - `status` (int): Código HTTP (400, 404, 409, 500, etc.)
  - `mensagem` (String): Descrição legível do erro
- **Exemplo de Resposta**:
  ```json
  {
    "status": 400,
    "mensagem": "Nome do contato não pode estar vazio!"
  }
  ```
- **Por quê?** Clientes (mobile/web) precisam de formato consistente

---

### 3. **ContatoRepository.java** (Camada de Acesso a Dados)
- **Localização**: `src/main/java/br/edu/iftm/aula03estudo/repository/`
- **Responsabilidade**: Realizar operações CRUD (Create, Read, Update, Delete)
- **Métodos Implementados**:

#### READ (Leitura)
| Método | Slide | HTTP | Descrição |
|--------|-------|------|-----------|
| `buscarTodos()` | 10 | GET | Lista todos os contatos |
| `buscarPorId(Integer codigo)` | 10 | GET | Busca um contato por ID |
| `existePorCodigo(Integer codigo)` | 29 | - | Verifica se ID já existe (conflito) |

#### CREATE (Criação)
| Método | Slide | HTTP | Descrição |
|--------|-------|------|-----------|
| `salvar(Contato contato)` | 25 | POST | Salva novo contato e atribui ID automático |

#### UPDATE (Atualização)
| Método | Slide | HTTP | Descrição |
|--------|-------|------|-----------|
| `atualizar(Integer codigo, Contato contatoAtualizado)` | 37 | PUT | Atualiza dados de um contato |

#### DELETE (Exclusão)
| Método | Slide | HTTP | Descrição |
|--------|-------|------|-----------|
| `deletar(Integer codigo)` | 42 | DELETE | Deleta um contato |

- **Implementação**: Usa `List<Contato>` em memória (depois será JPA/Banco de Dados)
- **Inovação**: Método `existePorCodigo()` para detectar conflito 409

---

### 4. **ContatoService.java** (Camada de Lógica de Negócio)
- **Localização**: `src/main/java/br/edu/iftm/aula03estudo/service/`
- **Responsabilidade**: Validar dados e aplicar regras de negócio
- **Exceções Lançadas**:
  - `IllegalArgumentException` → HTTP 400 (dados inválidos)
  - `IllegalStateException` → HTTP 409 (conflito de recurso)
  - `RuntimeException` → HTTP 404 (não encontrado)

#### Validações Implementadas (Slide 35):
1. **Nome vazio/nulo**: Lança `IllegalArgumentException`
2. **Nome com mais de 100 caracteres**: Lança `IllegalArgumentException`
3. **Código duplicado**: Lança `IllegalStateException` (SLIDE 29 - 409 Conflict)

#### Exemplo de Validação:
```java
// Slide 29: Detecção de conflito 409
if (contato.getCodigo() != null && repository.existePorCodigo(contato.getCodigo())) {
    throw new IllegalStateException("Já existe contato com código: " + contato.getCodigo());
}
```

---

### 5. **ContatoController.java** (Camada de Apresentação - HTTP)
- **Localização**: `src/main/java/br/edu/iftm/aula03estudo/controller/`
- **Responsabilidade**: Mapear URLs HTTP para métodos Java
- **Anotações**:
  - `@RestController`: API REST (retorna JSON, não HTML)
  - `@RequestMapping("/api/contatos")`: Prefixo de URL (Slides 11-13)

#### Endpoints Mapeados:

| Método | URL | HTTP | Status de Sucesso | Status de Erro |
|--------|-----|------|------------------|-----------------|
| `listar()` | `/api/contatos` | GET | 200 OK | - |
| `obter(codigo)` | `/api/contatos/{codigo}` | GET | 200 OK | 404 (ErroDTO) |
| `criar(contato)` | `/api/contatos` | POST | 201 Created | 400, 409 (ErroDTO) |
| `atualizar(codigo, contato)` | `/api/contatos/{codigo}` | PUT | 200 OK | 400, 404 (ErroDTO) |
| `deletar(codigo)` | `/api/contatos/{codigo}` | DELETE | 204 No Content | 404 (ErroDTO) |

#### Tratamento de Erros com ErroDTO:
```java
catch (IllegalArgumentException e) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErroDTO(400, e.getMessage()));
}
```

---

## 🔄 Fluxo de Requisição (Slide 13: Arquitetura em Camadas)

```
┌─────────────┐
│   Cliente   │ (Postman, navegador, app mobile)
│  (HTTP)     │
└──────┬──────┘
       │
       ↓ POST /api/contatos
┌──────────────────────────────┐
│  ContatoController.criar()   │ ← Recebe JSON
│  (Camada de Apresentação)    │ ← Trata HTTP
└──────────┬───────────────────┘
           │
           ↓ Chama: service.criar(contato)
┌──────────────────────────────┐
│  ContatoService.criar()      │ ← Valida dados
│  (Camada de Negócio)         │ ← Regras de negócio
│  - Nome vazio? ERRO 400      │
│  - Nome > 100? ERRO 400      │
│  - Código duplicado? ERRO 409│
└──────────┬───────────────────┘
           │
           ↓ Chama: repository.salvar(contato)
┌──────────────────────────────┐
│  ContatoRepository.salvar()  │ ← Acessa dados
│  (Camada de Dados)           │ ← Armazena em List
│  - Atribui ID automático     │ ← Depois: Banco de Dados
└──────────┬───────────────────┘
           │
           ↓ Retorna: novo Contato
┌──────────────────────────────┐
│  ContatoController           │ ← Serializa em JSON
│  ResponseEntity 201 Created  │ ← Define status HTTP
└──────────┬───────────────────┘
           │
           ↓ Resposta HTTP
┌─────────────────────────────┐
│       Cliente               │
│  {                          │
│    "codigo": 3,             │
│    "nome": "Maria Silva"    │
│  }                          │
│  Status: 201 Created        │
└─────────────────────────────┘
```

---

## 🧪 Como Testar os Endpoints

### 1️⃣ Listar Todos os Contatos
```bash
GET http://localhost:8080/api/contatos

# Resposta (200 OK):
[
  {"codigo": 1, "nome": "Jonas"},
  {"codigo": 2, "nome": "Tadeu"}
]
```

### 2️⃣ Buscar um Contato por ID
```bash
GET http://localhost:8080/api/contatos/1

# Resposta (200 OK):
{"codigo": 1, "nome": "Jonas"}

# Se não encontrar (404 Not Found):
{"status": 404, "mensagem": "Contato não encontrado com ID: 99"}
```

### 3️⃣ Criar um Novo Contato
```bash
POST http://localhost:8080/api/contatos
Content-Type: application/json

{
  "nome": "Maria Silva"
}

# Resposta (201 Created):
{
  "codigo": 3,
  "nome": "Maria Silva"
}

# Erro: Nome vazio (400 Bad Request):
{}
→ Resposta:
{
  "status": 400,
  "mensagem": "Nome do contato não pode estar vazio!"
}

# Erro: Nome > 100 caracteres (400 Bad Request):
{
  "nome": "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
}
→ Resposta:
{
  "status": 400,
  "mensagem": "Nome não pode ter mais de 100 caracteres!"
}

# Erro: Código duplicado (409 Conflict) - Slide 29:
{
  "codigo": 1,
  "nome": "João"
}
→ Resposta:
{
  "status": 409,
  "mensagem": "Já existe contato com código: 1"
}
```

### 4️⃣ Atualizar um Contato (PUT)
```bash
PUT http://localhost:8080/api/contatos/1
Content-Type: application/json

{
  "nome": "Jonas Atualizado"
}

# Resposta (200 OK):
{
  "codigo": 1,
  "nome": "Jonas Atualizado"
}

# Erro: Contato não encontrado (404 Not Found):
→ Resposta:
{
  "status": 404,
  "mensagem": "Contato não encontrado com ID: 99"
}
```

### 5️⃣ Deletar um Contato
```bash
DELETE http://localhost:8080/api/contatos/1

# Resposta (204 No Content):
(sem corpo, apenas header)

# Erro: Contato não encontrado (404 Not Found):
→ Resposta:
{
  "status": 404,
  "mensagem": "Contato não encontrado com ID: 99"
}
```

---

## 📊 Tabela de Status HTTP (Slides 14-43)

| Status | Descrição | Quando Usar | Exemplo |
|--------|-----------|-----------|---------|
| **200** | OK | Leitura bem-sucedida | GET /api/contatos/1 |
| **201** | Created | Criação bem-sucedida | POST /api/contatos |
| **204** | No Content | Sucesso mas sem corpo | DELETE /api/contatos/1 |
| **400** | Bad Request | Dados inválidos | Nome vazio, nome > 100 chars |
| **404** | Not Found | Recurso não existe | GET /api/contatos/999 |
| **409** | Conflict | Recurso já existe | POST com código duplicado |

---

## 🔐 Melhorias Implementadas

✅ **Validação Completa**: Nome vazio, comprimento máximo, detecção de conflito (409)
✅ **DTO para Erros**: Respostas padronizadas em JSON (Slides 31-34)
✅ **Separação em Camadas**: Controller → Service → Repository
✅ **Reuso de Código**: Optional<T> para segurança
✅ **Injeção de Dependência**: @Autowired automático
✅ **Comentários Alinhados com Slides**: Cada método referencia seu slide correspondente

---

## 📝 Notas Importantes

- **Slide 37**: PUT substitui o recurso inteiro (não implementamos PATCH parcial)
- **Slide 29**: 409 Conflict é retornado quando tenta criar contato com código já existente
- **Slide 31-34**: ErroDTO padroniza todas as respostas de erro
- **Dados em Memória**: Agora usamos `List<Contato>`. Depois migrar para JPA + Banco de Dados

---

## ▶️ Como Rodar o Projeto

```bash
# Compilar
mvnw compile

# Executar testes
mvnw test

# Rodar a aplicação
mvnw spring-boot:run

# Acessar a API
http://localhost:8080/api/contatos
```

---

## 📚 Referência Rápida dos Slides

- **Slides 1-6**: Introdução, SOAP vs REST
- **Slides 7-13**: Recursos, URLs, Spring Boot mapping
- **Slides 14-24**: HTTP status codes, @PathVariable, filtros
- **Slides 25-30**: POST, DTO, validação, 409 conflict
- **Slides 31-34**: DTO padrão para respostas
- **Slides 35**: Validação de campos
- **Slides 37-41**: PUT vs PATCH
- **Slides 42-44**: DELETE, resumo de status codes

---

**Desenvolvido com base nos slides de REST APIs (44 páginas)**
