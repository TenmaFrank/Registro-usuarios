# Evaluación Desarrollador Android

Este proyecto Android tiene la finalidad de ser una muestra de los conocimientos que tengo como desarrollador Android, es una aplicación que asemeja a una aplicación real con consumos a API, haciendo uso de elementos comunes que son esenciales para las aplicaciones actuales como lo son; activities, fragments, recyclerView, retrofit, corrutines.
Se hace uso de la [API gratuita](https://reqres.in), para el manejo de usuarios a travez de el consumo de sus recursos.


# Archivos
Con la idea de utilizar una arquitectura  MVVM, los archivos fueron acomodados en los directorios.
**view**: Contiene clases que definen el comportamiento de las vistas.
**viewmodel**: Contiene las classes con las ViewModels para cada vista.
**model**:  Contiene los archivos Nesesarios para el manejo de los datos, como modelos e interfazes para consumo de datos.
Se usan 2 activities para los 2 flujos que maneja la aplicación

## Caracteristicas de la aplicación

- Versión minima de Android es 5.0.
- El Lenguaje utilizado es Kotlin.
- Arquitectura MVVM.
- Uso de Databinding.
- Uso de bibliotecas internas y externas.
- Singleton.
- Rotacion de pantalla sin perder información.
- validación en los inputs para que no estén vacios o contengan emojis.
- Se notifican los errores de consumo a travéz de un toast.

## Activity inicio de sesión
1. Fragmento Login:
	* Solicita el correo y contraseña.
	* El boton Registrar envia al fragmento de registro.
	* El boton de Ingresar consume la API en su recurso [ /api/login](https://reqres.in/api/login).
	* Si el consumo es existoso continua con el Activity de Usuarios y guarda datos de manera local con sharedPreferences.
	* Si el consumo NO es exitoso muestra un mensaje a travéz de un toast.
2. Fragmento Registro:
	* Solicita Correo, Contraseña y nombre, si el usuario ya había llenado alguno en el fragmento login, se llena automaticamente.
	* El boton de Registrar Consume la API en su recurso  [/api/register](https://reqres.in/api/register) .
	* Si el consumo es existoso continua con el Activity de Usuarios y guarda datos de manera local con sharedPreferences.
	* Si el consumo NO es exitoso muestra un mensaje a travéz de un toast.

## Activity usuarios
1. Fragmento Lista:
	* Muestra nombre o correo en el Appbar.
	* Consume la API en su recurso [ /api/users?page](https://reqres.in/api/users?page=2) y muestra un recyclerView con los datos e imagen de los usuarios.
	* Cada item de usuario tiene botones de borrar y editar.
	* El boton Crear Usuario envia al fragmento Crear/editar.
	* El boton de Cerrar Sesión cierra el activity.
2. Fragmento Crear/Editar:
	* El formulario Solicita Nombre, Apellido, Correo y Trabajo.
	* El boton Guardar Consume la API en su recurso [/api/users](https://reqres.in/api/users)
	* Si el consumo es exitoso muestra la fecha de creación en un toast
> A la fecha de  04 de diciembre falta implementar mas funcionalidades
