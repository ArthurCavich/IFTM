# 🏗️ DIAGRAMA DA ARQUITETURA - API REST de Contatos

## 1️⃣ FLUXO COMPLETO DE uma Requisição (Slides 13)

```
┌──────────────────────────────────────────────────────────────────────────┐
│                           CLIENTE (Postman/Browser)                      │
│                        POST /api/contatos HTTP/1.1                       │
│                     Content-Type: application/json                       │
│                                                                          │
│                      {                                                  │
│                        "nome": "Maria Silva"                            │
│                      }                                                  │
└──────────────────────────────┬───────────────────────────────────────────┘
                               │
                               ↓ HTTP Request
┌──────────────────────────────────────────────────────────────────────────┐
│                    @RestController                                       │
│                ContatoController.criar(@RequestBody Contato)            │
│                                                                          │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │  Responsabilidade:                                             │    │
│  │  1. Receber JSON                                              │    │
│  │  2. Parsear para objeto Contato                               │    │
│  │  3. Chamar service.criar(contato)                             │    │
│  │  4. Tratar exceções                                           │    │
│  │  5. Retornar ResponseEntity com status HTTP                   │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  try {                                                                   │
│    Contato novo = service.criar(contato);  ← Chama Service             │
│    return ResponseEntity.status(201).body(novo);                        │
│  } catch (IllegalArgumentException e) {                                │
│    return ResponseEntity.status(400).body(new ErroDTO(400, ...));   │
│  } catch (IllegalStateException e) {  ← NOVO: 409 Conflict             │
│    return ResponseEntity.status(409).body(new ErroDTO(409, ...));   │
│  }                                                                      │
└──────────────────────────────┬───────────────────────────────────────────┘
                               │
                               ↓ Objetos Java (não JSON)
┌──────────────────────────────────────────────────────────────────────────┐
│                      @Service                                            │
│                   ContatoService.criar(Contato)                         │
│                                                                          │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │  Responsabilidade:                                             │    │
│  │  1. Validar dados                                             │    │
│  │  2. Aplicar regras de negócio                                 │    │
│  │  3. Lançar exceções específicas (com mensagens úteis)        │    │
│  │  4. Chamar repository para operação no BD                     │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  VALIDAÇÕES (Slide 35):                                                 │
│  ├─ nome == null? → throw new IllegalArgumentException(...)           │
│  ├─ nome.isEmpty()? → throw new IllegalArgumentException(...)         │
│  ├─ nome.length() > 100? → throw new IllegalArgumentException(...)    │
│  └─ repository.existePorCodigo(codigo)? → throw new IllegalStateEx... │
│                                  (Slide 29: 409 Conflict)              │
│                                                                          │
│  if (validações OK) {                                                   │
│    return repository.salvar(contato);  ← Chama Repository             │
│  }                                                                      │
└──────────────────────────────┬───────────────────────────────────────────┘
                               │
                               ↓ Operação CRUD
┌──────────────────────────────────────────────────────────────────────────┐
│                      @Repository                                         │
│              ContatoRepository.salvar(Contato)                          │
│                                                                          │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │  Responsabilidade:                                             │    │
│  │  1. Acessar dados (agora: List, depois: BD)                   │    │
│  │  2. Implementar CRUD                                           │    │
│  │  3. Retornar resultados                                        │    │
│  └────────────────────────────────────────────────────────────────┘    │
│                                                                          │
│  OPERAÇÕES:                                                             │
│  ├─ CREATE: salvar()         (Slide 25: POST)                         │
│  ├─ READ:   buscarTodos()    (Slide 10: GET /clientes)               │
│  ├─ READ:   buscarPorId()    (Slide 10: GET /clientes/1)             │
│  ├─ READ:   existePorCodigo() (Slide 29: verificar duplicado)        │
│  ├─ UPDATE: atualizar()      (Slide 37: PUT)                         │
│  └─ DELETE: deletar()        (Slide 42: DELETE)                      │
│                                                                          │
│  Hoje: static List<Contato> contatos = new ArrayList<>();            │
│  Depois: JpaRepository + Banco de Dados (MySQL, PostgreSQL, etc.)    │
│                                                                          │
│  // Simula um banco em memória                                          │
│  static {                                                               │
│    contatos.add(new Contato(1, "Jonas"));                             │
│    contatos.add(new Contato(2, "Tadeu"));                             │
│  }                                                                      │
│                                                                          │
│  public Contato salvar(Contato c) {                                    │
│    c.setCodigo(proximoCodigo++);  // ID automático                    │
│    contatos.add(c);               // Lista em memória                 │
│    return c;                                                           │
│  }                                                                      │
└──────────────────────────────┬───────────────────────────────────────────┘
                               │
                               ↓ Retorna Contato criado
┌──────────────────────────────────────────────────────────────────────────┐
│                         Service                                          │
│                   return novo contato                                   │
└──────────────────────────────┬───────────────────────────────────────────┘
                               │
                               ↓ Retorna Contato criado
┌──────────────────────────────────────────────────────────────────────────┐
│                       Controller                                         │
│          ResponseEntity.status(201).body(novoContato)                  │
└──────────────────────────────┬───────────────────────────────────────────┘
                               │
                               ↓ Serializa em JSON
┌──────────────────────────────────────────────────────────────────────────┐
│                     HTTP Response 201 Created                            │
│                  Content-Type: application/json                         │
│                                                                          │
│                      {                                                  │
│                        "codigo": 3,                                    │
│                        "nome": "Maria Silva"                           │
│                      }                                                  │
└──────────────────────────────────────────────────────────────────────────┘
                               │
                               ↓ Retorna ao Cliente
                        ┌──────────────┐
                        │    Cliente   │
                        │  Recebe 201  │
                        │  + JSON body │
                        └──────────────┘
```

---

## 2️⃣ MAPEAMENTO DE OPERAÇÕES CRUD

```
┌─────────────────────────────────────────────────────────────────┐
│                  OPERAÇÕES CRUD MAPEADAS                        │
├─────────────────────────────────────────────────────────────────┤

CREATE (Criação)
├─ HTTP: POST
├─ URL: /api/contatos
├─ Payload: {"nome": "João"}
├─ Status Sucesso: 201 Created
├─ Response: {"codigo": 3, "nome": "João"}
├─ Status Erro: 400 (nome inv.), 409 (duplicado)
└─ Slide: 25-30

READ (Leitura - Todos)
├─ HTTP: GET
├─ URL: /api/contatos
├─ Payload: (nenhum)
├─ Status Sucesso: 200 OK
├─ Response: [{"codigo": 1, "nome": "Jonas"}, ...]
├─ Status Erro: (nenhum)
└─ Slide: 10

READ (Leitura - Um)
├─ HTTP: GET
├─ URL: /api/contatos/1
├─ Payload: (nenhum)
├─ Status Sucesso: 200 OK
├─ Response: {"codigo": 1, "nome": "Jonas"}
├─ Status Erro: 404 Not Found
└─ Slide: 10

UPDATE (Atualização)
├─ HTTP: PUT
├─ URL: /api/contatos/1
├─ Payload: {"nome": "Jonas Atualizado"}
├─ Status Sucesso: 200 OK
├─ Response: {"codigo": 1, "nome": "Jonas Atualizado"}
├─ Status Erro: 400 (inv.), 404 (não encontrado)
└─ Slide: 37-41

DELETE (Exclusão)
├─ HTTP: DELETE
├─ URL: /api/contatos/1
├─ Payload: (nenhum)
├─ Status Sucesso: 204 No Content
├─ Response: (nenhum)
├─ Status Erro: 404 Not Found
└─ Slide: 42
```

---

## 3️⃣ EXCEÇÕES E HTTP STATUS CODES

```
┌──────────────────────────────────────────────────────────────┐
│         Exceção Java → HTTP Status → ErroDTO                 │
├──────────────────────────────────────────────────────────────┤

IllegalArgumentException
├─ Quando: validação falha (nome vazio, muito longo)
├─ HTTP Status: 400 Bad Request
├─ Resposta: {"status": 400, "mensagem": "..."}
└─ Exemplo: Nome tem mais de 100 caracteres

IllegalStateException ← NOVO (Slide 29)
├─ Quando: código já existe
├─ HTTP Status: 409 Conflict
├─ Resposta: {"status": 409, "mensagem": "Já existe contato..."}
└─ Exemplo: POST com codigo=1 sendo que já existe

RuntimeException
├─ Quando: contato não encontrado
├─ HTTP Status: 404 Not Found
├─ Resposta: {"status": 404, "mensagem": "Contato não encontrado..."}
└─ Exemplo: GET/PUT/DELETE com ID que não existe
```

---

## 4️⃣ CAMADAS E PADRÃO DTO

```
┌────────────────────────────────────────────────────────────┐
│                   CONTROLLER LAYER                         │
│  Mapea HTTP → Métodos | Retorna ResponseEntity            │
│  Imports: @RestController, @RequestMapping, @GetMapping   │
├─────────────────────────────────────────────────────────────┤
│ ContatoController {                                        │
│   @GetMapping → listar()     (GET /api/contatos)          │
│   @GetMapping("/{codigo}") → obter()  (GET /api/contatos/1)│
│   @PostMapping → criar()    (POST /api/contatos)          │
│   @PutMapping → atualizar() (PUT /api/contatos/1)         │
│   @DeleteMapping → deletar() (DELETE /api/contatos/1)     │
│ }                                                          │
└────────────────────────────────────────────────────────────┘
                          ↓ Chama
┌────────────────────────────────────────────────────────────┐
│                    SERVICE LAYER                           │
│  Valida dados | Regras de neg. | Lança exceções           │
│  Import: @Service                                          │
├─────────────────────────────────────────────────────────────┤
│ ContatoService {                                           │
│   criar(Contato) ← Validações aqui                        │
│   obterPorId(Integer)                                     │
│   atualizar(Integer, Contato) ← Validações               │
│   deletar(Integer)                                        │
│   listarTodos()                                           │
│ }                                                          │
└────────────────────────────────────────────────────────────┘
                          ↓ Chama
┌────────────────────────────────────────────────────────────┐
│                 REPOSITORY LAYER                           │
│  CRUD | Acesso a Dados | Retorna Optional<Contato>       │
│  Import: @Repository                                       │
├─────────────────────────────────────────────────────────────┤
│ ContatoRepository {                                        │
│   buscarTodos() → List<Contato>                           │
│   buscarPorId(Integer) → Optional<Contato>               │
│   salvar(Contato) → Contato                              │
│   atualizar(Integer, Contato) → Optional<Contato>        │
│   deletar(Integer) → boolean                             │
│   existePorCodigo(Integer) → boolean  ← NOVO             │
│ }                                                          │
└────────────────────────────────────────────────────────────┘
                          ↓ Acessa
┌────────────────────────────────────────────────────────────┐
│                    DATA LAYER                              │
│  List<Contato> (memória) → depois JDBC/JPA/BD             │
├─────────────────────────────────────────────────────────────┤
│ static List<Contato> contatos;                            │
│ static {                                                  │
│   contatos.add(new Contato(1, "Jonas"));                 │
│   contatos.add(new Contato(2, "Tadeu"));                 │
│ }                                                          │
└────────────────────────────────────────────────────────────┘

DTO (Data Transfer Object) - Slides 31-34
┌────────────────────────────────────────────────────────────┐
│                    ErrorDTO CLASS                          │
│  Responsabilidade: Padronizar respostas de erro            │
├─────────────────────────────────────────────────────────────┤
│ ErroDTO {                                                  │
│   int status         // Código HTTP (400, 404, 409, etc)  │
│   String mensagem    // Descrição legível                 │
│ }                                                          │
│                                                            │
│ Exemplo: {"status": 409, "mensagem": "Já existe..."}    │
└────────────────────────────────────────────────────────────┘
```

---

## 5️⃣ DOMAIN - Entidade

```
┌────────────────────────────────────────────────────────────┐
│                  @Entidade CONTATO                         │
├────────────────────────────────────────────────────────────┤
│                                                            │
│  @Data                   ← Gera getters, setters, etc     │
│  @AllArgsConstructor     ← Construtor completo            │
│  @NoArgsConstructor      ← Construtor vazio               │
│  public class Contato {                                   │
│                                                            │
│    Integer codigo;       ← ID único (Chave Primária)    │
│    String nome;          ← Dados                         │
│  }                                                        │
│                                                            │
└────────────────────────────────────────────────────────────┘
```

---

## 📝 Tabela de Referência: Slides vs Implementação

```
┌──────┬─────────────────────────┬──────────────────────────────────┐
│Slide │ Conceito                │ Implementado em                  │
├──────┼─────────────────────────┼──────────────────────────────────┤
│  7   │ Recursos                │ Contato (entity com ID único)    │
│ 10   │ GET /clientes           │ ContatoController.listar()       │
│ 10   │ GET /clientes/1         │ ContatoController.obter()        │
│ 13   │ Arquitetura em camadas  │ Controller → Service → Repository│
│14-19 │ Status codes            │ 200, 201, 204, 400, 404, 409    │
│ 25   │ POST novo recurso       │ ContatoController.criar()        │
│ 26   │ 201 Created             │ HttpStatus.CREATED              │
│ 29   │ 409 Conflict            │ IllegalStateException + ErroDTO  │
│31-34 │ DTO erro                │ ErroDTO class                    │
│ 35   │ Validação de campos     │ Service.criar() validações      │
│ 37   │ PUT atualizar           │ ContatoController.atualizar()   │
│ 41   │ PUT vs PATCH            │ Implementamus PUT completo      │
│ 42   │ DELETE                  │ ContatoController.deletar()     │
│ 42   │ 204 No Content          │ ResponseEntity.noContent()       │
└──────┴─────────────────────────┴──────────────────────────────────┘
```

---

## 🎯 Fluxo de Erro (409 Conflict - Slide 29)

```
POST /api/contatos
{"codigo": 1, "nome": "João"}   ← Código já existe!

        ↓

ContatoController.criar()
  → service.criar(contato)

        ↓

ContatoService.criar()
  if (contato.getCodigo() != null && 
      repository.existePorCodigo(contato.getCodigo())) {
    throw new IllegalStateException("Já existe contato com código: 1");
  }

        ↓

ContatoController (catch)
catch (IllegalStateException e) {
  return ResponseEntity
    .status(HttpStatus.CONFLICT)
    .body(new ErroDTO(409, e.getMessage()));
}

        ↓

HTTP Response 409 Conflict
{
  "status": 409,
  "mensagem": "Já existe contato com código: 1"
}
```

---

Este diagrama reflete os 44 slides da apresentação sobre APIs REST!
