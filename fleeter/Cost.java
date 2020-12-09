package agencyrentalprogram;

public interface Cost 
{
    // This public interface allows client code to create variables of type Cost
    public double getDailyRate();
    public double getWeeklyRate();
    public double getMonthlyRate();
    public double getMileageCharge();
    public double getDailyInsurRate();
}
