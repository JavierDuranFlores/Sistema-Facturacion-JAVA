--          CLIENTES
-- FUNCION PARA FILTRAR CLIENTES POR SUS DIFERENTES COLUMNAS
CREATE OR REPLACE FUNCTION filtrar_clientes (tipo CHARACTER VARYING, busqueda CHARACTER VARYING)
RETURNS SETOF clientes
AS
$BODY$
    BEGIN
        IF tipo = 'id_cliente' THEN
            RETURN QUERY SELECT * FROM clientes WHERE id_cliente = busqueda::SMALLINT;
        ELSIF tipo = 'nombre'THEN
            RETURN QUERY SELECT * FROM clientes WHERE nombre ILIKE busqueda;
        ELSIF tipo = 'apellidop'THEN
            RETURN QUERY SELECT * FROM clientes WHERE apellidop ILIKE busqueda;
        ELSIF tipo = 'apellidom'THEN
            RETURN QUERY SELECT * FROM clientes WHERE apellidom ILIKE busqueda;
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
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_clientes ('id_cliente', '1');
SELECT * FROM filtrar_clientes ('nombre', 'Javier');
SELECT * FROM filtrar_clientes ('apellidop', 'Duran');
SELECT * FROM filtrar_clientes ('apellidom', 'Flores');
SELECT * FROM filtrar_clientes ('edad', '21');
SELECT * FROM filtrar_clientes ('email', 'javierfloresj7@gmail.com');
SELECT * FROM filtrar_clientes ('direccion', 'Las lomas');
SELECT * FROM filtrar_clientes ('telefono', '9626302716');
SELECT * FROM filtrar_clientes ('create_at', '2021-09-11');

-- FUNCIONA PARA FILTAR CLIENTE POR SU ID
CREATE OR REPLACE FUNCTION filtrar_clientes (INT)
RETURNS SETOF clientes
AS
$BODY$
        SELECT * FROM clientes WHERE clientes.id_cliente = $1::SMALLINT;
$BODY$
LANGUAGE 'sql';
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_clientes (1);

-- FUNCION PARA MOSTRAR LOS CLIENTE
CREATE OR REPLACE FUNCTION leer_clientes ()
RETURNS SETOF clientes
AS
$BODY$
        SELECT * FROM clientes;
$BODY$
LANGUAGE 'sql';
-- EJECUTANDO FUNCION
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
-- EJECUTANDO FUNCION
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

-- FUNCION PARA ACTUALIZAR CLIENTE
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
-- EJECUTANDO FUNCION
SELECT actualizar_cliente(4, 'John', 'Doe', 'Barrios', '45', 'john.doe@gmail.com', 'Tapachula', '9876541591');

-- FUNCION PARA ELIMINAR CLIENTE POR SU ID
CREATE OR REPLACE FUNCTION eliminar_cliente(_id INT)
RETURNS VOID AS
$BODY$
    BEGIN
        DELETE FROM clientes WHERE id_cliente = _id::SMALLINT;
    END;
$BODY$
LANGUAGE plpgsql;
-- EJECUNTANDO FUNCION
SELECT eliminar_cliente(4);

-- FUNCION QUE FILTRA A CLIENTE POR LA FECHA
CREATE OR REPLACE FUNCTION filtar_clientes_creatAT(CHARACTER VARYING, CHARACTER VARYING)
RETURNS SETOF clientes
AS
$BODY$

    SELECT * FROM clientes WHERE create_at BETWEEN $1::DATE AND $2::DATE;


$BODY$
LANGUAGE sql;
-- EJECUTANDO FUNCION
SELECT * FROM filtar_clientes_creatAT('2021-09-1', '2021-09-13');