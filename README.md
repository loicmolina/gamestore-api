# GAMESTORE API
Dans le cadre de l'UE Gestion de Production, une api a dû être réalisé 
afin de mettre en place les différents outils permettant de gérer au 
mieux un projet.

## Installation
Pour pouvoir utiliser cette API, il faudra suivre les étapes suivantes
* S'assurer d'avoir installer un [JRE](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) et [Maven](https://maven.apache.org/install.html)
* Télécharger via un zip ou un git clone ce projet

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

Les jeux se composent des éléments suivants:

### Structure d'un jeu :
* Un nom
* Une date de sortie au format jj/mm/aaaa
* Un developpeur
* Un premier genre
* Un second genre

Les différents attributs sont tous optionnels. Un ID leur sera affecté leur sera affecté lors de leur entrée dans la base.

Voici les différentes requêtes utilisables de cette API :

### Get
* localhost:8080/magasin/jeux : retourne tout les jeux disponibles dans le magasin
* localhost:8080/magasin/jeux/[ID] : retourne tout les jeux disponibles dans le magasin
* localhost:8080/magasin/recherche/[mot] : recherche les jeux du magasin portant un nom contenant le mot indiqué dans l'URI

### Post
* localhost:8080/magasin/jeux + Requête JSON : ajoute un nouveau jeu dans le magasin 

### Put
* localhost:8080/magasin/jeux/[ID] + Requête JSON : modifie un jeu portant l'id inscrit dans le magasin

### Delete
* localhost:8080/magasin/jeux : supprime tout les jeux disponibles dans le magasin
* localhost:8080/magasin/jeux/[ID] : supprime le jeu portant l'id inscrit du magasin
