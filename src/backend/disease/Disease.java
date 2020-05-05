package backend.disease;

public class Disease {

    private double contagiousRange;
    private double contagiousPercent; //How likely to be infected when in range
    private double baseMortalityRate;
    private double startPercentHealthy;
    private double baseMinTimeSick; //Time is measured in centiseconds
    private double baseMaxTimeSick;

    /** Creates a disease object to be handed to each person
     *
     * @param contagiousRange The number of pixels two people must be within for a healthy person to become sick for an infected person
     * @param contagiousPercent The percent chance each tick that a sick person will infect a healthy person when within range
     * @param baseMortalityRate The base mortality rate for the disease (Changes depending on a person's age and pre-existing conditions
     * @param baseMinTimeSick The minimum time sick a person can be sick for (affected by the age of the person)
     * @param baseMaxTimeSick The maximum time sick a person can be sick for
     * @param startPercentHealthy The percent of people that start off as healthy in the simulation
     */
    public Disease(double contagiousRange, double contagiousPercent, double baseMortalityRate, double baseMinTimeSick, double baseMaxTimeSick, double startPercentHealthy)
    {
        this.contagiousRange = contagiousRange;
        this.contagiousPercent = contagiousPercent;
        this.baseMortalityRate = baseMortalityRate;
        this.startPercentHealthy = startPercentHealthy;
        this.baseMinTimeSick = baseMinTimeSick;
        this.baseMaxTimeSick = baseMaxTimeSick;
    }

    /** Getter and Setter Methods*/

    public double getContagiousRange()
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
    public double getBaseMinTimeSick()
    {
        return baseMinTimeSick;
    }
    public double getBaseMaxTimeSick()
    {
        return baseMaxTimeSick;
    }
}
