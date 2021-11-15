--          FACTURAS
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
-- EJECUTANDO FUNCION
SELECT * FROM total_factura (1);

-- FUNCION PARA MOSTRAR LAS FACTURAS
CREATE OR REPLACE FUNCTION leer_facturas ()
RETURNS SETOF facturas
AS
$BODY$
        SELECT * FROM facturas;
$BODY$
LANGUAGE 'sql';

SELECT * FROM leer_facturas ();

-- FUNCIONA PARA MOSTRAR FACTURAS PAGINAS
CREATE OR REPLACE FUNCTION consulta_paginada_facturas(_limite CHARACTER VARYING, _pagina CHARACTER VARYING)
RETURNS SETOF facturas AS
$BODY$
    DECLARE
        inicio INT;
    BEGIN
        inicio = _limite::INT * _pagina::INT - _limite::INT;
        RETURN QUERY SELECT id_factura, n_productos, total, id_cliente, create_at
                     FROM facturas ORDER BY id_factura
                     LIMIT _limite::INT OFFSET inicio;

    END;
$BODY$
LANGUAGE plpgsql;
-- EJECUTANDO FUNCION
SELECT * FROM consulta_paginada_facturas('10', '1');

-- FUNCION PARA PAGINAR FACTURAS POR ID CLIENTE
CREATE OR REPLACE FUNCTION consulta_paginada_facturas_byIdCliente(_limite CHARACTER VARYING, _pagina CHARACTER VARYING, _id_cliente CHARACTER VARYING)
RETURNS SETOF facturas AS
$BODY$
    DECLARE
        inicio INT;
    BEGIN
        inicio = _limite::INT * _pagina::INT - _limite::INT;
        RETURN QUERY SELECT id_factura, n_productos, total, id_cliente, create_at
                     FROM facturas WHERE id_cliente = _id_cliente::SMALLINT ORDER BY id_factura
                     LIMIT _limite::INT OFFSET inicio::INT;

    END;
$BODY$
LANGUAGE plpgsql;
-- EJECUTANDO FUNCION
SELECT * FROM consulta_paginada_facturas_byIdCliente('4', '1', '2');

-- FUNCION  PARA INGRESAR FACTURAS
CREATE OR REPLACE FUNCTION ingresa_facturas(CHARACTER VARYING, CHARACTER VARYING, CHARACTER VARYING)
RETURNS VOID
AS
$BODY$

    BEGIN
        INSERT INTO facturas (n_productos, total, id_cliente) VALUES ($1::SMALLINT, $2::NUMERIC, $3::SMALLINT);
    END;

$BODY$
LANGUAGE plpgsql;

-- FUNCION PARA FILTRAR FACTURAS POR SUS DIFERENTES COLUMNAS
CREATE OR REPLACE FUNCTION filtrar_facturas (tipo CHARACTER VARYING, busqueda CHARACTER VARYING)
RETURNS SETOF facturas
AS
$BODY$
    BEGIN
        IF tipo = 'id_factura' THEN
            RETURN QUERY SELECT * FROM facturas WHERE id_factura = busqueda::SMALLINT;
        ELSIF tipo = 'n_productos'THEN
            RETURN QUERY SELECT * FROM facturas WHERE n_productos = busqueda::SMALLINT;
        ELSIF tipo = 'total'THEN
            RETURN QUERY SELECT * FROM facturas WHERE total = busqueda::NUMERIC;
        ELSIF tipo = 'id_cliente'THEN
            RETURN QUERY SELECT * FROM facturas WHERE id_cliente = busqueda::SMALLINT;
        ELSIF tipo = 'create_at'THEN
            RETURN QUERY SELECT * FROM facturas WHERE create_at = busqueda::DATE;
        END IF;
    END;
$BODY$
LANGUAGE 'plpgsql';
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_facturas ('id_factura', '2');

-- FUNCION PARA INSERTAR EN LA TABLA facturas_items
CREATE OR REPLACE FUNCTION ingresa_facturas_items(CHARACTER VARYING, CHARACTER VARYING, CHARACTER VARYING)
RETURNS VOID
AS
$BODY$

    BEGIN
        INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES ($1::INT, $2::SMALLINT, $3::SMALLINT);
    END;

$BODY$
LANGUAGE plpgsql;

-- FUNCION PARA MOSTRAR FACTURAS ITEMS
CREATE OR REPLACE FUNCTION leer_facturas_items()
RETURNS TABLE (idf SMALLINT, np SMALLINT , totalf NUMERIC,  idc SMALLINT, caf DATE, idfi SMALLINT, cantidadfi INT, idp SMALLINT, nombrep CHARACTER VARYING, preciop NUMERIC(12, 2), stockp INT, cap DATE) AS
$BODY$

    BEGIN

        RETURN QUERY SELECT f.id_factura,
               f.n_productos,
               f.total,
               f.id_cliente,
               f.create_at,
               fi.id_factura_item,
               fi.cantidad,
               p.id_producto,
               p.nombre,
               p.precio,
               p.stock,
               p.create_at
        FROM facturas AS f
        INNER JOIN facturas_items AS fi
        ON f.id_factura = fi.id_factura
        INNER JOIN productos AS p
        ON fi.id_producto = p.id_producto;

    END;

$BODY$
LANGUAGE plpgsql;

-- FUNCION PARA MOSTRAR FACTURAS ITEMS POR SU COLUMNA IDCLIENTE
CREATE OR REPLACE FUNCTION leer_facturas_items_clientes(CHARACTER VARYING, CHARACTER VARYING)
RETURNS TABLE (idf SMALLINT,  idc SMALLINT, idfi SMALLINT, idp SMALLINT) AS
$BODY$

    BEGIN

        RETURN QUERY SELECT f.id_factura,
               f.id_cliente,
               fi.id_factura_item,
               p.id_producto
        FROM facturas AS f
        INNER JOIN facturas_items AS fi
        ON f.id_factura = fi.id_factura
        INNER JOIN productos AS p
        ON fi.id_producto = p.id_producto
        INNER JOIN clientes AS c
        ON c.id_cliente = f.id_cliente
        WHERE f.id_cliente = $1::SMALLINT
        AND
            f.id_factura = $2::SMALLINT;

    END;

$BODY$
LANGUAGE plpgsql;
-- EJECUTANDO FUNCION
SELECT * FROM leer_facturas_items_clientes('2', '36');

-- FUNCION PARA FILTRAR FACTURAS ITEMS POR SU COLUMNA IDFACTURA
CREATE OR REPLACE FUNCTION filtrar_facturas_items_ByIdFactura(CHARACTER VARYING)
RETURNS TABLE (idf SMALLINT, np SMALLINT , totalf NUMERIC,  idc SMALLINT, caf DATE, idfi SMALLINT, cantidadfi INT, idp SMALLINT, nombrep CHARACTER VARYING, preciop NUMERIC(12, 2), stockp INT, cap DATE) AS
$BODY$

    BEGIN

        RETURN QUERY SELECT f.id_factura,
               f.n_productos,
               f.total,
               f.id_cliente,
               f.create_at,
               fi.id_factura_item,
               fi.cantidad,
               p.id_producto,
               p.nombre,
               p.precio,
               p.stock,
               p.create_at
        FROM facturas AS f
        INNER JOIN facturas_items AS fi
        ON f.id_factura = fi.id_factura
        INNER JOIN productos AS p
        ON fi.id_producto = p.id_producto WHERE f.id_factura = $1::SMALLINT;

    END;

$BODY$
LANGUAGE plpgsql;
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_facturas_items('36');

-- FUNCION PARA FILTAR FACTURAS ITEMS POR SU ID
CREATE OR REPLACE FUNCTION filtrar_facturas_items (id INT)
RETURNS SETOF facturas_items
AS
$BODY$
    BEGIN
        RETURN QUERY SELECT * FROM facturas_items WHERE id_factura_item = id::SMALLINT;
    END;
$BODY$
LANGUAGE 'plpgsql';
-- EJECUNTANDO FUNCION
SELECT * FROM filtrar_facturas_items(189);

-- FUNCION PARA ELIMINAR FACTURA POR SU ID
CREATE OR REPLACE FUNCTION eliminar_factura(INT)
RETURNS VOID AS
$BODY$

    BEGIN

        DELETE FROM facturas WHERE id_factura = $1::SMALLINT;

    END;

$BODY$
LANGUAGE plpgsql;
-- EJECUTANOD FUNCION
SELECT eliminar_factura(21);

-- FUNCION PARA ACTUALIZAR FACTURA
CREATE OR REPLACE FUNCTION actualizar_facturas(CHARACTER VARYING, CHARACTER VARYING, CHARACTER VARYING, CHARACTER VARYING)
RETURNS VOID
AS
$BODY$

    BEGIN
        UPDATE facturas
        SET n_productos = $1::SMALLINT,
            total = $2::NUMERIC,
            id_cliente = $3::SMALLINT,
            create_at = NOW()
        WHERE id_factura = $4::SMALLINT;
    END;

$BODY$
LANGUAGE plpgsql;

-- FUNCION PARA ACTUALIZAR FACUTRAS ITEMS
CREATE OR REPLACE FUNCTION actualizar_facturas_items(CHARACTER VARYING, CHARACTER VARYING, CHARACTER VARYING, CHARACTER VARYING)
RETURNS VOID
AS
$BODY$

    BEGIN
        UPDATE facturas_items
        SET cantidad = $1::INT,
            id_factura = $2::SMALLINT,
            id_producto = $3::SMALLINT
        WHERE id_factura_item = $4::SMALLINT;
    END;

$BODY$
LANGUAGE plpgsql;


-- FUNCION PARA ELIMINAR FACTURA ITEMS
CREATE OR REPLACE FUNCTION eliminar_factura_items(INT)
RETURNS VOID
AS
$BODY$

    BEGIN

        DELETE FROM facturas_items
        WHERE id_factura_item = $1::SMALLINT;

    END;

$BODY$
LANGUAGE plpgsql;

-- FUNCION PARA FILTAR FACTURAS POR FECHA DE CREADA
CREATE OR REPLACE FUNCTION filtar_facturas_creatAT(CHARACTER VARYING, CHARACTER VARYING)
RETURNS SETOF facturas
AS
$BODY$

    SELECT * FROM facturas WHERE create_at BETWEEN $1::DATE AND $2::DATE;


$BODY$
LANGUAGE sql;
-- EJECUTANDO FUNCION
SELECT * FROM filtar_facturas_creatAT('2021-10-24', '2021-10-24');

-- FUNCION PARA FILTRAR FACTURAS POR SU IDCLIENTE
CREATE OR REPLACE FUNCTION filtrar_facturas_byCliente (id INT)
RETURNS SETOF facturas
AS
$BODY$
    BEGIN
        RETURN QUERY SELECT * FROM facturas WHERE id_cliente = id::SMALLINT;
    END;
$BODY$
LANGUAGE 'plpgsql';
-- EJECUNTANDO FUNCION
SELECT * FROM filtrar_facturas_byCliente (2);

-- FUNCION PARA FILTRAR FACTURAS POR SU ID
CREATE OR REPLACE FUNCTION filtrar_facturas (id INT)
RETURNS SETOF facturas
AS
$BODY$
    BEGIN
        RETURN QUERY SELECT * FROM facturas WHERE id_factura = id::SMALLINT;
    END;
$BODY$
LANGUAGE 'plpgsql';
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_facturas (2);