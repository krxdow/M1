import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            //same port as server, search registry local
            Registry registry = LocateRegistry.getRegistry(1105);
            //search in class in regisry


            //question petit 4
            // stub de l'implementation de l'interface Animal, pas la liste des animaux
            IAnimal stub = (IAnimal) registry.lookup("Animal");

            //stub.setEspeseObj("Canis lupus", Integer.parseInt("20 ans"));  //


            IAnimal animalCopy = stub.getAnimalObj();

            animalCopy.setName("zack");
            animalCopy.setRace("chien");
            animalCopy.setNameMaster("god");

            animalCopy.setEspeseObj("canus lupus",15);
            String rep= animalCopy.getEspeseInfo();

            System.out.println(rep);

            /*IEspece especeCopy = (IEspece) stub.getEspeseObj();
            especeCopy.setname("canu lupus");
            especeCopy.setlifeExpectancy(12);
*/

            ICabinetVeterinaire stubcab = (ICabinetVeterinaire) registry.lookup("Cabinet");





            //passage par valeur de espese pas de modification directe de lobjet

            IEspece reponse =stub.getEspeseObj();


           // System.out.println(reponse );

            ICabinetVeterinaire stubCab = (ICabinetVeterinaire) registry.lookup("CabinetVet");

            stub.setName("zack");
            stub.setNameMaster("Ludo");
            stub.setRace("chien");
            stub.setSuivi("c'est un chien cool");

            stub.getNameMaster();



            stubCab.addPatient();
            // stubCab.addPatient(( (Animal) stub);





            //call remote methode
            //stub.printAnimal();

          //String reponse = stub.test();
            System.out.println("response du serveur"+ reponse);



        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
