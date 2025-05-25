package com.applibrary;

import java.util.List;

public class Receipt {
    public static void printBorrowReceipt(List<Books> bookLists) {
        System.out.println("--- STRUK PEMINJAMAN ---");
        for (Books b : bookLists) {
            System.out.printf("Judul : %s | Penulis : %s | Kategori : %s | Status : %s%n",
                    b.getTitle(), b.getAuthor(), b.getCategory(),
                    b.isAvailable() ? "Tersedia" : "Dipinjam");
        }
        System.out.println("------------------------");
    }

    public static void printReturnReceipt(List<Books> bookLists, int hariTerlambat) {
        System.out.println("--- STRUK PENGEMBALIAN ---");
        int denda = hariTerlambat > 7 ? (hariTerlambat - 7) * 5000 : 0;
        for (Books b : bookLists) {
            System.out.printf("Judul : %s | Penulis : %s | Kategori : %s | Status : %s%n",
                    b.getTitle(), b.getAuthor(), b.getCategory(),
                    b.isAvailable() ? "Tersedia" : "Dipinjam");
        }
        if (denda > 0)
            System.out.printf("Denda keterlambatan: Rp %,d%n", denda);
        System.out.println("------------------------");
    }
}
