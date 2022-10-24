# Architectures Distribuées
## TP1: Java RMI, Cabinet Vétérinaire
Le code source du projet se trouve sur ce [Dépôt] (https://github.com/krxdow/M1/tree/master/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinetVet)
### Projet rendu Par AMAH GNIMDOU RICHARD et Fanus Ludovic

# Instruction de la compilation et lancement 

Simplement, télécharger les fichiers .jar et exécuter comme ci-dessous
````shell
java -jar server.jar
java -jar client.jar
````
Autrement Se placer dans le dossier racine du projet **cabinet** et exécuter les commandes suivantes


```shell
# compilation client
javac server/src/*.java -sourcepath common/src/ -d classesServer
```

```shell
# compilation client
javac client/src/*.java -sourcepath common/src/ -d classesClient
```

```shell
# lancement du server
cd classesServer
java Server Server.class
```

```shell
# lancement du client
cd classesClient
java Client Client.class
```




## **Une premiere version simple**
#### 1. Version de base

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

Le RMISecurityManager applique la politique de sécurité pour les classes qui sont chargées en tant que stubs pour les objets distants,(ici dans notre cas la classe Animal )en remplaçant toutes les méthodes de contrôle d'accès pertinentes du SecurityManager. 
Par défaut, les objets stub ne sont autorisés qu'à effectuer des opérations de définition de classe et d'accès aux classe

La gestion des droits se fait à traver le fichier [security.policy](https://github.com/krxdow/M1/blob/433a467fe5d48ee86732bef34bc78a34d0f7f553/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinet/server/src/security.policy)
```
grant {
  permission java.security.AllPermission;
};
```
Une premiere facon via le code avec l’objet RMISecurityManager  
petite astuse, la methode setProperty, accepte seulement le chemin absolu, afin que le projet soit exportable, les methodes la classe Paths sont utilisée.
```java
import java.nio.file.Paths;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {

 public static void main(String[] args) {

  //Chemain absolu du fichier
  Path policyFilePathURI = Paths.get("server/src/security.policy").toAbsolutePath().normalize();
  System.setProperty("java.security.policy", policyFilePathURI.toString());
  
  try {
   if (System.getSecurityManager() == null) {
    System.setSecurityManager(new RMISecurityManager());
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}
```

ll est aussi possible d'activer un security manager en utilisant simplement l'option -Djava.security.manager de la JVM. 
```shell
cd classesServer
java -Djava.security.manager -Djava.security.policy=../server/src/security.policy Server Server.class
```
via IntelliJ
![](misc/Screenshot_20221017_144018.png)


#### 3. Class Suivi
On crée une [interface](https://github.com/krxdow/M1/blob/433a467fe5d48ee86732bef34bc78a34d0f7f553/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinet/server/src/SuiviAnimal.java) et son [implementation](https://github.com/krxdow/M1/blob/433a467fe5d48ee86732bef34bc78a34d0f7f553/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinet/common/src/ISuiviAnimal.java) 

```java
public interface ISuiviAnimal extends Remote {
    String getSuivi() throws RemoteException;
    void setSuivi(String s)  throws RemoteException;
    }
```

```java
public class SuiviAnimal extends UnicastRemoteObject implements ISuiviAnimal {
    private String suivi;
    public SuiviAnimal() throws RemoteException {}
    public SuiviAnimal(String suivi) throws RemoteException {this.suivi = suivi;}
    public String getSuivi() throws RemoteException {return this.suivi;}
    public void setSuivi(String suivi) throws RemoteException {this.suivi = suivi;}
}
```




#### 4. Copies Espese
[Interface](https://github.com/krxdow/M1/blob/be6eec7b1d84a9a2c9f76ca0e686c72aa7950bb7/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinetVet/common/src/IEspece.java) et [l’Implementation](https://github.com/krxdow/M1/blob/be6eec7b1d84a9a2c9f76ca0e686c72aa7950bb7/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinetVet/common/src/Espece.java)



L'objet **Espese** (placé dans le module **common** ) est passé par valeur et nom par reference pour cela les valeurs doit être de type primitife et l’objet doit implementé l'interface sérialisable.
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
## **Classe CabinetVeterinaire**

[Interface](https://github.com/krxdow/M1/blob/be6eec7b1d84a9a2c9f76ca0e686c72aa7950bb7/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinetVet/common/src/ICabinetVeterinaire.java)  
[L’Implementation](https://github.com/krxdow/M1/blob/be6eec7b1d84a9a2c9f76ca0e686c72aa7950bb7/Architectures%20Logicielles%20Distribu%C3%A9es/RMI/cabinetVet/server/src/CabinnetVeterinaireImpl.java)

## **Creation de Patient**

## **Télechargment Du Codebase**
La mise en place d’un CodeBase permet de charger dynamiquement les classes

```java
public class Server {
 public Server() {
 }

 public static void main(String[] args) {
  try {

   URI commonDirectoryPath = Paths.get("../common/src").toUri().normalize();
   System.setProperty("java.rmi.server.codebase", commonDirectoryPath.toString());
  }
  ...
 }
```

## **Gestion Des Alerte**

