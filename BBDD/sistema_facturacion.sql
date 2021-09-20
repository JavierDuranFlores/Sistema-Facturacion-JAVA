PGDMP                         y           sistema_facturacion    10.12 #   12.8 (Ubuntu 12.8-0ubuntu0.20.04.1) H    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
                        false    2                       1255    62772 �   actualizar_cliente(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.actualizar_cliente(_id integer, _nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying) RETURNS void
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
       public          postgres    false                       1255    62625 3   autenticacion(character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.autenticacion(_usuario character varying, _contra character varying) RETURNS TABLE(usuario character varying)
    LANGUAGE plpgsql
    AS $$



    BEGIN



        RETURN QUERY SELECT usuarios.usuario FROM usuarios WHERE usuarios.usuario = _usuario AND

                encode(decrypt(usuarios.contra::bytea, 'llave', '3des'::text), 'escape'::text) = _contra;



    END;



$$;
 [   DROP FUNCTION public.autenticacion(_usuario character varying, _contra character varying);
       public          postgres    false                       1255    62715    concatenar(text, text, text)    FUNCTION       CREATE FUNCTION public.concatenar(text, text, text) RETURNS text
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
       public            postgres    false            	           1255    62765 @   consulta_paginada_clientes(character varying, character varying)    FUNCTION       CREATE FUNCTION public.consulta_paginada_clientes(_limite character varying, _pagina character varying) RETURNS SETOF public.clientes
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
       public          postgres    false    206                       1255    62773    eliminar_cliente(integer)    FUNCTION     �   CREATE FUNCTION public.eliminar_cliente(_id integer) RETURNS void
    LANGUAGE plpgsql
    AS $$

    BEGIN

        DELETE FROM clientes WHERE id_cliente = _id::SMALLINT;

    END;

$$;
 4   DROP FUNCTION public.eliminar_cliente(_id integer);
       public          postgres    false                       1255    62771    filtrar_clientes(integer)    FUNCTION     �   CREATE FUNCTION public.filtrar_clientes(integer) RETURNS SETOF public.clientes
    LANGUAGE sql
    AS $_$

        SELECT * FROM clientes WHERE clientes.id_cliente = $1::SMALLINT;

$_$;
 0   DROP FUNCTION public.filtrar_clientes(integer);
       public          postgres    false    206            
           1255    62766 #   filtrar_clientes(character varying)    FUNCTION     �   CREATE FUNCTION public.filtrar_clientes(character varying) RETURNS SETOF public.clientes
    LANGUAGE sql
    AS $_$

        SELECT * FROM clientes WHERE clientes.nombre = $1;

$_$;
 :   DROP FUNCTION public.filtrar_clientes(character varying);
       public          postgres    false    206                       1255    62774 6   filtrar_clientes(character varying, character varying)    FUNCTION     �  CREATE FUNCTION public.filtrar_clientes(tipo character varying, busqueda character varying) RETURNS SETOF public.clientes
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
       public          postgres    false    206                       1255    62769 �   ingresa_cliente(character varying, character varying, character varying, character varying, character varying, character varying, character varying)    FUNCTION       CREATE FUNCTION public.ingresa_cliente(_nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$



    BEGIN

        INSERT INTO clientes (id_cliente, nombre, apellidop, apellidom, edad, email, direccion, telefono, create_at)   

        VALUES (DEFAULT, _nombre, _apellidop, _apellidom, _edad::INT, _email, _direccion, _telefono, DEFAULT);

    END;



$$;
 �   DROP FUNCTION public.ingresa_cliente(_nombre character varying, _apellidop character varying, _apellidom character varying, _edad character varying, _email character varying, _direccion character varying, _telefono character varying);
       public          postgres    false                       1255    62764    leer_clientes()    FUNCTION     �   CREATE FUNCTION public.leer_clientes() RETURNS SETOF public.clientes
    LANGUAGE sql
    AS $$

        SELECT * FROM clientes;

$$;
 &   DROP FUNCTION public.leer_clientes();
       public          postgres    false    206            �            1255    62418    total_factura(integer)    FUNCTION     s  CREATE FUNCTION public.total_factura(integer) RETURNS TABLE(total numeric)
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
       public          postgres    false    206            �           0    0    clientes_id_cliente_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.clientes_id_cliente_seq OWNED BY public.clientes.id_cliente;
          public          postgres    false    205            �            1259    62735    facturas    TABLE     �   CREATE TABLE public.facturas (
    id_factura smallint NOT NULL,
    n_productos smallint NOT NULL,
    total numeric(12,2) NOT NULL,
    id_cliente smallint NOT NULL,
    create_at date NOT NULL
);
    DROP TABLE public.facturas;
       public            postgres    false            �            1259    62733    facturas_id_factura_seq    SEQUENCE     �   CREATE SEQUENCE public.facturas_id_factura_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.facturas_id_factura_seq;
       public          postgres    false    208            �           0    0    facturas_id_factura_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.facturas_id_factura_seq OWNED BY public.facturas.id_factura;
          public          postgres    false    207            �            1259    62748    facturas_items    TABLE     �   CREATE TABLE public.facturas_items (
    id_factura_item smallint NOT NULL,
    cantidad integer NOT NULL,
    id_factura smallint,
    id_producto smallint
);
 "   DROP TABLE public.facturas_items;
       public            postgres    false            �            1259    62746 "   facturas_items_id_factura_item_seq    SEQUENCE     �   CREATE SEQUENCE public.facturas_items_id_factura_item_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.facturas_items_id_factura_item_seq;
       public          postgres    false    210            �           0    0 "   facturas_items_id_factura_item_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.facturas_items_id_factura_item_seq OWNED BY public.facturas_items.id_factura_item;
          public          postgres    false    209            �            1259    62442    pregunta_recuperacion    TABLE     u   CREATE TABLE public.pregunta_recuperacion (
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
       public          postgres    false    200            �           0    0    pregunta_recuperacion_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.pregunta_recuperacion_id_seq OWNED BY public.pregunta_recuperacion.id;
          public          postgres    false    199            �            1259    62326 	   productos    TABLE     �   CREATE TABLE public.productos (
    id_producto smallint NOT NULL,
    nombre character varying(60) NOT NULL,
    precio numeric(12,2) NOT NULL,
    stock integer NOT NULL,
    create_at date NOT NULL
);
    DROP TABLE public.productos;
       public            postgres    false            �            1259    62324    productos_id_producto_seq    SEQUENCE     �   CREATE SEQUENCE public.productos_id_producto_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.productos_id_producto_seq;
       public          postgres    false    198            �           0    0    productos_id_producto_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.productos_id_producto_seq OWNED BY public.productos.id_producto;
          public          postgres    false    197            �            1259    62646    respuesta_recuperacion    TABLE     �   CREATE TABLE public.respuesta_recuperacion (
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
       public          postgres    false    204            �           0    0    respuesta_recuperacion_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.respuesta_recuperacion_id_seq OWNED BY public.respuesta_recuperacion.id;
          public          postgres    false    203            �            1259    62609    usuarios    TABLE     �   CREATE TABLE public.usuarios (
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
       public          postgres    false    202            �           0    0    usuarios_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;
          public          postgres    false    201                       2604    62727    clientes id_cliente    DEFAULT     z   ALTER TABLE ONLY public.clientes ALTER COLUMN id_cliente SET DEFAULT nextval('public.clientes_id_cliente_seq'::regclass);
 B   ALTER TABLE public.clientes ALTER COLUMN id_cliente DROP DEFAULT;
       public          postgres    false    205    206    206                       2604    62738    facturas id_factura    DEFAULT     z   ALTER TABLE ONLY public.facturas ALTER COLUMN id_factura SET DEFAULT nextval('public.facturas_id_factura_seq'::regclass);
 B   ALTER TABLE public.facturas ALTER COLUMN id_factura DROP DEFAULT;
       public          postgres    false    207    208    208                       2604    62751    facturas_items id_factura_item    DEFAULT     �   ALTER TABLE ONLY public.facturas_items ALTER COLUMN id_factura_item SET DEFAULT nextval('public.facturas_items_id_factura_item_seq'::regclass);
 M   ALTER TABLE public.facturas_items ALTER COLUMN id_factura_item DROP DEFAULT;
       public          postgres    false    209    210    210                       2604    62445    pregunta_recuperacion id    DEFAULT     �   ALTER TABLE ONLY public.pregunta_recuperacion ALTER COLUMN id SET DEFAULT nextval('public.pregunta_recuperacion_id_seq'::regclass);
 G   ALTER TABLE public.pregunta_recuperacion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    200    199    200                       2604    62329    productos id_producto    DEFAULT     ~   ALTER TABLE ONLY public.productos ALTER COLUMN id_producto SET DEFAULT nextval('public.productos_id_producto_seq'::regclass);
 D   ALTER TABLE public.productos ALTER COLUMN id_producto DROP DEFAULT;
       public          postgres    false    197    198    198                       2604    62649    respuesta_recuperacion id    DEFAULT     �   ALTER TABLE ONLY public.respuesta_recuperacion ALTER COLUMN id SET DEFAULT nextval('public.respuesta_recuperacion_id_seq'::regclass);
 H   ALTER TABLE public.respuesta_recuperacion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    204    204                       2604    62612    usuarios id    DEFAULT     j   ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 :   ALTER TABLE public.usuarios ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    201    202            �          0    62724    clientes 
   TABLE DATA           y   COPY public.clientes (id_cliente, nombre, apellidop, apellidom, edad, email, direccion, telefono, create_at) FROM stdin;
    public          postgres    false    206   �l       �          0    62735    facturas 
   TABLE DATA           Y   COPY public.facturas (id_factura, n_productos, total, id_cliente, create_at) FROM stdin;
    public          postgres    false    208   .t       �          0    62748    facturas_items 
   TABLE DATA           \   COPY public.facturas_items (id_factura_item, cantidad, id_factura, id_producto) FROM stdin;
    public          postgres    false    210   Kt       �          0    62442    pregunta_recuperacion 
   TABLE DATA           =   COPY public.pregunta_recuperacion (id, pregunta) FROM stdin;
    public          postgres    false    200   ht       �          0    62326 	   productos 
   TABLE DATA           R   COPY public.productos (id_producto, nombre, precio, stock, create_at) FROM stdin;
    public          postgres    false    198   �t       �          0    62646    respuesta_recuperacion 
   TABLE DATA           X   COPY public.respuesta_recuperacion (id, respuesta, id_usuario, id_pregunta) FROM stdin;
    public          postgres    false    204   �u       �          0    62609    usuarios 
   TABLE DATA           D   COPY public.usuarios (id, usuario, contra, id_pregunta) FROM stdin;
    public          postgres    false    202   �u       �           0    0    clientes_id_cliente_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.clientes_id_cliente_seq', 59, true);
          public          postgres    false    205            �           0    0    facturas_id_factura_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.facturas_id_factura_seq', 1, false);
          public          postgres    false    207            �           0    0 "   facturas_items_id_factura_item_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.facturas_items_id_factura_item_seq', 1, false);
          public          postgres    false    209            �           0    0    pregunta_recuperacion_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.pregunta_recuperacion_id_seq', 3, true);
          public          postgres    false    199            �           0    0    productos_id_producto_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.productos_id_producto_seq', 7, true);
          public          postgres    false    197            �           0    0    respuesta_recuperacion_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.respuesta_recuperacion_id_seq', 1, true);
          public          postgres    false    203            �           0    0    usuarios_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.usuarios_id_seq', 1, true);
          public          postgres    false    201            &           2606    62732    clientes pk_clientes_id_cliente 
   CONSTRAINT     e   ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT pk_clientes_id_cliente PRIMARY KEY (id_cliente);
 I   ALTER TABLE ONLY public.clientes DROP CONSTRAINT pk_clientes_id_cliente;
       public            postgres    false    206            (           2606    62740    facturas pk_facturas_id_factura 
   CONSTRAINT     e   ALTER TABLE ONLY public.facturas
    ADD CONSTRAINT pk_facturas_id_factura PRIMARY KEY (id_factura);
 I   ALTER TABLE ONLY public.facturas DROP CONSTRAINT pk_facturas_id_factura;
       public            postgres    false    208            *           2606    62753 #   facturas_items pk_facturas_items_id 
   CONSTRAINT     n   ALTER TABLE ONLY public.facturas_items
    ADD CONSTRAINT pk_facturas_items_id PRIMARY KEY (id_factura_item);
 M   ALTER TABLE ONLY public.facturas_items DROP CONSTRAINT pk_facturas_items_id;
       public            postgres    false    210                        2606    62447 1   pregunta_recuperacion pk_pregunta_recuperacion_id 
   CONSTRAINT     o   ALTER TABLE ONLY public.pregunta_recuperacion
    ADD CONSTRAINT pk_pregunta_recuperacion_id PRIMARY KEY (id);
 [   ALTER TABLE ONLY public.pregunta_recuperacion DROP CONSTRAINT pk_pregunta_recuperacion_id;
       public            postgres    false    200                       2606    62331 "   productos pk_productos_id_producto 
   CONSTRAINT     i   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT pk_productos_id_producto PRIMARY KEY (id_producto);
 L   ALTER TABLE ONLY public.productos DROP CONSTRAINT pk_productos_id_producto;
       public            postgres    false    198            $           2606    62651 3   respuesta_recuperacion pk_respuesta_recuperacion_id 
   CONSTRAINT     q   ALTER TABLE ONLY public.respuesta_recuperacion
    ADD CONSTRAINT pk_respuesta_recuperacion_id PRIMARY KEY (id);
 ]   ALTER TABLE ONLY public.respuesta_recuperacion DROP CONSTRAINT pk_respuesta_recuperacion_id;
       public            postgres    false    204            "           2606    62617    usuarios pk_usuarios_id 
   CONSTRAINT     U   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT pk_usuarios_id PRIMARY KEY (id);
 A   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT pk_usuarios_id;
       public            postgres    false    202            .           2606    62741 3   facturas fk_facturas_id_cliente_clientes_id_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturas
    ADD CONSTRAINT fk_facturas_id_cliente_clientes_id_cliente FOREIGN KEY (id_cliente) REFERENCES public.clientes(id_cliente) ON UPDATE CASCADE ON DELETE CASCADE;
 ]   ALTER TABLE ONLY public.facturas DROP CONSTRAINT fk_facturas_id_cliente_clientes_id_cliente;
       public          postgres    false    208    2854    206            /           2606    62754 ?   facturas_items fk_facturas_items_factura_id_facturas_id_factura    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturas_items
    ADD CONSTRAINT fk_facturas_items_factura_id_facturas_id_factura FOREIGN KEY (id_factura) REFERENCES public.facturas(id_factura) ON UPDATE CASCADE ON DELETE CASCADE;
 i   ALTER TABLE ONLY public.facturas_items DROP CONSTRAINT fk_facturas_items_factura_id_facturas_id_factura;
       public          postgres    false    2856    210    208            0           2606    62759 B   facturas_items fk_facturas_items_producto_id_productos_id_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturas_items
    ADD CONSTRAINT fk_facturas_items_producto_id_productos_id_producto FOREIGN KEY (id_producto) REFERENCES public.productos(id_producto) ON UPDATE CASCADE ON DELETE CASCADE;
 l   ALTER TABLE ONLY public.facturas_items DROP CONSTRAINT fk_facturas_items_producto_id_productos_id_producto;
       public          postgres    false    198    210    2846            -           2606    62657 R   respuesta_recuperacion fk_respuesta_recuperacion_pregunta_recuperacion_id_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.respuesta_recuperacion
    ADD CONSTRAINT fk_respuesta_recuperacion_pregunta_recuperacion_id_pregunta FOREIGN KEY (id_pregunta) REFERENCES public.pregunta_recuperacion(id) ON UPDATE CASCADE ON DELETE CASCADE;
 |   ALTER TABLE ONLY public.respuesta_recuperacion DROP CONSTRAINT fk_respuesta_recuperacion_pregunta_recuperacion_id_pregunta;
       public          postgres    false    200    204    2848            ,           2606    62652 D   respuesta_recuperacion fk_respuesta_recuperacion_usuarios_id_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.respuesta_recuperacion
    ADD CONSTRAINT fk_respuesta_recuperacion_usuarios_id_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id) ON UPDATE CASCADE ON DELETE CASCADE;
 n   ALTER TABLE ONLY public.respuesta_recuperacion DROP CONSTRAINT fk_respuesta_recuperacion_usuarios_id_usuario;
       public          postgres    false    2850    204    202            +           2606    62618 6   usuarios fk_usuarios_pregunta_recuperacion_id_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk_usuarios_pregunta_recuperacion_id_pregunta FOREIGN KEY (id_pregunta) REFERENCES public.pregunta_recuperacion(id) ON UPDATE CASCADE ON DELETE CASCADE;
 `   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT fk_usuarios_pregunta_recuperacion_id_pregunta;
       public          postgres    false    200    2848    202            �   m  x��W�rܶ]�|���Y��E[�#9��rR�����d����,�o��"�����=M̌I�[�*Y��A����FH���T�Fٖ.��$�����tm����Z��+LMg�)�-)��$�,��x��o�X���l:7�N��ڴ�t�W5��,�����t@���@�"��V��u-���<Q��b��f��d�˶��VM�Z�H�$L���"�O��{��-���/ҮeKaFvpx�s���U��RRd�	� #f�	�Hd㽪j��M�Q3&������]/�(^����n����O��z�3�����k�C��,N�ma��Z��z��I�i&�,�>�I�0m��5*��g�1ݱ�[;Ǹ���%~(Z�"LA��/����<ɊA#�"�jp��2��a(�+��<$��u��8(�<� љ�4ؑ��@XI�O�B\�QȢ�kex}�g' _tѡȒsw�#�kCY��~�L�C�9�k��U�C���]p�Q�Π�qŁ�	^�j5��C�ɦ伅�V/�N
��+W���|�0NE�dqNp�� �zK���
�1ߐ�$��3{�`A_s+Cn��D��؟2'��Nm7�ԏ�0UbH[�KaB���5�k��RV�NZ���%�#�.\��Jگ�B��i�>��.(E��<ɔ:�p��HsSplE�ӟ��#�+�@	Z�%�1��1!c�Y�[:������BT�(o5�Gp?��R�/Nj',��rA�	��s+׍R!#.��>.N�V� �;2�6O�P�8H���NO��zxCP��"���N�q�}����@aAz��Ә�'�B9J|r-q7�PH��۽-�a�C߀L^�@�w�O�@$�Ȳh�/��N�o�H�E'C��`�/�pݘ��z���u�B���p�2M)��P��4�pt�^Ә��辔%�����|II4{LJ' Q|�Nw�8���Ő��7'�ꪑ�(O�9X�Y�J���./��D�_�V��#����R���P�� ��=�9hЕ����KL����j�t3��۶�����<�c����S��C0!�g���vF�7=���Zc�|���_O�҉��Ó\Ɋ�p1|�����	����&0��d�D�僾�Ը\���:B~����!��s�H ց��%��n�T�c��e��y�'�eG��INA�˚D�����j�W�\�&9�>�G$���9Ќ��/����j���,�ݻe��W�fy����@�g#�霥�r��ܩ��HAH��y+6),����"׸����	[֟b��i�R��'	<��#܈gJ��L�L����#�r��K�@��>�.�flvl���Y�V�x��������Jڝ�d1}e���c<��K�(�)[�����'=vC��+��q8!�o��s�r�p8$��F�z��vH������a��ܫj'��%p�ʂ6�#�1������UE��"���Ĕ�'�k_@̰ڹ��s�g.���p5�\���n��]�`��;�W�UM���7��t��Ј�����0�1r��U�D��D����A?1�;jY��e&�b	LAo����SӰ��T�孜k���f���s$�����^[#9j�oB�:��<B��y"�X�?s�.{�.w{�9�d�{>���Hd3�F4t�>�W���c%aM/�w��n�Cs�s��t��ߙC;��҃���~��&�@DW5V��c�U���s5#��ފ�Ǌ+�� ^�{:ѹ�O�j���������p��+d��";��dR���c�ap���gN���3̖{v�K�୏׭*��^V%D$��]^�������4�i�,�c�����ǳ�	�A����棊\�vY�������&������ >?H      �      x������ � �      �      x������ � �      �   @   x�3�<��/?7�(U!%U��T��(37�H!7�89�$ў������L��1�tbJQ�=W� H  �      �   �   x�U��N�0�ϛ��Heo~��T�E�z@B\ǡK]�ʏo��RHo{�ofv4��������1>�[���k�R

 E:UUJeBp�[>���ɇD��6}�H5�)˯�VK.����,�>t8�wt^�Bʥ8��<�ɾ�p�WͲ��4[Jx�_�NSloN<t���$���=U���S-�ao���1цq3���c;1�m8����;|oG��G��\o�$I~ �Y,      �      x�3���,��/���4�4����� :n�      �   :   x�3��J,�L-⌉�H1J3O4�0NNNLKL1MJNNI3361I���L-9�b���� ��z     