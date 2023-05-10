# language: fr
Fonctionnalité: Caserne feature
    Scénario: This is a first scenario to test a feature
        Etant donné les feux suivants:
            | Corse             | 2009 |
            | Landes            | 1949 |
            | Charente-Maritime | 1976 |
            | Massif des Maures | 2003 |
        Quand je récupère le plus vieux feu
        Alors 1 feu retourné et le nom est "Landes"