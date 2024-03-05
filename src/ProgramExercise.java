import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class ProgramExercise {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Employee> emp = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String email = fields[1];
                double salary = Double.parseDouble(fields[2]);

                emp.add(new Employee(name, email, salary));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            double enteredSalary = sc.nextDouble();

            System.out.println("Email of people whose salary is more than " + String.format("%.2f", enteredSalary));

            Comparator<String> compEmails = (e1, e2) -> e1.toUpperCase().compareTo(e2.toUpperCase());

            List<String> emails = emp.stream()
                    .filter(s -> s.getSalary() > enteredSalary)
                    .map(e -> e.getEmail())
                    .sorted(compEmails)
                    .collect(Collectors.toList());

            emails.forEach(System.out::println);

            double sum = emp.stream()
                    .filter(e -> e.getName().charAt(0) == 'M')
                    .map(s -> s.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
