# 🎮MiniProyecto #4 – Simulación de Batalla Pokémon

Asignatura: Programación Orientada a Eventos  
Institución: Universidad del Valle  
Fecha: 12/06/2025 

---
## 👨‍💻 Autores

- Sebastián Rubio Piedrahita – 202459628  
- Sebastián Devia Acosta – 202459664  
- Simón David Tarazona Melo – 202459421

---
## 🧠 Descripción general

Este proyecto consiste en el desarrollo de una simulación de combate Pokémon con interfaz gráfica (GUI) y soporte en consola. El objetivo principal es aplicar los principios de la Programación Orientada a Objetos (POO), el patrón de diseño Modelo-Vista-Controlador (MVC) y eventos, para modelar combates entre entrenadores Pokémon de forma interactiva y visualmente atractiva.

---
## ⚙️ Características principales

- Registro y gestión de entrenadores Pokémon.
- Selección aleatoria de equipos de Pokémon.
- Simulación de batallas por turnos en consola y en GUI.
- Interfaz intuitiva para realizar ataques y visualizar estados de salud.
- Sonido y animaciones básicos.
- Registro del historial de batalla.

---
## 📚 Estructuras de datos utilizadas
Este proyecto implementa varias estructuras de datos para gestionar de forma clara y eficiente la lógica del sistema:

- Stack<String> (Pila):
   Empleada en la clase HistoryData para almacenar y consultar el historial de acciones realizadas durante el juego, utilizando el principio LIFO (Last In, First Out).
  
- Map<String, Integer> (HashMap):
  Utilizada en la clase Pokedex para asociar los nombres de los Pokémon con sus respectivos identificadores oficiales, facilitando la carga dinámica de imágenes desde una API externa.

- Pokemon[] y Attack[] (Arreglos):
  Definidos en PokemonFactory y AttackFactory, contienen listas predefinidas de Pokémon y ataques que pueden ser utilizados en las batallas.

---
## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java  
- **Paradigmas:** Programación Orientada a Objetos y a Eventos  
- **Entorno de desarrollo:** IntelliJ IDEA

---
## 📷 Capturas de Pantalla

### **GUI:**
- **Pantalla incial:**

![Captura de pantalla 2025-06-01 222643](https://github.com/user-attachments/assets/c2123ad1-6628-415c-b412-769f84ed4a3d)
---
- **Menu para ingresar entrenadores e inciar la batalla:**

![Captura de pantalla 2025-06-12 003339](https://github.com/user-attachments/assets/1562137b-90d7-4cee-929c-aa02b3598ed6)
---
- **Ventana emergente para visualizar los equipos de los entrenadores:**
  
![Captura de pantalla 2025-06-12 003404](https://github.com/user-attachments/assets/17b10a04-808c-45f7-8c11-2a1475776427)
---
- **Panel de seleccion de pokemon para la batalla:**

![Captura de pantalla 2025-06-12 003416](https://github.com/user-attachments/assets/5b65e656-d7c1-4db6-af83-cac0df58cbd1)
---
- **Combate:**

![Captura de pantalla 2025-06-12 003425](https://github.com/user-attachments/assets/29f693ee-dfc6-43b6-bdc9-e93641db078d)
---
- **Historial de Batalla:**

![Captura de pantalla 2025-06-12 003455](https://github.com/user-attachments/assets/32e446f4-e41f-4f5b-95b9-337109685a72)
---
- **Graficas de estadisticas:**

![Captura de pantalla 2025-06-12 003507](https://github.com/user-attachments/assets/4340673c-4e05-419d-ba97-fd69ec0534c1)



