--          USUARIOS
-- FUNCION PARA FILTAR USUARIO
CREATE OR REPLACE FUNCTION filtrar_usuario(CHARACTER VARYING)
RETURNS INT
AS
$BODY$
    DECLARE
        _id INT;
    BEGIN
        SELECT id INTO _id FROM usuarios WHERE usuario = $1;
        RETURN _id;
    END;
$BODY$
LANGUAGE plpgsql;
-- EJECUTANDO FUNCION
SELECT * FROM filtrar_usuario('Javier');

-- FUNCION PARA ACTUALIZAR CONTRASEÑA
CREATE OR REPLACE FUNCTION actualizar_contra(CHARACTER VARYING, CHARACTER VARYING)
RETURNS VOID
AS
$BODY$
    BEGIN
        UPDATE usuarios SET contra = ENCRYPT($1::BYTEA, 'llave', '3des')::TEXT WHERE id=$2::SMALLINT;
    END;
$BODY$
LANGUAGE plpgsql;
-- EJECUTANDO FUNCION
SELECT * FROM actualizar_contra('Duran2001', '1');

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
-- EJECUTANDO FUNCION
SELECT autenticacion ('Javier', 'Duran2001');