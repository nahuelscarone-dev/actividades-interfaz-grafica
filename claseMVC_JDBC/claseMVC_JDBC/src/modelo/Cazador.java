package modelo;

/*
 * ════════════════════════════════════════════════════════════════════════
 *  INTERFAZ Cazador  ·  Clase 9: interfaces (contrato básico)
 * ────────────────────────────────────────────────────────────────────────
 *  Una interfaz es un CONTRATO: define QUÉ debe saber hacer una clase, sin
 *  decir CÓMO. No es una clase padre: es una capacidad que pueden tener
 *  clases de familias distintas.
 *
 *  El problema que resuelve: cazan el tigre y el gato montés (felinos),
 *  el zorro y el lobo (caninos) y el águila (ave). Están en ramas distintas
 *  de la herencia, así que no hay una clase padre común para cazar(). La
 *  interfaz cruza la jerarquía en horizontal.
 * ════════════════════════════════════════════════════════════════════════
 */
public interface Cazador {
    void cazar();   // solo la firma: cada clase que implemente esto define SU cazar()
}
