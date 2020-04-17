package au.cgs.payroll;

/**
 * This class receives the inputted user pay and returns the calculated
 * tax, super and net pay
 */
public class PayCalculator {

    /**
     * Calculates the amount of tax that is needed to be paid based on
     * the amount of pay received and Australian tax brackets. If the pay
     * is for a time period less than a year, the tax for the entire year
     * is calculated and then interpolated to suit the time period
     *
     * @param payReceived the amount of pay that the employee received
     * @param cycle       the amount of time that the pay is for
     * @return the value of tax calculated
     */
    public int taxCalculate(double payReceived, String cycle) {

        payReceived = payReceived - superCalculate(payReceived);
        double initialTaxAmount;
        double calculatePayReceived = payReceived;
        int finalTaxAmount;

        if (cycle.equals("weekly")) {

            calculatePayReceived = calculatePayReceived * 52;
        } else if (cycle.equals("monthly")) {

            calculatePayReceived = calculatePayReceived * 12;
        }

        if (calculatePayReceived > 18200 && calculatePayReceived <= 37000) {
            initialTaxAmount = (calculatePayReceived - 18200) * 0.19;

        } else if (calculatePayReceived > 37000 && calculatePayReceived <= 90000) {
            initialTaxAmount = 3572 + (calculatePayReceived - 37000) * 0.325;

        } else if (calculatePayReceived > 90000 && calculatePayReceived <= 180000) {
            initialTaxAmount = 20797 + (calculatePayReceived - 90000) * 0.37;

        } else if (calculatePayReceived > 180000) {
            initialTaxAmount = 54097 + (calculatePayReceived - 180000) * 0.45;

        } else {
            finalTaxAmount = 0;
            return finalTaxAmount;
        }

        if (cycle.equals("weekly")) {

            initialTaxAmount = initialTaxAmount / 52;
        } else if (cycle.equals("monthly")) {

            initialTaxAmount = initialTaxAmount / 12;
        }

        finalTaxAmount = (int) initialTaxAmount;
        return finalTaxAmount;

    }

    /**
     * Calculates the amount of money that goes towards the super
     * from the base rate provided by the Australian government
     *
     * @param payReceived the amount of pay received in dollars
     * @return the amount of money that goes towards super from the pay
     */
    public int superCalculate(double payReceived) {

        double initialSuper;
        int finalSuper;

        initialSuper = payReceived * 0.0867579908675799;
        finalSuper = (int) initialSuper;
        return finalSuper;
    }

    /**
     * Calculates the net pay after the tax and super have been deducted
     *
     * @param payReceived the amount of pay received in dollars
     * @return the net pay after the deduction of tax and super
     */
    public int payCalculate(double payReceived, String cycle) {

        double initialPay;
        int finalPay;

        initialPay = payReceived - superCalculate(payReceived) - taxCalculate(payReceived, cycle);
        finalPay = (int) initialPay;
        return finalPay;
    }
}
