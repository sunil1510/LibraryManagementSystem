package com.library.system.main;

import com.library.system.entity.Book;
import com.library.system.entity.Staff;
import com.library.system.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Library {
    boolean exit;
    boolean userLoggedIn;
    boolean staffLoggedIn;
    List<Book> books;
    List<Staff> staffs;
    List<User> users;

    Scanner sc = new Scanner(System.in);

    public Library() {
        this.exit = false;
        this.userLoggedIn = false;
        this.staffLoggedIn = false;
        this.books = new ArrayList<>();
        this.staffs = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public static void main(String[] args) {
        Library mainLibrary = new Library();
        System.out.println("Welcome to Library Management System");
        mainLibrary.performAction();
    }

    private void performAction() {
        while(!this.isExit()) {
            System.out.println("Choose Options");
            System.out.println("=========================================");
            System.out.println("1. Add Staff Member");
            System.out.println("2. Add Book");
            System.out.println("3. Add User");
            System.out.println("4. Show all Books");
            System.out.println("5. Show all Users");
            System.out.println("6. Show all Staff Members");
            System.out.println("7. Search a book");
            System.out.println("8. Update a book");
            System.out.println("9. Rent a Book");
            System.out.println("10. Show all rented books for user");
            System.out.println("11. Return a book");
            System.out.println("12. Exit");
            int option = sc.nextInt();
            if(option >=1 && option <= 12) {
                switch (option) {
                    case 1: this.addStaff();
                        break;
                    case 2: this.addBook();
                        break;
                    case 3: this.addUser();
                        break;
                    case 4: this.showAllBooks();
                        break;
                    case 5: this.showAllUsers();
                        break;
                    case 6: this.showAllStaffMembers();
                        break;
                    case 7: this.showSearchBook();
                        break;
                    case 8:this.updateBook();
                        break;
                    case 9: this.rentABook();
                        break;
                    case 10: this.showRentedBooksForUser();
                        break;
                    case 11: this.returnBook();
                        break;
                    case 12: this.setExit(true);
                        break;
                }
                this.performAction();
            }
            else {
                System.out.println("Wrong option.... Choose again");
                this.performAction();
            }

        }
        System.out.println("Exiting..... Visit Again");
    }

    private void returnBook() {
        User user=validateUser();
        assert user != null;
        System.out.println("Enter isbn number of the book to return");
        String isbn=sc.next();
        Book book1=null;
        for(Book book : user.getRentedBooks()) {
            if(book.getIsbn().equals(isbn)) {
                book1=book;
            }
        }
        if(book1!=null) {
            book1.setInventory(book1.getInventory()+1);
            user.getRentedBooks().remove(book1);
            System.out.println("The user: "+user.getUserName()+ " Has returned the book: "+book1.getName());
        }
        else{
            System.out.println("The book is not available");
        }

    }

    private void showRentedBooksForUser() {
        System.out.println("Enter the username to check their borrowed books");
        String userName=sc.next();
        for(User user : getUsers()) {
            user.getRentedBooks().forEach(System.out::println);
        }
    }

    private void rentABook() {
        Book book=showSearchBook();
        if(book.getInventory()>=1) {
            User user = validateUser();
            if (user != null) {
                user.getRentedBooks().add(book);
                System.out.println("The user: " + user.getUserName() + " Has rented the book: " + book.getName());
                book.setInventory(book.getInventory() - 1);
            }
        }
        else{
            System.out.println("The Book is available but there is no enough inventory");
        }
    }

    private User validateUser() {
        System.out.println("Please enter the user name to validate");
        String checkUserName = sc.next();
        for (User user1 : getUsers()) {
            if (user1.getName().equals(checkUserName)) {
                System.out.println("Found a valid user with user Name: "+checkUserName);
                return user1;
            }
        }
        System.out.println("No user found with user name: "+checkUserName);
        return null;
    }



    private void addStaff() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add details below for staff member");
        System.out.print("Name: ");
        String name = sc.next();
        System.out.println();
        System.out.print("UserName: ");
        String userName = sc.next();
        System.out.println();
        System.out.print("Password: ");
        String password = sc.next();
        System.out.println();
        Staff staff = new Staff();
        staff.setId(UUID.randomUUID().toString());
        staff.setName(name);
        staff.setUserName(userName);
        staff.setPassword(password);
        getStaffs().add(staff);
        System.out.println("Successfully Added Staff");
        System.out.println(staff);
    }

    private void addUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add details below for user");
        System.out.print("Name: ");
        String name = sc.next();
        System.out.println();
        System.out.print("UserName: ");
        String userName = sc.next();
        System.out.println();
        System.out.print("Password: ");
        String password = sc.next();
        System.out.println();
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setUserName(userName);
        user.setPassword(password);
        getUsers().add(user);
        System.out.println("Successfully Added User");
        System.out.println(user);
    }

    private void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add details below for Book");
        System.out.print("Title: ");
        String name = sc.next();
        System.out.println();
        System.out.print("Author: ");
        String author = sc.next();
        System.out.println();
        System.out.print("Inventory: ");
        int inventory = sc.nextInt();
        System.out.println();
        System.out.print("ISBN: ");
        String isbn = sc.next();
        System.out.println();
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setName(name);
        book.setAuthor(author);
        book.setInventory(inventory);
        book.setIsbn(isbn);
        getBooks().add(book);
        System.out.println("Successfully Added Book");
        System.out.println(book);
    }

    public Book showSearchBook() {
        Book book=null;
        System.out.println("Enter ISBN of the book to search");
        String isbnToSearch=sc.next();
        for(Book book1 : getBooks()) {
            if(book1.getIsbn().equals(isbnToSearch)) {
                book=book1;
            }
        }
        if(book!=null) {
            System.out.println("The Book is available :" +book);
            return book;
        }
        else
        {
            System.out.println("The Book is not available");
        }
        return book;
    }

    public void updateBook() {
        System.out.println("Please enter the isbn number of book to update");
        String isbn=sc.next();
        for(Book book : getBooks()) {
            if(book.getIsbn().equals(isbn)) {
                System.out.println("Please enter the updated inventory: ");
                int inventory=sc.nextInt();
                book.setInventory(inventory);
                System.out.println("Book Updated Successfully: "+book);
            }
        }
    }

    /*private User validateUser() {
        System.out.println("Please enter the user name to validate");
        String checkUserName = sc.next();
        for (User user1 : getUsers()) {
            if (user1.getName().equals(checkUserName)) {
                System.out.println("Found a valid user with user Name: "+checkUserName);
                return user1;
            }
        }
        System.out.println("No user found with user name: "+checkUserName);
        return null;
    }

    private void rentABook() {
        Book book=showSearchBook();
        User user=validateUser();
        if(user!=null){
            user.getRentedBooks().add(book);
            book.setInventory(book.getInventory()-1);
        }
        else{
            System.out.println("The Book is not available");
        }
    }
}
*/
private void showAllBooks() {
    for(Book book : getBooks()) {
        System.out.println(book);
    }
}

private void showAllUsers() {
    for(User user : getUsers()) {
        System.out.println(user);
    }
}

private void showAllStaffMembers() {
    for(Staff staff : getStaffs()) {
        System.out.println(staff);
    }
}

public boolean isExit() {
    return exit;
}

public void setExit(boolean exit) {
    this.exit = exit;
}

public boolean isUserLoggedIn() {
    return userLoggedIn;
}

public void setUserLoggedIn(boolean userLoggedIn) {
    this.userLoggedIn = userLoggedIn;
}

public boolean isStaffLoggedIn() {
    return staffLoggedIn;
}

public void setStaffLoggedIn(boolean staffLoggedIn) {
    this.staffLoggedIn = staffLoggedIn;
}

public List<Book> getBooks() {
    return books;
}

public List<Staff> getStaffs() {
    return staffs;
}

public List<User> getUsers() {
    return users;
}
}
