public class Disease {

    private int contagiousRange;
    private double contagiousPercent; //How likely to be infected when in range
    private double baseMortalityRate;
    private double startPercentHealthy;

    public Disease(int contagiousRange, double contagiousPercent, double baseMortalityRate, double startPercentHealthy)
    {
        this.contagiousRange = contagiousRange;
        this.contagiousPercent = contagiousPercent;
        this.baseMortalityRate = baseMortalityRate;
        this.startPercentHealthy = startPercentHealthy;
    }

    public int getContagiousRange()
    {
        return contagiousRange;
    }
    public double getContagiousPercent()
    {
        return contagiousPercent;
    }
    public double getBaseMortalityRate()
    {
        return baseMortalityRate;
    }
    public double getStartPercentHealthy()
    {
        return startPercentHealthy;
    }
}
