_Generated with GPT-5.3-Codex_

# Site do CACo - Backend

API do Site do CACo, desenvolvida em Spring Boot com autenticação JWT + OAuth2 (Google), controle de permissões por papel e organização em camadas (controller, service, repository, dto, entity).

## Stack

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Security + OAuth2 Client
- JWT (Auth0 java-jwt)
- Spring Data JPA
- MySQL
- Bucket4j (rate limiting)
- Maven Wrapper (`mvnw`)

## Estrutura do Repositório

```text
site-do-caco-backend/
├── README.md
└── sitedocaco/
	├── pom.xml
	├── mvnw
	└── src/
		└── main/
			├── java/com/caco/sitedocaco/
			│   ├── config/
			│   ├── controller/
			│   │   ├── publicController/
			│   │   ├── privateController/
			│   │   └── admin/
			│   ├── security/
			│   ├── service/
			│   ├── repository/
			│   ├── dto/
			│   ├── entity/
			│   └── exception/
			└── resources/
				└── application.properties.example
```

## Dominios Cobertos

- Home (banners, avisos e destaques)
- Noticias
- Manual do calouro
- Eventos
- Banco de provas
- Album de figurinhas
- Loja
- Perfis de usuario
- Grupos de WhatsApp
- Fluxos administrativos (admin/editor/super-admin)

## Seguranca e Permissoes

- `server.servlet.context-path=/api`: todos os endpoints ficam sob `/api`.
- Rotas publicas: `/api/public/**`
- Rotas privadas autenticadas: `/api/**` (exceto publicas)
- Rotas de editor: `/api/editor/**`
- Rotas de admin: `/api/admin/**`
- Rotas de super-admin: `/api/super-admin/**`
- Login social com Google OAuth2 + emissao de JWT
- Filtro para exigir formulario de perfil no primeiro acesso
- Rate limiting por anotacao (`@RateLimit`) em endpoints sensiveis

## Como Rodar Localmente

### 1) Entrar no modulo Spring

```bash
cd sitedocaco
```

### 2) Criar arquivo de configuracao local

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

### 3) Ajustar variaveis no `application.properties`

Campos obrigatorios:

- `app.frontend.url` (URL do frontend para CORS)
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `spring.security.oauth2.client.registration.google.client-id`
- `spring.security.oauth2.client.registration.google.client-secret`
- `jwt.secret`
- `imgbb.api.key` (se upload de imagem estiver habilitado)

### 4) Subir aplicacao

```bash
./mvnw spring-boot:run
```

API local padrao:

- `http://localhost:8080/api`

## Build

```bash
cd sitedocaco
./mvnw clean package
```

## Observacoes

- Nao ha testes automatizados no repositorio neste momento (`src/test` vazio).
- O frontend deve apontar `VITE_API_URL` para o host da API sem sufixo `/api`.
- A aplicacao usa `ddl-auto=update` no exemplo; para producao, revisar estrategia de migracao de schema.
