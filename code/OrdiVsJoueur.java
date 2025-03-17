/**
 * S1.01 : Jeu de Marienbad
 * Programme du jeu de Marienbad, une variante du jeu de Nim (version Joueur vs Ordinateur)
 * 
 * @author CHAILLOLEAU Nolan & BOURRE Clovis
 */
class SaeOrdi {

    void principal() {

        int nbLigne; //le nombre de ligne du tableau
        int finPartie = 0; //variable pour la fin de partie
        char rejouer = 'O'; // variable pour rejouer
        int difficulte = 0; //variable pour la difficulté
        char afficheRegle = 'O';
        int[] tabLigneEtBaton = new int[2]; //variable ou la valeur à l'indice 0 est la ligne et l'indice 1 le nombre de baton retiré
        


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


        String nomJoueur = SimpleInput.getString("Saisir votre nom -> ");
        while(rejouer == 'O' || rejouer == 'o'){
            
            String[] ordreJoueur = quiDebute(nomJoueur); //défini qui commence

            do{
                difficulte = SimpleInput.getInt("Choisir la difficulté (1: facile / 2: moyen / 3: difficile) -> ");
            }while(difficulte > 4 || difficulte <= 0);

            do{
                nbLigne = SimpleInput.getInt("Saisir le nombre de ligne voulu (entre 2 et 15) -> ");
            }while(nbLigne < 2 || nbLigne > 15);

            int[] tabJeu = nouveauTableau(nbLigne);
            finPartie = nbLigne * nbLigne;
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

            while(finPartie > 0){ // fin de la partie quand il reste 0 bâtons
                tabLigneEtBaton = new int[2];
                System.out.println();

                affichage(tabJeu);

                if(ordreJoueur[0] == nomJoueur){
                    System.out.println("C'est à ton tour, " + nomJoueur);
                    System.out.println();
                    tabLigneEtBaton = coupJoueur(tabJeu, tabLigneEtBaton);
                    tabJeu = calcul(tabJeu,tabLigneEtBaton[0], tabLigneEtBaton[1]);
                    System.out.println("Vous avez retiré " + tabLigneEtBaton[1] + " bâton(s) à la ligne " + tabLigneEtBaton[0]);
                }

                else{
                    System.out.println("C'est au tour du robot");
                    System.out.println();
                    tabLigneEtBaton = ordi(tabJeu, tabLigneEtBaton, difficulte);
                    tabJeu = calcul(tabJeu,tabLigneEtBaton[0], tabLigneEtBaton[1]);
                    System.out.println("Le robot a retiré " + tabLigneEtBaton[1] + " bâton(s) à la ligne " + tabLigneEtBaton[0]);
                }
                tourJoueur(ordreJoueur);
                
                finPartie -= tabLigneEtBaton[1];
                System.out.println();//Séparation pour le prochain coup 
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

                 
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
     * Méthode qui affiche le jeu (nombre de bâtons par ligne)
     * @param tab le tableau de jeu que l'on veut afficher
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
     * Méthode qui change de joueur
     * @param tab le tableau avec l'ordre des joueurs
     */ 
    void tourJoueur(String[] tab){
        String val_0 = tab[0];
        tab[0] = tab[1];
        tab[1] = val_0;
    }

    /**
     * Permet à l'utilisateur de sélectionner une ligne (la ligne ne doit pas être vide et elle doit exister)
     * @param tab tableau du plateau de jeu 
     * @param tabLigneEtBaton le tableau qui contient la ligne et le nombre de bâtons à retirer (indice 0: ligne, indice 1: nb_bâton)
     * @return retourne la ligne choisie
     */
    int[] coupJoueur(int[] tab, int[] tabLigneEtBaton){
        do{
            tabLigneEtBaton[0] = SimpleInput.getInt("Choisir une ligne non vide : (entre 0 et " + (tab.length-1) + ") -> ");
        }while(tabLigneEtBaton[0] < 0 || tabLigneEtBaton[0] > tab.length-1 || tab[tabLigneEtBaton[0]] == 0 );

        if(tab[tabLigneEtBaton[0]] == 1){
            tabLigneEtBaton[1] = 1;
        }
        else{
            do{
                tabLigneEtBaton[1] = SimpleInput.getInt("Choisir le nombre de batons à retirer (entre 1 et " + tab[tabLigneEtBaton[0]] + ") -> ");
            }while(tabLigneEtBaton[1] <= 0 || tabLigneEtBaton[1] > tab[tabLigneEtBaton[0]]);
        }
        return tabLigneEtBaton;
    }

    /**
     * Méthode qui crée un tableau
     * @param nbLigne le nombre de ligne que l'utilisateur choisi
     * @return le tableau nouvellement créé
     */
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
        System.out.println ();
		System.out.println ("*** testNouveauTableau");
		System.out.println ();

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
     * Méthode qui détermine l'ordre des joueurs
     * @param nomJoueur le nom saisi par le joueur
     * @return un tableau avec l'ordre des joueurs
     */
    String[] quiDebute(String nomJoueur){
        int debut;
        String[] ordreJoueur = new String[2];
        int random = (int)(Math.random()*2);
        do{
            debut = SimpleInput.getInt("Qui commence ? (1: toi / 2: robot / 3: hasard): ");
        }while(debut <= 0 || debut > 3);
        
        if(debut == 1){
            ordreJoueur[0] = nomJoueur;
            ordreJoueur[1] = "le robot";
        }
        else if(debut == 2){
            ordreJoueur[0] = "le robot";
            ordreJoueur[1] = nomJoueur;
        }
        else if(debut==3){ 
            if (random == 0) {
                ordreJoueur[0] = nomJoueur;
                ordreJoueur[1] = "le robot";
            }else {
                ordreJoueur[0] = "le robot";
                ordreJoueur[1] = nomJoueur;
            }
        }
        return ordreJoueur;
    }

    /**
     * Fonction qui affiche un tableau
     * @param t le tableau que l'on veut afficher
     */
    void displayTab(int[] t) {
        if (t.length == 0){
            System.out.print("{}");
        }
        else{
            int i = 0;
            System.out.print("{");
            while (i < t.length - 1) {
                System.out.print(t[i] + ",");
                i = i + 1;
            }
            System.out.print(t[i] + "}");
        }
    }

    /**
     * Méthode qui calcule le nombre de bâtons à retirer pour chaque ligne
     * @param tabJeu le plateau de jeu
     * @param ligne la ligne choisie
     * @param nb_baton le nombre de bâtons choisi
     * @return le plateau de jeu
     */
    int[] calcul(int[]tabJeu,int ligne, int nb_baton){
        tabJeu[ligne] = tabJeu[ligne] - nb_baton;
        return tabJeu;
    }
    
    /**
     * Méthode qui test calcul()
     */
    void testCalcul(){
		System.out.println ();
		System.out.println ("*** testCalcul");
		System.out.println ();

        testCasCalcul(nouveauTableau(5),4,3, new int[] {1,3,5,7,6});
        testCasCalcul(nouveauTableau(2),1,1, new int[] {1,2});
        testCasCalcul(nouveauTableau(15),14,29, new int[] {1,3,5,7,9,11,13,15,17,19,21,23,25,27,0});
    }

    /**
     * Méthode qui teste un appel de calcul
     * @param tabJeu le plateau de jeu
     * @param ligne la ligne choisie
     * @param nb_baton le nombre de bâtons choisi
     * @param result le plateau de jeu après retrait de bâtons
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
    /**
     * Méthode qui définie les coups de l'ordinateur selon la difficulté
     * @param tabJeu le tableau qui contient le plateau de jeu
     * @param tabLigneEtBaton le tableau qui contient la ligne et le nombre de bâtons à retirer (indice 0: ligne, indice 1: nb_bâton)
     * @param difficulte la difficulté de l'ordinateur choisi par le joueur
     * @return la ligne et le nombre de bâtons à retirer choisi par le robot
     */
    int[] ordi(int[] tabJeu, int[] tabLigneEtBaton, int difficulte){
            if(difficulte == 1){
                coupHasard(tabJeu, tabLigneEtBaton); //au hasard
            }

            else if(difficulte == 2){
                int compteurLigneAvecBaton = 0;
                coupHasard(tabJeu, tabLigneEtBaton);

                for(int i = 0; i < tabJeu.length; i++){ //regarde s'il reste juste une ligne non vide
                    if(tabJeu[i] != 0){
                        compteurLigneAvecBaton++; // regardre le nombre de ligne restant avec des bâtons
                    }
                }
                if(compteurLigneAvecBaton == 1){ //si il y a seulement une ligne de vide, il prend tout les bâtons de la ligne
                    tabLigneEtBaton[1] = tabJeu[tabLigneEtBaton[0]];
                }
                
            }

            else if(difficulte == 3){
                
                if(positionGagnante(tabJeu) == true){
                    coupGagnant(tabJeu, tabLigneEtBaton); // si l'ordinateur est en position gagnante (il gagne la partie)
                }
                else{
                    coupHasard(tabJeu, tabLigneEtBaton);//si l'ordinateur est en position perdante
                }	                
            } 
            return tabLigneEtBaton;
    }

    /**
     * Méthode qui fait jouer à l'ordi un coup au hasard
     * @param tabJeu le tableau qui contient le plateau de jeu
     * @param tabLigneEtBaton le tableau qui contient la ligne et le nombre de bâtons à retirer (indice 0: ligne, indice 1: nb_bâton)
     * @return le tableau qui contient la ligne et le nombre de bâtons à retirer
     */
    int[] coupHasard(int[] tabJeu, int[] tabLigneEtBaton){
        
        do{
            tabLigneEtBaton[0] = (int)(Math.random()*tabJeu.length); 
        }while(tabJeu[tabLigneEtBaton[0]] == 0 );
    
        tabLigneEtBaton[1] = 1 + (int)(Math.random()*tabJeu[tabLigneEtBaton[0]]);
        return tabLigneEtBaton;
    }

    /**
     * Méthode qui fait jouer à l'ordi un coup gagnant
     * @param tabJeu le tableau qui contient le plateau de jeu
     * @param tabLigneEtBaton
     * @return le tableau qui contient la ligne et le nombre de bâtons à retirer
     */
    int[] coupGagnant(int[]tabJeu, int[]tabLigneEtBaton){
        int[] tabFictif = copierTableau(tabJeu);
        while(positionGagnante(calcul(tabFictif, tabLigneEtBaton[0], tabLigneEtBaton[1])) == true){
            tabFictif = copierTableau(tabJeu);
            do{
                tabLigneEtBaton[0] = (int)(Math.random()*tabJeu.length); 
            }while(tabJeu[tabLigneEtBaton[0]] == 0 );
            
            tabLigneEtBaton[1] = 1 + (int)(Math.random()*tabJeu[tabLigneEtBaton[0]]);
        }
        return tabLigneEtBaton;
    }

    /**
     * Méthode qui détermine si la position est gagnante ou non
     * @param tabJeu le tableau qui contient le plateau de jeu
     * @return vrai si la tout les nombres de la somme sont paires
     */
	boolean positionGagnante(int[] tabJeu){
		int[] test;
		boolean positionGagnante = false;
		int[] somme = new int[5]; //somme vaut 5 car le nombre maximum de baton est 29 (il faut donc seulement 5 bits)
		for(int i = 0; i < tabJeu.length; i++){
			test = convertitEnBinaire(tabJeu[i]);
			for(int j = 0; j < test.length; j++){
				if(test[j] == 1){
					somme[j] += 1;
				}
			}
		}
		for(int k =0; k < somme.length; k++){
			if(somme[k]%2 != 0){
				positionGagnante = true;
			}
		}
		return positionGagnante;			
	}

    /**
	 * Test de la méthode copierTableau
	 */
	void testPositionGagnante(){
		System.out.println ();
		System.out.println ("*** testPositionGagnante");
		System.out.println ();
		
        testCasPositionGagnante(new int[] {0,1,3,5,7},false);
        testCasPositionGagnante(new int[] {0,0,0,6,8},true);
        testCasPositionGagnante(new int[] {0,0,0,0,1},true);
	}

	/**
	 * Teste un appel de copierTableau
	 * @param tab1 le tableau que l'on veut copier
	 * @param result le tableau qui contient le tableau copié
	 */
	void testCasPositionGagnante(int[] tab1, boolean result){
		// Affichage
		System.out.print("positionGagnante(");
        displayTab(tab1);
        System.out.print(") = " + result + ": ");
		
		// Appel
		boolean resExec = positionGagnante(tab1);
		// Verification
		if (result == resExec){
			System.out.println (" OK");
		} else {
			System.err.println (" ERREUR");
		}
	}

    /**
     * Convertit un nombre décimal en binaire (A tester)
     * @param nb le nombre que l'on veut convertir
     * @return le nombre en binaire dans un tableau
     */
    int[] convertitEnBinaire(int nb){
        int[] conversion = new int[5]; //il faut 5 bits pour représenter 29, le max de bâtons possible sur une ligne
        for (int j = 4; j >= 0 ; j--) {
            if (nb % 2 == 1) {
                conversion[j] = 1;
            } else {
                conversion[j] = 0;
            }
            nb = nb / 2;
        }
        return conversion;
    }

    /**
     * Méthode qui test convertitEnBinaire()
     */
    void testConvertitEnBinaire(){
		System.out.println ();
		System.out.println ("*** testConvertitEnBinaire");
		System.out.println ();

        testCasConvertitEnBinaire(1, new int[] {0,0,0,0,1});
        testCasConvertitEnBinaire(5, new int[] {0,0,1,0,1});
        testCasConvertitEnBinaire(29, new int[] {1,1,1,0,1});
        testCasConvertitEnBinaire(0, new int[] {0,0,0,0,0});  
    }

    /**
     * Méthode qui teste un appel de convertitEnBinaire
     * @param nb le nombre que l'on veut convertir
     * @param result le tableau que doit renvoyer la méthode
     */
    void testCasConvertitEnBinaire(int nb, int[] result){
        // Affichage
		System.out.print("convertitEnBinaire(" + nb + ") = " );
        displayTab(result);
        System.out.print(":");
		
		// Appel
		int[] resExec = convertitEnBinaire(nb);
		// Verification
		if (compareTableau(resExec, result)){
			System.out.println (" OK");
		} else {
			System.err.println (" ERREUR");
		}
    }

    /**
     * Méthode qui permet de copier un tableau 
     * @param tableauOriginal la tableau que l'on veut copier
     * @return le tableau copié
     */
    int[] copierTableau(int[] tableauOriginal) {
		int[] tableauCopie = new int[tableauOriginal.length];

		for (int i = 0; i < tableauOriginal.length; i++) {
			tableauCopie[i] = tableauOriginal[i];
		}
		return tableauCopie;
	}

    /**
	 * Test de la méthode copierTableau
	 */
	void testCopierTableau(){
		System.out.println ();
		System.out.println ("*** testCopierTableau");
		System.out.println ();
		
        testCasCopierTableau(new int[] {1,3,5,7},new int[] {1,3,5,7});
        testCasCopierTableau(new int[] {10,4,5,9},new int[] {10,4,5,9});
        testCasCopierTableau(new int[] {10,4},new int[] {10,4});	
	}

	/**
	 * Teste un appel de copierTableau
	 * @param tab1 le tableau que l'on veut copier
	 * @param result le tableau qui contient le tableau copié
	 */
	void testCasCopierTableau(int[] tab1, int[] result){
		// Affichage
		System.out.print("copierTableau(");
        displayTab(tab1);
        System.out.print(") = ");
        displayTab(result);
        System.out.print(": ");
		
		// Appel
		int[] resExec = copierTableau(tab1);
		// Verification
		if (compareTableau(resExec, result)){
			System.out.println (" OK");
		} else {
			System.err.println (" ERREUR");
		}
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
		System.out.println ("*** testCompareTableau");
		System.out.println ();
		
        testCasCompareTableau(new int[] {1,3,5,7},new int[] {1,3,5,7}, true);
        testCasCompareTableau(new int[] {1,5,7},new int[] {1,4,7}, false);
        testCasCompareTableau(new int[] {},new int[] {}, true);		
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
