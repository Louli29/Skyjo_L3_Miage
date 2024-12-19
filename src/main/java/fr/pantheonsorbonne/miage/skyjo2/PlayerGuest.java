package fr.pantheonsorbonne.miage.skyjo2;

public class PlayerGuest extends Player {

    public PlayerGuest(String name){
        super(name);
    }

    @Override
    public void chooseKeepOrNot(Card card, boolean isFromTrash) {
        throw new UnsupportedOperationException("Unimplemented method 'chooseKeepOrNot'");
    }

    @Override
    public void chooseWhereToReplace(Card card) {
        throw new UnsupportedOperationException("Unimplemented method 'chooseWhereToReplace'");
    }

    @Override
    public void revealCard() {
        throw new UnsupportedOperationException("Unimplemented method 'revealCard'");
    }

    @Override
    public void jouer() {
        throw new UnsupportedOperationException("Unimplemented method 'jouer'");
    }

}
