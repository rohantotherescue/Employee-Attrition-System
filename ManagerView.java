import java.io.*;
import java.util.Scanner;

public class ManagerView {
    public void ManagerFunc(String filename){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String man_username = scanner.nextLine();

        System.out.print("Enter password: ");
        String man_password = scanner.nextLine();

        System.out.println();

        if (man_username.equals("manager_user") && man_password.equals("manager_pass")){
            System.out.println("Welcome Manager.");
            System.out.println();

            //Display table with all employee info
            System.out.println("Employee information table: ");
            System.out.println();

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                System.out.printf("|%-10s|%-15s|%-15s|%-10s|%n", "Sl.num", "First Name", "Last Name", "Salary");
                System.out.println("+----------+---------------+---------------+----------+");

                int serialNumber = 1;
                while ((line = br.readLine()) != null) {
                    String[] employeeInfo = line.split(",");
                    String firstName = employeeInfo[2];
                    String lastName = employeeInfo[3];
                    int salary = Integer.parseInt(employeeInfo[4]);

                    System.out.printf("|%-10d|%-15s|%-15s|%-10d|%n", serialNumber, firstName, lastName, salary);
                    serialNumber++;
                }

                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean man_exit = false;
                while (!man_exit) {

                    System.out.println("Action:");
                    System.out.println("1. Change salary of employee");
                    System.out.println("2. Exit");
                    System.out.print("Enter choice: ");
                    int man_choice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println();

                    switch(man_choice){
                        case 1:
                            System.out.print("Enter serial number of the employee: ");
                            int serial_number = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();

                            try (BufferedReader br_man = new BufferedReader(new FileReader(filename))) {
                                String line_man;
                                int current_serial_number = 1;
                                StringBuilder sb = new StringBuilder();

                                while ((line_man = br_man.readLine()) != null) {
                                    String[] employeeInfo = line_man.split(",");
                                    int salary = Integer.parseInt(employeeInfo[4]);

                                    if (current_serial_number == serial_number) {
                                        System.out.println("Current salary: " + salary);
                                        System.out.print("Enter new salary: ");
                                        int newSalary = scanner.nextInt();
                                        scanner.nextLine();

                                        employeeInfo[4] = Integer.toString(newSalary); // update salary in employee info array

                                        sb.append(String.join(",", employeeInfo)).append("\n"); // append updated info for the employee
                                    } else {
                                        sb.append(line_man).append("\n"); // append existing info for other employees
                                    }

                                    current_serial_number++;
                                }
                                br_man.close();

                                // write all updated employee information back to the file
                                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                                bw.write(sb.toString());
                                bw.close();

                                System.out.println("Salary updated successfully.");
                                System.out.println();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                        case 2:
                            System.out.println("Farewell.");
                            System.out.println();
                            man_exit = true;
                            break;
                            
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            System.out.println();
                    }
                }
        }
        else{
            System.out.println("Incorrect username and/or password.");
            System.out.println();
        }
    }
}