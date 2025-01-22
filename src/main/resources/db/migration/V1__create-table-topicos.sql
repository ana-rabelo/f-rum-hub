create table topicos(

                        id bigint not null auto_increment,
                        titulo varchar(100) not null,
                        mensagem varchar(500) not null,
                        dataCriacao datetime not null,
                        nomeAutor varchar(100) not null,
                        status boolean not null,
                        nomeCurso varchar(100) not null,

                        primary key(id)

);