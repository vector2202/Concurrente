# Practica 5

Para esta practica se nos pide adaptar el executableQ para la implementacion obstruction free en simplesnapshot, para esto nos damos cuenta que la clase stampedvalue no tiene snaps asociados mas que solo un valor, por lo que hay que hacer una modificacion aqui y es agregar una lista de valores para que los vaya almacenando, por ello es la linea extra agregada de add value y ademas un atributo values que es donde se va a guardar esta lista en stampedvalue, por ulitmo igual tenemos que generar un constructor nuevo para guardar estas listas, esos son las nuevas implementaciones y con respecto a los ejecutables, todo el codigo es muy similar salvo que ahora no imprimiremos los snaps asociados sino la lista de valores, por lo que cambiamos esta parte del codigo y ejecutamos, para la parte 3 basta con comentar las lineas 45-49 y descomentar la linea 50 y para la parte 1 viceversa, solo hay que comentar la linea 49 y descomentar esas lineas, la compilacion es:
```
javac *.java
```
Y la ejecucion;
```
java ExecutableSnapshotOF
```
