# 🎓 Projeto REST API - Contatos (Spring Boot)

## ✨ Descritivo

Este projeto é uma implementação de uma **API REST completa** baseada rigorosamente nos **44 slides sobre APIs REST** (Projeto Back-End Monolítico com ORM). O projeto demonstra conceitos como:

- ✅ Separação em camadas (MVC: Model → Controller → Service → Repository)
- ✅ Mapeamento de URLs HTTP para métodos Java
- ✅ Status codes HTTP apropriados (200, 201, 204, 400, 404, 409)
- ✅ Validação de dados
- ✅ Padrão DTO para resposta padronizada de erros
- ✅ Detecção de conflito 409 (recurso duplicado)
- ✅ Injeção de Dependência com Spring

---

## 📚 Estrutura do Projeto

```
src/main/java/br/edu/iftm/aula03estudo/
├── Aula03estudoApplication.java        ← Classe main (Spring Boot)
├── domain/
│   └── Contato.java                    ← Entidade (modelo de dados)
├── controller/
│   └── ContatoController.java          ← Camada HTTP (requests/responses)
├── service/
│   └── ContatoService.java             ← Camada de lógica de negócio
├── repository/
│   └── ContatoRepository.java          ← Camada de acesso a dados
└── dto/
    └── ErroDTO.java                    ← Padrão de resposta de erro (NOVO)

DOCUMENTAÇÃO:
├── ARQUITETURA_E_EXPLICACOES.md        ← Guia completo com todas as explicações
├── DIAGRAMA_ARQUITETURA.md             ← Diagramas visuais do fluxo
├── RESUMO_MUDANCAS.md                  ← Resumo do que foi implementado
├── TESTES_API.json                     ← Lista de testes com exemplos
├── TESTE_API.sh                        ← Script de testes em bash/curl
└── README.md                           ← Este arquivo
```

---

## 🚀 Quick Start

### 1. Compilar
```bash
cd "c:\Users\arthu\OneDrive\Documentos\GitHub\IFTM\3Period\Back-End Project\aula03\aula03estudo"
.\mvnw compile
```

### 2. Executar
```bash
.\mvnw spring-boot:run
```

Acesse: `http://localhost:8080/api/contatos`

---

## 📡 Endpoints da API

| Método | URL | HTTP | Status | Descrição |
|--------|-----|------|--------|-----------|
| Listar | `/api/contatos` | GET | 200 | Lista todos os contatos |
| Buscar | `/api/contatos/{codigo}` | GET | 200/404 | Busca um contato |
| Criar | `/api/contatos` | POST | 201/400/409 | Cria novo contato |
| Atualizar | `/api/contatos/{codigo}` | PUT | 200/400/404 | Atualiza contato |
| Deletar | `/api/contatos/{codigo}` | DELETE | 204/404 | Deleta contato |

---

## 🧪 Exemplos de Uso (cURL)

### GET - Listar todos
```bash
curl -X GET http://localhost:8080/api/contatos
```

**Resposta (200 OK):**
```json
[
  {"codigo": 1, "nome": "Jonas"},
  {"codigo": 2, "nome": "Tadeu"}
]
```

### POST - Criar novo (Sucesso)
```bash
curl -X POST http://localhost:8080/api/contatos \
  -H "Content-Type: application/json" \
  -d '{"nome": "Maria Silva"}'
```

**Resposta (201 Created):**
```json
{
  "codigo": 3,
  "nome": "Maria Silva"
}
```

### POST - Criar novo (Erro 409 Conflict)
```bash
curl -X POST http://localhost:8080/api/contatos \
  -H "Content-Type: application/json" \
  -d '{"codigo": 1, "nome": "João"}'
```

**Resposta (409 Conflict):**
```json
{
  "status": 409,
  "mensagem": "Já existe contato com código: 1"
}
```

### POST - Criar novo (Erro 400 Bad Request)
```bash
curl -X POST http://localhost:8080/api/contatos \
  -H "Content-Type: application/json" \
  -d '{"nome": ""}'
```

**Resposta (400 Bad Request):**
```json
{
  "status": 400,
  "mensagem": "Nome do contato não pode estar vazio!"
}
```

### PUT - Atualizar
```bash
curl -X PUT http://localhost:8080/api/contatos/1 \
  -H "Content-Type: application/json" \
  -d '{"nome": "Jonas Atualizado"}'
```

**Resposta (200 OK):**
```json
{
  "codigo": 1,
  "nome": "Jonas Atualizado"
}
```

### DELETE - Deletar
```bash
curl -X DELETE http://localhost:8080/api/contatos/1
```

**Resposta (204 No Content):**
(sem corpo, apenas headers)

---

## 🔍 Validações Implementadas (Slide 35)

1. **Nome vazio/nulo** → HTTP 400
2. **Nome > 100 caracteres** → HTTP 400
3. **Código duplicado** → HTTP 409 (Slide 29)

---

## 🏗️ Arquitetura em Camadas (Slide 13)

```
┌─────────────────────────┐
│    Contato Controller   │  ← HTTP: recebe requisições
│  (request/response)     │
└────────────┬────────────┘
             │
             ↓ (objetos Java)
┌─────────────────────────┐
│   Contato Service       │  ← Lógica: valida dados
│  (negócio/validações)   │
└────────────┬────────────┘
             │
             ↓ (operações CRUD)
┌─────────────────────────┐
│ Contato Repository      │  ← Dados: acessa List/BD
│  (acesso a dados)       │
└────────────┬────────────┘
             │
             ↓
┌─────────────────────────┐
│   List<Contato>         │  ← Dados em memória
│  (depois: Banco de BD)  │
└─────────────────────────┘
```

---

## 📑 Arquivos de Documentação

1. **ARQUITETURA_E_EXPLICACOES.md** 
   - Explicação detalhada de cada arquivo
   - Responsabilidades de cada camada
   - Referência aos slides para cada conceito

2. **DIAGRAMA_ARQUITETURA.md**
   - Fluxo completo de uma requisição
   - Diagramas ASCII
   - Mapeamento de operações CRUD
   - Tratamento de exceções

3. **RESUMO_MUDANCAS.md**
   - O que foi criado/modificado
   - Comparação antes vs depois
   - Destaques técnicos

4. **TESTES_API.json**
   - Lista de testes para Postman/Insomnia
   - Incluindo casos de sucesso e erro
   - Examples com payloads e respostas

5. **TESTE_API.sh**
   - Script bash com testes usando curl
   - Testes automatizados de todos os endpoints

---

## 🎓 Referência aos Slides

| Slides | Tópico | Implementação |
|--------|--------|----------------|
| 1-6 | Introdução, SOAP vs REST | Conceitos aplicados |
| 7-13 | Recursos, URLs, Arquitetura | Contato.java, Controller-Service-Repository |
| 14-24 | Status codes, @PathVariable | HTTP 200/201/204/400/404/409 |
| 25-30 | POST, DTO, validação | ContatoController.criar() |
| 31-34 | DTO padrão | ErroDTO.java |
| 35 | Validação de campos | ContatoService.criar() |
| 37-41 | PUT vs PATCH | ContatoController.atualizar() (PUT completo) |
| 42-44 | DELETE, resumo | ContatoController.deletar(), 204 No Content |

---

## 💡 Principais Conceitos Implementados

### 1. Separação em Camadas
```
Controller  → Recebe HTTP, retorna ResponseEntity
Service     → Valida, aplica regras, lança exceções
Repository  → CRUD, acessa dados
Domain      → Modelo, apenas dados
DTO         → Transferência de dados (erro)
```

### 2. Exceções e HTTP Status
```
IllegalArgumentException  → 400 Bad Request
IllegalStateException     → 409 Conflict (NOVO)
RuntimeException         → 404 Not Found
```

### 3. Padrão DTO para Erro
```json
{
  "status": 409,
  "mensagem": "Já existe contato com código: 1"
}
```

### 4. Detecção de Conflito 409 (Slide 29)
```java
if (repository.existePorCodigo(contato.getCodigo())) {
    throw new IllegalStateException("Já existe...");
}
```

---

## 🔧 Tecnologias Utilizadas

- **Spring Boot** 4.0.3
- **Java** 25
- **Maven** (build tool)
- **Lombok** (code generation)
- **HTTP/REST** (arquitetura)

---

## 📋 Checklist de Implementação

- ✅ Contato.java → Entidade com Lombok
- ✅ ContatoRepository.java → Operações CRUD
- ✅ ContatoService.java → Validações + lógica
- ✅ ContatoController.java → HTTP endpoints
- ✅ ErroDTO.java → Padrão de resposta (NOVO)
- ✅ Validação de nome vazio
- ✅ Validação de tamanho máximo
- ✅ Detecção de código duplicado (409)
- ✅ Status codes apropriados
- ✅ Comentários referenciando slides
- ✅ Testes (TESTES_API.json, TESTE_API.sh)
- ✅ Documentação completa

---

## 🎯 Próximos Passos (Sugestões)

1. Substituir `List<Contato>` por JPA + Banco de Dados
2. Adicionar PATCH (atualização parcial)
3. Adicionar paginação e filtros
4. Adicionar autenticação/autorização
5. Adicionar logs (SLF4J)
6. Adicionar Swagger para documentação automática
7. Adicionar testes unitários (JUnit + Mockito)
8. Adicionar tratamento de exceptions globais (@ExceptionHandler)

---

## 📞 Contato/Suporte

Este projeto é uma implementação educacional baseada nos **44 slides sobre API REST**.

---

## 📝 Notas Importantes

- Dados armazenados em memória (List<Contato>)
- Sem autenticação/autorização
- 409 Conflict é validado no service
- Erros retornam ErroDTO em JSON

---

**Status**: ✅ Compilando e funcionando corretamente
**Última atualização**: 2026-03-24

---

## 🎓 Como Usar Este Projeto para Aprender

1. **Leia ARQUITETURA_E_EXPLICACOES.md** → Entenda cada componente
2. **Veja DIAGRAMA_ARQUITETURA.md** → Visualize o fluxo
3. **Execute TESTE_API.sh** → Veja funcionando
4. **Modifique o código** → Experimente mudanças
5. **Adicione validações** → Aprenda mais

Bom estudo! 🚀
