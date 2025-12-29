-- Inserts para la tabla 'rutas'
-- NOTA: los campos de texto (titulo, duracion, dificultad, leyenda, descripcion, importante)
-- almacenan CLAVES de recursos string cuando corresponda (p. ej. 'ruta_1_titulo').

-- ID 1: Ruta Compleja
-- Nota: ahora guardamos la dificultad como el nombre del enum (BAJA|MEDIA|ALTA)
INSERT INTO "rutas" VALUES (1, 'ruta_1_titulo', 'ruta_1_duracion', 5.5, 'MEDIA', '@drawable/pruebas', '@drawable/pruebas', 'http://example.com/ruta1/mapa', 'ruta_1_leyenda', 'ruta_1_descripcion', 'ruta_1_importante', 1);

-- ID 2: Ruta Simple
INSERT INTO "rutas" VALUES (2, 'ruta_2_titulo', 'ruta_2_duracion', 2.0, 'BAJA', '@drawable/pruebas', '@drawable/pruebas', 'http://example.com/ruta2/mapa', 'ruta_2_leyenda', 'ruta_2_descripcion', 'ruta_2_importante', 0);
