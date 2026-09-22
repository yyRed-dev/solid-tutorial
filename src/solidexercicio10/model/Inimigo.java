package solidexercicio10.model;

public class Inimigo extends EntidadeMapa implements Movel {

    public Inimigo(int x, int y) {
        super(x, y);
    }

    public boolean colideCom(Nave n) {
        return n.getX() == getX()
                && n.getY() == getY();
    }

    @Override
    public void mover(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}