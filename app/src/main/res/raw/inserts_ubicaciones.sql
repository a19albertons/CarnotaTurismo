-- Inserts para la tabla 'ubicaciones'
-- NOTA: los campos de texto (titulo, leyenda, descripcion, importante) almacenan CLAVES de recursos string
-- (p. ej. 'lugar_1_titulo') para resolver en tiempo de ejecución usando resources.getIdentifier(...)

-- ID 1: Monumento (Estará en Ruta 1 y Ruta 2)
INSERT INTO "ubicaciones" VALUES (1, 'lugar_1_titulo', 'Carnota Centro', 'lugar_1_leyenda', 'lugar_1_descripcion', '@drawable/pruebas', '@drawable/pruebas', 'http://example.com/horreo/mapa', 'Monumento', 'lugar_1_importante', 1);

-- ID 2: Playa (Estará en Ruta 2)
INSERT INTO "ubicaciones" VALUES (2, 'lugar_2_titulo', 'Costa de Carnota', 'lugar_2_leyenda', 'lugar_2_descripcion', '@drawable/pruebas', '@drawable/pruebas', 'http://example.com/boca/mapa', 'Playa', 'lugar_2_importante', 0);

-- ID 3: Senderismo (Estará en Ruta 1)
INSERT INTO "ubicaciones" VALUES (3, 'lugar_3_titulo', 'O Pindo', 'lugar_3_leyenda', 'lugar_3_descripcion', '@drawable/pruebas', '@drawable/pruebas', 'http://example.com/pindo/mapa', 'Senderismo', 'lugar_3_importante', 1);

-- ID 4: Monumento (No asignado a ninguna ruta - Huérfano)
INSERT INTO "ubicaciones" VALUES (4, 'lugar_4_titulo', 'Dumbría/Carnota', 'lugar_4_leyenda', 'lugar_4_descripcion', '@drawable/pruebas', '@drawable/pruebas', 'http://example.com/ezaro/mapa', 'Monumento', 'lugar_4_importante', 0);
