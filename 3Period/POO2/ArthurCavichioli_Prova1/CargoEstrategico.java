public abstract class CargoEstrategico extends Colaborador implements Avaliavel {
    protected double kpiScore;

    public CargoEstrategico(String nome, double salarioBase) {
        super(nome, salarioBase);
    }

    @Override
    public void registrarKpi(double score) {
        this.kpiScore = score;
    }

    public double getKpiScore() {
        return kpiScore;
    }
}
