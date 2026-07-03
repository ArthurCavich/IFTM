INSERT INTO cliente(nome) VALUES ('CARLOS');
INSERT INTO cliente(nome) VALUES ('MARIA');

INSERT INTO permissao(nome) VALUES ('CLIENTE_READ');
INSERT INTO permissao(nome) VALUES ('CLIENTE_WRITE');

INSERT INTO role(nome) VALUES ('ADMIN');
INSERT INTO role(nome) VALUES ('USER');

INSERT INTO role_permissao(role_id, permissao_id) VALUES (1, 1);
INSERT INTO role_permissao(role_id, permissao_id) VALUES (1, 2);
INSERT INTO role_permissao(role_id, permissao_id) VALUES (2, 1);

INSERT INTO usuario(login, senha) VALUES ('cadu', '$2a$10$EqSv2PfkQJFzp4jzHdL3E.j44puL7nU9DyupNQNE.MK70oNfJU3Sa');
INSERT INTO usuario(login, senha) VALUES ('joao', '$2a$10$EoJZk06ojzhOLYIK75U.7.P99w8.YHNxYvAuSAZRkrYR1K5b/KmB.');

INSERT INTO usuario_role(usuario_login, role_id) VALUES ('cadu', 1);
INSERT INTO usuario_role(usuario_login, role_id) VALUES ('joao', 2);
