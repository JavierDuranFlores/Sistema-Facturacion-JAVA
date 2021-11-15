-- CREACION DE LA BASE DE DATOS
CREATE DATABASE sistema_facturacion;

-- CREACION DE LA TABLA USUARIOS
CREATE TABLE usuarios (
    id SMALLSERIAL,
    usuario CHARACTER VARYING (40) NOT NULL,
    contra TEXT NOT NULL,

    CONSTRAINT pk_usuarios_id PRIMARY KEY (id)
);

-- CREACION DE LA TABLA CLIENTES
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

-- CREACION DE LA TABLA FACTURAS
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

-- CREACION DE LA TABLA PRODUCTOS
CREATE TABLE productos (
    id_producto SMALLSERIAL,
    nombre CHARACTER VARYING (60) NOT NULL,
    precio NUMERIC(12, 2) NOT NULL,
    stock INT NOT NULL,
    create_at DATE DEFAULT NOW(),

    CONSTRAINT pk_productos_id_producto PRIMARY KEY(id_producto)
);

-- CREACION DE LA TABLA FACTURAS ITEMS
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