import java.util.ArrayList;

class Tavalier extends Piece {

    public Tavalier() throws CouleurPieceException {
        super('B', new Position());
    }

    public Tavalier(char couleur, Position position) throws CouleurPieceException {
        super(couleur, position);
    }

    public String getType() {
        return new String("tavalier");
    }

    public ArrayList<Position> getDeplacementPossible(Plateau pl) throws ErreurCoordonneesException, CouleurPieceException {
        ArrayList<Position> retour = new ArrayList<>();

        // Crée une instance de Tour et Cavalier avec la même position et couleur que Tavalier
        Tour tour = new Tour(this.getCouleur(), this.getPosition());
        Cavalier cavalier = new Cavalier(this.getCouleur(), this.getPosition());

        // Ajoute les déplacements possibles de la Tour
        ArrayList<Position> deplacementPossible = tour.getDeplacementPossible(pl);
        for (Position p : deplacementPossible) {
            retour.add(p);
        }

        // Ajoute les déplacements possibles du Cavalier
        deplacementPossible = cavalier.getDeplacementPossible(pl);
        for (Position p : deplacementPossible) {
            retour.add(p);
        }

        return retour;
    }

    

    public boolean isValidPosition(int x, int y) {
        // Vérifie si la position est valide sur le plateau (par exemple, 8x8)
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}