## Backend de l'application web de gestion de patrimoine en Java

- Création d'une architecture microservices avec java springboot et API REST, actuellement 4 micro-services en utilisant la méthodologie TDD.
- Chaque microservice est indépendant et possède sa propre base de données (Si nécessaire).
- Deploiment sur kubernetes avec conteneurs docker.
- CI/CD grâce à Jenkins, Maven, Github, Docker et kubernetes, SonarQube.

## Technologies utilisées
- Java 18
- Springboot 2.7.0 (JPA Hibernate...)
- Maven 4.0.0
- Mysql 8
- JUnit 5.8.2
- Docker 4.10.1
- Kubernetes GKE
- SonarQube 9.6
- Intellij / Visual Studio Code

## Microservices 

- Le microservice User-Service est utilisé pour la partie login et register de l'application et globalement tout ce qui se rapporte aux utilisateurs.
- Le microservice Investment-Service est utilisé pour les différents actifs/passifs de chaque utilisateur.
- Le microservice Statistics-Service est utilisé pour stocker et envoyer la liste de toutes les modifications et changements de chaque actif/passif, permettant ainsi de faire les graphiques pour chaque investissement.
- Le microservice Calculator-Service est utilisé pour effectuer le calcul des intérêts composés en fonction des paramètres de l'utilisateur, il donne aussi une date pour laquelle l'objectif de rendement mensuel est atteint.
- Les microservices Investment-Service et Statistics-Service ne communiquent pas entre eux afin d'optimiser au maximum les bénéfices de l'architecture microservice, pour cela la base de données du service Statistics contient la base de données Investment (duplication des données). De ce fait Statistics utilise uniquement ses propres données et est donc indépendant.

- Chaque méthode est soumise à un test unitaire via JUnit et Mockito afin d'en vérifier la fonctionnalité, en suivant la méthodologie TDD.

## Architecture kubernetes du projet (Obsolète car manque le microservice Calculator) :

![1](https://user-images.githubusercontent.com/107629615/181930367-55e41975-5169-4418-959c-7003aa5e58fa.PNG)

## Concernant kubernetes

- le backend et le frontend sont sur kubernetes et communiquent via un ingress controleur.
- Chaque microservices posséde 2 replicas (2 pods) afin d'assurer un fonctionnement optimal et une bonne répartition des charges (Sauf les bases de données).
- Chaque microservices possédent ça propre base de données héberger par google kubernetes (S'il est nécessaire d'en avoir une).
- Chaque microservices possède un Service en ClusterIp afin d'augmenter la sécurité, seul l'ingress controller est en LoadBalancer.

## Pistes d'améliorations et tâches en cours

- Ajouter Spring Security avec Token JWT (actuellement l'username est stocké dans le localstorage).
- Nouveau microservice permettant de faire une simulation du patrimoine de l'utilisateur en fonction de ses performances actuelles.


