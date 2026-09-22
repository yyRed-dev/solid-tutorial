package solidexercicio10.model;

public class Inimigo extends EntidadeMapa implements Movel{
    private int x;
    private int y;

       public Inimigo(int x, int y) {
        super(x, y);
    }

    public boolean colideCom(Nave n) {
        return n.getX() == x && n.getY() == y;
    }

  @Override
public void mover(int dx, int dy) {
    x += dx;
    y += dy;
}
}
