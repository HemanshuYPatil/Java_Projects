package Projects;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.*;

class library{
    String[] books;
    int no_of_books;
    library(){
        this.books = new String[100];
        this.no_of_books = 0;
    }

    void addBook(String book){
        this.books[no_of_books] = book;
        no_of_books++;
        System.out.println(book+ " has been added!");
    }


    void AvailableBooks(){
        System.out.println("Available Books are:");
        for (String book : this.books) {
            if (book == null){
                continue;
            }
            System.out.println("* " + book);
        }
    }

    void issueBook(String book){
        try {
            for (int i = 0; i < this.books.length; i++) {
                if (this.books[i].equals(book)) {
                    System.out.println("The book has been issued!");
                    this.books[i] = null;
                    return;
                }
            }
        }catch (Exception e){
            System.out.println("Book Does not Exist in Library");
        }

    }

    void returnBook(String book){
        addBook(book);
        System.out.println(book+" is successfully return to the libaray");
    }

}

@SuppressWarnings("ALL")
public class Online_Library_login{
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        Library libaray_books = new Library();
        int choice;
        File file = new File("Owner.txt");
        File file_M = new File("Member.txt");

        File login = new File("Login.txt");
        File Sign_up = new File("Sign_Up.txt");

        loop:
        do {
            System.out.println("----- W E L C O M E -----");
            System.out.println("To our Online Library ");
            System.out.println("1. To add a Book in libaray");
            System.out.println("2. To show available Books in libaray");
            System.out.println("3. To show Issued book in libaray");
            System.out.println("4. To return Book in libaray");
            System.out.println("5. To know about our faculty");
            System.out.println("6. Exit");
            System.out.println("Enter your Choice : ");
            choice = in.nextInt();

            if (choice == 6) {
                System.out.println("Thank You for Using our Online Library ");
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter the book name which you added in libaray : ");
                    String add = in.next();
                    libaray_books.addBook(add);
                    break;

                case 2:
                    System.out.println("The Available book in libaray : ");
                    libaray_books.AvailableBooks();
                    break;

                case 3:
                    System.out.println("Enter the book to check were the book is issued or not : ");
                    String iss = in.next();
                    libaray_books.issueBook(iss);
                    break;

                case 4:
                    System.out.println("Enter the book which you return to libaray : ");
                    String return1 = in.next();
                    libaray_books.returnBook(return1);
                    break;


                case 5:
                    log:
                    System.out.println("---------------------------------------------");
                    System.out.println("For Security Reasons ");
                    System.out.println("To move further process please login and Sign up");
                    System.out.println("1. Login");
                    System.out.println("2. Sign Up");
                    System.out.println("3. Forget Username and Password");
                    System.out.println("4. Back");


                    System.out.println("Enter your Choice : ");
                    int login_in = in.nextInt();

                    while (true) {
                        if (login_in == 1) {
                            try {

                                Path path = Paths.get(Sign_up.toString());
                                InputStream input = Files.newInputStream(path);
                                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                                System.out.println("LOGIN :- ");
                                System.out.println("Enter the Username : ");
                                String user_login = in.next();
                                System.out.println("Enter the Password : ");
                                String pass_login = in.next();

                                Scanner login1 = new Scanner(Sign_up);
                                String login_data = null;
                                String _temp = null;
                                Boolean found = false;
                                while ((_temp = reader.readLine()) != null) {
                                    login_data = login1.nextLine();
                                    String[] account = _temp.split(",");
                                    String user = account[0];
                                    String pass = account[1];
                                    if (login_data != null) {

                                        if (user.equals(user_login) && pass.equals(pass_login)) {
                                            found = true;
                                        } else {
                                            found = false;
                                        }
                                    }
                                }
                                if (found == true) {
                                    System.out.println("Successfully Loginned");
                                    while (true) {
                                        System.out.println("-----------------------------------------------");
                                        System.out.println("Our Faculty member and Owner ");
                                        System.out.println("1. Owner");
                                        System.out.println("2. Member Lists");
                                        System.out.println("3. Would you like to be a member of our library");
                                        System.out.println("4. Log Out");

                                        System.out.println("Enter your Choice : ");
                                        int faculty_in = in.nextInt();
                                        if (faculty_in == 1) {
                                            System.out.println("Owner of our Online Library is : ");
                                            Scanner owner = new Scanner(file);
                                            while (owner.hasNextLine()) {
                                                String data = owner.nextLine();
                                                System.out.println(data);
                                            }
                                            owner.close();

                                        }

                                        if (faculty_in == 2) {
                                            System.out.println("Member of our Online Library is : ");
                                            Scanner member = new Scanner(file_M);
                                            while (member.hasNextLine()) {
                                                String data_m = member.nextLine();
                                                System.out.println(data_m);
                                            }
                                            member.close();

                                        }


                                        if (faculty_in == 3) {
                                            System.out.println("To make a Member of our Online Library ");

                                            System.out.println("Please Enter your Name : ");
                                            String name_m = in.next();

                                            System.out.println("Please Enter your Email : ");
                                            String email_m = in.next();

                                            System.out.println("Please Enter your Age : ");
                                            String age_m = in.next();

                                            try {
                                                FileWriter fw = new FileWriter(file_M, true);
                                                fw.write("-----------------------------------------------");
                                                fw.write(System.getProperty("line.separator"));
                                                fw.write("Name :- " + name_m);
                                                fw.write(System.getProperty("line.separator"));
                                                fw.write("Email :- " + email_m);
                                                fw.write(System.getProperty("line.separator"));
                                                fw.write("Age :- " + age_m);
                                                fw.write(System.getProperty("line.separator"));
                                                fw.write("-----------------------------------------------");
                                                fw.write(System.getProperty("line.separator"));
                                                fw.write(System.getProperty("line.separator"));
                                                fw.close();
                                                fw.write("-----------------------------------------------");
                                            } catch (Exception e) {
                                                System.out.println("Data not be Stored In database ");
                                            }

                                        }
                                        if (faculty_in == 4) {
                                            break;
                                        }

                                    }
                                    System.out.println("Confirm to Log Out [ y or n ] : ");
                                    String log_choice = in.next();

                                    if (Objects.equals(log_choice, "y") || Objects.equals(log_choice, "Y")) {
                                        break;
                                    }
                                } else {
                                    System.out.println("please enter valid credentials");
                                }

                            } catch (Exception e) {
                                System.out.println(e);
                            }


                        }
                        if (login_in == 2) {
                            System.out.println("SIGN - UP HERE :- ");
                            System.out.println("Enter your Username : ");
                            String username = in.next();

                            System.out.println("Enter your Password : ");
                            String password = in.next();


                            try {
                                FileWriter signup = new FileWriter(Sign_up, true);
                                signup.write(username + "," + password);
                                signup.write(System.getProperty("line.separator"));
                                signup.close();

                                System.out.println("Your Account has been Created ");
                                System.out.println("Please login and check your account");

                            } catch (Exception e) {
                                System.out.println("Please Enter valid Details");
                            }
                            break;
                        }
                        if (login_in == 4) {
                            continue loop;
                        }
                        if (login_in == 3){
                            Scanner obj=new Scanner(System.in);
                            File input=new File("Sign_Up.txt");
                            FileReader fr=null;
                            String SearcWord,str="";

                            System.out.print("Enter your Username / Password : ");
                            SearcWord=obj.nextLine();

                            try{
                                fr=new FileReader(input);
                                BufferedReader br=new BufferedReader(fr);

                                do{
                                    if(str.contains(SearcWord))
                                        System.out.print("The Username and Password is : "+str+"\n");

                                }while((str=br.readLine())!=null);
                                break;
                            }
                            catch (IOException ex) {
                                Logger.getLogger(Online_Library_login.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
            }

//            System.out.println("Would you continue the process [y or n] : ");
//            String ch = in.next();
//
//            if(Objects.equals(ch, "y")|| Objects.equals(ch, "Y")){
//                continue loop;
//            }
//            else{
//                break;
//            }

        } while (choice != 6);
    }
}