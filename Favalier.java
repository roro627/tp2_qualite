import java.util.ArrayList;

class Favalier extends Piece {

    public Favalier() throws CouleurPieceException {
        super('B', new Position());
    }

    public Favalier(char couleur, Position position) throws CouleurPieceException {
        super(couleur, position);
    }

    public String getType() {
        return new String("favalier");
    }

    public ArrayList<Position> getDeplacementPossible(Plateau pl) throws ErreurCoordonneesException, CouleurPieceException {
        ArrayList<Position> retour = new ArrayList<>();

        // Crée une instance de Fou et Cavalier avec la même position et couleur que Favalier
        Fou fou = new Fou(this.getCouleur(), this.getPosition());
        Cavalier cavalier = new Cavalier(this.getCouleur(), this.getPosition());

        // Ajoute les déplacements possibles du fou
        ArrayList<Position> deplacementPossible = fou.getDeplacementPossible(pl);
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