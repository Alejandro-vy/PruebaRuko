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
1. Detectar clientes con 5 visitas seguidas sin recarga.

Punto 1:
  
   - Se procesan los eventos por cliente y tienda.

   - Se identifican secuencias de 5 visitas consecutivas sin recargas entre medio.

   - Se otorga un beneficio por cada secuencia encontrada.
  
Punto 2:

   - Para esta primera instancia, se eligió guardar los beneficios en un archivo JSON, dado el contexto de prueba técnica. Sin embargo, como se muestra en el diagrama ubicado en PruebaRuko/outputs/PruebaRuko DiagramaUML.PNG, una mejor solución a futuro es implementar una base de datos relacional, lo que permitiría una trazabilidad más robusta y un formato de datos estandarizado. Ejemplo Diagrama UML: ![Modelo Diagrama de clases UML](/outputs/PruebaRuko DiagramaUML.PNG)

Punto 3:

   - Para evitar errores y mantener buen rendimiento con grandes volúmenes de datos, usaría: Una base de datos como mecioné anteriormente para almacenar los beneficios y los eventos, también es importante guardar las validaciones y logs para el control de errores, validación de datos y trazabilidad mediante logging, lo cual considero muy importante al trabajar con grandes volumenes de datos. Finalmente Implementaría un script ETL que gestione la carga de los archivos JSON, garantizando su integridad antes de llegar a la base de datos y al backend.


🗂 Resultado: benefits_output.json

📦 Modelo: ClientVisitStreak ubicado en `src/main/java/com.example.PruebaRuko/model/ClientVisitStreak.java`




2. Historial de transacciones por cliente, agrupado por tipo y semana


   - Se genera un resumen por cliente, agrupado semanalmente.

   - Se incluyen todas las semanas, incluso aquellas sin recargas.

   - Se calcula el promedio de monto recargado por semana (0 si no hubo recargas).

   - Las semanas se ordenan cronológicamente para facilitar el análisis.


🗂 Resultado: summary_output.json

📦 Modelo: ClientTransactionSummary ubicado en `src/main/java/com.example.PruebaRuko/model/ClientTransactionSummary.java`


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
       └── PruebaRuko DiagramaUML.png
       
```
                     




## Parte 2 - Preguntas

### Limitaciones actuales:

- Procesamiento en memoria, toda la data se carga y procesa en memoria, lo que puede causar problemas de rendimiento o errores por falta de recursos.

- No se utiliza base de datos, lo que impide consultas dinamicas y/o almacenamiento historico de los datos

- Resultados estáticos en archivos JSON

- No hay control de concurrencia, ni validación de duplicados

- Cada ejecución reprocesa todo el archivo, incluso eventos ya procesados. Por lo cual no es incremental.

### Escenario con 100.000 eventos diarios:

   - El rendimiento disminuiría considerablemente, tiempos de ejecución altos.

   - Posible error de memoria por el aumento de datos.

   - Dificil de mantener

Para escalar, se recomienda:

   - Persistencia en base de datos con índices.

   - Procesamiento incremental o por lotes.

   - Uso de arquitectura orientada a eventos o de microservicios.

   - Exposición de resultados mediante de una APIs REST.

# Autor
Desarrollado por: [Alejandro Vera]

