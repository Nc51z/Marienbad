/**
 * S1.01 : Jeu de Marienbad
 * Programme du jeu de Marienbad, une variante du jeu de Nim (version Joueur vs Joueur)
 * 
 * @author CHAILLOLEAU Nolan & BOURRE Clovis
 */
class Sae {

    void principal() {
		
        int nbLigne;
        int finPartie = 0;
        char rejouer = 'O';
        int[] tabLigneEtBaton = new int[2];
        char afficheRegle = 'O';
        String[] ordreJoueur = new String[2];
        
        System.out.println("\r\n" + //
                        "\r\n" + //
                        "\t\t\t    __              _        _____         _         _         _ \r\n" + //
                        "\t\t\t __|  |___ _ _    _| |_ _   |     |___ ___|_|___ ___| |_ ___ _| |\r\n" + //
                        "\t\t\t|  |  | -_| | |  | . | | |  | | | | .'|  _| | -_|   | . | .'| . |\r\n" + //
                        "\t\t\t|_____|___|___|  |___|___|  |_|_|_|__,|_| |_|___|_|_|___|__,|___|\r\n" + //
                        "                                                                 \r\n" + //
                        "\r\n" + //
                        " ");
        afficheRegle = SimpleInput.getChar("Connaissez-vous les règles du jeu ? (O/N) -> ");

        if (afficheRegle == 'N' || afficheRegle == 'n'){
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.println();
            System.out.println("    1. Le but du jeu est de retirer la dernière allumette ");
            System.out.println("    2. A chaque tour, le retrait d'allumette(s) se fait sur une seule ligne");
            System.out.println();
            System.out.println("\t\t\t\tBonne chance ! ;)");
            System.out.println();
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        }


        ordreJoueur[0] = SimpleInput.getString("Saisir le nom du joueur 1 -> ");
        ordreJoueur[1] = SimpleInput.getString("Saisir le nom du joueur 2 -> ");
        while(rejouer == 'O'){
            do{
                nbLigne = SimpleInput.getInt("Saisir le nombre de ligne voulu (entre 2 et 15) -> ");
            }while(nbLigne < 2 || nbLigne > 15);

            int[] tabJeu = nouveauTableau(nbLigne);
            finPartie = nbLigne * nbLigne;
            
            while(finPartie > 0){ //que des fonctions (coeur de la partie)
                System.out.println();
                System.out.println("C'est au tour du joueur: " + ordreJoueur[0]);
                System.out.println();
                
                affichage(tabJeu);

                tabLigneEtBaton = coupJoueur(tabJeu, tabLigneEtBaton);
                tabJeu = calcul(tabJeu,tabLigneEtBaton[0], tabLigneEtBaton[1]);
                System.out.println( ordreJoueur[0] + " a retiré " + tabLigneEtBaton[1] + " bâton(s) à la ligne: " + tabLigneEtBaton[0]);
                tourJoueur(ordreJoueur);
                System.out.println();//Séparation pour le jeu du prochain joueur 
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

                finPartie -= tabLigneEtBaton[1]; 
            }
            System.out.println();
            System.out.println("\t\t\t Le gagnant est: "+ ordreJoueur[1]);
            System.out.println();
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.println();
            rejouer = SimpleInput.getChar("Voulez-vous rejouer une partie ? (O/N) -> ");
        }
    }

    /**
     * Méthode qui affiche le plateau jeu (nombre de bâtons)
     * @param tab le tableau du début
     */
    void affichage(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + ": \t");
            for (int j = 0; j < tab[i]; j++) {
                System.out.print("| ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /**
     * Méthode qui change le tour du joueur
     * @param tab le tableau avec l'ordre des joueurs
     */ 
    void tourJoueur(String[] tab){
            String val_0 = tab[0];
            tab[0] = tab[1];
            tab[1] = val_0;
    }
    /**
     * Permet à l'utilisateur de sélectionner une ligne (la ligne ne doit pas être vide et elle doit exister)
     * @param tabJeu le tableau qui contient le plateau de jeu
     * @param tabLigneEtBaton le tableau qui contient la ligne et le nombre de bâtons à retirer (indice 0: ligne, indice 1: nb_bâton)
     * @return la ligne et le nombre de bâtons choisi par le joueur
     */
    int[] coupJoueur(int[] tabJeu, int[] tabLigneEtBaton){
        do{
            tabLigneEtBaton[0] = SimpleInput.getInt("Choisir une ligne non vide : (entre 0 et " + (tabJeu.length-1) + ") -> ");
        }while(tabLigneEtBaton[0] < 0 || tabLigneEtBaton[0] > tabJeu.length-1 || tabJeu[tabLigneEtBaton[0]] == 0 );

        if(tabJeu[tabLigneEtBaton[0]] == 1){
            tabLigneEtBaton[1] = 1;
        }
        else{
			do{
				tabLigneEtBaton[1] = SimpleInput.getInt("Choisir le nombre de batons à retirer (entre 1 et " + tabJeu[tabLigneEtBaton[0]] + ") -> ");
			}while(tabLigneEtBaton[1] <= 0 || tabLigneEtBaton[1] > tabJeu[tabLigneEtBaton[0]]);
		}
        return tabLigneEtBaton;
    }

    /**
     * 
     * @param tabJeu le tableau qui contient le plateau de jeu
     * @param ligne la ligne choisie par le jouer
     * @param nb_baton le nombre de bâtons choisi par le joueur
     * @return le tableau de jeu modifié après le retrait de baton
     */
    int[] calcul(int[]tabJeu,int ligne, int nb_baton){
        tabJeu[ligne] = tabJeu[ligne] - nb_baton;
        return tabJeu;
    }

    /**
     * Méthode qui test calcul()
     */
    void testCalcul(){
        testCasCalcul(nouveauTableau(5),4,3, new int[] {1,3,5,7,6});
        testCasCalcul(nouveauTableau(2),1,1, new int[] {1,2});
        testCasCalcul(nouveauTableau(15),14,29, new int[] {1,3,5,7,9,11,13,15,17,19,21,23,25,27,0});
    }
    /**
     * Méthode qui teste un appel de calcul
     * @param tabJeu le tableau qui contient le plateau de jeu
     * @param ligne la ligne choisie par le jouer
     * @param nb_baton le nombre de bâtons choisi par le joueur
     * @param result renvoie la tableau de jeu après retrait des bâtons
     */
    void testCasCalcul(int[]tabJeu,int ligne, int nb_baton, int[] result){
        // Affichage
		System.out.print("calcul(");
        displayTab(tabJeu);
		System.out.print( ", " + ligne + ", " + nb_baton + ") = " );
        displayTab(result);
        System.out.print(":");
		
		// Appel
		int[] resExec = calcul(tabJeu, ligne, nb_baton);
		// Verification
		if (compareTableau(resExec, result)){
			System.out.println (" OK");
		} else {
			System.err.println (" ERREUR");
		}
	
    }
    
    int[] nouveauTableau(int nbLigne){
        int[] tabJeu = new int[nbLigne];
            
        for (int i = 0; i < nbLigne; i++) {
           tabJeu[i] = 2 * i + 1;
        }
        return tabJeu;
    }

    /**
     * Méthode qui test nouveauTableau()
     */
    void testNouveauTableau(){
        testCasNouveauTableau(2, new int[] {1,3});
        testCasNouveauTableau(15, new int[] {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29});
        testCasNouveauTableau(5, new int[] {1,3,5,7,9});
    }
    /**
     * Méthode qui teste un appel de nouveauTableau
     */
    void testCasNouveauTableau(int nbLigne, int[] result){
        // Affichage
		System.out.print("nouveauTableau(" + nbLigne + ") = " );
        displayTab(result);
        System.out.print(":");
		
		// Appel
		int[] resExec = nouveauTableau(nbLigne);
		// Verification
		if (compareTableau(resExec, result)){
			System.out.println (" OK");
		} else {
			System.err.println (" ERREUR");
		}
	
    }

    /**
     * fonction qui affiche un tableau
     * @param t le tableau que l'on veut afficher
     */
    void displayTab(int[] t) {

        int i = 0;
        System.out.print("{");
        while (i < t.length - 1) {
            System.out.print(t[i] + ",");
            i = i + 1;
        }
        System.out.print(t[i] + "}");
    }


    /**
     * Méthode qui permet de comparer 2 tableaux
	 * @param tab1 le premier tableau que l'on compare
     * @param tab2 le deuxième tableau que l'on compare
     * @return resultat de la comparaison, vrai si les deux tableaux sont identiques
     */
    boolean compareTableau (int[] tab1, int[] tab2){
        int i = 0;
        boolean idem = true;
        while ( i < tab1.length && idem == true){
            if (tab1[i] != tab2[i]){
                idem = false;
            }
            i = i + 1;
        }
        return idem;
    }
    
    /**
	 * Test de la méthode compareTableau
	 */
	void testCompareTableau(){
		System.out.println ();
		System.out.println ("*** testCompareTableau)");
		System.out.println ();
		
        testCasCompareTableau(new int[] {1,3,5,7},new int[] {1,3,5,7}, true);
		
	}
	/**
	 * Teste un appel de compareTableau
	 * @param tab1 le premier tableau que l'on compare
     * @param tab2 le deuxième tableau que l'on compare
	 * @param result vaut vrai si les deux tableaux sont identiques
	 */
	void testCasCompareTableau(int[] tab1, int[] tab2, boolean result){
		// Affichage
		System.out.print("compareTableau(");
        displayTab(tab1);
		System.out.print(", ");
        displayTab(tab2);
        System.out.print(") = " + result + ": ");
		
		// Appel
		boolean resExec = compareTableau(tab1, tab2);
		// Verification
		if (resExec == result){
			System.out.println (" OK");
		} else {
			System.err.println (" ERREUR");
		}
	}
}
