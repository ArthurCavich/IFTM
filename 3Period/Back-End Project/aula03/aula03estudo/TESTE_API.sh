#!/bin/bash
# Script de Testes da API REST de Contatos
# Baseado nos slides (44 páginas)
# Antes de executar: mvnw spring-boot:run

echo "=========================================="
echo "TESTES API REST - Contatos"
echo "=========================================="
echo ""

BASE_URL="http://localhost:8080/api/contatos"

# Cores para output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# ============ TEST 1: GET ALL ============
echo -e "${YELLOW}[TEST 1] GET /api/contatos (Listar todos)${NC}"
echo "Slide 10: GET /clientes - Listar todos os clientes"
echo "Esperado: 200 OK com lista de contatos"
echo ""
curl -s -X GET "$BASE_URL" \
  -H "Content-Type: application/json" | jq '.'
echo ""
echo ""

# ============ TEST 2: GET BY ID ============
echo -e "${YELLOW}[TEST 2] GET /api/contatos/1 (Buscar um que existe)${NC}"
echo "Slide 10: GET /clientes/1 - Buscar os detalhes"
echo "Esperado: 200 OK com contato de ID 1"
echo ""
curl -s -X GET "$BASE_URL/1" \
  -H "Content-Type: application/json" | jq '.'
echo ""
echo ""

# ============ TEST 3: GET NONEXISTENT ============
echo -e "${YELLOW}[TEST 3] GET /api/contatos/999 (Contato não existe)${NC}"
echo "Slide 14: 404 Not Found"
echo "Esperado: 404 com ErroDTO"
echo ""
curl -s -w "\nHTTP Status: %{http_code}\n" -X GET "$BASE_URL/999" \
  -H "Content-Type: application/json" | jq '.'
echo ""
echo ""

# ============ TEST 4: POST SUCCESS ============
echo -e "${YELLOW}[TEST 4] POST /api/contatos (Criar novo - SUCESSO)${NC}"
echo "Slide 25: POST para criar novo recurso"
echo "Esperado: 201 Created com novo contato"
echo ""
curl -s -w "\nHTTP Status: %{http_code}\n" -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"nome": "João Silva"}' | jq '.'
echo ""
echo ""

# ============ TEST 5: POST EMPTY NAME ============
echo -e "${RED}[TEST 5] POST /api/contatos (Nome vazio - ERRO 400)${NC}"
echo "Slide 35: Validação de campo obrigatório"
echo "Esperado: 400 Bad Request com ErroDTO"
echo ""
curl -s -w "\nHTTP Status: %{http_code}\n" -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"nome": ""}' | jq '.'
echo ""
echo ""

# ============ TEST 6: POST DUPLICATE CODE ============
echo -e "${RED}[TEST 6] POST /api/contatos (Código duplicado - ERRO 409)${NC}"
echo "Slide 29: 409 Conflict - Recurso já existe"
echo "Esperado: 409 Conflict com ErroDTO"
echo ""
curl -s -w "\nHTTP Status: %{http_code}\n" -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"codigo": 1, "nome": "Novo Nome"}' | jq '.'
echo ""
echo ""

# ============ TEST 7: PUT SUCCESS ============
echo -e "${YELLOW}[TEST 7] PUT /api/contatos/1 (Atualizar - SUCESSO)${NC}"
echo "Slide 37: PUT para atualizar recurso"
echo "Esperado: 200 OK com contato atualizado"
echo ""
curl -s -w "\nHTTP Status: %{http_code}\n" -X PUT "$BASE_URL/1" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Jonas Atualizado"}' | jq '.'
echo ""
echo ""

# ============ TEST 8: PUT NONEXISTENT ============
echo -e "${RED}[TEST 8] PUT /api/contatos/999 (Contato não existe - ERRO 404)${NC}"
echo "Slide 14: 404 Not Found"
echo "Esperado: 404 com ErroDTO"
echo ""
curl -s -w "\nHTTP Status: %{http_code}\n" -X PUT "$BASE_URL/999" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Alguém"}' | jq '.'
echo ""
echo ""

# ============ TEST 9: DELETE SUCCESS ============
echo -e "${YELLOW}[TEST 9] DELETE /api/contatos/2 (Deletar - SUCESSO)${NC}"
echo "Slide 42: DELETE para excluir; 204 No Content"
echo "Esperado: 204 No Content (sem corpo)"
echo ""
curl -s -w "\nHTTP Status: %{http_code}\n" -X DELETE "$BASE_URL/2" \
  -H "Content-Type: application/json"
echo ""
echo ""

# ============ TEST 10: DELETE NONEXISTENT ============
echo -e "${RED}[TEST 10] DELETE /api/contatos/999 (Não existe - ERRO 404)${NC}"
echo "Slide 14: 404 Not Found"
echo "Esperado: 404 com ErroDTO"
echo ""
curl -s -w "\nHTTP Status: %{http_code}\n" -X DELETE "$BASE_URL/999" \
  -H "Content-Type: application/json" | jq '.'
echo ""
echo ""

echo "=========================================="
echo "TESTES CONCLUÍDOS"
echo "=========================================="
echo ""
echo "Resumo:"
echo "✅ GET (lista) - 200 OK"
echo "✅ GET (um) - 200 OK"
echo "❌ GET (inexistente) - 404 Not Found"
echo "✅ POST (sucesso) - 201 Created"
echo "❌ POST (nome vazio) - 400 Bad Request"
echo "❌ POST (código duplicado) - 409 Conflict"
echo "✅ PUT (sucesso) - 200 OK"
echo "❌ PUT (não existe) - 404 Not Found"
echo "✅ DELETE (sucesso) - 204 No Content"
echo "❌ DELETE (não existe) - 404 Not Found"
echo ""
