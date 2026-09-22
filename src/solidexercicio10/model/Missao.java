package solidexercicio10.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Missao {

    private final Nave nave;

    private final List<Passageiro> passageiros = new ArrayList<>();
    private final List<Asteroide> asteroides = new ArrayList<>();
    private final List<Inimigo> inimigos = new ArrayList<>();

    public Missao(Nave nave) {
        this.nave = nave;
    }

    public Nave getNave() {
        return nave;
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public List<Asteroide> getAsteroides() {
        return asteroides;
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }

    public void addPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }

    public void addAsteroide(Asteroide asteroide) {
        asteroides.add(asteroide);
    }

    public void addInimigo(Inimigo inimigo) {
        inimigos.add(inimigo);
    }

    public boolean todosEmbarcados() {
        return passageiros.isEmpty();
    }

    public Passageiro passagemNaPosicao() {
        for (Passageiro p : passageiros)
            if (p.getX() == nave.getX() && p.getY() == nave.getY()) return p;
        return null;
    }

    public boolean embarcarPassageiroNaPosicao() {
        Passageiro p = passagemNaPosicao();
        if (p == null || nave.getPassageiros().size() >= nave.getCapacidade()) return false;
        if (!nave.embarcar(p)) return false;
        passageiros.remove(p);
        return true;
    }

    public void moverInimigos(Random random, int minX, int maxX, int minY, int maxY) {
        for (Inimigo i : inimigos) {
            int dx = random.nextInt(3) - 1, dy = random.nextInt(3) - 1;
            int x = i.getX() + dx, y = i.getY() + dy;
            if (x >= minX && x <= maxX && y >= minY && y <= maxY) i.mover(dx, dy);
        }
    }

    public boolean verificaColisao() {
        for (Asteroide a : asteroides)
            if (a.getX() == nave.getX() && a.getY() == nave.getY()) return true;
        for (Inimigo i : inimigos)
            if (i.colideCom(nave)) return true;
        return false;
    }

}