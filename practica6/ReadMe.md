# Practica 6
1 Para la primera implementacion hay que descomentar la linkea 24 y comentar la linea 23 de CASConsensus, que es cambiar compareAndSet por getAndSet que es el equivalente en util.concurrent.atomic.AtomicInteger en java de testAndSet

2. Para la ejecucion del consenso con una tecnica de ayuda implementamos un arreglo de ayuda llamado help, el cual tiene el mismo tamaño de numero de hilos, por lo que cuando un hilo quiera decidir, vea si su vecino de la derecha necesita ayuda, en caso de que si, entonces lo ayude, en caso de que el hilo actual haya ganado menos veces que su vecino de la derecha entonces se prefiere a el mismo y continua.

3. Lo que hicimos aqui fue, notemos que la condicion a esta igual, por lo que el lock reader no cambia, ahora el lock writer es el que quita la condicion de que si un escritor esta en la seccion critica, otro escritor no puede entrar, por lo que comentamos la condicion que maneja ese caso, es decir ya no tenemos que esperar si un escrito esta donde queremos acceder y luego en el ejecutable imprimimos cuantos escritores hay en la zona critica, y hay ejecuciones donde si vemos que hay mas de 1.

Para la ejecucion del consenso basta con ejecutar:
```
./script.sh consenso
```

Para ejecutar el ejecutable de los escritores lectores fifo:
```
./script.sh write
```

Asegurese de tener permisos de ejecucion sobre el ejecutable:
```
chmod +x script.sh
```
