# GAMESTORE API
Dans le cadre de l'UE Gestion de Production, une api a dû être réalisé 
afin de mettre en place les différents outils permettant de gérer au 
mieux un projet.

## Installation
Pour pouvoir utiliser cette API, il faudra suivre les étapes suivantes
* Installer [JRE](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
* Installer [Maven](https://maven.apache.org/download.cgi) ([installation](https://maven.apache.org/install.html))
* Récupérer via un zip ou un git clone ce projet

## Lancement

via une invite de commande :
* Ouvrir une invite de commande
* Placer vous dans le dossier téléchargé
* Executer la commande suivante : mvn spring-boot:run

via un IDE :
* Importer le projet
* Lancer le fichier Application. java situé dans src/main/java/fr/mim/gamestoreAPI/

## Utilisation

[Documentation Swagger](https://app.swaggerhub.com/apis-docs/loicmolina1/gamestore-API/0.1)

Chaque requête doit commencer avec l'URI suivant : localhost:8080/magasin/jeux

## Modification du code

Toute modification du code ne peut se faire sur les branches master ou develop. Il faut donc créer une branch par fonctionnalité depuis la branch develop.

Pour cela, on s'assure de se placer tout d'abord sur la branch develop avec la commande git checkout -b 
avec la commande "*git checkout develop*", puis, on utilise la commande "*git checkout -b {nom de la branch}*" pour créer une branche personnelle. Toute modification se fera sur celle-ci.

Une fois le code commit et push, il fera l'objet d'un pull request vers la branch develop où le code sera review et validé par un autre developpeur avec d'être merge vers cette dernière. Une fois la feature validée, on se replace sur la develop et on recréé une branche pour une nouvelle feature.

Si le build Jenkins ne comporte aucune erreur sur la branch develop, il sera merge vers la branch master.
