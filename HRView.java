import java.io.*;
import java.util.Scanner;

public class HRView {
    public void HRFunc(String filename){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String hr_username = scanner.nextLine();

        System.out.print("Enter password: ");
        String hr_password = scanner.nextLine();

        System.out.println();

        if (hr_username.equals("HR_user") && hr_password.equals("HR_pass")){
            System.out.println("Welcome HR.");
            System.out.println();

            //Display table with all employee info
            System.out.println("Employee information table: ");
            System.out.println();

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-15s|%n", "Sl.num", "First Name", "Last Name", "Salary", "Attrition Risk");
                System.out.println("+----------+---------------+---------------+----------+---------------+");

                int serialNumber = 1;
                while ((line = br.readLine()) != null) {
                    String[] employeeInfo = line.split(",");
                    String firstName = employeeInfo[2];
                    String lastName = employeeInfo[3];
                    String salary = employeeInfo[4];

                    String[] attrition_1 = new String[10];
                    System.arraycopy(employeeInfo, 5, attrition_1, 0, 10);
                    int[] attrition = new int[attrition_1.length];

                    for (int i = 0; i < attrition_1.length; i++) {
                    attrition[i] = Integer.parseInt(attrition_1[i]);}

                    String attrition_tag;

                    int sum = 0;

                    for (int i = 0; i < attrition.length; i++) {
                        sum += attrition[i];
                    }
                    if(sum>60) attrition_tag="high";
                    else if ((sum<60)&&(sum>40)) attrition_tag="mid";
                    else attrition_tag="low";


                    System.out.printf("|%-10d|%-15s|%-15s||%-15s|%-15s|%n", serialNumber, firstName, lastName, salary, attrition_tag);
                    serialNumber++;
                }

                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean hr_exit = false;
                while (!hr_exit) {

                    System.out.println("Action:");
                    System.out.println("1. Change attrition values of employee");
                    System.out.println("2. Exit");
                    System.out.print("Enter choice: ");
                    int hr_choice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println();

                    switch(hr_choice){
                        case 1:
                            System.out.print("Enter serial number of the employee: ");
                            int serial_number = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();

                            try (BufferedReader br_hr = new BufferedReader(new FileReader(filename))) {
                                String line_hr;
                                int current_serial_number = 1;
                                StringBuilder sb = new StringBuilder();

                                while ((line_hr = br_hr.readLine()) != null) {
                                    String[] employeeInfo = line_hr.split(",");

                                    if (current_serial_number == serial_number) {
                                        String[] newAttrition= new String[10];
                                        System.out.print("Update attrition risk (enter 10 numerical values for 10 months): ");
                                        for(int i=5;i<15;i++)
                                        newAttrition[i-5] = scanner.nextLine();
                                        System.arraycopy(newAttrition, 0, employeeInfo, 5, 10);
                                        for(int i=0;i<15;i++)
                                        sb.append(String.join(",", employeeInfo[i])).append(","); // append updated info for the employee
                                        sb.deleteCharAt(sb.length() - 1);
                                        sb.append("\n");
                                    } else {
                                        sb.append(line_hr).append("\n"); // append existing info for other employees
                                    }

                                    current_serial_number++;
                                }
                                br_hr.close();

                                // write all updated employee information back to the file
                                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                                bw.write(sb.toString());
                                bw.close();

                                System.out.println("Attrition risk updated successfully.");
                                System.out.println();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                        case 2:
                            System.out.println("Farewell.");
                            System.out.println();
                            hr_exit = true;
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