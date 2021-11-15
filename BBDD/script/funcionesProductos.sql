---          PRODUCTOS
-- FUNCION PARA MOSTRAR LOS PRODUCTOS
CREATE OR REPLACE FUNCTION leer_productos ()
RETURNS SETOF productos
AS
$BODY$
        SELECT * FROM productos;
$BODY$
LANGUAGE 'sql';
-- EJECUTANDO FUNCION
SELECT * FROM leer_productos ();

-- FUNCION PARA MOSTRAR PAGINADOS LOS PRODUCTOS
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
-- EJECUTANDO FUNCION
SELECT * FROM consulta_paginada_productos('10', '1');

-- FUNCIONA PARA FULTRAR LOS PRODUCTOS POR SU ID
CREATE OR REPLACE FUNCTION filtrar_productos (INT)
RETURNS SETOF productos
AS
$BODY$
        SELECT * FROM productos WHERE productos.id_producto = $1::SMALLINT;
$BODY$
LANGUAGE 'sql';
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_productos (1);

-- FUNCION PARA FILTAR PRODUCTOS POR SUS DIFERENTES COLUMNA
CREATE OR REPLACE FUNCTION filtrar_productos (tipo CHARACTER VARYING, busqueda CHARACTER VARYING)
RETURNS SETOF productos
AS
$BODY$
    BEGIN
        IF tipo = 'id_producto' THEN
            RETURN QUERY SELECT * FROM productos WHERE id_producto = busqueda::SMALLINT;
        ELSIF tipo = 'nombre'THEN
            RETURN QUERY SELECT * FROM productos WHERE nombre ILIKE busqueda;
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
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_productos('nombre', 'arroz');

-- FUNCION PRODUCTOS CLIENTES
CREATE OR REPLACE FUNCTION filtrar_productos_factura(CHARACTER VARYING)
RETURNS SETOF productos
AS
$BODY$
    BEGIN
        RETURN QUERY SELECT p.id_producto, p.nombre, p.precio, p.stock, p.create_at
        FROM productos AS p
        INNER JOIN facturas_items AS fi
        ON p.id_producto = fi.id_producto
        INNER JOIN facturas AS f
        ON f.id_factura = fi.id_factura
        INNER JOIN clientes AS c
        ON c.id_cliente = f.id_cliente
        WHERE f.id_factura = $1::SMALLINT;
    END;
$BODY$
LANGUAGE plpgsql;
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_productos_factura('36');

-- FUNCION QUE FILTRA PRODUCTOS POR FECHA CREADO
CREATE OR REPLACE FUNCTION filtar_productos_creatAT(CHARACTER VARYING, CHARACTER VARYING)
RETURNS SETOF productos
AS
$BODY$

    SELECT * FROM productos WHERE create_at BETWEEN $1::DATE AND $2::DATE;


$BODY$
LANGUAGE sql;
-- EJECUTANDO FUNCION
SELECT * FROM filtar_productos_creatAT('2021-10-24', '2021-10-25');

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
-- EJECUTANDO FUNCION
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

-- FUNCION PARA ELIMINAR PRODUCTO POR ID
CREATE OR REPLACE FUNCTION eliminar_producto(_id INT)
RETURNS VOID AS
$BODY$
    BEGIN
        DELETE FROM productos WHERE id_producto = _id::SMALLINT;
    END;
$BODY$
LANGUAGE plpgsql;