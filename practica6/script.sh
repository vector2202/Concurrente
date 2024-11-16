#!/bin/bash

SRC_DIR="src"
BIN_DIR="bin"

mkdir -p $BIN_DIR

echo "Compilando archivos Java..."
javac -d $BIN_DIR $SRC_DIR/*.java

if [ $? -ne 0 ]; then
    echo "Error al compilar los archivos Java."
    exit 1
fi

echo "Compilacion exitosa. Archivos compilados en $BIN_DIR."

case $1 in
    consenso)
        echo "Ejecutando ExecConsensusRounds..."
        java -cp $BIN_DIR ExecConsenRounds
        ;;
    write)
        echo "Ejecutando ExecReadersWriters..."
        java -cp $BIN_DIR ExecReadersWriters
        ;;
    *)
        echo "Uso: $0 [consenso|write]"
        exit 1
        ;;
esac
