package Practice;

import java.util.Objects;
import java.util.Scanner;
import java.io.*;

class Library{
    String[] books;
    int no_of_books;
    Library(){
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
        for (int i=0;i<this.books.length;i++){
            if (this.books[i].equals(book)){
                System.out.println("The book has been issued!");
                this.books[i] = null;
                return;
            }
        }
        System.out.println("This book does not exist");
    }

    void returnBook(String book){
        addBook(book);
        System.out.println(book+" is successfully return to the libaray");
    }

}
public class Online_libaray {
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        Library libaray_books = new Library();
        int choice;
        File file = new File("Owner.txt");
        File file_M = new File("Member.txt");
//

        loop :
        do {
            System.out.println("1. To add a Book in libaray");
            System.out.println("2. To show available Books in libaray");
            System.out.println("3. To show Issued book in libaray");
            System.out.println("4. To return Book in libaray");
            System.out.println("5. To know about our faculty");
            System.out.println("6. Exit");
            System.out.println("Enter your Choice : ");
            choice = in.nextInt();

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

                case 6:
                    break;

                case 5:
                    System.out.println("-----------------------------------------------");
                    System.out.println("Our Faculty member and Owner ");
                    System.out.println("1. Owner");
                    System.out.println("2. Member Lists");
                    System.out.println("3. Would you like to be a member of our library");

                    System.out.println("Enter your Choice : ");
                    int faculty_in = in.nextInt();
                    switch (faculty_in){
                        case 1:
                            System.out.println("Owner of our Online Library is : ");
                            Scanner owner = new Scanner(file);
                            while (owner.hasNextLine()){
                                String data = owner.nextLine();
                                System.out.println(data);
                            }
                            owner.close();
                            break;
                        case 2:
                            System.out.println("Member of our Online Library is : ");
                            Scanner member = new Scanner(file_M);
                            while (member.hasNextLine()){
                                String data_m = member.nextLine();
                                System.out.println(data_m);
                            }
                            member.close();
                            break;

                        case 3:
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
                                fw.write("Name :- "+ name_m);
                                fw.write(System.getProperty("line.separator"));
                                fw.write("Email :- "+email_m);
                                fw.write(System.getProperty("line.separator"));
                                fw.write("Age :- "+age_m);
                                fw.write(System.getProperty("line.separator"));
                                fw.write("-----------------------------------------------");
                                fw.write(System.getProperty("line.separator"));
                                fw.write(System.getProperty("line.separator"));
                                fw.close();
                                fw.write("-----------------------------------------------");
                            }
                            catch (Exception c){
                                System.out.println("Data not be Stored In database ");
                            }

                            break;
                    }

            }
            System.out.println("Would you continue the process [y or n] : ");
            String ch = in.next();

            if(Objects.equals(ch, "y")|| Objects.equals(ch, "Y")){
                continue loop;
            }
            else{
                break;
            }
        } while (true);
    }
}

