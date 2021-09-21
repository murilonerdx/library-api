CREATE TABLE IF NOT EXISTS `person` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `address` varchar(100) NOT NULL,
    `first_name` varchar(80) NOT NULL,
    `gender` varchar(6) NOT NULL,
    `last_name` varchar(80) NOT NULL
    )

    INSERT INTO `person` (`id`, `address`, `first_name`, `gender`, `last_name`)
    VALUES (1, 'Uberlândia - Minas Gerais - Brasil', 'Leandro', 'Male', 'Costa'),
(2, 'Uberlândia - Minas Gerais - Brasil', 'Gabriela', 'Female', 'Costa'),
(5, 'Patos de Minas - Minas Gerais - Brasil', 'Flávio', 'Male', 'Costa'),
(7, 'Uberlândia - Minas Gerais - Brasil', 'Fernanda', 'Female', 'Cardoso da Silva'),
(8, 'Patos de Minas - Minas Gerais - Brasil', 'Pedro', 'Male', 'Paulo'),
(9, 'Patos de Minas - Minas Gerais - Brasil', 'Marcos', 'Male', 'Paulo');

CREATE TABLE `books` (
                         `id` INT(10) PRIMARY KEY AUTO_INCREMENT,
                         `author` longtext,
                         `launch_date` datetime(6) NOT NULL,
                         `price` decimal(65, 2) NOT NULL,
                         `title` longtext
) ENGINE = InnoDB;

INSERT INTO `books` (`author`, `launch_date`, `price`, `title`)
VALUES ('Michael C. Feathers', '2017-11-29 13:50:05.878000', 49.00, 'Working effectively with legacy code'),
       ('Ralph Johnson, Erich Gamma, John Vlissides e Richard Helm', '2017-11-29 15:15:13.636000', 45.00, 'Design Patterns'),
       ('Robert C. Martin', '2009-01-10 00:00:00.000000', 77.00, 'Clean Code'),
       ('Crockford', '2017-11-07 15:09:01.674000', 67.00, 'JavaScript'),
       ('Steve McConnell', '2017-11-07 15:09:01.674000', 58.00, 'Code complete'),
       ('Martin Fowler e Kent Beck', '2017-11-07 15:09:01.674000', 88.00, 'Refactoring'),
       ('Eric Freeman, Elisabeth Freeman, Kathy Sierra, Bert Bates', '2017-11-07 15:09:01.674000', 110.00, 'Head First Design Patterns'),
       ('Eric Evans', '2017-11-07 15:09:01.674000', 92.00, 'Domain Driven Design'),
       ('Brian Goetz e Tim Peierls', '2017-11-07 15:09:01.674000', 80.00, 'Java Concurrency in Practice'),
       ('Susan Cain', '2017-11-07 15:09:01.674000', 123.00, 'O poder dos quietos'),
       ('Roger S. Pressman', '2017-11-07 15:09:01.674000', 56.00, 'Engenharia de Software: uma abordagem profissional'),
       ('Viktor Mayer-Schonberger e Kenneth Kukier', '2017-11-07 15:09:01.674000', 54.00, 'Big Data: como extrair volume, variedade, velocidade e valor da avalanche de informação cotidiana'),
       ('Richard Hunter e George Westerman', '2017-11-07 15:09:01.674000', 95.00, 'O verdadeiro valor de TI'),
       ('Marc J. Schiller', '2017-11-07 15:09:01.674000', 45.00, 'Os 11 segredos de líderes de TI altamente influentes'),
       ('Aguinaldo Aragon Fernandes e Vladimir Ferraz de Abreu', '2017-11-07 15:09:01.674000', 54.00, 'Implantando a governança de TI');

CREATE TABLE IF NOT EXISTS `permission` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `description` varchar(255) DEFAULT NULL
    ) ENGINE = InnoDB;


INSERT INTO `permission` (`description`)
VALUES ('ADMIN'),
       ('MANAGER'),
       ('COMMON_USER');

CREATE TABLE IF NOT EXISTS `users` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) DEFAULT NULL,
    `full_name` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `account_non_expired` bit(1) DEFAULT NULL,
    `account_non_locked` bit(1) DEFAULT NULL,
    `credentials_non_expired` bit(1) DEFAULT NULL,
    `enabled` bit(1) DEFAULT NULL
    ) ENGINE = InnoDB;

INSERT INTO `users` (`user_name`, `full_name`, `password`, `account_non_expired`, `account_non_locked`
                    , `credentials_non_expired`, `enabled`)
VALUES ('murilo', 'Murilo Pereira', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', 1, 1
       , 1, 1),
       ('flavio', 'Flavio Costa', '$2a$16$h4yDQCYTy62R6xrtFDWONeMH3Lim4WQuU/aj8hxW.dJJoeyvtEkhK', 1, 1
       , 1, 1);

CREATE TABLE IF NOT EXISTS `user_permission` (
    `id_user` bigint(20) NOT NULL,
    `id_permission` bigint(20) NOT NULL
    ) ENGINE = InnoDB;

INSERT INTO `user_permission` (`id_user`, `id_permission`) VALUES
                                                               (1, 1),
                                                               (2, 1),
                                                               (1, 2);

