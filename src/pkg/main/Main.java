package pkg.main;

import java.util.Scanner;

import pkg.Transactions.BookTransactionManager;
import pkg.book.Book;
import pkg.book.BookManager;
import pkg.exception.BookNotFoundException;
import pkg.exception.StudentNotFoundException;
import pkg.person.Student;
import pkg.person.StudentManager;

public class Main {

	public static void main(String[] args) {
		int choice;

		Scanner  sc=new Scanner(System.in);

		BookManager bm=new BookManager();
		StudentManager sm=new StudentManager();
		BookTransactionManager btm=new BookTransactionManager();

		do {
			System.out.println("Enter 1: If You are Student\nEnter 2: If You are Librarian\nEnter 3: If You Want To Exit Here!");
			choice=sc.nextInt();

			if(choice==1)//if user is student
			{
				System.out.println("Enter Your Roll Number:");
				int rollNo=sc.nextInt();
				try {
					Student s=sm.get(rollNo);
					if(s==null)
						throw new StudentNotFoundException();
					int stud_choice;
					do {
						System.out.println("Enter 1: View All Books\nEnter 2: Search Book by ISBN\nEnter 3: Search Books By Subject\nEnter 4: Issue a Book\nEnter 5: Return a Book\nEnter 99:For Exit");
						stud_choice = sc.nextInt();
						switch(stud_choice) {
						case 1:
							System.out.println("All The Book Record Are!");
							bm.viewAllBooks();
							break;
							
						case 2:
							System.out.println("Sreach by ISBN Selected!");
							int search_isbn = sc.nextInt();
							Book book = bm.searchBookByIsbn(search_isbn);
							if (book == null)
								System.out.println("The Book is Not Available with this ISBN!");
							else
								System.out.println(book);
							break;
							
						case 3:
							System.out.println("Enter Subject Name To Search!");
							sc.nextLine();
							String search_subject = sc.nextLine();
							bm.listBooksBySubject(search_subject);
							break;
							
						case 4:
							System.out.println("Enter the ISBN Number to Issual a Book!");
							int issue_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(issue_isbn);
							try {
								if (book == null) {
									throw new BookNotFoundException();				
								}
								if (book.getAvailable_quantity() > 0)
								{
									if (btm.issueBook(rollNo, issue_isbn)) {
										book.setAvailable_quantity(book.getAvailable_quantity()-1);
										System.out.println("Book Has Been Issued");
									}
									else {
										System.out.println("You Cannot Take More Than 3 Books!");
									}
								}
								else {
									System.out.println("The Book has Been Issued...");
								}
								
							}
							catch(BookNotFoundException bnfe) {
								System.out.println(bnfe);
							}
							break;
							
						case 5:
							System.out.println("Please enter ISBN Number To Return a Book!");
							int return_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(return_isbn);
							if (book != null) {
								if (btm.returnBook(rollNo, return_isbn)) {
									book.setAvailable_quantity(book.getAvailable_quantity()+1);
									System.out.println("Thank You for Returning the Book");
								}
								else
									System.out.println("Could Not Return the Book");
							}
							else
								System.out.println("Book with this ISBN Does not Exists");
							break;
							
						case 99:
							System.out.println("Thank You For Using Library");
							break;
						default:
							System.out.println("Invalid choice");

						}
					}while(stud_choice != 99);
				}
				catch(StudentNotFoundException snfe) {
					System.out.println(snfe);
				}

			}
			else if(choice == 2)//user is a librarian
			{
				int lib_choice;
				do {
					System.out.println("Enter 11: View all Students\nEnter 12: Search a Student by Roll Number\nEnter 13: Register a New Student\nEnter 14: Update Student Details\nEnter 15: Delete a Student");
					System.out.println("Enter 21: View all Books\nEnter 22: Search a Book by ISBN\nEnter 23: Add a New Book\nEnter 24: Update The Book Details\nEnter 25: Delete a Book");
					System.out.println("Enter 31 to View All Transactions");
					System.out.println("Enter 99: For Exit!");

					lib_choice = sc.nextInt();

					switch(lib_choice) {
					case 11://view all students
						System.out.println("All The Student Records!");
						sm.viewAStudents();
						break;
					case 12://search a student based on roll number
						System.out.println("Enter Roll Number To Fetch Student's Record!");
						int get_rollNo=sc.nextInt();
						Student student=sm.get(get_rollNo);
						if(student==null) {
							System.out.println("No Record Matches This Roll Number");
						}
						else
							System.out.println(student);
						break;
					case 13://Adding a Student
						System.out.println("Enter Student Details To Add");
						String name;
						String emailId;
						String phoneNumber;
						String address;
						String dob;
						int rollNo;
						int std;
						String division;
						
						
						sc.nextLine();
						System.out.println("Name");
						name=sc.nextLine();

						System.out.println("Email Id");
						emailId=sc.nextLine();

						System.out.println("Phone Numebr");
						phoneNumber=sc.nextLine();

						System.out.println("Address");
						address=sc.nextLine();

						System.out.println("Date Of Birth");
						dob=sc.nextLine();

						System.out.println("Roll Number");
						rollNo=sc.nextInt();

						System.out.println("Standard as Integer");
						std=sc.nextInt();
						
						sc.nextLine();

						System.out.println("Division");
						division=sc.nextLine();

						student=new Student(name,emailId,phoneNumber,address,dob,rollNo,std,division);
						sm.addAStudent(student);
						System.out.println("Student is Added!");
						break;
						
					case 14://update a student
						System.out.println("Enter The Roll Number to Modify The Records!");
						int modify_rollNo;
						modify_rollNo=sc.nextInt();
						student=sm.get(modify_rollNo);
						try {
							if(student== null)
								throw new StudentNotFoundException();

							sc.nextLine();

							System.out.println("Name");
							name=sc.nextLine();

							System.out.println("Email Id");
							emailId=sc.nextLine();

							System.out.println("Phone Numebr");
							phoneNumber=sc.nextLine();

							System.out.println("Address");
							address=sc.nextLine();

							System.out.println("Date Of Birth");
							dob=sc.nextLine();

							System.out.println("Standard as Integer");
							std=sc.nextInt();
							
							sc.nextLine();

							System.out.println("Division");
							division=sc.nextLine();

							sm.updateStudent(modify_rollNo,name,emailId,phoneNumber,address,dob,std,division);
							System.out.println("Student Record is Updated!");
						}
						catch(StudentNotFoundException snfe) {
							System.out.println(snfe);
						}
						break;
						
					case 15:
						System.out.println("Enter The Roll Number to Delet The Records!");
						int delete_rollNo;
						delete_rollNo=sc.nextInt();
						if(sm.deleteStudent(delete_rollNo))
							System.out.println("Student Record is Deleted!");
						else
							System.out.println("Student Record Not Found!");
					
					case 21://view all the books
						bm.viewAllBooks();
						break;
					case 22://search book by ISBN number
						int search_isbn;
						System.out.println("Enter ISBN Number To search Book");
						search_isbn=sc.nextInt();
						Book book=bm.searchBookByIsbn(search_isbn);
						if(book==null)
							System.out.println("Book is Not Available With this ISBN Number");
						else
							System.out.println(book);
						break;
					case 23://Add a new book
						System.out.println("Please Enter Book Details To Add");
						int isbn;
						String title;
						String author;
						String publisher;
						int edition;
						String subjects;
						int available_quantity;
						
						System.out.println("ISBN");
						isbn=sc.nextInt();
						
						sc.nextLine();
						
						System.out.println("Title");
						title=sc.nextLine();
						
						System.out.println("Author");
						author=sc.nextLine();
						
						System.out.println("Publisher");
						publisher=sc.nextLine();
						
						System.out.println("Edition Integer");
						edition=sc.nextInt();
						
						sc.nextLine();
						
						System.out.println("Subject");
						subjects=sc.nextLine();
						
						System.out.println("Quantity");
						available_quantity=sc.nextInt();
						
						book=new Book(isbn,title,author,publisher,edition,subjects,available_quantity);
						bm.addABook(book);
						System.out.println("A Book Record is Added");
						break;
					
					case 24://Update a book
						System.out.println("Please Enter The ISBN Number To Update A Book");
						int update_isbn;
						update_isbn=sc.nextInt();
						try {
							book=bm.searchBookByIsbn(update_isbn);
							if(book==null)
								throw new BookNotFoundException();
							
							
							System.out.println("Enter Book Details To Update");
							sc.nextLine();
							
							System.out.println("Title");
							title=sc.nextLine();
							
							System.out.println("Author");
							author=sc.nextLine();
							
							System.out.println("Publisher");
							publisher=sc.nextLine();
							
							System.out.println("Edition");
							edition=sc.nextInt();
							
							sc.nextLine();
							
							System.out.println("Subject");
							subjects=sc.nextLine();
							
							System.out.println("Quantity");
							available_quantity=sc.nextInt();
							
							bm.updateBook(update_isbn,title,author,publisher,edition,subjects,available_quantity);
						}
						catch(BookNotFoundException bnfe) {
							System.out.println(bnfe);
						}
						break;
						
					case 25://Delete a book
						System.out.println("Please Enter The ISBN Number To Delete A Book");
						int delete_isbn;
						delete_isbn=sc.nextInt();
						try {
							book=bm.searchBookByIsbn(delete_isbn);
							if(book==null)
								throw new BookNotFoundException();
							bm.deletBook(delete_isbn);
						}
						catch(BookNotFoundException bnfe) {
							System.out.println(bnfe);
						}
						break;
					case 31:
						System.out.println("View All Transactions!");
						btm.showAll();
					case 99:
						System.out.println("Thank You For Using Library");
						break;
					default:
						System.out.println("Invalid choice");
					}
					
				}while(lib_choice !=99);
			}
			
		}while(choice != 3);
		System.out.println("Thank You Visit Again!");
		sm.writeToFile();
		bm.writeToFile();
		btm.writeToFile();
		sc.close();
	}
}
