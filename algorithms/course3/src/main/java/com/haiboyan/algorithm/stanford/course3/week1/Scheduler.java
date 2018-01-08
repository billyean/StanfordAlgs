package com.haiboyan.algorithm.stanford.course3.week1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Scheduler {
    private static class Job implements Comparable<Job>{
        int weight;

        int length;

        Job(String w, String l) {
            weight = Integer.parseInt(w);
            length = Integer.parseInt(l);
        }

        public double weightOverLen() {
            return (double) weight / length;
        }

        public int different() {
            return weight - length;
        }

        public int weight() {
            return weight;
        }

        @Override
        public int compareTo(Job o) {
            return Double.compare(weightOverLen(), o.weightOverLen());
        }
    }

    private final Job[] jobs;

    private final int jobN;

    Scheduler(Job[] jobs, int n) {
        this.jobs = jobs;
        this.jobN = n;
    }

    public long sumOfWeightCompletionTime(Comparator<Job> comp) {
        Arrays.sort(jobs, comp);

        long sum = 0, len = 0;

        for (Job j : jobs) {
            len += j.length;
            sum += len * j.weight;
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(args[0]));
        Integer n = Integer.parseInt(lines.get(0));

        Job[] jobs = lines.subList(1, lines.size()).stream().map(line -> line.split(" ")).map(wl -> new Job(wl[0], wl[1])).toArray(Job[]::new);

        Scheduler scheduler = new Scheduler(jobs, n);

        System.out.printf("sum of weight completion time by difference: %d\n",
                scheduler.sumOfWeightCompletionTime(Comparator.comparing(Job::different).thenComparing(Job::weight).reversed()));

        System.out.printf("sum of weight completion time by weight over length: %d\n",
                scheduler.sumOfWeightCompletionTime(Comparator.comparing(Job::weightOverLen).reversed()));
    }
}
