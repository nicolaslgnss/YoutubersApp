# YoutubersApp - Práctica Módulo Android

Aplicación móvil desarrollada en Android Studio utilizando Kotlin, diseñada como catálogo interactivo de creadores de contenido de YouTube, cumpliendo con los estándares de la especificación técnica del módulo.

## 🚀 Funcionalidad de la Aplicación
La aplicación permite al usuario realizar un inicio de sesión, acceder a un listado principal estructurado mediante un `RecyclerView` de creadores de contenido, consultar información detallada y abrir los enlaces oficiales de los canales de forma nativa.

## ✅ Cumplimiento de Requisitos de la Rúbrica
* **Actividades múltiples:** Consta de tres actividades principales (`LoginActivity`, `MainActivity` y la gestión de detalles/enlaces).
* **Paso de Extras:** Se transfieren datos y parámetros entre actividades y adaptadores mediante `Intent.putExtra()`[cite: 10, 11].
* **AlertDialog:** Implementado un diálogo emergente de alerta para mostrar información de la aplicación al usuario[cite: 10, 11].
* **SharedPreferences:** Sistema de control de sesión de usuario integrado en el flujo de acceso[cite: 10, 11].
* **Base de datos SQLite / Estructura de Datos:** Modelado de entidades y gestión local de información[cite: 10, 11].
* **Consumo de API REST:** Integración configurada con Retrofit para la obtención de datos dinámicos[cite: 10, 11].
* **RecyclerView:** Listado eficiente de elementos con gestión de eventos de clic optimizados[cite: 10, 11].

## 🛠️ Tecnologías Utilizadas
* Lenguaje: **Kotlin**
* IDE: **Android Studio**
* Librerías: **Retrofit**, **RecyclerView**, **ConstraintLayout**