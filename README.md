# UC00619 — Sessão 13 — Exercício 5 — Solução (library-jpa: Optional, streams e paginação)

Pedidos para testar (Postman):
- GET /api/books?page=0&size=2&sort=title        → Clean Code, Effective Java (totalElements 4, totalPages 2)
- GET /api/books?page=1&size=2&sort=title        → Head First Java, The Clean Coder
- GET /api/books?size=2&sort=publishedYear,desc  → Effective Java, The Clean Coder
- GET /api/books?author=Robert C. Martin&sort=publishedYear,desc → The Clean Coder, Clean Code
- GET /api/books?sort=price                      → 400 INVALID_SORT
- GET /api/books?size=1000                       → size 50 (max-page-size)
