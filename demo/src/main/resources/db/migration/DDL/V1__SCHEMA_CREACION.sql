
CREATE  TABLE cliente (
                                     id_cliente           INT  NOT NULL  auto_increment,
                                     id_persona           INT  NOT NULL,
                                     contrasena           VARCHAR(32)    ,
                                     estado_cliente       BOOLEAN    ,
                                     CONSTRAINT pk_cliente PRIMARY KEY ( id_cliente )
);

CREATE  TABLE cuenta (
                                    id_cuenta            INT  NOT NULL  auto_increment,
                                    id_cliente           INT  NOT NULL,
                                    numero_cuenta        VARCHAR(16)    ,
                                    tipo_cuenta          VARCHAR(32)    ,
                                    saldo_inicial        DECIMAL(8,3)    ,
                                    estado_cuenta        BOOLEAN    ,
                                    CONSTRAINT pk_cuenta PRIMARY KEY ( id_cuenta )
);

CREATE  TABLE movimiento (
                                        id_movimiento        INT  NOT NULL  auto_increment,
                                        id_cuenta            INT  NOT NULL,
                                        fecha                DATETIME    ,
                                        tipo_movimiento      VARCHAR(32)    ,
                                        valor                DECIMAL(8,3)    ,
                                        saldo                DECIMAL(8,3)    ,
                                        CONSTRAINT pk_movimiento PRIMARY KEY ( id_movimiento )
);

CREATE  TABLE persona (
                                     id_persona           INT  NOT NULL  auto_increment,
                                     nombre               VARCHAR(128)    ,
                                     identificacion       VARCHAR(10)    ,
                                     genero               VARCHAR(1)    ,
                                     edad                 INT    ,
                                     direccion            VARCHAR(256)    ,
                                     telefono             VARCHAR(10)    ,
                                     CONSTRAINT pk_persona PRIMARY KEY ( id_persona )
);
