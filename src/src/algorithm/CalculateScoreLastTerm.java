package src.algorithm;

import src.objects.ConversionTable;

public class CalculateScoreLastTerm {
    public static double[] getResults(double score1, double coefficient1, double score2,
            double coefficient2, ConversionTable conversionTable) {
        double coefficient3 = 1 - coefficient1 - coefficient2;
        double[] minScores = new double[conversionTable.getCharacterScore().size() - 1]; // min score to reach D, D+,
                                                                                         // ..., A+
        for (int i = 0; i < minScores.length; i++) {
            double scoreHaving = score1 * coefficient1 + score2 * coefficient2;
            minScores[i] = Math.round((conversionTable.getScore10().get(i + 1) - scoreHaving) / coefficient3 * 100)
                    / 100.0;
        }
        return minScores;
    }

    public static String[] getStringResults(double score1, double coefficient1, double score2,
            double coefficient2, ConversionTable conversionTable) {
        double[] minScores = getResults(score1, coefficient1, score2, coefficient2, conversionTable);
        String[] stringResults = new String[minScores.length];
        for (int i = 0; i < minScores.length; i++) {
            if (minScores[i] > 10) {
                stringResults[i] = String.format("Đạt điểm %s là điều bất khả thi.",
                        conversionTable.getCharacterScore().get(i + 1));
            } else if (minScores[i] > 0) {
                stringResults[i] = String.format("Muốn đạt điểm %s thì điểm cuối kì cần đạt ít nhất là %s điểm.",
                        conversionTable.getCharacterScore().get(i + 1), minScores[i]);
            } else {
                stringResults[i] = String.format(
                        "Muốn đạt điểm %s thì điểm cuối kì cần đạt ít nhất là %s điểm. (easy game, bro)",
                        conversionTable.getCharacterScore().get(i + 1), 0);
            }

        }
        return stringResults;
    }

    public static double[] getResults(double score1, double coefficient1, double score2,
            double coefficient2, double score3, double coefficient3, ConversionTable conversionTable) {
        double coefficient4 = 1 - coefficient1 - coefficient2 - coefficient3;
        double[] minScores = new double[conversionTable.getCharacterScore().size() - 1]; // min score to reach D, D+,
                                                                                         // ..., A+
        for (int i = 0; i < minScores.length; i++) {
            double scoreHaving = score1 * coefficient1 + score2 * coefficient2 + score3 * coefficient3;
            minScores[i] = Math.round((conversionTable.getScore10().get(i + 1) - scoreHaving) / coefficient4 * 100)
                    / 100.0;
        }
        return minScores;
    }

    public static String[] getStringResults(double score1, double coefficient1, double score2,
            double coefficient2, double score3, double coefficient3, ConversionTable conversionTable) {
        double[] minScores = getResults(score1, coefficient1, score2, coefficient2, score3, coefficient3,
                conversionTable);
        String[] stringResults = new String[minScores.length];
        for (int i = 0; i < minScores.length; i++) {
            if (minScores[i] > 10) {
                stringResults[i] = String.format("Đạt điểm %s là điều bất khả thi.",
                        conversionTable.getCharacterScore().get(i + 1));
            } else if (minScores[i] > 0) {
                stringResults[i] = String.format("Muốn đạt điểm %s thì điểm cuối kì cần đạt ít nhất là %s điểm.",
                        conversionTable.getCharacterScore().get(i + 1), minScores[i]);
            } else {
                stringResults[i] = String.format(
                        "Muốn đạt điểm %s thì điểm cuối kì cần đạt ít nhất là %s điểm. (easy game, bro)",
                        conversionTable.getCharacterScore().get(i + 1), 0);
            }

        }
        return stringResults;
    }
}
