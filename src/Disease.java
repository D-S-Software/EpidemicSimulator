public class Disease {

    private int contagiousRange;
    private double contagiousPercent; //How likely to be infected when in range
    private double baseMortalityRate;
    private double startPercentHealthy;
    private int baseMinTimeSick; //Time is measured in centiseconds
    private int baseMaxTimeSick;

    public Disease(int contagiousRange, double contagiousPercent, double baseMortalityRate, int baseMinTimeSick, int baseMaxTimeSick, double startPercentHealthy)
    {
        this.contagiousRange = contagiousRange;
        this.contagiousPercent = contagiousPercent;
        this.baseMortalityRate = baseMortalityRate;
        this.startPercentHealthy = startPercentHealthy;
        this.baseMinTimeSick = baseMinTimeSick;
        this.baseMaxTimeSick = baseMaxTimeSick;
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
    public int getBaseMinTimeSick()
    {
        return baseMinTimeSick;
    }
    public int getBaseMaxTimeSick()
    {
        return baseMaxTimeSick;
    }
}
