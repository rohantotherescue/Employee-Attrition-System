import java.io.*;
import java.util.Scanner;

public class ComplianceView {
    public void ComplianceFunc(String filename){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String co_username = scanner.nextLine();

        System.out.print("Enter password: ");
        String co_password = scanner.nextLine();

        System.out.println();

        if (co_username.equals("compliance_user") && co_password.equals("compliance_pass")){
            System.out.println("Welcome Compliance Officer.");
            System.out.println();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                System.out.printf("|%-10s|%-15s|%-15s|%n", "Sl.num", "Compliance Status", "Compliance Date");
                System.out.println("+----------+---------------+---------------+");

                int serialNumber = 1;
                while ((line = br.readLine()) != null) {
                    String[] complianceInfo = line.split(",");
                    String complianceStatus = complianceInfo[0];
                    String complianceDate = complianceInfo[1];

                    System.out.printf("|%-10d|%-15s|%-15s|%n", serialNumber, complianceStatus, complianceDate);
                    serialNumber++;
                }

                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean com_exit = false;
                while (!com_exit) {

                    System.out.println("Action:");
                    System.out.println("1. Add compliance warnings");
                    System.out.println("2. Exit");
                    System.out.print("Enter choice: ");
                    int com_choice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println();

                    switch(com_choice){
                        case 1:
                            System.out.print("Enter serial number of the employee: ");
                            int serial_number = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();

                            try (BufferedReader br_compliance = new BufferedReader(new FileReader(filename))) {
                                String line_compliance;
                                int current_serial_number = 1;
                                StringBuilder sb = new StringBuilder();
                        
                                while ((line_compliance = br_compliance.readLine()) != null) {
                                    String[] complianceInfo = line_compliance.split(",");
                                    String complianceStatus = complianceInfo[0];
                                    String complianceDate = complianceInfo[1];
                        
                                    if (current_serial_number == serial_number) {
                                        System.out.println("Current compliance status: " + complianceStatus);
                                        System.out.print("Update compliance status: ");
                                        String newComplianceStatus = scanner.nextLine();
                        
                                        complianceInfo[0] = newComplianceStatus; // update compliance status in compliance info array
                        
                                        sb.append(String.join(",", complianceInfo)).append("\n"); // append updated info for the compliance
                                    } else {
                                        sb.append(line_compliance).append("\n"); // append existing info for other compliances
                                    }
                        
                                    current_serial_number++;
                                }
                                br_compliance.close();
                        
                                // write all updated compliance information back to the file
                                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                                bw.write(sb.toString());
                                bw.close();
                        
                                System.out.println("Compliance status updated successfully.");
                                System.out.println();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        
                        case 2:
                            System.out.println("Farewell.");
                            System.out.println();
                            com_exit = true;
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