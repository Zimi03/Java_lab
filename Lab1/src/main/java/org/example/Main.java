package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        KnapsackProblem problem = new KnapsackProblem(10,1, 1,10);
        problem.printItems();

        Result result = problem.solve(23);
        System.out.println(result);
    }
}