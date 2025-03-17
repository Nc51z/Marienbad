class TestMethode{
	void principal(){
		testCalcul();
		testCompareTableau();
		testCopierTableau();
		testNouveauTableau();
		testPositionGagnante();
		testConvertitEnBinaire();
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
}    
