package solidexercicio10.model;

import java.util.ArrayList;
import java.util.List;

public class Nave extends EntidadeMapa implements Movel {

    private String id;
    private int capacidade;
    private int vidas;
    private List<Passageiro> passageiros = new ArrayList<>();

    public Nave(String id, int capacidade) {
        super(0, 0);
        this.id = id;
        this.capacidade = capacidade;
        this.vidas = 3;
    }

    public String getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getVidas() {
        return vidas;
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    @Override
    public void mover(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public void moverComLimites(char direcao, int minX, int maxX, int minY, int maxY) {

        switch (direcao) {
            case 'w': if (getY() < maxY) mover(0, 1); break;
            case 's': if (getY() > minY) mover(0, -1); break;
            case 'a': if (getX() > minX) mover(-1, 0); break;
            case 'd': if (getX() < maxX) mover(1, 0); break;
        }
    }

    public boolean embarcar(Passageiro p) {
        if (passageiros.size() < capacidade) {
            passageiros.add(p);
            return true;
        }
        return false;
    }

    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    @Override
    public String getSimbolo() {
        return "N";
    }

}