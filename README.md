# Taller-Grupal_CAE

# Sistema CAE 

El sistema CAE tiene como propósito garantizar la equidad en el servicio, procesando cada solicitud en el orden riguroso de su llegada, y proveer una plataforma de trabajo auditable, con plena capacidad de marcha atrás y adelante en las modificaciones del expediente.
# Decisiones de diseño 
## Mecanismo de Deshacer/Rehacer (Undo/Redo)

Uno de los requerimientos más importantes fue agregar el mecanismo de Deshacer/Rehacer, donde cada operación significativa (como agregar o eliminar una nota) se encapsula en un objeto Acción.
La gestión del historial se apoya en dos estructuras de datos de tipo Pila (PilaAcciones), gestionadas en la clase TicketServicio:

•	Pila de Deshacer (undo): Almacena las Acciones ejecutadas. Cuando se llama a deshacer (), la última acción se revierte y se mueve a la pila redo.

•	Pila de Rehacer (redo): Contiene las acciones que han sido deshechas y están listas para ser restauradas. Si se realiza una nueva acción, esta pila se vacía para mantener la coherencia del historial.

## Estructuras de Datos
El diseño del sistema prioriza el rendimiento y el flujo de trabajo lógico mediante el uso estratégico de estructuras de datos enlazadas:

•	Cola de Tickets (ColaTickets): Implementada para asegurar la política FIFO. Las nuevas solicitudes se encolan al final y se atienden desde el frente, garantizando que el ticket con más tiempo de espera sea el siguiente en procesarse.

•	Lista de Notas (ListaNotas): Se utiliza una Lista Enlazada Simple dentro de cada Ticket para almacenar observaciones. Las nuevas notas se insertan eficientemente al inicio de la lista, permitiendo un registro rápido de la actividad reciente.

# Catálogo de Estados 
Cada solicitud (Ticket) transiciona a través de distintos estados que reflejan su posición actual en el ciclo de atención:

•	EN_COLA: Estado predeterminado al crear el ticket. El caso espera a ser tomado por un operador.

•	EN_ATENCION: El ticket ha sido extraído de la cola y está siendo gestionado activamente. Se permite la modificación de notas.

•	COMPLETADOEl servicio ha concluido, y el ticket pasa a un historial inmutable.

## Manejo de errores 
Tenemos las Validaciones rigurosas para asegurar la integridad de los datos y la lógica operativa.

•	Si la ColaTickets está vacía al intentar atenderSiguiente(), el sistema informa que "no hay tickets pendientes".

•	Validación Se asegura que el nombre del estudiante sea válido (no vacío y con longitud mínima) antes de crear un ticket. De igual forma, las opciones de menú y el texto de las notas deben ser válidos.

# Casos bordes 

El sistema está diseñado para mantener su estabilidad y coherencia, gestionando activamente los siguientes escenarios operativos críticos:
-	Al intentar iniciar la atención, el sistema ejecuta una verificación inmediata sobre la estructura de la cola. Si no hay tickets esperando, se notifica explícitamente al operador con el mensaje "no hay tickets pendientes" y la función finaliza.
-	 El sistema está diseñado para manejar un único caso en el estado EN_ATENCION (ticketActual). El flujo de trabajo queda bloqueado en el menuTicket() de ese caso específico hasta que el operador selecciona la opción de "finalizar atencion" (opción 6), que culmina el proceso y limpia el ticketActual.
-	Deshacer (Undo): Antes de intentar revertir una acción, el método deshacer() verifica si la pila de acciones realizadas (undo) se encuentra vacía. Si no hay acciones que revertir, el sistema lo indica con el mensaje "no hay acciones para deshacer".
-	Rehacer (Redo): La función rehacer() comprueba el estado de la pila redo. Si no contiene acciones previamente deshechas, se impide la operación y se muestra el mensaje "no hay acciones para rehacer".
-	La operación eliminarNota solo tiene éxito si encuentra una coincidencia exacta por el texto de la nota dentro de la ListaNotas. Si la nota no es encontrada, la ListaNotas retorna false, y el sistema notifica "sin notas". 
Se imponen validaciones mínimas a las entradas de texto:
-	Nombre de Estudiante: El nombre debe ser válido y contener al menos dos caracteres (esNombreValido) antes de que el ticket sea creado.  El texto de la nota no puede ser nulo o vacío (esNotaValida).
-	Selección de Menú Inválida: El menuTicket() exige que la opción seleccionada sea un número entero, utilizando los métodos esNumeroEntero y esOpcionValida. Cualquier entrada que no cumpla con estos criterios es rechazada.
  
# Guía de ejecución 

1.	Al ejecutar el programa, se le solicitará al usuario la cantidad de tickets a ingresar.
2.	Una vez que se a puesto cuantos tickets desea crear, se pide el Nombre del Estudiante, aplicando una validación para asegurar que el nombre sea válido.
3.	Menú Principal: Tras la creación, el menú principal ofrece:
Opción 1: Atender Siguiente Ticket: Extrae el caso más antiguo (FIFO) de la cola y lo toma para gestión.
Opción 2: Salir: Finaliza el programa.
4.	Al seleccionar "Atender siguiente ticket", el sistema extrae el caso (desencolar), lo asigna a la variable ticketActual, cambia su estado a EN_ATENCION y presenta el submenú de gestión:
5. Agregar Nota: Solicita un texto (ej: "es bonita" en la imagen) y lo añade a la ListaNotas del ticket. Esta acción se registra en la pila undo y limpia la pila redo.
6. Eliminar Nota: Requiere el texto exacto de la nota a borrar. Si se elimina con éxito, se registra en undo y limpia redo. Si la nota no existe, el sistema alerta "no se encontro la nota".
7. Mostrar Notas: Imprime los detalles del ticket (ID, Estudiante, Estado) y lista todas las notas registradas, recorriendo la ListaNotas enlazada.
8. Deshacer (Undo): Revierte la última acción registrada en la pila undo. Si revierte una adición, la nota se elimina; si revierte una eliminación, la nota se restaura. La acción se mueve a la pila redo.
9. Rehacer (Redo)Restaura la última acción revertida que se encuentre en la pila redo. La acción se mueve de vuelta a la pila undo.
10. Finalizar AtenciónCierra el ciclo de atención: el estado del ticket pasa a COMPLETADO y el ticketActual se libera (null), devolviendo el control al Menú Principal.
                                        <img width="500" height="161" alt="image" src="https://github.com/user-attachments/assets/e1d90eef-3aba-4090-adb9-9b767b5f02b9" />

 Equipo de desarrollo:
 - Soledad Buri
 - Victor
 - Ana Panamito
 - Martina Maldonado 
    

