#!/bin/bash

mkdir -p bin

javac -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "Compilacion exitosa."

    for i in {1..7}
    do
        java -cp bin RunSpin $i
    done
else
    echo "Error en la compilacion."
fi
