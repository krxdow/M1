# Architectures Distribué via Web service
MilddleWare

Le but est de faire communiqué des applications sur deux machine differente
communication bloquante synchrone

---



- SOAP (Simple Object Acces Protocol)
    - Protocol d'échange inter applications indépendante de tout plate-forme basé sur du mangage XML
    - Le protocole SOAP spécifie exactement la manière de coder un en-tête HTTP et un fichier XML,
    - Le protocole SOAP spécifie également la manière dont le programme appelé renvoie une réponse.
    - Bien que fréquemment associé au protocole HTTP, SOAP prend en charge d'autres protocoles de transport.



[WSDL](https://download.oracle.com/otn_hosted_doc/jdeveloper/1012/web_services/ws_wsdlstructure.html) (Web Service Description Language)
Donne la description au format XML des Web Service en précisant les méthodes pouvant être invoquées, leurs signature et leur point d'accès (URL,port,etc...)
Un fichier WSDL décrit un service Web avec les éléments [suivants](https://www.ibm.com/docs/fr/was/9.0.5?topic=services-wsdl)


portType (Analogs to Java interface)
    PortType is an abstraction part of WSDL.
    An abstract set of operations supported by one or more endpoints.




UDDI (Universal Description, Discovery and Integration)
normalise une solution d'annuaire Distribué de Web Service , permettant à la fois la publication et l'exploration (recherche) de web service

UDDI se comporte lui-même comme un Web service dont les méthodes sont appelées via le protocole SOAP
etape avec annuaire [link](https://youtu.be/39MR3AzU2JI?t=3771)



----
# JAX-WS




JAX B
difference entre attribue et element XML?

integrer JAX b libari
