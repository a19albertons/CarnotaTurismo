#!/bin/bash

# 1. Definir nombres de archivos
DB_NAME="turismo.db"
SQL_SCRIPT="script_datos.sql"

# 2. Borrar la base de datos anterior para asegurar que está limpia
# (Importante: si no la borras, sqlite3 AÑADE datos a la existente)
if [ -f "$DB_NAME" ]; then
    rm "$DB_NAME"
    echo "Base de datos antigua eliminada."
fi

# 3. Generar la nueva base de datos desde el SQL
sqlite3 "$DB_NAME" < "$SQL_SCRIPT"

# 4. Verificación rápida (Opcional)
if [ -f "$DB_NAME" ]; then
    echo "✅ Éxito: Se ha creado '$DB_NAME'."
    # Mostrar el tamaño del archivo
    ls -lh "$DB_NAME"
else
    echo "❌ Error: No se pudo crear la base de datos."
fi