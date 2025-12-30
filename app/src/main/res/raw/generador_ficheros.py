import os

# Nombre del archivo de entrada
INPUT_FILE = 'muestra.txt'

def escape_xml(text):
    """Escapa caracteres especiales para XML y preserva saltos de línea como &#10;.
    Reemplaza comillas simples por la entidad XML &apos;.
    """
    if not text:
        return ""
    text = text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;")
    # Normalize Windows CRLF to LF, luego convertir saltos reales en la entidad XML "&#10;"
    text = text.replace("\r\n", "\n").replace("\n", "&#10;")
    # También normalizar secuencias literales "\\n" (barra + n) a la entidad, por si provienen de entradas ya escapadas
    text = text.replace("\\n", "&#10;")
    return text


def escape_sql(text):
    """Escapa comillas simples para SQL y preserva saltos de línea como \\n
    Duplica comillas simples (''), y convierte saltos de línea en la representación literal "\\n" para evitar romper el SQL.
    """
    if text is None:
        return ""
    # Duplicar comillas simples para evitar romper literales SQL
    return text.replace("'", "''").replace("\n", "\\n")

def parse_input(filename):
    """Lee el archivo muestra.txt y devuelve listas de lugares y rutas."""
    if not os.path.exists(filename):
        print(f"Error: No se encuentra el archivo {filename}")
        return [], []

    with open(filename, 'r', encoding='utf-8') as f:
        content = f.read()

    # Separar por bloques usando el separador
    raw_blocks = content.split('-------------')
    
    lugares = []
    rutas = []

    for block in raw_blocks:
        lines = block.strip('\n').split('\n')  # No eliminar líneas vacías; necesitamos conservar saltos
        if not any(l.strip() for l in lines):
            continue

        # Detectar tipo (La primera línea debe ser "Lugar" o "Ruta")
        tipo_bloque = lines[0].strip()
        data = {'id': '0'} # Default ID
        
        current_key = None
        
        for line in lines[1:]:
            # Si la línea está vacía, se considera una línea en blanco dentro del campo actual
            if line.strip() == '':
                if current_key:
                    data[current_key] += '\n'
                continue

            if ':' in line:
                # Es una nueva clave (ej: "titulo: mi titulo")
                parts = line.split(':', 1)
                key = parts[0].strip()
                value = parts[1].lstrip()
                data[key] = value
                current_key = key
            elif current_key:
                # No tiene dos puntos, es continuación de la línea anterior (multilínea)
                data[current_key] += '\n' + line.strip()

        if tipo_bloque == 'Lugar':
            lugares.append(data)
        elif tipo_bloque == 'Ruta':
            rutas.append(data)

    return lugares, rutas

def generate_strings_lugares(lugares):
    """Genera strings_lugares.xml"""
    xml_content = '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n    <!-- Strings para lugares (clave-based) -->\n\n'
    
    for l in lugares:
        lid = l.get('id', '0')
        xml_content += f"    <string name=\"lugar_{lid}_titulo\">{escape_xml(l.get('titulo', ''))}</string>\n"
        xml_content += f"    <string name=\"lugar_{lid}_leyenda\">{escape_xml(l.get('leyenda', ''))}</string>\n"
        xml_content += f"    <string name=\"lugar_{lid}_descripcion\">{escape_xml(l.get('descripcion', ''))}</string>\n"
        xml_content += f"    <string name=\"lugar_{lid}_importante\">{escape_xml(l.get('importante', ''))}</string>\n\n"

    xml_content += "</resources>"
    
    with open('strings_lugares.xml', 'w', encoding='utf-8') as f:
        f.write(xml_content)
    print("Generado: strings_lugares.xml")

def generate_strings_rutas(rutas):
    """Genera strings_rutas.xml"""
    xml_content = '<?xml version="1.0" encoding="utf-8"?>\n<resources>\n    <!-- Strings para rutas (clave-based) -->\n\n'
    
    for r in rutas:
        rid = r.get('id', '0')
        xml_content += f"    <string name=\"ruta_{rid}_titulo\">{escape_xml(r.get('titulo', ''))}</string>\n"
        xml_content += f"    <string name=\"ruta_{rid}_duracion\">{escape_xml(r.get('duracion', ''))}</string>\n"
        xml_content += f"    <string name=\"ruta_{rid}_dificultad\">{escape_xml(r.get('dificultad', ''))}</string>\n"
        xml_content += f"    <string name=\"ruta_{rid}_leyenda\">{escape_xml(r.get('leyenda', ''))}</string>\n"
        xml_content += f"    <string name=\"ruta_{rid}_descripcion\">{escape_xml(r.get('descripcion', ''))}</string>\n"
        xml_content += f"    <string name=\"ruta_{rid}_importante\">{escape_xml(r.get('importante', ''))}</string>\n\n"

    xml_content += "</resources>"
    
    with open('strings_rutas.xml', 'w', encoding='utf-8') as f:
        f.write(xml_content)
    print("Generado: strings_rutas.xml")

def generate_inserts_ubicaciones(lugares):
    """Genera inserts_ubicaciones.txt"""
    sql_content = "-- Inserts para la tabla 'ubicaciones'\n"
    
    for l in lugares:
        lid = l.get('id', '0')
        # Mapeo de claves de recurso
        titulo_res = f"lugar_{lid}_titulo"
        leyenda_res = f"lugar_{lid}_leyenda"
        desc_res = f"lugar_{lid}_descripcion"
        imp_res = f"lugar_{lid}_importante"
        
        # Datos raw (escapados para SQL)
        ubicacion = escape_sql(l.get('ubicacion', ''))
        img = escape_sql(l.get('imagen', ''))
        imgMap = escape_sql(l.get('imagenMapa', ''))
        imgMapLink = escape_sql(l.get('imagenMapaEnlace', ''))
        tipo = escape_sql(l.get('tipo', 'Monumento'))
        fav = l.get('favorito', '0')
        
        sql = f"INSERT INTO \"ubicaciones\" VALUES ({lid}, '{titulo_res}', '{ubicacion}', '{leyenda_res}', '{desc_res}', '{img}', '{imgMap}', '{imgMapLink}', '{tipo}', '{imp_res}', {fav});\n"
        sql_content += sql

    with open('inserts_ubicaciones.sql', 'w', encoding='utf-8') as f:
        f.write(sql_content)
    print("Generado: inserts_ubicaciones.sql")

def generate_inserts_rutas(rutas):
    """Genera inserts_rutas.sql"""
    sql_content = "-- Inserts para la tabla 'rutas'\n"
    
    for r in rutas:
        rid = r.get('id', '0')
        # Mapeo de claves de recurso
        titulo_res = f"ruta_{rid}_titulo"
        dur_res = f"ruta_{rid}_duracion"
        # Nota: Dificultad en SQL suele ser ENUM (raw), pero en strings.xml se creó recurso.
        # Basado en tu ejemplo 'inserts_rutas.txt', la dificultad se guarda RAW ('MEDIA'), no como recurso.
        # Ajustaré aquí para usar el valor crudo en SQL como en tu ejemplo.
        dificultad_raw = escape_sql(r.get('dificultad', 'BAJA')) 
        
        leyenda_res = f"ruta_{rid}_leyenda"
        desc_res = f"ruta_{rid}_descripcion"
        imp_res = f"ruta_{rid}_importante"
        
        # Datos raw (escapados para SQL)
        km = r.get('km', '0.0')
        img = escape_sql(r.get('imagen', ''))
        imgMap = escape_sql(r.get('imagenMapa', ''))
        imgMapLink = escape_sql(r.get('imagenMapaEnlace', ''))
        fav = r.get('favorito', '0')
        
        sql = f"INSERT INTO \"rutas\" VALUES ({rid}, '{titulo_res}', '{dur_res}', {km}, '{dificultad_raw}', '{img}', '{imgMap}', '{imgMapLink}', '{leyenda_res}', '{desc_res}', '{imp_res}', {fav});\n"
        sql_content += sql

    with open('inserts_rutas.sql', 'w', encoding='utf-8') as f:
        f.write(sql_content)
    print("Generado: inserts_rutas.sql")

def generate_ruta_lugar_relacion(rutas):
    """
    Genera inserts_ruta_lugar.sql.
    NOTA: El archivo 'muestra.txt' proporcionado NO tiene campo para definir relaciones.
    Este script buscará un campo opcional 'lugares_ids' (ej: 1,3) en el bloque de Ruta.
    Si no existe, generará un archivo vacío con comentario.
    """
    sql_content = "-- Inserciones para la tabla intermedia 'RutaLugar'\n"
    
    found_relations = False
    for r in rutas:
        rid = r.get('id')
        # Buscamos si el usuario añadió un campo extra "lugares_ids: 1, 2, 3" en muestra.txt
        ids_raw = r.get('lugares_ids', '') 
        if ids_raw:
            ids = [x.strip() for x in ids_raw.split(',')]
            for lid in ids:
                if lid:
                    sql_content += f"INSERT INTO \"RutaLugar\" VALUES ({rid}, {lid});\n"
                    found_relations = True
    
    if not found_relations:
        sql_content += "-- ADVERTENCIA: No se encontraron campos 'lugares_ids' en las Rutas de muestra.txt.\n"
        sql_content += "-- Agrega 'lugares_ids: 1, 2' en el bloque de Ruta en muestra.txt para generar esto.\n"

    with open('inserts_ruta_lugar.sql', 'w', encoding='utf-8') as f:
        f.write(sql_content)
    print("Generado: inserts_ruta_lugar.sql")

# --- Ejecución ---
if __name__ == "__main__":
    lugares, rutas = parse_input(INPUT_FILE)
    
    if lugares or rutas:
        generate_strings_lugares(lugares)
        generate_strings_rutas(rutas)
        generate_inserts_ubicaciones(lugares)
        generate_inserts_rutas(rutas)
        generate_ruta_lugar_relacion(rutas)
        print("\n¡Proceso completado con éxito!")
    else:
        print("No se encontraron datos válidos.")