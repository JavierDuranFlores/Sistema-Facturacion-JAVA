/*

    NOMENCLATURA DE NOMBRE A LLAVES PRIMARIA
    pk_nombreTabla_atributo

    NOMENCLATURA DE NOMBRE A LLAVES FORANEA
    fk_nombreTabla_nombreTablaRefenciada_atributo

*/

CREATE DATABASE sistema_facturacion;

-------------------- USUARIOS --------------------

-- CREACION DE LA TABLA PREGUNTAS_RECUPERACION
CREATE TABLE pregunta_recuperacion (
    id SMALLSERIAL,
    pregunta CHARACTER VARYING (40) NOT NULL,

    CONSTRAINT pk_pregunta_recuperacion_id 
        PRIMARY KEY (id)
);

INSERT INTO pregunta_recuperacion (id, pregunta) 
VALUES (DEFAULT, '¿Nombre de tu primer mascota?'),
       (DEFAULT, '¿Nombre de tu primer novia?'),
       (DEFAULT, '¿Nombre de tu padre?');

-- CREACION DE LA TABLA USUARIOS
CREATE TABLE usuarios (
    id SMALLSERIAL,
    usuario CHARACTER VARYING (40) NOT NULL,
    contra TEXT NOT NULL,
    id_pregunta SMALLINT,

    CONSTRAINT pk_usuarios_id PRIMARY KEY (id),
    CONSTRAINT fk_usuarios_pregunta_recuperacion_id_pregunta
        FOREIGN KEY (id_pregunta)
            REFERENCES pregunta_recuperacion (id)
                ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO usuarios (id, usuario, contra, id_pregunta) VALUES (DEFAULT, 'Javier', ENCRYPT('Duran2001','llave', '3des')::text, 1);

SELECT id, encode(decrypt(contra::bytea, 'llave', '3des'::text), 'escape'::text) FROM usuarios;

-- CREACION DE LA TABLA RESPUESTA_RECUPERACION
CREATE TABLE respuesta_recuperacion (
    id SMALLSERIAL,
    respuesta CHARACTER VARYING(30) NOT NULL,
    id_usuario SMALLINT,
    id_pregunta SMALLINT,

    CONSTRAINT pk_respuesta_recuperacion_id     
        PRIMARY KEY (id),

    CONSTRAINT fk_respuesta_recuperacion_usuarios_id_usuario
        FOREIGN KEY (id_usuario) 
            REFERENCES usuarios (id)
                ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT fk_respuesta_recuperacion_pregunta_recuperacion_id_pregunta
        FOREIGN KEY (id_pregunta) 
            REFERENCES pregunta_recuperacion (id)
                ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO respuesta_recuperacion (respuesta, id_usuario, id_pregunta) VALUES ('Mishorin', 1, 1);

CREATE TABLE clientes (
    id_cliente SMALLSERIAL,
    nombre CHARACTER VARYING (30) NOT NULL,
    apellidoP CHARACTER VARYING(30) NOT NULL,
    apellidoM CHARACTER VARYING(30) NOT NULL,
    email CHARACTER VARYING(40) NOT NULL,
    create_at DATE NOT NULL,

    CONSTRAINT pk_clientes_id_cliente PRIMARY KEY (id_cliente) 
);

CREATE TABLE facturas (
    id_factura SMALLSERIAL,
    n_productos SMALLINT NOT NULL,
    total NUMERIC (12, 2) NOT NULL,
    id_cliente SMALLINT NOT NULL,
    create_at DATE NOT NULL,

    CONSTRAINT pk_facturas_id_factura PRIMARY KEY (id_factura),
    CONSTRAINT fk_facturas_id_cliente_clientes_id_cliente FOREIGN KEY (id_cliente)
        REFERENCES clientes (id_cliente) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE productos (
    id_producto SMALLSERIAL,
    nombre CHARACTER VARYING (60) NOT NULL,
    precio NUMERIC(12, 2) NOT NULL,
    stock INT NOT NULL,
    create_at DATE NOT NULL,

    CONSTRAINT pk_productos_id_producto PRIMARY KEY(id_producto)
);

CREATE TABLE facturas_items (
    id_factura_item SMALLSERIAL,
    cantidad INT NOT NULL,
    id_factura SMALLINT,
    id_producto SMALLINT,

    CONSTRAINT pk_facturas_items_id PRIMARY KEY (id_factura_item),

    CONSTRAINT fk_facturas_items_factura_id_facturas_id_factura FOREIGN KEY (id_factura)
        REFERENCES facturas (id_factura) ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT fk_facturas_items_producto_id_productos_id_producto FOREIGN KEY (id_producto)
        REFERENCES productos (id_producto) ON UPDATE CASCADE ON DELETE CASCADE
);

-- DATOS PARA EL SISTEMA

INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Javier', 'Duran', 'Flores','javierfloresj7@gmail.com', '2021-08-30');



INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('John', 'Doe', 'Barrios', 'john.doe@gmail.com', '2017-08-02');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Linus', 'Benedict', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Jane', 'Doe', 'Barrios', 'jane.doe@gmail.com', '2017-08-04');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Rasmus', 'Lerdorf', 'Vargas','rasmus.lerdorf@gmail.com', '2017-08-05');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Erich', 'Gamma', 'Muñoz', 'erich.gamma@gmail.com', '2017-08-06');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Richard', 'Helm', 'Sanchez', 'richard.helm@gmail.com', '2017-08-07');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Ralph', 'Johnson', 'Rodriguez', 'ralph.johnson@gmail.com', '2017-08-08');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('John', 'Vlissides', 'Santos', 'john.vlissides@gmail.com', '2017-08-09');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('James', 'Gosling', 'Benitez', 'james.gosling@gmail.com', '2017-08-010');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Bruce', 'Lee', 'Gonzalez', 'bruce.lee@gmail.com', '2017-08-11');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Johnny', 'Doe', 'Smith', 'johnny.doe@gmail.com', '2017-08-12');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('John', 'Roe', 'Perez', 'john.roe@gmail.com', '2017-08-13');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Jane', 'Roe', 'Perez', 'jane.roe@gmail.com', '2017-08-14');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Richard', 'Doe', 'Pierre', 'richard.doe@gmail.com', '2017-08-15');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Janie', 'Doe', 'Fernandez','janie.doe@gmail.com', '2017-08-16');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Phillip', 'Webb', 'Browne','phillip.webb@gmail.com', '2017-08-17');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Stephane', 'Nicoll', 'Charles', 'stephane.nicoll@gmail.com', '2017-08-18');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Sam', 'Brannen', 'John', 'sam.brannen@gmail.com', '2017-08-19');  
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20'); 
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Janie', 'Roe', 'Clarke', 'janie.roe@gmail.com', '2017-08-21');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('John', 'Smith', 'Scarlet', 'john.smith@gmail.com', '2017-08-22');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Joe', 'Bloggs', 'Crockett','joe.bloggs@gmail.com', '2017-08-23');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('John', 'Stiles', 'Dragneel', 'john.stiles@gmail.com', '2017-08-24');
INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('Richard', 'Roe', 'Singh', 'stiles.roe@gmail.com', '2017-08-25');

/* Populate tables clientes */
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Javier', 'Duran', 'javier.duran@.com', '2017-08-01', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');  
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', ''); 
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '');

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Panasonic Pantalla LCD', 259990, 5, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Apple iPod shuffle', 1499990, 6, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Sony Notebook Z110', 37990, 3, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, 8, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Mica Comoda 5 Cajones', 299990, 15, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(4, 667960, 1, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

-- INNER JOIN FACTURAS
SELECT * FROM clientes;
SELECT * FROM facturas;
SELECT * FROM facturas_items;
SELECT * FROM productos;

SELECT 
    clientes.id_cliente, 
    clientes.nombre, 
    facturas.id_factura,
    facturas_items.id_factura_item,
    productos.nombre,
    productos.precio
FROM 
    clientes 
    INNER JOIN facturas 
ON 
    facturas.id_cliente = clientes.id_cliente
    INNER JOIN facturas_items
ON 
    facturas.id_factura = facturas_items.id_factura
    INNER JOIN productos
ON  
    facturas_items.id_producto = productos.id_producto;

--   FUNCIONES
--  FUNCION PARA OBTENER LA SUMA DE TODOS LOS PRODUCTOS DE LA FACTURA DEL CLIENTE

CREATE OR REPLACE FUNCTION total_factura (INT) RETURNS TABLE (total productos.precio%TYPE)
AS
$BODY$
BEGIN
    RETURN QUERY SELECT 
                    SUM(productos.precio) 
                 FROM 
                    productos 
                 INNER JOIN facturas_items 
                 ON productos.id_producto = facturas_items.id_producto 
                 INNER JOIN facturas 
                 ON facturas_items.id_factura = facturas.id_factura 
                 WHERE facturas.id_factura = $1;
END;
$BODY$
LANGUAGE 'plpgsql';

--- FUNCION PARA VERIFICAR CUENTA

CREATE OR REPLACE FUNCTION autenticacion (_usuario CHARACTER VARYING, _contra CHARACTER VARYING)
RETURNS TABLE (usuario usuarios.usuario%TYPE)
AS
$BODY$

    BEGIN

        RETURN QUERY SELECT 
                        usuarios.usuario 
                     FROM 
                        usuarios 
                     WHERE usuarios.usuario = _usuario 
                     AND encode(decrypt(usuarios.contra::bytea, 'llave', '3des'::text), 'escape'::text) = _contra;

    END;

$BODY$
LANGUAGE plpgsql;

SELECT autenticacion ('Javier', 'Duran2001');
