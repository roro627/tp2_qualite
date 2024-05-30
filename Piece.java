import java.util.ArrayList;

abstract class Piece {
    private char couleur;
    private Position position;
    

    public Piece() throws ErreurCoordonneesException {
	this.position = new Position(0,0);
	this.couleur = 'B';
    }

    
    public Piece(char couleur, Position position) throws CouleurPieceException {
	if(!this.verificationCouleur(couleur)){
	    System.out.println("Problème de couleur constructeur de Piece : "+couleur);
	    System.exit(1);
	}
	this.position = new Position(position);
	this.couleur = couleur;
    }

    public Piece(char couleur, int x, int y) throws ErreurCoordonneesException, CouleurPieceException {
	this(couleur, new Position(x,y));
    }

    public Piece(char couleur, String position) throws ErreurCoordonneesException, CouleurPieceException{
	this(couleur, new Position(position));
    }

    
    public Piece(Piece p){
	this.position = new Position(p.position);
	this.couleur = p.couleur;
    }


    private boolean verificationCouleur(char c) throws CouleurPieceException{
	return (c == 'B' || c == 'N');
    }


    
    public char getCouleur(){
	return this.couleur;
    }

    public void setCouleur(char couleur) throws CouleurPieceException {
	if(this.verificationCouleur(couleur)){
	    this.couleur = couleur;
	}else{
	    System.out.println("Problème de couleur setCouleur: "+couleur);
	    System.exit(1);
	}
    }

    public Position getPosition(){
	return new Position(this.position);
    }

    public void setPosition(Position position){
	this.position = new Position(position);
    }

    
    abstract public String getType();
    
    public String getNomCourt(){
	String initiale = this.getType().substring(0,1).toUpperCase();
	String secondeLettre = this.getType().substring(1,2);
	return initiale+secondeLettre+this.couleur;
    }
    
    public String getNomLong(){
	return this.getType()+"_"+this.couleur;
    }


    abstract public ArrayList<Position> getDeplacementPossible(Plateau pl) throws ErreurCoordonneesException, CouleurPieceException;


    public boolean equals(Object o){
	if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof Piece)) {
            return false; 
        }

	Piece p = (Piece) o;

	return (this.position.equals(p.position)) && (this.couleur == p.couleur);
    }
    
    
    public String toString(){
	if(this.couleur == 'N')
	    return this.getType() + " noir en " + this.position;
	return this.getType() + " blanc en " + this.position;
    }
}
