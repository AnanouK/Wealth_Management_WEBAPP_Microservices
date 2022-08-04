Cette partie concerne le backend de mon projet

- Création d'une architecture microservices avec java springboot, actuellement 3 micro-services.
- Chaque microservice est indépendant et possède sa propre base de données.
- Deploiment sur kubernetes avec conteneurs docker.

Concernant le code :

- Le microservice User-Service est utilisé pour la partie login et register de l'application et globalement tout ce qui se rapporte aux utilisateurs.
- Le microservice Investment-Service est utilisé pour les différents actifs/passifs de chaque utilisateur.
- Le microservice Statistics-Service est utilisé pour stocker et envoyé la liste de toutes les modifications et changements de chaque actif/passif, permettant ainsi de faire les graphiques pour chaque investissement.

- Les microservices Investment-Service et Statistics-Service ne communiquent pas entre eux afin d'optimiser au maximum les bénéfices de l'architecture microservice, pour cela la base de données du service Statistics contient la base de données Investment (duplication des données). De ce fait Statistics utilise uniquement ses propres données et est donc indépendant.

- Chaque méthode est soumise à un test unitaire via JUnit et Mockito afin d'en vérifier la fonctionnalité (En cours).

Architecture kubernetes du projet :

![1](https://user-images.githubusercontent.com/107629615/181930367-55e41975-5169-4418-959c-7003aa5e58fa.PNG)

Concernant kubernetes

- le backend et le frontend sont sur kubernetes et communiquent via un ingress controleur.
- Chaque microservices posséde 2 replicas (2 pods) afin d'assurer un fonctionnement optimal et une bonne répartition des charges (Sauf les bases de données).
- Chaque microservices possédent ça propre base de données héberger par google kubernetes.


