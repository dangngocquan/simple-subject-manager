package src.algorithm;

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
        return Math.round(sum / count * 100) / 100.0;
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
                    System.out.println(subject.getName());
                } else {
                    sumMin += subject.getScore4() * subject.getNumberCredits();
                    sumMax += subject.getScore4() * subject.getNumberCredits();
                }
            } else {
                sumMin += 1.0 * subject.getNumberCredits();
                sumMax += 4.0 * subject.getNumberCredits();
            }
        }
        System.out.println(sumMax);

        ans[0] = Math.round(sumMin / totalCredit * 100) / 100.0;
        ans[1] = Math.round(sumMax / totalCredit * 100) / 100.0;

        return ans;
    }
}
