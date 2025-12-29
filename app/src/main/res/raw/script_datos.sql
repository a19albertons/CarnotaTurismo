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
-- INSERCIÓN DE DATOS DE MUESTRA (archivos externos)
-- ==========================================

-- Incluimos los inserts desde archivos separados para facilitar mantenimiento y traducción
.read inserts_ubicaciones.sql
.read inserts_rutas.sql
.read inserts_ruta_lugar.sql

COMMIT;