# GAMESTORE API
Dans le cadre de l'UE Gestion de Production, une api a dû être réalisé 
afin de mettre en place les différents outils permettant de gérer au 
mieux un projet.

## Installation
Pour pouvoir utiliser cette API, il faudra suivre les étapes suivantes :
* Si vous êtes développeur vous devez installer [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Si vous êtes utilisateur de l'API vous devez simplement installer [JRE](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
* Installer [Maven](https://maven.apache.org/download.cgi) à l'aide de ce [guide d'installation](https://maven.apache.org/install.html)
* Récupérer via un zip ou clonez le projet "git clone https://github.com/loicmolina/gamestore-api.git" dans votre workspace
* Si vous êtes développeur ou que vous souhaitez démarrer le projet via un IDE vous pouvez installer [Eclipse IDE for Enterprise Java Developers (JEE)](https://www.eclipse.org/downloads/packages/)

## Lancement

Via une invite de commande :
* Ouvrir une invite de commande
* Placer vous dans le dossier téléchargé
* Executer la commande suivante : mvn spring-boot:run

Via un IDE (Eclipse par exemple) :
* Importer le projet dans Eclipse (File > Import > Existing Maven Projects) en selectionnant le dossier du projet précédemment cloné
* Lancer le fichier Application. java situé dans src/main/java/fr/mim/gamestoreAPI/

## Utilisation

Pour utiliser l'API il est important que le projet soit lancé (via cmd ou IDE).
Une API répond à des requêtes pouvant être émises par différents éléments. Il est possible d'envoyer des requêtes de type GET via un simple navigateur mais pour des types plus complexe il faut utiliser un environnement de développement d'API tel que [Postman](https://www.getpostman.com/).
Chaque requête doit commencer avec l'URI suivant : "localhost:8080/magasin/". [Cette documentation](https://app.swaggerhub.com/apis-docs/loicmolina1/Gamestore-api/1.0.0) contient le détail des différentes requêtes traitables par l'API.

## Modification du code

Pour plus d'informations, consultez le dossier `/doc` situé à la racine du projet.

Ceci est une phrase