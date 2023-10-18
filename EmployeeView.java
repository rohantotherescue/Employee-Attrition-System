import java.io.*;
import java.util.Scanner;

public class EmployeeView {
    public void EmployeeFunc(String filename){
        Scanner scanner = new Scanner(System.in);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean found = false;
            String[] employeeInfo = new String[14]; // declare an array to hold employee info
            String cur_fn = "";
            String cur_ln = "";
            int cur_salary = 0;

            System.out.print("Enter username: ");
            String emp_username = scanner.nextLine();

            System.out.print("Enter password: ");
            String emp_password = scanner.nextLine();

            System.out.println();

            while ((line = br.readLine()) != null) {
                employeeInfo = line.split(",");
                String empUsername = employeeInfo[0];
                String empPassword = employeeInfo[1];
                String firstName = employeeInfo[2];
                String lastName = employeeInfo[3];
                int salary = Integer.parseInt(employeeInfo[4]);

                if (empUsername.equals(emp_username) && empPassword.equals(emp_password)) {
                    System.out.println("Welcome " + firstName + " " + lastName);
                    System.out.println();
                    found = true;

                    cur_fn = firstName;
                    cur_ln = lastName;
                    cur_salary = salary;

                    break;
                }
            }

            if (!found) {
                System.out.println("Incorrect username and/or password.");
                System.out.println();
            } else {
                boolean emp_exit = false;
                while (!emp_exit) {
                    System.out.println("Action:");
                    System.out.println("1. View info");
                    System.out.println("2. Change password");
                    System.out.println("3. Exit");
                    System.out.print("Enter choice: ");
                    int emp_choice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println();

                    switch (emp_choice) {
                        case 1:
                            System.out.println("------------------------------------------");
                            System.out.print("Full name: ");
                            System.out.print(cur_fn);
                            System.out.print(" ");
                            System.out.println(cur_ln);
                            System.out.print("Salary: ");
                            System.out.print(cur_salary);
                            System.out.println(" rupees.");
                            System.out.println("------------------------------------------");
                            System.out.println();
                            break;

                        case 2:
                            System.out.print("Enter new password: ");
                            String newPassword = scanner.nextLine();
                            employeeInfo[1] = newPassword; // update password in employee info array

                            // read all existing employee information from file
                            File file = new File(filename);
                            BufferedReader br2 = new BufferedReader(new FileReader(file));
                            String fileLine;
                            StringBuilder sb = new StringBuilder();

                            while ((fileLine = br2.readLine()) != null) {
                                String[] empInfo = fileLine.split(",");
                                if (empInfo[0].equals(emp_username)) { // update info for the specific employee
                                    empInfo[1] = newPassword;
                                    for (String info : empInfo) {
                                        sb.append(info).append(",");
                                    }
                                    sb.deleteCharAt(sb.length() - 1); // remove the last comma
                                    sb.append("\n"); // add a newline
                                } else { // append existing info for other employees
                                    sb.append(fileLine).append("\n");
                                }
                            }
                            br2.close();

                            // write all updated employee information back to the file
                            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                            bw.write(sb.toString());
                            bw.close();

                            System.out.println("Password updated successfully.");
                            System.out.println();
                            break;

                        case 3:
                            System.out.println("Farewell.");
                            System.out.println();
                            emp_exit = true;
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}