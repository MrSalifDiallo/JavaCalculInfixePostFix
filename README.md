# Java Calcul Infixe-Postfix 🌟

## Description

Ce projet consiste en une **application Java en ligne de commande** permettant de :

✅ **Convertir une expression en notation Infixe en Postfixe**.  
✅ **Évaluer une expression Postfixe** afin d'en obtenir le résultat.  

Ce programme illustre l'utilisation de structures de données fondamentales, notamment les piles (stack), afin de traiter des expressions mathématiques de manière efficace.

---

## Langage

- **Java**

---

## Fonctionnement

1️⃣ L’utilisateur saisit une expression en **infixe** (comme `3 + 4 * 2`)  
2️⃣ Le programme la **convertit en une expression postfixe** (comme `3 4 2 * +`)  
3️⃣ Une fois la conversion faite, le programme **calcule le résultat de l’expression postfixe**  
4️⃣ Le résultat est affiché à l’utilisateur

---

## Fichiers

- **src/** : le code source Java
- **Java Calcul Infixe-Postfix.pdf** : le rapport PDF expliquant le fonctionnement de l’algorithme, le cas d’étude, et les détails d’implémentation

---

## Utilisation

1️⃣ Compile le programme :
```bash
javac src/Main.java
```
2️⃣ Exécute-le :
```bash
java src.Main
```
3️⃣ Tape une expression en notation infixe, par ex :
```bash
( 3 + 4 ) * 2
```
4️⃣ Le programme générera :
	•	La notation postfixe
	•	Le résultat de l’évaluation

## Algorithm
	•	Conversion Infixe → Postfixe:
        Utilise une pile afin de gérer les opérateurs en fonction de leur priorité.
	•	Évaluation Postfixe:
    Utilise une pile d’opérandes.
    On empile lorsqu’on voit un élément, et on dépile afin d’effectuer l’opération lorsqu’on rencontre un opérateur.

## Les Auteurs
    Merci de consulter le fichier pdf [Rapport Projet](./Projet Java.pdf) pour plus d’informations.