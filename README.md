Primera Infancia Rest
==
Propiedades del proyecto
--
- Desarrollado en Spring-Boot
- Service Oriented Architecture

Configuración 
--
- En el archivo de [configuración](src/main/resources/application.properties) se encuentran los detalles para la conexión con la **DB**.
- Las **imágenes, videos y audios** se alojarán dentro de [static](src/main/resources/static) y en la subcarpeta correspondiente para cada caso.

Documentación
--
###Diagramas  
Dentro de la carpeta [diagrams](documentation/diagrams) se encuentra un diagrama de clases completo .uml. Se recomienda abrir el mismo con IntelliJ IDEA u otro entorno capaz de visualizar archivos con esta extensión.  
En la carpeta [tip](documentation/diagrams/tip) se encuentran 3 imágenes de **diagramas de secuencia** correspondientes a 3 métodos pertenecientes a [TipController](src/main/java/com/ml/primerainfanciarest/controllers/TipController.java). Estos diagramas ayudan a un acercamiento del flujo de todos los servicios, lo que permite deducir el recorrido a seguir desde los demás controladores.  
En la carpeta [dailyTip](documentation/diagrams/dailyTip) se encuentra una imagen de un diagrama de secuencia correspondiente al método get de [DailyTipController](src/main/java/com/ml/primerainfanciarest/controllers/DailyTipController.java) que es un caso especial del flujo del servicio para lograr un mejor entendimiento del funcionamiento.

###Doxygen  
Abrir el archivo [index](documentation/doxygen/html/index.html) para visualizar toda la documentación generada por [doxygen](https://www.doxygen.nl/).  
Para visualizar la documentación imprimible abrir el archivo [refman](documentation/doxygen/rtf/refman.rtf).

###JavaDoc  
Para visualizar la documentación en JavaDoc abrir el archivo [javadoc](documentation/JavaDoc/index.html).