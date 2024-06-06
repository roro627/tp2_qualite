import java.util.ArrayList;

class Plateau{
    private ArrayList<Piece> pieces;

    public Plateau() throws ErreurCoordonneesException , CouleurPieceException, CaseOccupeeException
	{
	pieces = new ArrayList<Piece>();
	this.ajouterPiece(new Tour('B', new Position("A1")));
	this.ajouterPiece(new Cavalier('B', new Position("B1")));
	this.ajouterPiece(new Fou('B', new Position("C1")));
	this.ajouterPiece(new Dame('B', new Position("D1")));
	this.ajouterPiece(new Roi('B', new Position("E1")));
	this.ajouterPiece(new Fou('B', new Position("F1")));
	this.ajouterPiece(new Cavalier('B', new Position("G1")));
	this.ajouterPiece(new Tour('B', new Position("H1")));
	
	
	// ajouter une erreur pour le test
	// postion
	// this.ajouterPiece(new Tour('B', new Position("H9")));
	// couleur
	// this.ajouterPiece(new Tour('C', new Position("H8")));

	for(char c = 'A'; c < 'I' ; c++){
	    this.ajouterPiece(new Tavalier('B',new Position(c+"2")));
	    this.ajouterPiece(new Tavalier('N',new Position(c+"7")));
	    }

	this.ajouterPiece(new Tour('N', new Position("A8")));
	this.ajouterPiece(new Cavalier('N', new Position("B8")));
	this.ajouterPiece(new Fou('N', new Position("C8")));
	this.ajouterPiece(new Dame('N', new Position("D8")));
	this.ajouterPiece(new Roi('N', new Position("E8")));
	this.ajouterPiece(new Fou('N', new Position("F8")));
	this.ajouterPiece(new Cavalier('N', new Position("G8")));
	this.ajouterPiece(new Tour('N', new Position("H8")));
    }


    private boolean ajouterPiece(Piece p) throws ErreurCoordonneesException, CaseOccupeeException {
	// Vérifier si une pièce n'est pas déjà placé sur la case.
	for(Piece item : pieces)
	    if(item.getPosition().equals(p.getPosition())){
		System.out.println("Impossible d'ajouter la pièce " + p + " au plateau.");
		System.out.println("Une pièce y est déjà présente : "+item);
		System.exit(1);
	    }
	pieces.add(p);
	return true;
    }


    public Piece getCase(Position position){
	for(Piece p : pieces)
	    if(p.getPosition().equals(position))
		return p;

	return null;
    }

    public Piece getCase(int x, int y) throws ErreurCoordonneesException {
	return this.getCase(new Position(x,y));
    }

    public Piece getCase(String position) throws ErreurCoordonneesException {
	return this.getCase(new Position(position));
    }


    public ArrayList<Piece> getPiecesBlanches(){
	ArrayList<Piece> liste = new ArrayList<Piece>();
	for(Piece p : pieces)
	    if(p.getCouleur()=='B')
		liste.add(p);

	return liste;
    }

    public ArrayList<Piece> getPiecesNoires(){
	ArrayList<Piece> liste = new ArrayList<Piece>();
	for(Piece p : pieces)
	    if(p.getCouleur()=='N')
		liste.add(p);

	return liste;
    }
    

    public void deplacer(Position from, Position to) throws ErreurCoordonneesException, CouleurPieceException, ErreurDeplacementException{
	Piece aDeplacer = this.getCase(from);
	if(aDeplacer == null){
	    System.out.println("Erreur lors du déplacement. Pas de pièce en "+from);
	    System.exit(1);
	}
	if(!aDeplacer.getDeplacementPossible(this).contains(to)){
	    System.out.println("Impossible de déplacer la pièce de " + from + " en " + to);
	    System.exit(1);
	}
	
	Piece caseArrivee = this.getCase(to);
	
	pieces.remove(caseArrivee);
	
	aDeplacer.setPosition(to);
    }

    public void deplacer(String from, String to) throws ErreurCoordonneesException, CouleurPieceException, ErreurDeplacementException {
	this.deplacer(new Position(from), new Position(to));
    }

    public void deplacer(int xf, int yf, int xt, int yt) throws ErreurCoordonneesException, CouleurPieceException, ErreurDeplacementException {
	this.deplacer(new Position(xf,yf), new Position(xt,yt));
    }
    

    public Piece getRoi(char couleur) throws RoiNonTrouveException {
	for(Piece p : pieces)
	    if((p.getType().equals("roi")) && (p.getCouleur()==couleur))
		return p;
	System.out.println("Probleme : roi " + couleur + " manquant à l'appel");
	System.exit(1);
	return null;
    }

    public Piece getRoiBlanc() throws RoiNonTrouveException{
	return this.getRoi('B');
    }

    public Piece getRoiNoir() throws RoiNonTrouveException{
	return this.getRoi('N');
    }
    

    public boolean estEchec(char couleur) throws ErreurCoordonneesException, CouleurPieceException, RoiNonTrouveException {
	if(couleur == 'B'){
	    Piece roiBlanc = this.getRoiBlanc();
	    Position positionRoiBlanc = roiBlanc.getPosition();
	    ArrayList<Piece> liste= this.getPiecesNoires();
	    for(Piece p : liste){
		ArrayList<Position> coupPossible = p.getDeplacementPossible(this);
		for(Position pos : coupPossible)
		    if(pos.equals(positionRoiBlanc))
			return true;
	    }
	}else{
	    Piece roiNoir = this.getRoiNoir();
	    Position positionRoiNoir = roiNoir.getPosition();
	    ArrayList<Piece> liste = this.getPiecesBlanches();
	    for(Piece p : liste){
		ArrayList<Position> coupPossible = p.getDeplacementPossible(this);
		for(Position pos : coupPossible)
		    if(pos.equals(positionRoiNoir))
			return true;
	    }
	}
	return false;
    }
    
    public String toString() {
	String retour = "  ";
	for(char c = 'A' ; c < 'I' ; c++)
	    retour = retour + " " + c + "  ";
	retour = retour+"\n";
	for(int j = 8 ; j>=1 ; j--){
		retour = retour + " |---|---|---|---|---|---|---|---| \n";
		retour = retour + j + "|";
		for(int i = 'A' ; i < 'I' ; i++){
			try {
				Piece p = this.getCase((char)(i)+""+j);
				if(p==null)
					retour = retour+"   |";
				else
					retour = retour+p.getNomCourt()+"|";
			} catch (ErreurCoordonneesException e) {
				System.err.println("Erreur lors de l'affichage du plateau"+e);
			}
		}
		retour = retour+j+"\n";
	}
	retour = retour + " |---|---|---|---|---|---|---|---| \n";
	retour = retour + "  ";
	for(char c = 'A' ; c < 'I' ; c++)
	    retour = retour + " " + c + "  ";
	
	return retour;
    }
    

    public static void main(String[] args) throws ErreurCoordonneesException, CouleurPieceException, CaseOccupeeException {
	Plateau p = new Plateau();
	System.out.println(p);
    }
}
