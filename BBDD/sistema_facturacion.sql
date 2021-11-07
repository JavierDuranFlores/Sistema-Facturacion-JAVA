PGDMP         3            
    y            sistema_facturacion    10.12 #   12.8 (Ubuntu 12.8-0ubuntu0.20.04.1) a    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    61596    sistema_facturacion    DATABASE     �   CREATE DATABASE sistema_facturacion WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_MX.UTF-8' LC_CTYPE = 'es_MX.UTF-8';
 #   DROP DATABASE sistema_facturacion;
                postgres    false                        3079    62488    pgcrypto 	   EXTENSION     <   CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;
    DROP EXTENSION pgcrypto;
                   false            �           0    0    EXTENSION pgcrypto    COMMENT     <   COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';
                        false    2                       1255    62772 �   actualizar_cliente(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.actualizar_cliente(_id integer, _nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$



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



$$;
 �   DROP FUNCTION public.actualizar_cliente(_id integer, _nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying);
       public          postgres    false                       1255    63086 �   actualizar_cliente(character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.actualizar_cliente(_id character varying, _nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$



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



$$;
   DROP FUNCTION public.actualizar_cliente(_id character varying, _nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying);
       public          postgres    false                        1255    63165 _   actualizar_facturas(character varying, character varying, character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.actualizar_facturas(character varying, character varying, character varying, character varying) RETURNS void
    LANGUAGE plpgsql
    AS $_$



    BEGIN

        UPDATE facturas

        SET n_productos = $1::SMALLINT,

            total = $2::NUMERIC,

            id_cliente = $3::SMALLINT,

            create_at = NOW()

        WHERE id_factura = $4::SMALLINT;

    END;



$_$;
 v   DROP FUNCTION public.actualizar_facturas(character varying, character varying, character varying, character varying);
       public          postgres    false                       1255    63164 e   actualizar_facturas_items(character varying, character varying, character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.actualizar_facturas_items(character varying, character varying, character varying, character varying) RETURNS void
    LANGUAGE plpgsql
    AS $_$



    BEGIN

        UPDATE facturas_items

        SET cantidad = $1::INT,

            id_factura = $2::SMALLINT,

            id_producto = $3::SMALLINT

        WHERE id_factura_item = $4::SMALLINT;

    END;



$_$;
 |   DROP FUNCTION public.actualizar_facturas_items(character varying, character varying, character varying, character varying);
       public          postgres    false                       1255    63084 U   actualizar_producto(integer, character varying, character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.actualizar_producto(_id integer, _nombre character varying, _precio character varying, _stock character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$



    BEGIN

        UPDATE productos SET nombre = _nombre,

                            precio = _precio,

                            stock = _stock

        WHERE productos.id_producto = _id::SMALLINT;



    END;



$$;
 �   DROP FUNCTION public.actualizar_producto(_id integer, _nombre character varying, _precio character varying, _stock character varying);
       public          postgres    false                       1255    63085 _   actualizar_producto(character varying, character varying, character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.actualizar_producto(_id character varying, _nombre character varying, _precio character varying, _stock character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$



    BEGIN

        UPDATE productos SET nombre = _nombre,

                            precio = _precio::NUMERIC,

                            stock = _stock::SMALLINT

        WHERE productos.id_producto = _id::SMALLINT;



    END;



$$;
 �   DROP FUNCTION public.actualizar_producto(_id character varying, _nombre character varying, _precio character varying, _stock character varying);
       public          postgres    false                       1255    62625 3   autenticacion(character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.autenticacion(_usuario character varying, _contra character varying) RETURNS TABLE(usuario character varying)
    LANGUAGE plpgsql
    AS $$



    BEGIN



        RETURN QUERY SELECT usuarios.usuario FROM usuarios WHERE usuarios.usuario = _usuario AND

                encode(decrypt(usuarios.contra::bytea, 'llave', '3des'::text), 'escape'::text) = _contra;



    END;



$$;
 [   DROP FUNCTION public.autenticacion(_usuario character varying, _contra character varying);
       public          postgres    false                       1255    62715    concatenar(text, text, text)    FUNCTION       CREATE FUNCTION public.concatenar(text, text, text) RETURNS text
    LANGUAGE plpgsql
    AS $_$

        BEGIN

            IF $1 IS NULL THEN

                RETURN $2;

            ELSE

                RETURN $1 || $3 || $2;

            END IF;

        END;

    $_$;
 3   DROP FUNCTION public.concatenar(text, text, text);
       public          postgres    false            �            1259    62724    clientes    TABLE     H  CREATE TABLE public.clientes (
    id_cliente smallint NOT NULL,
    nombre character varying(30) NOT NULL,
    apellidop character varying(30) NOT NULL,
    apellidom character varying(30) NOT NULL,
    edad integer,
    email character varying(40) NOT NULL,
    direccion character varying(40),
    telefono character varying(20),
    create_at date DEFAULT now() NOT NULL,
    CONSTRAINT ck_clientes_edad CHECK ((edad < 100)),
    CONSTRAINT ck_clientes_emial CHECK (((email)::text ~ '^[a-z0-9!#$%&*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$'::text)),
    CONSTRAINT ck_clientes_telefono CHECK (((telefono)::text ~ '[\(]?[\+]?(\d{2}|\d{3})[\)]?[\s]?((\d{6}|\d{8})|(\d{3}[\*\.\-\s]){3}|(\d{2}[\*\.\-\s]){4}|(\d{4}[\*\.\-\s]){2})|\d{8}|\d{10}|\d{12}'::text))
);
    DROP TABLE public.clientes;
       public            postgres    false            
           1255    62765 @   consulta_paginada_clientes(character varying, character varying)    FUNCTION       CREATE FUNCTION public.consulta_paginada_clientes(_limite character varying, _pagina character varying) RETURNS SETOF public.clientes
    LANGUAGE plpgsql
    AS $$

    DECLARE

        inicio INT;

    BEGIN

        inicio = _limite::INT * _pagina::INT - _limite::INT;

        RETURN QUERY SELECT id_cliente, nombre, apellidop, apellidom, edad, email, direccion, telefono, create_at

                     FROM clientes ORDER BY id_cliente

                     LIMIT _limite::INT OFFSET inicio;

    

    END;

$$;
 g   DROP FUNCTION public.consulta_paginada_clientes(_limite character varying, _pagina character varying);
       public          postgres    false    204            �            1259    62794    facturas    TABLE     �   CREATE TABLE public.facturas (
    id_factura smallint NOT NULL,
    n_productos smallint NOT NULL,
    total numeric(12,2) NOT NULL,
    id_cliente smallint NOT NULL,
    create_at date DEFAULT now()
);
    DROP TABLE public.facturas;
       public            postgres    false                       1255    63145 @   consulta_paginada_facturas(character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.consulta_paginada_facturas(_limite character varying, _pagina character varying) RETURNS SETOF public.facturas
    LANGUAGE plpgsql
    AS $$

    DECLARE

        inicio INT;

    BEGIN

        inicio = _limite::INT * _pagina::INT - _limite::INT;

        RETURN QUERY SELECT id_factura, n_productos, total, id_cliente, create_at

                     FROM facturas ORDER BY id_factura

                     LIMIT _limite::INT OFFSET inicio;



    END;

$$;
 g   DROP FUNCTION public.consulta_paginada_facturas(_limite character varying, _pagina character varying);
       public          postgres    false    206            �            1259    62810 	   productos    TABLE     �   CREATE TABLE public.productos (
    id_producto smallint NOT NULL,
    nombre character varying(60) NOT NULL,
    precio numeric(12,2) NOT NULL,
    stock integer NOT NULL,
    create_at date DEFAULT now()
);
    DROP TABLE public.productos;
       public            postgres    false                       1255    62836 A   consulta_paginada_productos(character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.consulta_paginada_productos(_limite character varying, _pagina character varying) RETURNS SETOF public.productos
    LANGUAGE plpgsql
    AS $$

    DECLARE

        inicio INT;

    BEGIN

        inicio = _limite::INT * _pagina::INT - _limite::INT;

        RETURN QUERY SELECT id_producto, nombre, precio, stock, create_at

                     FROM productos ORDER BY id_producto

                     LIMIT _limite::INT OFFSET inicio;



    END;

$$;
 h   DROP FUNCTION public.consulta_paginada_productos(_limite character varying, _pagina character varying);
       public          postgres    false    208                       1255    62773    eliminar_cliente(integer)    FUNCTION     �   CREATE FUNCTION public.eliminar_cliente(_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

    BEGIN

        DELETE FROM clientes WHERE id_cliente = _id::SMALLINT;

    END;

$$;
 4   DROP FUNCTION public.eliminar_cliente(_id integer);
       public          postgres    false                       1255    63160    eliminar_factura(integer)    FUNCTION     �   CREATE FUNCTION public.eliminar_factura(integer) RETURNS void
    LANGUAGE plpgsql
    AS $_$



    BEGIN



        DELETE FROM facturas WHERE id_factura = $1::SMALLINT;



    END;



$_$;
 0   DROP FUNCTION public.eliminar_factura(integer);
       public          postgres    false            $           1255    63168    eliminar_factura_items(integer)    FUNCTION     �   CREATE FUNCTION public.eliminar_factura_items(integer) RETURNS void
    LANGUAGE plpgsql
    AS $_$



    BEGIN



        DELETE FROM facturas_items

        WHERE id_factura_item = $1::SMALLINT;



    END;



$_$;
 6   DROP FUNCTION public.eliminar_factura_items(integer);
       public          postgres    false            #           1255    63167 )   eliminar_factura_items(character varying)    FUNCTION     �   CREATE FUNCTION public.eliminar_factura_items(character varying) RETURNS void
    LANGUAGE plpgsql
    AS $_$



    BEGIN



        DELETE FROM facturas_items

        WHERE id_factura_item = $1;



    END;



$_$;
 @   DROP FUNCTION public.eliminar_factura_items(character varying);
       public          postgres    false                       1255    63087    eliminar_producto(integer)    FUNCTION     �   CREATE FUNCTION public.eliminar_producto(_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

    BEGIN

        DELETE FROM productos WHERE id_producto = _id::SMALLINT;

    END;

$$;
 5   DROP FUNCTION public.eliminar_producto(_id integer);
       public          postgres    false                       1255    62771    filtrar_clientes(integer)    FUNCTION     �   CREATE FUNCTION public.filtrar_clientes(integer) RETURNS SETOF public.clientes
    LANGUAGE sql
    AS $_$

        SELECT * FROM clientes WHERE clientes.id_cliente = $1::SMALLINT;

$_$;
 0   DROP FUNCTION public.filtrar_clientes(integer);
       public          postgres    false    204                       1255    62766 #   filtrar_clientes(character varying)    FUNCTION     �   CREATE FUNCTION public.filtrar_clientes(character varying) RETURNS SETOF public.clientes
    LANGUAGE sql
    AS $_$

        SELECT * FROM clientes WHERE clientes.nombre = $1;

$_$;
 :   DROP FUNCTION public.filtrar_clientes(character varying);
       public          postgres    false    204                       1255    62774 6   filtrar_clientes(character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.filtrar_clientes(tipo character varying, busqueda character varying) RETURNS SETOF public.clientes
    LANGUAGE plpgsql
    AS $$

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

$$;
 [   DROP FUNCTION public.filtrar_clientes(tipo character varying, busqueda character varying);
       public          postgres    false    204                       1255    63146 6   filtrar_facturas(character varying, character varying)    FUNCTION     .  CREATE FUNCTION public.filtrar_facturas(tipo character varying, busqueda character varying) RETURNS SETOF public.facturas
    LANGUAGE plpgsql
    AS $$

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

$$;
 [   DROP FUNCTION public.filtrar_facturas(tipo character varying, busqueda character varying);
       public          postgres    false    206                       1255    63158 )   filtrar_facturas_items(character varying)    FUNCTION     �  CREATE FUNCTION public.filtrar_facturas_items(character varying) RETURNS TABLE(idf smallint, np smallint, totalf numeric, idc smallint, caf date, idfi smallint, cantidadfi integer, idp smallint, nombrep character varying, preciop numeric, stockp integer, cap date)
    LANGUAGE plpgsql
    AS $_$



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



$_$;
 @   DROP FUNCTION public.filtrar_facturas_items(character varying);
       public          postgres    false                       1255    62837    filtrar_productos(integer)    FUNCTION     �   CREATE FUNCTION public.filtrar_productos(integer) RETURNS SETOF public.productos
    LANGUAGE sql
    AS $_$

        SELECT * FROM productos WHERE productos.id_producto = $1::SMALLINT;

$_$;
 1   DROP FUNCTION public.filtrar_productos(integer);
       public          postgres    false    208                       1255    62838 7   filtrar_productos(character varying, character varying)    FUNCTION       CREATE FUNCTION public.filtrar_productos(tipo character varying, busqueda character varying) RETURNS SETOF public.productos
    LANGUAGE plpgsql
    AS $$

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

$$;
 \   DROP FUNCTION public.filtrar_productos(tipo character varying, busqueda character varying);
       public          postgres    false    208                       1255    62769 �   ingresa_cliente(character varying, character varying, character varying, character varying, character varying, character varying, character varying)    FUNCTION       CREATE FUNCTION public.ingresa_cliente(_nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$



    BEGIN

        INSERT INTO clientes (id_cliente, nombre, apellidop, apellidom, edad, email, direccion, telefono, create_at)   

        VALUES (DEFAULT, _nombre, _apellidop, _apellidom, _edad::INT, _email, _direccion, _telefono, DEFAULT);

    END;



$$;
 �   DROP FUNCTION public.ingresa_cliente(_nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying);
       public          postgres    false            "           1255    63147 I   ingresa_facturas(character varying, character varying, character varying)    FUNCTION       CREATE FUNCTION public.ingresa_facturas(character varying, character varying, character varying) RETURNS void
    LANGUAGE plpgsql
    AS $_$



    BEGIN

        INSERT INTO facturas (n_productos, total, id_cliente) VALUES ($1::SMALLINT, $2::NUMERIC, $3::SMALLINT);

    END;



$_$;
 `   DROP FUNCTION public.ingresa_facturas(character varying, character varying, character varying);
       public          postgres    false            !           1255    63166 \   ingresa_facturas(character varying, character varying, character varying, character varying)    FUNCTION     K  CREATE FUNCTION public.ingresa_facturas(character varying, character varying, character varying, character varying) RETURNS void
    LANGUAGE plpgsql
    AS $_$



    BEGIN

        INSERT INTO facturas (id_factura, n_productos, total, id_cliente) VALUES ($1::SMALLINT, $2::SMALLINT, $3::NUMERIC, $4::SMALLINT);

    END;



$_$;
 s   DROP FUNCTION public.ingresa_facturas(character varying, character varying, character varying, character varying);
       public          postgres    false                       1255    63148 O   ingresa_facturas_items(character varying, character varying, character varying)    FUNCTION     )  CREATE FUNCTION public.ingresa_facturas_items(character varying, character varying, character varying) RETURNS void
    LANGUAGE plpgsql
    AS $_$



    BEGIN

        INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES ($1::INT, $2::SMALLINT, $3::SMALLINT);

    END;



$_$;
 f   DROP FUNCTION public.ingresa_facturas_items(character varying, character varying, character varying);
       public          postgres    false                       1255    62781 I   ingresa_producto(character varying, character varying, character varying)    FUNCTION     ]  CREATE FUNCTION public.ingresa_producto(_nombre character varying, _precio character varying, _stock character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$



    BEGIN

        INSERT INTO productos (id_producto, nombre, precio, stock, create_at)

        VALUES (DEFAULT, _nombre, _precio::NUMERIC, _stock::INT, DEFAULT);

    END;



$$;
 w   DROP FUNCTION public.ingresa_producto(_nombre character varying, _precio character varying, _stock character varying);
       public          postgres    false            	           1255    62764    leer_clientes()    FUNCTION     �   CREATE FUNCTION public.leer_clientes() RETURNS SETOF public.clientes
    LANGUAGE sql
    AS $$

        SELECT * FROM clientes;

$$;
 &   DROP FUNCTION public.leer_clientes();
       public          postgres    false    204                       1255    63143    leer_facturas()    FUNCTION     �   CREATE FUNCTION public.leer_facturas() RETURNS SETOF public.facturas
    LANGUAGE sql
    AS $$

        SELECT * FROM facturas;

$$;
 &   DROP FUNCTION public.leer_facturas();
       public          postgres    false    206                       1255    63157    leer_facturas_items()    FUNCTION     Z  CREATE FUNCTION public.leer_facturas_items() RETURNS TABLE(idf smallint, np smallint, totalf numeric, idc smallint, caf date, idfi smallint, cantidadfi integer, idp smallint, nombrep character varying, preciop numeric, stockp integer, cap date)
    LANGUAGE plpgsql
    AS $$



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



$$;
 ,   DROP FUNCTION public.leer_facturas_items();
       public          postgres    false                       1255    62835    leer_productos()    FUNCTION     �   CREATE FUNCTION public.leer_productos() RETURNS SETOF public.productos
    LANGUAGE sql
    AS $$

        SELECT * FROM productos;

$$;
 '   DROP FUNCTION public.leer_productos();
       public          postgres    false    208            �            1255    62418    total_factura(integer)    FUNCTION     s  CREATE FUNCTION public.total_factura(integer) RETURNS TABLE(total numeric)
    LANGUAGE plpgsql
    AS $_$

BEGIN

    RETURN QUERY SELECT SUM(productos.precio) FROM productos INNER JOIN facturas_items ON productos.id_producto = facturas_items.id_producto INNER JOIN facturas ON facturas_items.id_factura = facturas.id_factura WHERE facturas.id_factura = $1;

END;

$_$;
 -   DROP FUNCTION public.total_factura(integer);
       public          postgres    false            �            1259    62722    clientes_id_cliente_seq    SEQUENCE     �   CREATE SEQUENCE public.clientes_id_cliente_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.clientes_id_cliente_seq;
       public          postgres    false    204            �           0    0    clientes_id_cliente_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.clientes_id_cliente_seq OWNED BY public.clientes.id_cliente;
          public          postgres    false    203            �            1259    62792    facturas_id_factura_seq    SEQUENCE     �   CREATE SEQUENCE public.facturas_id_factura_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.facturas_id_factura_seq;
       public          postgres    false    206            �           0    0    facturas_id_factura_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.facturas_id_factura_seq OWNED BY public.facturas.id_factura;
          public          postgres    false    205            �            1259    62819    facturas_items    TABLE     �   CREATE TABLE public.facturas_items (
    id_factura_item smallint NOT NULL,
    cantidad integer NOT NULL,
    id_factura smallint,
    id_producto smallint
);
 "   DROP TABLE public.facturas_items;
       public            postgres    false            �            1259    62817 "   facturas_items_id_factura_item_seq    SEQUENCE     �   CREATE SEQUENCE public.facturas_items_id_factura_item_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.facturas_items_id_factura_item_seq;
       public          postgres    false    210            �           0    0 "   facturas_items_id_factura_item_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.facturas_items_id_factura_item_seq OWNED BY public.facturas_items.id_factura_item;
          public          postgres    false    209            �            1259    62853    personas    TABLE     �   CREATE TABLE public.personas (
    idpersona character varying(30) NOT NULL,
    nombre character varying(30) NOT NULL,
    apellido character varying(30) NOT NULL
);
    DROP TABLE public.personas;
       public            postgres    false            �            1259    62442    pregunta_recuperacion    TABLE     u   CREATE TABLE public.pregunta_recuperacion (
    id smallint NOT NULL,
    pregunta character varying(40) NOT NULL
);
 )   DROP TABLE public.pregunta_recuperacion;
       public            postgres    false            �            1259    62440    pregunta_recuperacion_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pregunta_recuperacion_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.pregunta_recuperacion_id_seq;
       public          postgres    false    198            �           0    0    pregunta_recuperacion_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.pregunta_recuperacion_id_seq OWNED BY public.pregunta_recuperacion.id;
          public          postgres    false    197            �            1259    62808    productos_id_producto_seq    SEQUENCE     �   CREATE SEQUENCE public.productos_id_producto_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.productos_id_producto_seq;
       public          postgres    false    208            �           0    0    productos_id_producto_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.productos_id_producto_seq OWNED BY public.productos.id_producto;
          public          postgres    false    207            �            1259    62646    respuesta_recuperacion    TABLE     �   CREATE TABLE public.respuesta_recuperacion (
    id smallint NOT NULL,
    respuesta character varying(30) NOT NULL,
    id_usuario smallint,
    id_pregunta smallint
);
 *   DROP TABLE public.respuesta_recuperacion;
       public            postgres    false            �            1259    62644    respuesta_recuperacion_id_seq    SEQUENCE     �   CREATE SEQUENCE public.respuesta_recuperacion_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.respuesta_recuperacion_id_seq;
       public          postgres    false    202            �           0    0    respuesta_recuperacion_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.respuesta_recuperacion_id_seq OWNED BY public.respuesta_recuperacion.id;
          public          postgres    false    201            �            1259    62609    usuarios    TABLE     �   CREATE TABLE public.usuarios (
    id smallint NOT NULL,
    usuario character varying(40) NOT NULL,
    contra text NOT NULL,
    id_pregunta smallint
);
    DROP TABLE public.usuarios;
       public            postgres    false            �            1259    62607    usuarios_id_seq    SEQUENCE     �   CREATE SEQUENCE public.usuarios_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.usuarios_id_seq;
       public          postgres    false    200            �           0    0    usuarios_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;
          public          postgres    false    199            /           2604    62727    clientes id_cliente    DEFAULT     z   ALTER TABLE ONLY public.clientes ALTER COLUMN id_cliente SET DEFAULT nextval('public.clientes_id_cliente_seq'::regclass);
 B   ALTER TABLE public.clientes ALTER COLUMN id_cliente DROP DEFAULT;
       public          postgres    false    203    204    204            4           2604    62797    facturas id_factura    DEFAULT     z   ALTER TABLE ONLY public.facturas ALTER COLUMN id_factura SET DEFAULT nextval('public.facturas_id_factura_seq'::regclass);
 B   ALTER TABLE public.facturas ALTER COLUMN id_factura DROP DEFAULT;
       public          postgres    false    205    206    206            8           2604    62822    facturas_items id_factura_item    DEFAULT     �   ALTER TABLE ONLY public.facturas_items ALTER COLUMN id_factura_item SET DEFAULT nextval('public.facturas_items_id_factura_item_seq'::regclass);
 M   ALTER TABLE public.facturas_items ALTER COLUMN id_factura_item DROP DEFAULT;
       public          postgres    false    210    209    210            ,           2604    62445    pregunta_recuperacion id    DEFAULT     �   ALTER TABLE ONLY public.pregunta_recuperacion ALTER COLUMN id SET DEFAULT nextval('public.pregunta_recuperacion_id_seq'::regclass);
 G   ALTER TABLE public.pregunta_recuperacion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    198    197    198            6           2604    62813    productos id_producto    DEFAULT     ~   ALTER TABLE ONLY public.productos ALTER COLUMN id_producto SET DEFAULT nextval('public.productos_id_producto_seq'::regclass);
 D   ALTER TABLE public.productos ALTER COLUMN id_producto DROP DEFAULT;
       public          postgres    false    207    208    208            .           2604    62649    respuesta_recuperacion id    DEFAULT     �   ALTER TABLE ONLY public.respuesta_recuperacion ALTER COLUMN id SET DEFAULT nextval('public.respuesta_recuperacion_id_seq'::regclass);
 H   ALTER TABLE public.respuesta_recuperacion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    201    202            -           2604    62612    usuarios id    DEFAULT     j   ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 :   ALTER TABLE public.usuarios ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    200    199    200            �          0    62724    clientes 
   TABLE DATA           y   COPY public.clientes (id_cliente, nombre, apellidop, apellidom, edad, email, direccion, telefono, create_at) FROM stdin;
    public          postgres    false    204   r�       �          0    62794    facturas 
   TABLE DATA           Y   COPY public.facturas (id_factura, n_productos, total, id_cliente, create_at) FROM stdin;
    public          postgres    false    206   ��       �          0    62819    facturas_items 
   TABLE DATA           \   COPY public.facturas_items (id_factura_item, cantidad, id_factura, id_producto) FROM stdin;
    public          postgres    false    210   7�       �          0    62853    personas 
   TABLE DATA           ?   COPY public.personas (idpersona, nombre, apellido) FROM stdin;
    public          postgres    false    211   ��       �          0    62442    pregunta_recuperacion 
   TABLE DATA           =   COPY public.pregunta_recuperacion (id, pregunta) FROM stdin;
    public          postgres    false    198   5�       �          0    62810 	   productos 
   TABLE DATA           R   COPY public.productos (id_producto, nombre, precio, stock, create_at) FROM stdin;
    public          postgres    false    208   ��       �          0    62646    respuesta_recuperacion 
   TABLE DATA           X   COPY public.respuesta_recuperacion (id, respuesta, id_usuario, id_pregunta) FROM stdin;
    public          postgres    false    202   �       �          0    62609    usuarios 
   TABLE DATA           D   COPY public.usuarios (id, usuario, contra, id_pregunta) FROM stdin;
    public          postgres    false    200   @�       �           0    0    clientes_id_cliente_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.clientes_id_cliente_seq', 60, true);
          public          postgres    false    203            �           0    0    facturas_id_factura_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.facturas_id_factura_seq', 34, true);
          public          postgres    false    205            �           0    0 "   facturas_items_id_factura_item_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.facturas_items_id_factura_item_seq', 185, true);
          public          postgres    false    209            �           0    0    pregunta_recuperacion_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.pregunta_recuperacion_id_seq', 3, true);
          public          postgres    false    197            �           0    0    productos_id_producto_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.productos_id_producto_seq', 49, true);
          public          postgres    false    207            �           0    0    respuesta_recuperacion_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.respuesta_recuperacion_id_seq', 1, true);
          public          postgres    false    201            �           0    0    usuarios_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.usuarios_id_seq', 1, true);
          public          postgres    false    199            H           2606    62857    personas personas_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.personas
    ADD CONSTRAINT personas_pkey PRIMARY KEY (idpersona);
 @   ALTER TABLE ONLY public.personas DROP CONSTRAINT personas_pkey;
       public            postgres    false    211            @           2606    62732    clientes pk_clientes_id_cliente 
   CONSTRAINT     e   ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT pk_clientes_id_cliente PRIMARY KEY (id_cliente);
 I   ALTER TABLE ONLY public.clientes DROP CONSTRAINT pk_clientes_id_cliente;
       public            postgres    false    204            B           2606    62800    facturas pk_facturas_id_factura 
   CONSTRAINT     e   ALTER TABLE ONLY public.facturas
    ADD CONSTRAINT pk_facturas_id_factura PRIMARY KEY (id_factura);
 I   ALTER TABLE ONLY public.facturas DROP CONSTRAINT pk_facturas_id_factura;
       public            postgres    false    206            F           2606    62824 #   facturas_items pk_facturas_items_id 
   CONSTRAINT     n   ALTER TABLE ONLY public.facturas_items
    ADD CONSTRAINT pk_facturas_items_id PRIMARY KEY (id_factura_item);
 M   ALTER TABLE ONLY public.facturas_items DROP CONSTRAINT pk_facturas_items_id;
       public            postgres    false    210            :           2606    62447 1   pregunta_recuperacion pk_pregunta_recuperacion_id 
   CONSTRAINT     o   ALTER TABLE ONLY public.pregunta_recuperacion
    ADD CONSTRAINT pk_pregunta_recuperacion_id PRIMARY KEY (id);
 [   ALTER TABLE ONLY public.pregunta_recuperacion DROP CONSTRAINT pk_pregunta_recuperacion_id;
       public            postgres    false    198            D           2606    62816 "   productos pk_productos_id_producto 
   CONSTRAINT     i   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT pk_productos_id_producto PRIMARY KEY (id_producto);
 L   ALTER TABLE ONLY public.productos DROP CONSTRAINT pk_productos_id_producto;
       public            postgres    false    208            >           2606    62651 3   respuesta_recuperacion pk_respuesta_recuperacion_id 
   CONSTRAINT     q   ALTER TABLE ONLY public.respuesta_recuperacion
    ADD CONSTRAINT pk_respuesta_recuperacion_id PRIMARY KEY (id);
 ]   ALTER TABLE ONLY public.respuesta_recuperacion DROP CONSTRAINT pk_respuesta_recuperacion_id;
       public            postgres    false    202            <           2606    62617    usuarios pk_usuarios_id 
   CONSTRAINT     U   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT pk_usuarios_id PRIMARY KEY (id);
 A   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT pk_usuarios_id;
       public            postgres    false    200            L           2606    62801 3   facturas fk_facturas_id_cliente_clientes_id_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturas
    ADD CONSTRAINT fk_facturas_id_cliente_clientes_id_cliente FOREIGN KEY (id_cliente) REFERENCES public.clientes(id_cliente) ON UPDATE CASCADE ON DELETE CASCADE;
 ]   ALTER TABLE ONLY public.facturas DROP CONSTRAINT fk_facturas_id_cliente_clientes_id_cliente;
       public          postgres    false    2880    204    206            M           2606    62825 ?   facturas_items fk_facturas_items_factura_id_facturas_id_factura    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturas_items
    ADD CONSTRAINT fk_facturas_items_factura_id_facturas_id_factura FOREIGN KEY (id_factura) REFERENCES public.facturas(id_factura) ON UPDATE CASCADE ON DELETE CASCADE;
 i   ALTER TABLE ONLY public.facturas_items DROP CONSTRAINT fk_facturas_items_factura_id_facturas_id_factura;
       public          postgres    false    2882    206    210            N           2606    62830 B   facturas_items fk_facturas_items_producto_id_productos_id_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturas_items
    ADD CONSTRAINT fk_facturas_items_producto_id_productos_id_producto FOREIGN KEY (id_producto) REFERENCES public.productos(id_producto) ON UPDATE CASCADE ON DELETE CASCADE;
 l   ALTER TABLE ONLY public.facturas_items DROP CONSTRAINT fk_facturas_items_producto_id_productos_id_producto;
       public          postgres    false    2884    210    208            K           2606    62657 R   respuesta_recuperacion fk_respuesta_recuperacion_pregunta_recuperacion_id_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.respuesta_recuperacion
    ADD CONSTRAINT fk_respuesta_recuperacion_pregunta_recuperacion_id_pregunta FOREIGN KEY (id_pregunta) REFERENCES public.pregunta_recuperacion(id) ON UPDATE CASCADE ON DELETE CASCADE;
 |   ALTER TABLE ONLY public.respuesta_recuperacion DROP CONSTRAINT fk_respuesta_recuperacion_pregunta_recuperacion_id_pregunta;
       public          postgres    false    2874    198    202            J           2606    62652 D   respuesta_recuperacion fk_respuesta_recuperacion_usuarios_id_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.respuesta_recuperacion
    ADD CONSTRAINT fk_respuesta_recuperacion_usuarios_id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id) ON UPDATE CASCADE ON DELETE CASCADE;
 n   ALTER TABLE ONLY public.respuesta_recuperacion DROP CONSTRAINT fk_respuesta_recuperacion_usuarios_id_usuario;
       public          postgres    false    2876    202    200            I           2606    62618 6   usuarios fk_usuarios_pregunta_recuperacion_id_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk_usuarios_pregunta_recuperacion_id_pregunta FOREIGN KEY (id_pregunta) REFERENCES public.pregunta_recuperacion(id) ON UPDATE CASCADE ON DELETE CASCADE;
 `   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT fk_usuarios_pregunta_recuperacion_id_pregunta;
       public          postgres    false    198    200    2874            �   +  x��W�r�8>��B/����v�f�'Ne������$4 �8~�9�a[�~���ҢgS���?4������5t�l�lGWJQ������1���O�F�:(MC�-���ȳ4	���B�fU�b���m�Qt.�զ�xE���Q#��r'��PK�d-Rzgu��K�4������'�Rl6l��mٕ�֪�UG@Y�FY�2^d�Iv��w���w�Eڍ�(��:GP{��}m��$ea��(�1bN��?���[ٖ[�0��la�Z�����%\� ���Qؙ�>������+�Wp�]#H�|oq��J����B6;�8�H�<̲(�� V�A6 ��t�n7�|�{��$tώ`��o�v���hY��LL0�ۡT�z.M�$kD���I�u���ȢH �	V��Z��+�Q8(�{X;)�/���W~;�����ऋU�N�c��.o��VQ,�I�V�%gi�ҔDq�(��%�V�=E�_7�0�zwV��P��|���EI&�4O�h���������kPj������7�`A�p���[�ps��LE��&<��^��L�G]��F>��QQJ����5塒���ig^��c�p�'�suQK�UQ�ڳ0��g��%e%Y�I�C�E����nK���8��ѱu�tm��H�wB��i�J&h!Р>��l:�������"dǨ`��#�_�����"S��$��A�v��[+7�R5�����3�u��	7�I�n�K�tz���<ޢ��a��/xD��h����󟽴�E�"�Π (g��5hj	��������,!�?	���uy}崔�j��W�lm�J���T�2� Ƀ7��5�K�*Y񵮟�|D-.)�g��� ���Kw'��#��̛t� e4�7�/�qi4[ v�j]J���p&x�m%���4|�:�?(О�*;GS \{�x���ŵ���[V�1�40�v��������
�+��@�����?�������2#��2���P���'i��j��$ײ�1W��ur����L��'���Ɔ��9}�"���΅,�#�1�k��,��"X�:��K�s%R�N�'˃/��8�8�d5Kr�2m�I��H����p��l`��	�Q~A=jh5��t���ـ��ޣ;n��ۃ;6��`�W���-$u6҂޲
׾��%wn�(���`͆1��L�b&tT���X���=F��;gS
m3�d�PEq���wjy)Q�P��e��$���A�)�����K��}5`�A�q<���"���g�;�Gki���"K�+;��i5<��n�=�d&m���7�ؾ�z����p�񄜾����3���C�hT���α�a��t���R> �����^U;F/�SV��鸬'��#�s�	f�FD+'��,�@�X�x��	�|o�+X{׌p"�^
'Ps�SV�R�M�i��=A�<��b�"w�oA�Ѝ�F�%�T�ޣ����U�D��DMVa�~bE3��ae�9^�\L����UvC�e=>��T���k����2���8	]�ѯ��Fr�V�!��w���<B��y�BF���̽#�t�ܯ�椓�����L'OE"��4����T^�S���5�bߩ���U̅��5��7�v�����7�~�%�@DW�V�>`�T��9�jN�ۃ5�OW��A�.P�Nq�j ߗh!iI��6�x�l�fW'��-޿x�
�'��A֕k��]A����������T�h}l����F7{5��������9<\�1�b�F+�ߏ�=��?�Y���n��`�I�d��M�)	<t�����h��W�X,�(��      �   z   x�]��� �ҋy���K��#��r�Y�3߆b��&�dZ�T��ZUY����p�O�Dѳ�RT�;fm͂GЙE� ���7�8�~�W|G8�ѻb��9�P3Q&���E� ^�5�      �   �   x�E���0C�a��c�]��� w���!)&.�_���{��[�V[-��5\�HX,�IzN+��xsk�9P�9��Ϊ�ǃ�K�S�!Cw�p��2�y�{�v�e4���"�A|���2�D��1I� f����[G�/�^�;�=���A��$��0�d�Zcr�[7x�U�E6�      �   &   x�3426153��40��J,�L-�t)-J������ r]      �   @   x�3�<��/?7�(U!%U��T��(37�H!7�89�$ў������L��1�tbJQ�=W� H  �      �     x�u�=n�@���)�6�?���Vb;���*͘�P��ƒ4�&��A���X���P!̇�y�q�\�l�*�G� "�Ϣ�L������J<:�)��櫫@�C19��p�U�[dk�����H�lв��5ab��Sl	o�7�k�'�R��i�u�9֮�����+2���0��ͽ�ȴe[Wc�A����V�c�xҍs����X��%���Y�t�a���G�G�	
.rmh�TZ=Wcxߕ�ĳ{�8*O��||�p��~kjg��@�2�tS�F ; W^7��9 ""]����N�-)>zt��s��,)7X�u��hZ3���Q�A�k��8]�p���}�~������$�!��B�;�\�%�-�*w-�T �5~w�Y����]��>h�T*9|�T�^��.���gN�rh���#�+���u����d����d]'�l��M�͕K���#e(�Ja�_�'�ΒN��X�X�#=�;a��E��UÓC�ЋQ萫�Õ)�kXٙF��d[�#V�/( ��4����n�����SRV
M���(�e��1���!�|W��^�3�n ��+z��[���nd�*,i�����R*��S�x����X��r��9z{L�������/f@|      �      x�3���,��/���4�4����� :n�      �   :   x�3��J,�L-⌉�H1J3O4�0NNNLKL1MJNNI3361I���L-9�b���� ��z     