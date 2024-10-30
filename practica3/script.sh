#!/bin/bash

# Nombre del archivo Java compilado (sin la extensión .class)
JAVA_CLASS="LockPeterson"

# Número de veces que deseas ejecutar el programa
NUM_RUNS=20

# Ejecutar el programa Java 20 veces
for ((i=1; i<=NUM_RUNS; i++))
do
    #echo "Ejecución número $i"
    java $JAVA_CLASS
done
