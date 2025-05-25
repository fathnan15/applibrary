package com.applibrary;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class LibraryManager {
    private List<Books> collectionBooks = new ArrayList<>();
    private List<Books> borrowedBooks = new ArrayList<>();

    public LibraryManager() {
        initDataBooks();
    }

    private void initDataBooks() {
        collectionBooks.add(new Books("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", true));
        collectionBooks.add(new Books("The Hobbit", "J.R.R. Tolkien", "Fantasy", true));
        collectionBooks.add(new Books("1984", "George Orwell", "Dystopian", true));
        collectionBooks.add(new Books("To Kill a Mockingbird", "Harper Lee", "Fiction", true));
        collectionBooks.add(new Books("The Great Gatsby", "F. Scott Fitzgerald", "Classic", true));
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("=== Menu Perpustakaan ===");
            System.out.println("1. Lihat Koleksi Buku");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Manajemen Buku(Admin)");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewCollectionBooks();
                    break;
                case 2:
                    borrowBook(scanner);
                    break;
                case 3:
                    returnBook(scanner);
                    break;
                case 4:
                    manageBooks(scanner);
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan perpustakaan!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        } while (choice != 5);
        scanner.close();
    }

    private void viewCollectionBooks() {
        System.out.println("--- Koleksi Buku Perpustakaan ---");
        for (Books book : collectionBooks) {
            System.out.printf("Judul: %s | Penulis: %s | Kategori: %s | Status: %s%n",
                    book.getTitle(), book.getAuthor(), book.getCategory(),
                    book.isAvailable() ? "Tersedia" : "Dipinjam");
        }
        System.out.println("-------------------------------");
    }

    private void borrowBook(Scanner scanner) {
        System.out.println("--- PEMINJAMAN ---");
        System.out.println("Ketik 'selesai' untuk akhir daftar.");
        while (true) {
            System.out.print("Masukkan judul buku: ");
            String judul = scanner.nextLine();
            if (judul.equalsIgnoreCase("selesai"))
                break;

            boolean found = false;
            for (Books b : collectionBooks) {
                if (b.getTitle().equalsIgnoreCase(judul) && b.isAvailable()) {
                    b.setAvailable(false);
                    borrowedBooks.add(b);
                    found = true;
                    break;
                }
            }
            if (!found)
                System.out.println("Buku tidak tersedia atau tidak ditemukan.");
        }
        Receipt.printBorrowReceipt(borrowedBooks);
    }

    private void returnBook(Scanner scanner) {
        System.out.println("--- PENGEMBALIAN ---");
        List<Books> hasReturnBooks = new ArrayList<>();
        while (true) {
            System.out.print("Masukkan judul buku yang dikembalikan: ");
            String judul = scanner.nextLine();
            Books toReturn = null;
            for (Books b : borrowedBooks) {
                if (b.getTitle().equalsIgnoreCase(judul)) {
                    toReturn = b;
                    break;
                }
            }
            if (toReturn == null) {
                System.out.println("Buku tidak ada dalam daftar peminjaman.");
                continue;
            }
            borrowedBooks.remove(toReturn);
            toReturn.setAvailable(true);
            hasReturnBooks.add(toReturn);
            break;
        }
        System.out.print("Masukkan total hari keterlambatan: ");
        int hari = scanner.nextInt();
        scanner.nextLine();
        Receipt.printReturnReceipt(hasReturnBooks, hari);
    }

    private void manageBooks(Scanner sc) {
        int pilih;
        do {
            System.out.println("--- ADMIN: Manajemen Buku ---");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Ubah Buku");
            System.out.println("3. Hapus Buku");
            System.out.println("4. Kembali");
            System.out.print("Pilih (1-4): ");
            pilih = sc.nextInt();
            sc.nextLine();
            switch (pilih) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    editBook(sc);
                    break;
                case 3:
                    deleteBook(sc);
                    break;
                case 4:
                    System.out.println("Kembali ke menu utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 4);
    }

    private void addBook(Scanner sc) {
        System.out.print("Judul baru: ");
        String title = sc.nextLine();
        System.out.print("Penulis: ");
        String author = sc.nextLine();
        System.out.print("Kategori: ");
        String category = sc.nextLine();
        collectionBooks.add(new Books(title, author, category, true));
        System.out.println("Buku berhasil ditambahkan.");
    }

    private void editBook(Scanner sc) {
        System.out.print("Masukkan judul buku untuk diubah: ");
        String judul = sc.nextLine();
        Books found = null;
        for (Books b : collectionBooks)
            if (b.getTitle().equalsIgnoreCase(judul))
                found = b;
        if (found == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }
        System.out.print("Konfirmasi ubah (Y/N): ");
        if (!sc.nextLine().equalsIgnoreCase("Y"))
            return;
        System.out.print("Judul baru: ");
        String newTitle = sc.nextLine();
        found.setTitle(newTitle);
        System.out.print("Penulis baru: ");
        String newAuthor = sc.nextLine();
        found.setAuthor(newAuthor);
        System.out.print("Kategori baru: ");
        String newCategory = sc.nextLine();
        found.setCategory(newCategory);
        System.out.println("Data buku diperbarui.");
    }

    private void deleteBook(Scanner sc) {
        System.out.print("Masukkan judul buku untuk dihapus: ");
        String judul = sc.nextLine();
        Books found = null;
        for (Books b : collectionBooks)
            if (b.getTitle().equalsIgnoreCase(judul))
                found = b;
        if (found == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }
        System.out.print("Konfirmasi hapus (Y/N): ");
        if (!sc.nextLine().equalsIgnoreCase("Y"))
            return;
        collectionBooks.remove(found);
        System.out.println("Buku berhasil dihapus.");
    }
}
