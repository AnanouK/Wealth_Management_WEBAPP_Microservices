Cette partie concerne le backend de mon projet

- Création d'une architecture microservices avec java springboot, actuellement 3 micro-services
- Chaque microservices est indépendant et posséde sa propre base de données
- Deploiment sur kubernetes avec conteneurs docker

Concernant le code :

- Le microservice User-Service est utilisé pour la partie login et register de l'application et globalement tout se qui se rapporte aux utilisateurs.
- Le microservice Investment-Service est utilisé pour les différents actifs/passifs de chaque utilisateur.
- Le microservice Statistics-Service est utilisé pour stocker et envoyé la liste de toutes les modifications et changements de chaque actifs/passifs, permettant ainsi de faire les graphiques pour chaque investissements.

- Les microservices Investment-Service et Statistics-Service ne communiquent pas entre eux afin d'optimiser au maximum les bénéfices de l'architecture microservice, pour cela la base de données du service Statistics contient la base de données Investment (duplication des données). De ce fait Statistics utilise uniquement ses propres données et est donc indépendant.

- Chaque méthodes est soumise à un test unitaire via JUnit et Mockito afin d'en vérifier la fonctionnalité.

Architecture kubernetes du projet :
......

Concernant kubernetes

- le backend et le frontend sont sur kubernetes et communiquent via un ingress controleur
- Chaque microservices possédent 3 replicas (3 pods) afin d'assurer un fonctionnement optimal et une bonne répartition des charges
- Chaque microservices possédent ça propre base de données héberger par google kubernetes 


