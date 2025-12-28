BEGIN TRANSACTION;

-- 1. Tabla obligatoria para Android Room
CREATE TABLE IF NOT EXISTS "android_metadata" ("locale" TEXT);
INSERT INTO "android_metadata" VALUES ('es_ES');

-- 2. Tabla de Lugares
-- Nota: 'favorito' es INTEGER (0=false, 1=true)
CREATE TABLE IF NOT EXISTS "ubicaciones" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "titulo" TEXT NOT NULL,
    "ubicacion" TEXT NOT NULL,
    "leyenda" TEXT NOT NULL,
    "descripcion" TEXT NOT NULL,
    "imagen" TEXT NOT NULL,
    "imagenMapa" TEXT NOT NULL,
    "imagenMapaEnlace" TEXT NOT NULL,
    "tipo" TEXT NOT NULL,
    "importante" TEXT NOT NULL,
    "favorito" INTEGER NOT NULL
);

-- 3. Tabla de Rutas
CREATE TABLE IF NOT EXISTS "rutas" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "titulo" TEXT NOT NULL,
    "duracion" TEXT NOT NULL,
    "km" REAL NOT NULL,
    "dificultad" TEXT NOT NULL,
    "imagen" TEXT NOT NULL,
    "imagenMapa" TEXT NOT NULL,
    "imagenMapaEnlace" TEXT NOT NULL,
    "leyenda" TEXT NOT NULL,
    "descripcion" TEXT NOT NULL,
    "importante" TEXT NOT NULL,
    "favorito" INTEGER NOT NULL
);

-- 4. Tabla Intermedia (Relación Muchos a Muchos)
-- Nombre según la entidad `RutaLugar` en el modelo
CREATE TABLE IF NOT EXISTS "RutaLugar" (
    "rutaId" INTEGER NOT NULL,
    "lugarId" INTEGER NOT NULL,
    PRIMARY KEY("rutaId", "lugarId"),
    FOREIGN KEY("rutaId") REFERENCES "rutas"("id") ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY("lugarId") REFERENCES "ubicaciones"("id") ON UPDATE NO ACTION ON DELETE CASCADE
);

-- ==========================================
-- INSERCIÓN DE DATOS DE MUESTRA
-- ==========================================

-- Insertar Lugares
-- ID 1: Monumento (Estará en Ruta 1 y Ruta 2)
INSERT INTO "ubicaciones" VALUES (1, 'Hórreo de Carnota', 'Carnota Centro', 'Uno de los más grandes de Galicia', 'Monumento nacional construido en piedra...', 'horreo_carnota.jpg', 'horreo_carnota_mapa.jpg', 'http://example.com/horreo/mapa', 'Monumento', 'Visita obligada', 1);

-- ID 2: Playa (Estará en Ruta 2)
INSERT INTO "ubicaciones" VALUES (2, 'Playa de Boca do Río', 'Costa de Carnota', 'Donde el río encuentra el mar', 'Hermosa playa con formaciones rocosas...', 'boca_do_rio.jpg', 'boca_do_rio_mapa.jpg', 'http://example.com/boca/mapa', 'Playa', 'Llevar protector solar', 0);
-- ID 3: Senderismo (Estará en Ruta 1)
INSERT INTO "ubicaciones" VALUES (3, 'Monte Pindo', 'O Pindo', 'El Olimpo Celta', 'Formaciones graníticas con historia mitológica.', 'monte_pindo.jpg', 'monte_pindo_mapa.jpg', 'http://example.com/pindo/mapa', 'Senderismo', 'Calzado cómodo necesario', 1);

-- ID 4: Monumento (No asignado a ninguna ruta - Huérfano)
INSERT INTO "ubicaciones" VALUES (4, 'Fervenza de Ézaro', 'Dumbría/Carnota', 'El río Xallas cae al mar', 'Espectacular cascada iluminada en verano.', 'ezaro.jpg', 'ezaro_mapa.jpg', 'http://example.com/ezaro/mapa', 'Monumento', 'Verificar horarios de iluminación', 0);

-- Insertar Rutas
-- ID 1: Ruta Compleja
INSERT INTO "rutas" VALUES (1, 'Ruta de los Gigantes', '3 horas', 5.5, 'Media', 'ruta_gigantes.jpg', 'ruta_gigantes_mapa.jpg', 'http://example.com/ruta1/mapa', 'Recorrido por monumentos clave', 'Una ruta que une historia y naturaleza.', 'Llevar agua', 1);

-- ID 2: Ruta Simple
INSERT INTO "rutas" VALUES (2, 'Ruta Costera Relax', '1 hora', 2.0, 'Baja', 'ruta_costa.jpg', 'ruta_costa_mapa.jpg', 'http://example.com/ruta2/mapa', 'Paseo por la orilla', 'Ideal para familias.', 'Marea baja recomendada', 0);


-- Insertar Relaciones (Tabla Cruzada)
-- Ruta 1 contiene Hórreo (1) y Monte Pindo (3)
INSERT INTO "RutaLugar" VALUES (1, 1);
INSERT INTO "RutaLugar" VALUES (1, 3);

-- Ruta 2 contiene Playa (2) y TAMBIÉN el Hórreo (1) (Escenario compartido)
INSERT INTO "RutaLugar" VALUES (2, 2);
INSERT INTO "RutaLugar" VALUES (2, 1);

COMMIT;