package solidexercicio10.model;

public class Professor {
     public Professor(String nome, int x, int y) {
        super(nome, "Professor", x, y);
    }

    @Override
    public int getPontuacao() {
        return 10;  // Professor vale 10 pontos
    }
}
