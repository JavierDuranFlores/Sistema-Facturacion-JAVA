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
    edad INT,
    email CHARACTER VARYING(40) NOT NULL,
    direccion CHARACTER VARYING(40),
    telefono CHARACTER VARYING(20),
    create_at DATE DEFAULT NOW(),

    CONSTRAINT pk_clientes_id_cliente PRIMARY KEY (id_cliente),
    CONSTRAINT ck_clientes_edad CHECK (edad < 100),
    CONSTRAINT ck_clientes_telefono CHECK (telefono ~ '[\(]?[\+]?(\d{2}|\d{3})[\)]?[\s]?((\d{6}|\d{8})|(\d{3}[\*\.\-\s]){3}|(\d{2}[\*\.\-\s]){4}|(\d{4}[\*\.\-\s]){2})|\d{8}|\d{10}|\d{12}'),
    CONSTRAINT ck_clientes_emial CHECK (email ~ '^[a-z0-9!#$%&*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$')
);

CREATE TABLE facturas (
    id_factura SMALLSERIAL,
    n_productos SMALLINT NOT NULL,
    total NUMERIC (12, 2) NOT NULL,
    id_cliente SMALLINT NOT NULL,
    create_at DATE DEFAULT NOW(),

    CONSTRAINT pk_facturas_id_factura PRIMARY KEY (id_factura),
    CONSTRAINT fk_facturas_id_cliente_clientes_id_cliente FOREIGN KEY (id_cliente)
        REFERENCES clientes (id_cliente) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE productos (
    id_producto SMALLSERIAL,
    nombre CHARACTER VARYING (60) NOT NULL,
    precio NUMERIC(12, 2) NOT NULL,
    stock INT NOT NULL,
    create_at DATE DEFAULT NOW(),

    CONSTRAINT pk_productos_id_producto PRIMARY KEY(id_producto)
);


CREATE TABLE facturas_items (
    consecutivo SMALLSERIAL,
    cantidad INT NOT NULL,
    id_factura SMALLINT,
    id_producto SMALLINT,

    CONSTRAINT pk_consecutivo_id PRIMARY KEY (consecutivo, id_factura),

    CONSTRAINT fk_facturas_items_factura_id_facturas_id_factura FOREIGN KEY (id_factura)
        REFERENCES facturas (id_factura) ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT fk_facturas_items_producto_id_productos_id_producto FOREIGN KEY (id_producto)
        REFERENCES productos (id_producto) ON UPDATE CASCADE ON DELETE CASCADE
);

-- DATOS PARA EL SISTEMA

INSERT INTO clientes (nombre, apellidoP, apellidoM, edad, email, direccion, telefono, create_at) VALUES('Javier', 'Duran', 'Flores', 20, 'javierfloresj7@gmail.com', 'Las lomas', '9626302716', '2021-08-30');
INSERT INTO clientes (nombre, apellidoP, apellidoM, edad, email, direccion, telefono, create_at) VALUES('Linus', 'Benedict', 'Torvalds', 51, 'linus.torvalds@gmail.com', 'Finlandia', '9218126752', DEFAULT);
ingresa_cliente('Tim', 'Berners', 'Lee', 66, 'berners.timothy@gmail.com', 'Concord', '9876527891', DEFAULT);
ingresa_cliente('John', 'Doe', 'Barrios', 45, 'john.doe@gmail.com', 'Tapachula', '9876541591', DEFAULT);


INSERT INTO clientes (nombre, apellidoP, apellidoM, email, create_at) VALUES('John', 'Doe', 'Barrios', 'john.doe@gmail.com', '2017-08-02');
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

SELECT ingresa_cliente('Erich', 'Gamma', 'Muñoz', 49, 'erich.gamma@gmail.com', 'Aguascalientes', '2017-08-06');
SELECT ingresa_cliente('Rasmus', 'Lerdorf', 'Vargas', 38, 'rasmus.lerdorf@gmail.com', 'Florida','2017-08-05');
SELECT ingresa_cliente('Richard', 'Helm', 'Sanchez', 45, 'richard.helm@gmail.com', 'Tijuana', '2017-08-07');4
SELECT ingresa_cliente('Ralph', 'Johnson', 'Rodriguez', 40, 'ralph.johnson@gmail.com', 'San Francisco de Campeche', '2017-08-08');
SELECT ingresa_cliente('James', 'Gosling', 'Benitez', 55, 'james.gosling@gmail.com', 'Chihuahua', '2017-08-010');
SELECT ingresa_cliente('Bruce', 'Lee', 'Gonzalez', 56, 'bruce.lee@gmail.com', 'Saltillo', '2017-08-11');
SELECT ingresa_cliente('Johnny', 'Doe', 'Smith', 59, 'johnny.doe@gmail.com', 'Colima', '2017-08-12');
SELECT ingresa_cliente('Jane', 'Roe', 'Perez', 66, 'jane.roe@gmail.com', 'Victoria de Durango', '2017-08-14');
SELECT ingresa_cliente('Richard', 'Doe', 'Pierre', 41, 'richard.doe@gmail.com', 'Guanajuato', '2017-08-15');
SELECT ingresa_cliente('Janie', 'Doe', 'Fernandez', 53, 'janie.doe@gmail.com', 'Acapulco de Juárez', '2017-08-16');
SELECT ingresa_cliente('Phillip', 'Webb', 'Browne', '69', 'phillip.webb@gmail.com', 'Pachuca de Soto', '2017-08-17');
SELECT ingresa_cliente('Stephane', 'Nicoll', 'Charles', 36, 'stephane.nicoll@gmail.com', 'Guadalajara', '2017-08-18');
SELECT ingresa_cliente('Sam', 'Brannen', 'John', 58, 'sam.brannen@gmail.com', 'Morelia', '2017-08-19');
SELECT ingresa_cliente('Juergen', 'Hoeller', 'Guzma', 24, 'juergen.Hoeller@gmail.com', 'Cuernavaca', '2017-08-20');
SELECT ingresa_cliente('Janie', 'Roe', 'Clarke', 30, 'janie.roe@gmail.com', 'Tepic', '2017-08-21');
SELECT ingresa_cliente('John', 'Smith', 'Scarlet', 44, 'john.smith@gmail.com', 'Monterrey', '2017-08-22');
SELECT ingresa_cliente('Joe', 'Bloggs', 'Crockett', 33, 'joe.bloggs@gmail.com', 'Oaxaca de Juárez', '2017-08-23');
SELECT ingresa_cliente('John', 'Stiles', 'Dragneel', 52, 'john.stiles@gmail.com', 'Puebla de Zaragoza', '2017-08-24');
SELECT ingresa_cliente('Richard', 'Roe', 'Singh', 29, 'stiles.roe@gmail.com', 'Santiago de Querétaro', '2017-08-25');

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

INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Coca Cola', 40, 5, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Arroz', 23, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Frijol', 25, 6, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Galleta Maria', 11, 3, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Galleta Pan Crema', 12, 8, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Doritos', 16, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Takis Guacamole', 15, 15, NOW());

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


CREATE FUNCTION concatenar(text, text, text)
    RETURNS text AS
    $$
        BEGIN
            IF $1 IS NULL THEN
                RETURN $2;
            ELSE
                RETURN $1 || $3 || $2;
            END IF;
        END;
    $$
LANGUAGE plpgsql;

SELECT concatenar('E'', 'Javier', 'E'');


CREATE OR REPLACE FUNCTION filtrar_clientes (tipo CHARACTER VARYING, busqueda CHARACTER VARYING)
RETURNS SETOF clientes
AS
$BODY$
    BEGIN
        IF tipo = 'id_cliente' THEN
            RETURN QUERY SELECT * FROM clientes WHERE id_cliente = busqueda::SMALLINT;
        ELSIF tipo = 'nombre'THEN
            RETURN QUERY SELECT * FROM clientes WHERE nombre = busqueda;
        ELSIF tipo = 'apellidop'THEN
            RETURN QUERY SELECT * FROM clientes WHERE apellidop = busqueda;
        ELSIF tipo = 'apellidom'THEN
            RETURN QUERY SELECT * FROM clientes WHERE apellidom = busqueda;
        ELSIF tipo = 'edad'THEN
            RETURN QUERY SELECT * FROM clientes WHERE edad = busqueda::INT;
        ELSIF tipo = 'email'THEN
            RETURN QUERY SELECT * FROM clientes WHERE email = busqueda;
        ELSIF tipo = 'direccion'THEN
            RETURN QUERY SELECT * FROM clientes WHERE direccion = busqueda;
        ELSIF tipo = 'telefono'THEN
            RETURN QUERY SELECT * FROM clientes WHERE telefono = busqueda;
        ELSIF tipo = 'create_at'THEN
            RETURN QUERY SELECT * FROM clientes WHERE create_at = busqueda::DATE;
        END IF;
    END;
$BODY$
LANGUAGE 'plpgsql';

SELECT * FROM filtrar_clientes ('id_cliente', '1');
SELECT * FROM filtrar_clientes ('nombre', 'Javier');
SELECT * FROM filtrar_clientes ('apellidop', 'Duran');
SELECT * FROM filtrar_clientes ('apellidom', 'Flores');
SELECT * FROM filtrar_clientes ('edad', '21');
SELECT * FROM filtrar_clientes ('email', 'javierfloresj7@gmail.com');
SELECT * FROM filtrar_clientes ('direccion', 'Las lomas');
SELECT * FROM filtrar_clientes ('telefono', '9626302716');
SELECT * FROM filtrar_clientes ('create_at', '2021-09-11');

CREATE OR REPLACE FUNCTION filtrar_clientes (INT)
RETURNS SETOF clientes
AS
$BODY$
        SELECT * FROM clientes WHERE clientes.id_cliente = $1::SMALLINT;
$BODY$
LANGUAGE 'sql';

SELECT * FROM filtrar_clientes (1);

CREATE OR REPLACE FUNCTION leer_clientes ()
RETURNS SETOF clientes
AS
$BODY$
        SELECT * FROM clientes;
$BODY$
LANGUAGE 'sql';

SELECT * FROM leer_clientes ();


/* FUNCION PAGINACION CLIENTES */
CREATE OR REPLACE FUNCTION consulta_paginada_clientes(_limite CHARACTER VARYING, _pagina CHARACTER VARYING)
RETURNS SETOF clientes AS
$BODY$
    DECLARE
        inicio INT;
    BEGIN
        inicio = _limite::INT * _pagina::INT - _limite::INT;
        RETURN QUERY SELECT id_cliente, nombre, apellidop, apellidom, edad, email, direccion, telefono, create_at
                     FROM clientes ORDER BY id_cliente
                     LIMIT _limite::INT OFFSET inicio;

    END;
$BODY$
LANGUAGE plpgsql;
SELECT * FROM consulta_paginada_clientes('10', '1');

/* FUNCION PARA INGRESAR CLIENTES */
CREATE OR REPLACE FUNCTION ingresa_cliente(
    _nombre CHARACTER VARYING,
    _apellidop CHARACTER VARYING,
    _apellidom CHARACTER VARYING,
    _edad CHARACTER VARYING,
    _email CHARACTER VARYING,
    _direccion CHARACTER VARYING,
    _telefono CHARACTER VARYING
) RETURNS VOID AS
$BODY$

    BEGIN
        INSERT INTO clientes (id_cliente, nombre, apellidop, apellidom, edad, email, direccion, telefono, create_at)
        VALUES (DEFAULT, _nombre, _apellidop, _apellidom, _edad::INT, _email, _direccion, _telefono, DEFAULT);
    END;

$BODY$
LANGUAGE plpgsql;

SELECT ingresa_cliente('Tim', 'Berners', 'Lee', '66', 'berners.timothy@gmail.com', 'Concord', '9876527891');
SELECT ingresa_cliente('John', 'Doe', 'Barrios', '45', 'john.doe@gmail.com', 'Tapachula', '9876541591');

SELECT ingresa_cliente('Erich', 'Gamma', 'Muñoz', '49', 'erich.gamma@gmail.com', 'Aguascalientes', '8761726372');
SELECT ingresa_cliente('Rasmus', 'Lerdorf', 'Vargas', '38', 'rasmus.lerdorf@gmail.com', 'Florida','7287283829');
SELECT ingresa_cliente('Richard', 'Helm', 'Sanchez', '45', 'richard.helm@gmail.com', 'Tijuana', '7829723829');
SELECT ingresa_cliente('Ralph', 'Johnson', 'Rodriguez', '40', 'ralph.johnson@gmail.com', 'San Francisco de Campeche', '9628277362');
SELECT ingresa_cliente('James', 'Gosling', 'Benitez', '55', 'james.gosling@gmail.com', 'Chihuahua', '8362735171');
SELECT ingresa_cliente('Bruce', 'Lee', 'Gonzalez', '56', 'bruce.lee@gmail.com', 'Saltillo', '9727331827');

SELECT ingresa_cliente('Johnny', 'Doe', 'Smith', '59', 'johnny.doe@gmail.com', 'Colima', '7381627620');
SELECT ingresa_cliente('Jane', 'Roe', 'Perez', '66', 'jane.roe@gmail.com', 'Victoria de Durango', '8926371086');
SELECT ingresa_cliente('Richard', 'Doe', 'Pierre', '41', 'richard.doe@gmail.com', 'Guanajuato', '7853452134');
SELECT ingresa_cliente('Janie', 'Doe', 'Fernandez', '53', 'janie.doe@gmail.com', 'Acapulco de Juárez', '3571368523');
SELECT ingresa_cliente('Phillip', 'Webb', 'Browne', '69', 'phillip.webb@gmail.com', 'Pachuca de Soto', '8152871502');
SELECT ingresa_cliente('Stephane', 'Nicoll', 'Charles', '36', 'stephane.nicoll@gmail.com', 'Guadalajara', '8263786152');
SELECT ingresa_cliente('Sam', 'Brannen', 'John', '58', 'sam.brannen@gmail.com', 'Morelia', '2017-08-19');
SELECT ingresa_cliente('Juergen', 'Hoeller', 'Guzma', '24', 'juergen.Hoeller@gmail.com', 'Cuernavaca', '8418726183');
SELECT ingresa_cliente('Janie', 'Roe', 'Clarke', '30', 'janie.roe@gmail.com', 'Tepic', '7162891627');
SELECT ingresa_cliente('John', 'Smith', 'Scarlet', '44', 'john.smith@gmail.com', 'Monterrey', '1728648295');
SELECT ingresa_cliente('Joe', 'Bloggs', 'Crockett', '33', 'joe.bloggs@gmail.com', 'Oaxaca de Juárez', '5182619724');
SELECT ingresa_cliente('John', 'Stiles', 'Dragneel', '52', 'john.stiles@gmail.com', 'Puebla de Zaragoza', '3619875267');
SELECT ingresa_cliente('Richard', 'Roe', 'Singh', '29', 'stiles.roe@gmail.com', 'Santiago de Querétaro', '4910819524');

SELECT ingresa_cliente('Adriana Carolina', 'Hernandez ', 'Monterroza', '35', 'adriana.hernandez@gmail.com', 'Aguascalientes', '4910819524');
SELECT ingresa_cliente('Alejandro', 'Abondano', 'Acevedo', '20', 'alejandro.abondono@gmail.com', 'Ciudad de México ', '6410819524');
SELECT ingresa_cliente('Andrea Catalina', 'Acero', 'Caro', '29', 'andrea.caro@gmail.com', 'Coahuila', '49630819524');
SELECT ingresa_cliente('Angela', 'Mahecha', 'Piñeros', '59', 'angela.piñeros@gmail.com', 'Colima', '49181519524');
SELECT ingresa_cliente('Angelica', 'Blanco', 'SinConchagh', '60', 'stiles.sinconchagh@gmail.com', 'Durango', '4910819810');
SELECT ingresa_cliente('Angie', 'Fernández', 'Martínez', '38', 'angie.martinez@gmail.com', 'Estado de México', '4910819524');
SELECT ingresa_cliente('Brigite', 'Polanco', 'Ruiz', '51', 'brigite.ruiz@gmail.com', 'Guanajuato', '4981719524');
SELECT ingresa_cliente('Camilo', 'Villamizar', 'Aristizabal', '45', 'camilo.aristizabal@gmail.com', 'Guerrero', '4917181952');
SELECT ingresa_cliente('Carlos', 'Polo', 'Castellanos', '27', 'carlos.castellanos@gmail.com', 'Hidalgo', '8770819524');
SELECT ingresa_cliente('Carol', 'Gomez', 'Gianine', '57', 'carol.gianine@gmail.com', 'Jalisco', '9110819524');
SELECT ingresa_cliente('Carolina', 'Pintor', 'Pinzon', '63', 'carolina.pinzon@gmail.com', 'Michoacán', '4919919524');
SELECT ingresa_cliente('Catherine', 'Ospina', 'Alfonso', '24', 'catherine.alfonso@gmail.com', 'Morelos', '5010819524');
SELECT ingresa_cliente('Cinthya', 'Dussán', 'Guzmán', '37', 'cinthya.guzman@gmail.com', 'Nayarit', '6000819524');
SELECT ingresa_cliente('Claudia', 'Torres', 'Frias', '35', 'claudia.frias@gmail.com', 'Nuevo León', '7110819524');
SELECT ingresa_cliente('Daniela', 'Hernández', 'Bravo', '23', 'daniela.bravo@gmail.com', 'Oaxaca', '8162819524');
SELECT ingresa_cliente('Deny', 'Muñoz', 'Lizarazo', '49', 'deny.lizarazo@gmail.com', 'Puebla', '4918719524');

SELECT ingresa_cliente('Kiara', 'Escobar ', 'Montes', '25', 'kiara.escobar@gmail.com', 'Tuxtla Chico', '5000819524');
SELECT ingresa_cliente('Willian Alexander', 'Escobar', 'Montes', '28', 'willian.escobar@gmail.com', 'Tuxtla Chico ', '5010819524');
SELECT ingresa_cliente('Noemi Berenice', 'Guzman', 'Flores', '23', 'noemi.guzma@gmail.com', 'Coahuila', '49630123524');
SELECT ingresa_cliente('Esther', 'Flores', 'Barrios', '45', 'esther.flores@gmail.com', 'Colima', '49181519372');
SELECT ingresa_cliente('Mariana', 'Barrios', 'Salazar', '21', 'mariana.barrios@gmail.com', 'Durango', '9147819810');
SELECT ingresa_cliente('Monserat', 'Montes', 'Torres', '26', 'monserat.montes@gmail.com', 'Estado de México', '1049819524');
SELECT ingresa_cliente('Ximena', 'Palacios', 'Ruiz', '34', 'ximena.palacios@gmail.com', 'Guanajuato', '4987119524');
SELECT ingresa_cliente('Carlos', 'Camacho', 'Guzman', '30', 'carlos.camacho@gmail.com', 'Guerrero', '4917811952');
SELECT ingresa_cliente('Eduardo', 'Bonilla', 'Barrios', '27', 'eduardo.bonilla@gmail.com', 'Huixtla', '1000819524');
SELECT ingresa_cliente('Jose', 'Quiroa', 'Carrifo', '43', 'jose.quiroa@gmail.com', 'Jalisco', '9180119524');
SELECT ingresa_cliente('Luis Fernando', 'Cancino', 'Salazar', '22', 'luis.cancino@gmail.com', 'Michoacán', '4918819524');
SELECT ingresa_cliente('Dalia', 'Guzman', 'Manchinelie', '24', 'dalia.guzman@gmail.com', 'Puebla', '5090119524');
SELECT ingresa_cliente('Meliton', 'Flores', 'Cosen', '37', 'meliton.flores@gmail.com', 'Nayarit', '6000819524');
SELECT ingresa_cliente('Enrique', 'Bello', 'Torres', '28', 'enrique.bello@gmail.com', 'Oaxaca', '1170819524');
SELECT ingresa_cliente('Victor', 'Diaz', 'Esteban', '23', 'victor.diaz@gmail.com', 'Mazatan', '1862819524');
SELECT ingresa_cliente('Juan Carlos', 'Fernandez', 'Lopez', '49', 'deny.lizarazo@gmail.com', 'Nayarit', '4918719524');


Aguascalientes
Baja California
Baja California Sur
Campeche
Chiapas
Chihuahua

Ciudad de México
Coahuila
Colima
Durango
Estado de México
Guanajuato
Guerrero
Hidalgo
Jalisco
Michoacán
Morelos
Nayarit
Nuevo León
Oaxaca
Puebla


Querétaro
Quintana Roo
San Luis Potosí
Sinaloa
Sonora
Tabasco
Tamaulipas
Tlaxcala
Veracruz
Yucatán
Zacatecas

CREATE OR REPLACE FUNCTION actualizar_cliente(
    _id CHARACTER VARYING,
    _nombre CHARACTER VARYING,
    _apellidop CHARACTER VARYING,
    _apellidom CHARACTER VARYING,
    _edad CHARACTER VARYING,
    _email CHARACTER VARYING,
    _direccion CHARACTER VARYING,
    _telefono CHARACTER VARYING
) RETURNS VOID AS
$BODY$

    BEGIN
        UPDATE clientes SET nombre = _nombre,
                            apellidop = _apellidop,
                            apellidom = _apellidom,
                            edad = _edad::INT,
                            email = _email,
                            direccion = _direccion,
                            telefono = _telefono
        WHERE clientes.id_cliente = _id::SMALLINT;

    END;

$BODY$
LANGUAGE plpgsql;

SELECT actualizar_cliente(4, 'John', 'Doe', 'Barrios', '45', 'john.doe@gmail.com', 'Tapachula', '9876541591');

CREATE OR REPLACE FUNCTION eliminar_cliente(_id INT)
RETURNS VOID AS
$BODY$
    BEGIN
        DELETE FROM clientes WHERE id_cliente = _id::SMALLINT;
    END;
$BODY$
LANGUAGE plpgsql;

SELECT eliminar_cliente(4);


--          PRODUCTOS
------
CREATE OR REPLACE FUNCTION leer_productos ()
RETURNS SETOF productos
AS
$BODY$
        SELECT * FROM productos;
$BODY$
LANGUAGE 'sql';

SELECT * FROM leer_productos ();

------
CREATE OR REPLACE FUNCTION consulta_paginada_productos(_limite CHARACTER VARYING, _pagina CHARACTER VARYING)
RETURNS SETOF productos AS
$BODY$
    DECLARE
        inicio INT;
    BEGIN
        inicio = _limite::INT * _pagina::INT - _limite::INT;
        RETURN QUERY SELECT id_producto, nombre, precio, stock, create_at
                     FROM productos ORDER BY id_producto
                     LIMIT _limite::INT OFFSET inicio;

    END;
$BODY$
LANGUAGE plpgsql;

------
SELECT * FROM consulta_paginada_productos('10', '1');

------
CREATE OR REPLACE FUNCTION filtrar_productos (INT)
RETURNS SETOF productos
AS
$BODY$
        SELECT * FROM productos WHERE productos.id_producto = $1::SMALLINT;
$BODY$
LANGUAGE 'sql';

SELECT * FROM filtrar_productos (1);

------
CREATE OR REPLACE FUNCTION filtrar_productos (tipo CHARACTER VARYING, busqueda CHARACTER VARYING)
RETURNS SETOF productos
AS
$BODY$
    BEGIN
        IF tipo = 'id_producto' THEN
            RETURN QUERY SELECT * FROM productos WHERE id_producto = busqueda::SMALLINT;
        ELSIF tipo = 'nombre'THEN
            RETURN QUERY SELECT * FROM productos WHERE nombre = busqueda;
        ELSIF tipo = 'precio'THEN
            RETURN QUERY SELECT * FROM productos WHERE precio = busqueda::NUMERIC;
        ELSIF tipo = 'stock'THEN
            RETURN QUERY SELECT * FROM productos WHERE stock = busqueda::INT;
        ELSIF tipo = 'create_at'THEN
            RETURN QUERY SELECT * FROM productos WHERE create_at = busqueda::DATE;
        END IF;
    END;
$BODY$
LANGUAGE 'plpgsql';

SELECT * FROM filtrar_clientes ('id_cliente', '1');

/* FUNCION PARA INGRESAR PRODUCTOS */
CREATE OR REPLACE FUNCTION ingresa_producto(
    _nombre CHARACTER VARYING,
    _precio CHARACTER VARYING,
    _stock CHARACTER VARYING
) RETURNS VOID AS
$BODY$

    BEGIN
        INSERT INTO productos (id_producto, nombre, precio, stock, create_at)
        VALUES (DEFAULT, _nombre, _precio::NUMERIC, _stock::INT, DEFAULT);
    END;

$BODY$
LANGUAGE plpgsql;

SELECT ingresa_producto('Top-Tops', '10', '10');

-- ACTUALIZAR PRODUCTOS
CREATE OR REPLACE FUNCTION actualizar_producto(
    _id CHARACTER VARYING,
    _nombre CHARACTER VARYING,
    _precio CHARACTER VARYING,
    _stock CHARACTER VARYING
) RETURNS VOID AS
$BODY$

    BEGIN
        UPDATE productos SET nombre = _nombre,
                            precio = _precio::NUMERIC,
                            stock = _stock::SMALLINT
        WHERE productos.id_producto = _id::SMALLINT;

    END;

$BODY$
LANGUAGE plpgsql;

-- ELIMINAR PRODUCTO
CREATE OR REPLACE FUNCTION eliminar_producto(_id INT)
RETURNS VOID AS
$BODY$
    BEGIN
        DELETE FROM productos WHERE id_producto = _id::SMALLINT;
    END;
$BODY$
LANGUAGE plpgsql;



