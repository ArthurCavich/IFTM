# Lista de Exercícios 1 - Problemas Clássicos de Sincronização

**Disciplina:** Sistemas Distribuídos  
**Professor:** Carlos Eduardo de Carvalho Dantas (`carloseduardoxp`)  
**Aluno:** Arthur Santana Cavichioli  

Este repositório contém a implementação dos três problemas clássicos de sincronização em Java puro:
1. **Jantar dos Filósofos**
2. **Barbeiro Dorminhoco**
3. **Leitores e Escritores**

Cada problema foi implementado utilizando três primitivas de sincronização distintas:
- **Semáforos** (`Semaphore`)
- **Monitores** (`synchronized`, `wait()`, `notifyAll()`)
- **Locks** (`ReentrantLock`, `Condition`, `ReentrantReadWriteLock`)

---

## Estrutura de Pastas

```text
src/main/java/br/edu/iftm/sistemasdistribuidos/sincronizacao/
│
├── jantar_filosofos/
│   ├── JantarFilosofosSemaforo.java
│   ├── JantarFilosofosMonitor.java
│   └── JantarFilosofosLock.java
│
├── barbeiro_dorminhoco/
│   ├── BarbeiroSemaforo.java
│   ├── BarbeiroMonitor.java
│   └── BarbeiroLock.java
│
└── leitores_escritores/
    ├── LeitoresEscritoresSemaforo.java
    ├── LeitoresEscritoresMonitor.java
    └── LeitoresEscritoresLock.java
```

---

## Como Compilar

No terminal, na pasta raiz do projeto:

```bash
javac -d target/classes src/main/java/br/edu/iftm/sistemasdistribuidos/sincronizacao/*/*.java
```

*(Ou simplesmente abra e execute o projeto na sua IDE Maven: NetBeans, VS Code, IntelliJ ou Eclipse)*

---

## Como Executar

### Opção 1: Menu Interativo Geral (Recomendado)
Você pode executar a classe `Main`, que apresenta um menu no terminal para escolher qualquer um dos 9 exercícios:

```bash
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.Main
```

### Opção 2: Executar Cada Arquivo Individualmente
Cada arquivo possui sua própria classe com método `main` independente:

### 1. Jantar dos Filósofos
```bash
# Semáforos
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.jantar_filosofos.JantarFilosofosSemaforo

# Monitores
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.jantar_filosofos.JantarFilosofosMonitor

# Locks
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.jantar_filosofos.JantarFilosofosLock
```

### 2. Barbeiro Dorminhoco
```bash
# Semáforos
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.barbeiro_dorminhoco.BarbeiroSemaforo

# Monitores
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.barbeiro_dorminhoco.BarbeiroMonitor

# Locks
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.barbeiro_dorminhoco.BarbeiroLock
```

### 3. Leitores e Escritores
```bash
# Semáforos
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.leitores_escritores.LeitoresEscritoresSemaforo

# Monitores
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.leitores_escritores.LeitoresEscritoresMonitor

# Locks
java -cp target/classes br.edu.iftm.sistemasdistribuidos.sincronizacao.leitores_escritores.LeitoresEscritoresLock
```
