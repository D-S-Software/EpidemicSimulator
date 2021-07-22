package backend.disease;

public class Disease {

    private int contagiousRange;
    private int contagiousPercent; //How likely to be infected when in range
    private int baseMortalityRate;
    private int startPercentHealthy;
    private int baseMinTimeSick; //Time is measured in centiseconds
    private int baseMaxTimeSick;

    /** Creates a disease object to be handed to each person
     *
     * @param contagiousRange The number of pixels two people must be within for a healthy person to become sick for an infected person
     * @param contagiousPercent The percent chance each tick that a sick person will infect a healthy person when within range
     * @param baseMortalityRate The base mortality rate for the disease (Changes depending on a person's age and pre-existing conditions
     * @param baseMinTimeSick The minimum time sick a person can be sick for (affected by the age of the person)
     * @param baseMaxTimeSick The maximum time sick a person can be sick for
     * @param startPercentHealthy The percent of people that start off as healthy in the simulation
     */
    public Disease(int contagiousRange, int contagiousPercent, int baseMortalityRate, int baseMinTimeSick, int baseMaxTimeSick, int startPercentHealthy)
    {
        this.contagiousRange = contagiousRange;
        this.contagiousPercent = contagiousPercent;
        this.baseMortalityRate = baseMortalityRate;
        this.startPercentHealthy = startPercentHealthy;
        this.baseMinTimeSick = baseMinTimeSick;
        this.baseMaxTimeSick = baseMaxTimeSick;
    }

    /** Getter and Setter Methods*/

    public int getContagiousRange()
    {
        return contagiousRange;
    }
    public int getContagiousPercent()
    {
        return contagiousPercent;
    }
    public int getBaseMortalityRate()
    {
        return baseMortalityRate;
    }
    public int getStartPercentHealthy()
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
