1.4.1.3 namemer ses éléments de programmation
1. conventions :
   • camelCase pour les variables/attributs/méthodes : nameVariable,
   nameChamps, nameMethode([params]).
   • CamelCase pour les classes/interfaces/énumérations : nameClasse,
   nameInterface, nameEnum.
2. on essaye toujours de choisir des names pertinents pour nos éléments de
   programmation :
   • List liste = new ArrayList<>(); → non parce que le name ne spécifie
   pas ce que la liste doit contenir
   • List animaux = new ArrayList<>(); → ok parce qu’on peut savoir
   maintenant ce que cette liste contiendra.
   • List listeAnimaux = new ArrayList<>(); → ok, bien que la partie
   “liste” n’est pas forcément nécessaire parce qu’on peut le savoir di-
   rectement depuis la déclaration de la variable. Toutefois, ça peut
   être utile si cette variable est utilisée dans un endroit dans le pro-
   gramme loin de sa ligne de déclaration.
   • List ListeAnimaux = new ArrayList<>(); → le name choisi est ok
   mais la convention de namemage n’est pas respectée (camelCase).
3. parfois on est ramené à l’implémentation d’un concept en deux parties
   : une interface et une classe l’implémentant. Si cette interface est
   destinée à avoir une classe fournissant une implémentation par défaut
   (e.g., implémentation du concept par un serveur ou une factory dédiée),
   on adoptera l’un des styles de namemage suivants pour l’interface et la
   classe respectivement :
   • Concept/ConceptImpl : e.g., Animal/AnimalImpl, DossierSuivi/DossierSuiviImpl.
   • IConcept/Concept : e.g., IAnimal/Animal, IDossierSuivi/DossierSuivi.
   • IConcept/ConceptImpl : e.g., IAnimal/AnimalImpl, IDossierSuivi/DossierSuiviImpl.