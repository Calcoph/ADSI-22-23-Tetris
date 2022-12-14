# Entidades
- Usuario: TODO
- Premio: Al terminar una partida, se determina si un usuario ha avanzado en elgún logro. Solo estarán relacionados si han hecho algún progreso (aunque en la interfaz de usuario se muestren todos). Los premios tienen un nombre y una descripción, también tienen un "progresoMax" que determinará cual es el progreso necesario para considerar que se ha obtenido el logro.


# Relaciones
* Progresa: Se considera que un usuario ha adquirido un premio cuando el progreso de la relación es igual o superior al progreso máximo del premio. Cada usuario sólo puede progresar en el mismo premio una sola vez. Pero varios usuarios pueden progresar en el mismo premio y un usuario puede progresar en varios premios.
