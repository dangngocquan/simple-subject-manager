package src.algorithm;

import java.util.List;

import src.objects.Plan;
import src.objects.Subject;

public class CalculateCPA {
    public static double getCurrentCPA(Plan plan) {
        double sum = 0;
        int count = 0;
        for (Subject subject : plan.getSubjects()) {
            if (subject.getState() == Subject.COMPLETED) {
                sum += subject.getScore4() * subject.getNumberCredits();
                count += subject.getNumberCredits();
            }
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

    public static double[] getMinAndMaxCPA(Plan plan, boolean canImproveScore) {
        double[] ans = new double[] { 0.0, 0.0 };
        int totalCredit = 0;
        double sumMin = 0;
        double sumMax = 0;

        for (Subject subject : plan.getSubjects()) {
            totalCredit += subject.getNumberCredits();
            if (subject.getState() == Subject.COMPLETED) {
                if (canImproveScore
                        && (subject.getCharacterScore().equals("D")
                                || subject.getCharacterScore().equals("D+"))) {
                    sumMin += subject.getScore4() * subject.getNumberCredits();
                    sumMax += 4.0 * subject.getNumberCredits();
                } else {
                    sumMin += subject.getScore4() * subject.getNumberCredits();
                    sumMax += subject.getScore4() * subject.getNumberCredits();
                }
            } else {
                sumMin += 1.0 * subject.getNumberCredits();
                sumMax += 4.0 * subject.getNumberCredits();
            }
        }

        ans[0] = sumMin / totalCredit;
        ans[1] = sumMax / totalCredit;

        return ans;
    }

    public static double[] getWorstCase(Plan plan, double targetCPA, boolean canImproveScore) {
        double currentSum = 0;
        int numberCreditCompleted = 0;
        int numberCredits = 0;
        for (Subject subject : plan.getSubjects()) {
            numberCredits += subject.getNumberCredits();
            if (subject.getState() == Subject.COMPLETED) {
                if (canImproveScore) {
                    if (subject.getCharacterScore().equals("D")
                            || subject.getCharacterScore().equals("D+")) {
                        continue;
                    } else {
                        numberCreditCompleted += subject.getNumberCredits();
                        currentSum += subject.getScore4() * subject.getNumberCredits();
                    }
                } else {
                    numberCreditCompleted += subject.getNumberCredits();
                    currentSum += subject.getScore4() * subject.getNumberCredits();
                }
            }

        }
        int numberCreditLeft = numberCredits - numberCreditCompleted;
        double currentSumLeft = targetCPA * numberCredits - currentSum;
        double gpaLeft = Math.ceil(currentSumLeft / numberCreditLeft * 100) / 100.0;

        double score1 = 0.0;
        double score2 = 0.0;
        List<Double> score4s = plan.getConversionTable().getScore4();
        for (int index = 0; index < score4s.size(); index++) {
            if (gpaLeft >= score4s.get(index)) {
                score1 = score4s.get(index);
                score2 = score4s.get(index);
            } else {
                score2 = score4s.get(index);
                break;
            }
        }

        int count1 = 0;
        int count2 = 0;
        for (int count = numberCreditLeft; count >= 0; count--) {
            if (count * score1 + (numberCreditLeft - count) * score2 >= currentSumLeft) {
                count1 = count;
                count2 = numberCreditLeft - count1;
                break;
            }
        }

        return (new double[] {
                numberCreditLeft,
                currentSumLeft / numberCreditLeft,
                score1, count1, score2, count2 });

    }
}
