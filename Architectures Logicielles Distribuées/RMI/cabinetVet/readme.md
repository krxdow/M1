# Architectures Distribuées
## TP1: Java RMI, Cabinet Vétérinaire
###  Une premiere version simple
### Projet rendu Par AMAH GNIMDOU RICHARD et Fanus Ludovic

Le code source du projet se trouve sur ce [Dépôt] (https://github.com/krxdow/M1/tree/master/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinetVet)

#### 1. Une premiere version simple

L'ensemble du projet est fait à l'aide de l'environment integré IntelliJ

```shell
.
├── client
│   ├── client.iml
│   └── src
│       └── Client.java
├── common
│   ├── common.iml
│   └── src
│       ├── Espece.java
│       ├── IAnimal.java
│       ├── ICabinetVeterinaire.java
│       ├── IEspece.java
│       └── ISuiviAnimal.java
├── readme.md
└── server
    ├── security.policy
    ├── server.iml
    └── src
        ├── Animal.java
        ├── CabinnetVeterinaireImpl.java
        ├── server.iml
        ├── Server.java
        └── SuiviAnimal.java
```
Dans un but de compartimenter le projet, le **server**, le **client** et **common** sont des modules.  
 **server** et client ont accesé à au module **common**, les modules **server** et **client** ne se voient pas

Configuration
![](misc/Screenshot_20221017_132338.png)


#### 2. Gestionnaire de securité
L'objet distant distribué est la classe Animal , donc il faut liée cette classe à un security manager
Sans security manager, il faut obligatoirement mettre à la disposition du serveur toutes les classes dont il aura besoin (Elles doivent être dans le CLASSPATH du serveur). Avec un security manager, le serveur peut charger dynamiquement certaines classes.

La gestion des droits se fait à traver le ficher security.policy
```
grant {
  permission java.security.AllPermission;
};
```
Une premier facon via le code avec l'objet RMISecurityManager
```java
import java.rmi.RMISecurityManager;

public class Animal extends UnicastRemoteObject implements IAnimal{
    try {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.setProperty("java.security.policy", "security.policy");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```
Ou ll est aussi possible d'activer un security manager en utilisant simplement l'option -Djava.security.manager de la JVM. 
```shell
java -Djava.security.manager -Djava.security.policy=security.policy 
```



#### 3. Class Suivi
On creer une interface et son implementation 

#### 4. 
L'objet Espese est pas un objets distants, pas envoyer par le serveur mais le client y accede donc ici l'interface Espese exentende serialization 
``` java
public interface IEspece extends Serializable {...}
```
L'objet Espese est convertir en un flux d'octets et ensuite réassemble les octets en une copie identique de l'objet d'origine. 

```java
public  class Client { 
    IAnimal stub = (IAnimal) registry.lookup("Animal");
    stub.setName("zack");
    Espece especeCopy = new Espece("canus lupus", 15);
    stub.setEspeseObj(especeCopy.getname(),especeCopy.getlifeExpectancy());
}
```



