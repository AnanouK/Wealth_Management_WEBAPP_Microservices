Cette partie concerne le backend de mon projet

- Création d'une architecture microservices avec java springboot, actuellement 3 micro-services
- Chaque microservices est indépendant et posséde sa propre base de données
- Deploiment sur kubernetes avec conteneurs docker

Architecture kubernetes du projet :
......

Concernant kubernetes

- le backend et le frontend sont sur kubernetes et communiquent via un ingress controleur
- Chaque microservices possédent 3 replicas (3 pods) afin d'assurer un fonctionnement optimal et une bonne répartition des charges
- Chaque microservices possédent ça propre base de données héberger par google kubernetes 


