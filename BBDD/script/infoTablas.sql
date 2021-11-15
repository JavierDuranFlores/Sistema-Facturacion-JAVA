
-- INSERCION DEL USUARIO
INSERT INTO usuarios (id, usuario, contra) VALUES (DEFAULT, 'Javier', ENCRYPT('Duran2001','llave', '3des')::text);

-- INSERCION DE CLIENTES
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


-- INSERCION DE PRODUCTOS
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

INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Jugo Manzana', 25, 5, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Jugo Uva', 23, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Jugo Limon', 25, 6, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Jugo Mango', 11, 3, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Jugo Fresa', 12, 8, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Sabrita Chetos', 16, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Takis Barbacoa', 15, 15, NOW());
--
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Hamburguesas', 40, 5, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Aceite de Girasol', 23, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Aceite de Maíz', 25, 6, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Aceite Mezcla', 26, 3, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Arroz Blanco', 28, 8, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Edulcorante', 16, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Mayonesa', 15, 15, NOW());

INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Mostaza', 25, 5, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Sal', 23, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Vinagre', 25, 6, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Atún', 15, 3, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Salsa Tomate', 12, 8, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Mermelada', 20, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Harina de Maíz', 15, 15, NOW());

--
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Harina de Trigo', 20, 5, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Huevos', 50, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Café Instantáneo 50g', 50, 6, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Café 180g', 90, 3, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Pan de molde', 28, 8, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Fideos guiseros', 16, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Fideos largos', 15, 15, NOW());

INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Pañales extra grandes', 130, 5, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Agua sin gas', 23, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Salchichas', 25, 6, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Jamón cocido', 25, 3, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Paleta', 12, 8, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Paté', 20, 10, NOW());
INSERT INTO productos (nombre, precio, stock, create_at) VALUES('Picadillo de carne', 25, 15, NOW());


-- INSERCION DE FACTURAS
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(4, 667960, 1, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 1, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(2, 667960, 2, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 2, 8);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 2, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(4, 667960, 3, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 3, 1);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 3, 4);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 3, 5);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 3, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(2, 667960, 4, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 4, 8);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 4, 7);

INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(4, 667960, 5, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 5, 1);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 5, 4);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 5, 5);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 5, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(2, 667960, 6, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 6, 8);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 6, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(4, 667960, 7, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 7, 1);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 7, 4);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 7, 5);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 7, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(2, 667960, 8, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 8, 8);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 8, 7);

-- INSERCION DE FACTURAS ITEMS
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(4, 667960, 9, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 9, 1);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 9, 4);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 9, 5);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 9, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(2, 667960, 10, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 10, 8);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 10, 7);

INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(4, 667960, 11, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 11, 1);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 11, 4);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 11, 5);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 11, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(2, 667960, 12, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 12, 8);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 12, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(4, 667960, 13, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 13, 1);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 13, 4);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 13, 5);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 13, 7);
INSERT INTO facturas (n_productos, total, id_cliente, create_at) VALUES(2, 667960, 14, NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(1, 14, 8);
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(2, 14, 7);