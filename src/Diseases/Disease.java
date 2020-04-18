package Diseases;

public abstract class Disease {

    private double baseSpreadRate;
    private double baseMortalityRate;

    public double getBaseSpreadRate()
    {
        return baseSpreadRate;
    }

    public void setBaseSpreadRate(double baseSpreadRate)
    {
        this.baseSpreadRate = baseSpreadRate;
    }

    public double getBaseMortalityRate()
    {
        return baseMortalityRate;
    }

    public void setBaseMortalityRate(double baseMortalityRate)
    {
        this.baseMortalityRate = baseMortalityRate;
    }






}
