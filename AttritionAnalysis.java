import java.io.*;
import java.util.Scanner;

public class AttritionAnalysis {
    public static void main(String[] args) {

        String emp_filename = "input_files\\employee.txt";
        String c_filename = "input_files\\compliance.txt";

        // Environment variable changes asked by sir during presentation
        // String emp_filename = System.getenv("EMP_FILE") + "\\"+ "employee.txt";
        // String c_filename = System.getenv("EMP_FILE") + "\\" + "compliance.txt";

        Scanner scanner = new Scanner(System.in);

        EmployeeView empobj = new EmployeeView();
        ManagerView mngobj = new ManagerView();
        HRView hrobj = new HRView();
        ComplianceView cobj = new ComplianceView();
        

        boolean outer_exit = false;
        while (!outer_exit) {
            System.out.println();
            System.out.println("Log in as:");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("3. HR");
            System.out.println("4. Compliance officer");
            System.out.println("5. Exit");

            System.out.println();
            System.out.print("Enter choice: ");
            int outer_choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (outer_choice){
                case 1:
                    empobj.EmployeeFunc(emp_filename);
                    break;
                
                case 2:
                    mngobj.ManagerFunc(emp_filename);
                    break;
                
                case 3:
                    hrobj.HRFunc(emp_filename);
                    break;

                case 4:
                    cobj.ComplianceFunc(c_filename);
                    break;

                case 5:
                    System.out.println("Farewell.");
                    System.out.println();
                    outer_exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }
        }

        scanner.close();
    }
}