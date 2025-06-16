# 📊 Prueba Técnica – Ruklo

Este proyecto resuelve dos desafíos planteados por Ruklo mediante el procesamiento de un archivo JSON con eventos simulados de clientes. Se implementó usando **Spring Boot**, con énfasis en la correcta modelación del problema, claridad de la lógica y escalabilidad.

---

## ✅ Requisitos Técnicos

- **Java 17** o superior
- **Maven 3.8+**
- Sistema operativo compatible (Windows, Linux, macOS)
- Archivo de eventos: `ruklo_events_1000.json` ubicado en `src/main/resources/`

---

## 🚀 Cómo ejecutar el programa

1. Clona este repositorio:
   ```bash
   https://github.com/Alejandro-vy/PruebaRuko.git
   cd PruebaRuko
   
2. Compila el proyecto:
   ```bash
   mvn clean install
3. Ejecuta la aplicación: 
   ```bash
   mvn spring-boot:run
4. Archivos generados:

- Al finalizar la ejecución, se generarán dos archivos en la carpeta outputs/:

  - benefits_output.json

  - summary_output.json   
   
# Descripción del problema y soluciones

## Parte 1 – Requerimientos
1. Detectar clientes con 5 visitas seguidas sin recarga

   - Se procesan los eventos por cliente y tienda.

   - Se identifican secuencias de 5 visitas consecutivas sin recargas entre medio.

   - Se otorga un beneficio por cada secuencia encontrada.

   - Se permiten secuencias solapadas.

🗂 Resultado: benefits_output.json
📦 Modelo: ClientVisitStreak
📅 Orden: cronológico por timestamp

2. Historial de transacciones por cliente, agrupado por tipo y semana

   - Se genera un resumen por cliente con agrupación semanal.

   - Se incluyen todas las semanas, incluso sin recargas.

   - Se calcula el promedio de monto recargado por semana (0 si no hubo recargas).

   - Se ordenan las semanas cronológicamente.

🗂 Resultado: summary_output.json
📦 Modelo: ClientTransactionSummary


## 📁 Estructura del proyecto

```bash
   src/
   ├── main/
   │   ├── java/
   │   │   └── com.example.PruebaRuko/
   │   │       ├── model/
   │   │       ├── service/
   │   │       └── runner/
   │   └── resources/
   │       └── ruklo_events_1000.json
   └── outputs/
       ├── benefits_output.json
       └── summary_output.json
```
                     




## Parte 2 - Preguntas

### Limitaciones actuales:

- Procesamiento completo en memoria

- No se utiliza base de datos

- Resultados estáticos en archivos JSON

- No hay control de concurrencia ni validación de duplicados

### Escenario con 100.000 eventos diarios:

- El rendimiento disminuiría considerablemente.

- Posible error de memoria.

Para escalar, se recomienda:

- Persistencia en base de datos con índices.

- Procesamiento incremental o por lotes.

- Uso de arquitectura orientada a eventos (Kafka, Redis).

- Exposición de resultados a través de APIs REST.

# Autor
Desarrollado por: [Alejandro Vera]

