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
* Importer le projet dans Eclipse (File > Import > Existing Maven Projects) en selectionnant le dossier du projet précédemment cloné :

![](/images/CaptureExisting.PNG)
* Lancer le fichier Application. java situé dans src/main/java/fr/mim/gamestoreAPI/

## Utilisation

Pour utiliser l'API il est important que le projet soit lancé (via cmd ou IDE).
Une API répond à des requêtes pouvant être émises par différents éléments. Il est possible d'envoyer des requêtes de type GET via un simple navigateur mais pour des types plus complexe il faut utiliser un environnement de développement d'API tel que [Postman](https://www.getpostman.com/).
Chaque requête doit commencer avec l'URI suivant : "localhost:8080/magasin/". [Cette documentation](https://app.swaggerhub.com/apis-docs/loicmolina1/Gamestore-api/1.0.0) contient le détail des différentes requêtes traitables par l'API.

![](/images/CaptureSwagger.PNG)

## Modification du code

Toute modification du code ne peut se faire sur les branches master ou develop. Il faut donc créer une branche locale par fonctionnalité depuis la branche develop.

Pour cela, on s'assure de se placer sur la branche develop avec la commande `git checkout develop`, puis, on utilise la commande `git checkout -b {nom de la branch}` pour créer une branche personnelle. Toute modification se fera sur celle-ci.

Lorsque le dévelopement de la feature est terminé et que tous les fichiés ont été modifiés, il faut envoyer ces modifications vers la branche locale. Exécutez la commande `git add -a` pour prendre en compte les fichiers puis `git commit -m "nom du commit"` pour créer le commit. Il est important de bien respecter les règles conventionnelles de nomage des commit : 
  * Décrire l’intention et non la technique
  * Inclure le numéro de bug/feature
  * Pas de gags
  * N’oubliez pas que le message est là pour être lu

Une fois le code commit créé, il faut utiliser la commande `git push` pour le transmettre. Ces modifications feront l'objet d'un pull request vers la branche develop où le code sera relu et validé par un autre developpeur avant d'être fusionné (merge) vers cette dernière. La branche locale peut maintenant être supprimée. Une nouvelle branche locale tirée de dévelop devra être créée pour chaque nouvelle fearure développée.

A titre indicatif, si le build Jenkins ne comporte aucune erreur sur la branch develop, l'équipe pourra alors merge cette branche vers la branche master afin de produire une nouvelle release.
