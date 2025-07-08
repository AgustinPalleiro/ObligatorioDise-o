# Aplicación de Poker - Obligatorio de Diseño y Desarrollo de Aplicaciones

Este repositorio contiene el código fuente de una aplicación de póker desarrollada como parte del **Obligatorio de la asignatura Diseño y Desarrollo de Aplicaciones** de la **Universidad ORT Uruguay**.

## 🎯 Objetivo

El proyecto consistió en el diseño y desarrollo de una aplicación de escritorio que simula una partida de póker, utilizando **Java** y **Swing (JSwing)** para la interfaz gráfica, aplicando varios **patrones de diseño** vistos en el curso.

---

## 🛠️ Tecnologías utilizadas

- **Java 8+**
- **Swing (JSwing)** para la interfaz gráfica
- **NetBeans IDE** (opcional)
- **Paradigma orientado a objetos**
- **Principios SOLID**

---

## 🧠 Patrones de diseño implementados

Se aplicaron distintos patrones de diseño para estructurar y desacoplar la lógica de la aplicación:

- 🧩 **MVC (Modelo-Vista-Controlador)**: separa la lógica de negocio, la interfaz gráfica y el control de eventos.
- 👁️ **Observer (Observador)**: para la actualización de vistas según los cambios en el modelo del juego.
- 🧱 **Template Method**: define el flujo base de la evaluación de jugadas, permitiendo extender tipos de manos (Poker, Escalera, etc.).
- 🧭 (Otros): se incorporaron buenas prácticas de encapsulamiento y reutilización siguiendo los principios de diseño de software.

---

## 🃏 Descripción de la aplicación

- Permite simular partidas de póker con múltiples jugadores.
- Manejo de estados de la mesa (abierta, iniciada, etc.).
- Creación automática de manos y evaluación de jugadas.
- Visualización de los diferentes tipos de jugadas en un **JDialog** informativo.
- Registro de eventos y acciones del juego mediante controladores y listeners.
