# INTRODUCCIÓN
Representación en Java de un autómata de estados finitos no determinista (con cadena vacía) que lee su configuración desde un fichero. El proyecto se llama "Máquina expendedora" porque la configuración por defecto representa una: permite introducir dinero, comprar productos y devolver.
Para ejecutar, debe usarse el siguiente comando:

```
java -jar MaquinaExpendedora.jar
```

# FUNCIONAMIENTO

El programa pedirá una serie de inputs. Estos serán tomados por separado: al escribir "123" se interpretará como "1", "2", "3". LA máquina irá realizando las transiciones asociadas a sus estados actuales y esas entradas, dando como resultado nuevos estados. A cada paso imprimirá los estados actuales, indicando si son finales.

# FICHERO DE CONFIGURACIÓN

El programa leerá la configuración del autómata del fichero "definicion.txt". El formato debe ser el siguiente:
```
#NumeroEstados NombreEstado1 NombreEstado2 ... NombreEstadoN
#NumeroEstadosFinales NombreEstadoFinal1 NombreEstadoFinal2 ... NombreEstadoFinalN
#NumeroInputs Input1 Input2 ... InputN
--TABLA DE TRANSICIONES
Fila1
Fila2
...
FilaN
```

Cada fila de la tabla está asociada a un estado, y cada columna a un input (salvo la última, para la cadena vacía). Esto significa que cada casilla se corresponde con el estado alcanzado al introducir el input de esa columna sobre el estado de esa fila.
