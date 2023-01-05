package src.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import src.objects.Subject;
import src.objects.TimeTable;

public class CalculateTimeTable {
    public static List<List<Integer>> start(TimeTable timeTable) {
        // save answer
        List<List<Integer>> list = new LinkedList<>();

        // Get subjects which enable in timetable
        List<Subject> enableSubjects = new LinkedList<>();
        for (Subject subject : timeTable.getSubjects()) {
            if (subject.getEnable() && subject.getListTimeNames().size() > 0) {
                boolean flag = false;
                for (Boolean enableTimeLesson : subject.getListEnableTimeLessons()) {
                    if (enableTimeLesson) {
                        flag = true;
                    }
                }
                if (flag) {
                    enableSubjects.add(subject);
                }

            }
        }

        // Save status of times
        int[] status = new int[timeTable.getMaxLessonPerDay() * 8];
        Arrays.fill(status, 0);

        // Array save index of time lesson of each subject in 'enableSubjects'
        int[] tempIndexTimeLessons = new int[enableSubjects.size()];
        Arrays.fill(tempIndexTimeLessons, -1);
        if (enableSubjects.size() > 0) {
            find(0, enableSubjects, status, tempIndexTimeLessons, list);
        }

        return list;
    }

    public static void find(int indexSubject, List<Subject> enableSubjects, int[] status, int[] tempIndexTimeLessons,
            List<List<Integer>> list) {
        if (indexSubject >= enableSubjects.size()) {
            List<Integer> tempList = new LinkedList<>();
            for (int num : tempIndexTimeLessons) {
                tempList.add(num);
            }
            list.add(tempList);
        } else {
            List<List<Integer>> listTimes = enableSubjects.get(indexSubject).getListTimes();
            List<Boolean> listEnableTimes = enableSubjects.get(indexSubject).getListEnableTimeLessons();
            for (int indexTimeLesson = 0; indexTimeLesson < listTimes.size(); indexTimeLesson++) {
                boolean valid = listEnableTimes.get(indexTimeLesson);
                for (int time : listTimes.get(indexTimeLesson)) {
                    status[time]++;
                    if (status[time] > 1) {
                        valid = false;
                    }
                }
                if (valid) {
                    tempIndexTimeLessons[indexSubject] = indexTimeLesson;
                    find(indexSubject + 1, enableSubjects, status, tempIndexTimeLessons, list);
                }
                tempIndexTimeLessons[indexSubject] = -1;
                for (int time : listTimes.get(indexTimeLesson)) {
                    status[time]--;
                }

            }
        }
    }

}
