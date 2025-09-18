# Bank Management Console (Java)

&gt; Une application console Java pour gérer des comptes bancaires numériques de manière simple et sécurisée.

---

## 📌 Description

Cette application est un **projet pédagogique** conçu pour initier aux concepts de la **programmation orientée objet en Java** tout en appliquant les **bonnes pratiques** de développement.  
Elle permet aux utilisateurs de :

- Créer et gérer des comptes bancaires
- Effectuer des **dépôts**, **retraits** et **virements**
- Consulter leur **solde** et **l’historique des transactions**
- Mettre à jour leur **profil** et **changer leur mot de passe**

---

## 🎯 Objectifs pédagogiques

- ✅ Mettre en œuvre la **POO** (entités, services, repositories, séparation des responsabilités)
- ✅ Manipuler des montants financiers avec **BigDecimal** (précision, 2 décimales)
- ✅ Implémenter une **authentification basique** (email + mot de passe)
- ✅ Appliquer des **règles métier bancaires** (soldes, virements, clôture)
- ✅ Produire un **code structuré, lisible, testable**

---

## 🧩 Fonctionnalités

### 🔐 Authentification & Profil
- Inscription avec email unique et mot de passe ≥ 6 caractères
- Connexion / Déconnexion
- Mise à jour du profil (email, adresse)
- Changement de mot de passe

### 💰 Comptes bancaires
- Création de compte avec identifiant lisible (ex: `BK-XXXX-1234`)
- Liste des comptes de l’utilisateur connecté
- Consultation du solde
- **Clôture** de compte (si solde = 0 et propriétaire)

### 💸 Transactions
- **Dépôt** : augmentation du solde
- **Retrait** : diminution si fonds suffisants
- **Virement interne** : entre comptes existants ou vers compte externe
- **Historique** : trié par date, avec détails (type, montant, description, contrepartie)

---

## 🧪 Règles métier

| Règle | Description |
|------|-------------|
| **Montants** | `BigDecimal` avec 2 décimales max, montant &gt; 0 |
| **Retrait/Virement** | Refusés si solde insuffisant |
| **Clôture** | Uniquement si solde = 0 et propriétaire |
| **Propriété** | Un utilisateur ne peut agir que sur ses propres comptes |
| **Traçabilité** | Toute opération crée une transaction immuable |
| **Inactivité** | Opérations refusées sur compte inactif |
| **Email unique** | L’inscription échoue si l’email existe déjà |

---

## 🧱 Architecture
```
org.bankManagement
├── Domain
│   ├── User
│   ├── Account
│   └── Transaction
├── Repository
│   ├── UserRepository
│   ├── AccountRepository
│   └── TransactionRepository
├── RepositoryMemory
│   ├── InMemoryUserRepository
│   ├── InMemoryAccountRepository
│   └── InMemoryTransactionRepository
├── Service
│   ├── AuthService
│   ├── AccountService
│   └── TransactionService
└── Main
```

---

## 🧭 Parcours utilisateur (console)

### 🔓 Non connecté
- Register
- Login
- Exit

### 🔒 Connecté
- Create account
- List my accounts
- Deposit
- Withdraw
- Transfer
- Transaction history
- Update profile
- Change password
- Close account
- Logout
- Exit

---

## 🧪 Scénarios de test suggérés

### Utilisateurs
- `alice@example.com` (mot de passe ≥ 8)
- `bob@example.com` (mot de passe ≥ 8)

### Scénarios
1. **Alice** :
    - Crée 2 comptes (`A1`, `A2`)
    - Dépose 500 sur `A1`
    - Transfère 200 de `A1` vers `A2`
    - Retire 50 sur `A2`
    - Tente de clôturer `A2` (si solde = 0 → succès)

2. **Bob** :
    - Crée 1 compte (`B1`)
    - Tente un retrait de 100 → échec (solde 0)
    - Dépose 100
    - Retire 80 → succès

---

## 🧰 Prérequis

- Java 17+
- Aucune dépendance externe (tout est en mémoire)

---

## 🚀 Lancement

```bash
git clone https://github.com/ka-amina/bank-management/
cd bank-management-console
