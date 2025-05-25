# Aplikasi Manajemen Perpustakaan – Java Console

Repositori ini berisi implementasi **Aplikasi Manajemen Perpustakaan** berbasis Java _console_ sederhana.  
Tujuan proyek adalah mendemonstrasikan:

* penggunaan **class, object, method, array, dan string**  
* struktur keputusan `if`, `if‑else`, `switch‑case`  
* struktur pengulangan `for`, `for‑each`, `while`, `do‑while`  
* pemisahan tanggung‑jawab (model, service, utility, entry point)  

---

## Fitur Utama

| Fitur | Deskripsi singkat |
|-------|------------------|
| **Input data buku** | Koleksi awal di‐inisialisasi di `LibraryManager.initDataBooks()`. Setiap buku memiliki *title*, *author*, *category*, dan *availability*. |
| **Peminjaman** | Pengguna dapat meminjam beberapa buku (loop `while`) hingga mengetik **`selesai`**. Sistem menolak buku yang tidak tersedia. |
| **Pengembalian + Denda** | Pengguna mengembalikan judul buku tertentu, lalu mengisi jumlah hari keterlambatan. Denda: Rp 5.000 /hari > 7 hari. |
| **Cetak Struk** | `Receipt.printBorrowReceipt()` dan `printReturnReceipt()` menampilkan daftar buku beserta denda bila ada. |
| **Admin CRUD** | Tambah, ubah, dan hapus buku melalui sub‑menu **Manajemen Buku** dengan konfirmasi Y/N dan validasi input. |
| **Navigasi menu** | Menu utama berulang dengan **do‑while**; `switch‑case` memanggil aksi 1–5. |

---

## Struktur Direktori

```
src/
└─ main/
   └─ java/
      └─ com/
         └─ applibrary/
            ├─ Books.java          # model buku
            ├─ Receipt.java        # util pencetak struk
            ├─ LibraryManager.java # alur menu, pinjam, return, admin
            └─ Main.java           # entry point aplikasi
```

---

## Prasyarat

* **JDK 17+** (direkomendasikan JDK 21 LTS)  
* Terminal (Linux/macOS/WSL) atau Command Prompt/PowerShell  
* VS Code / IntelliJ IDEA (opsional untuk debugging)

---

## Cara Menjalankan

1. **Clone repositori**

   ```bash
   git clone https://github.com/username/app-library.git
   cd app-library
   ```

2. **Kompilasi**

   ```bash
   # Buat folder keluaran
   mkdir -p out
   # Kompilasi semua kelas ke folder out/
   javac -d out src/main/java/com/applibrary/*.java
   ```

3. **Eksekusi**

   ```bash
   java -cp out com.applibrary.Main
   ```

---

## Contoh Alur Penggunaan

```text
=== MENU UTAMA ===
1. Lihat Data Buku
2. Peminjaman Buku
3. Pengembalian Buku
4. Manajemen Buku (Admin)
5. Keluar
Pilih (1-5):
```

### 1 – Lihat Data Buku  
Menampilkan seluruh koleksi beserta status (Tersedia/Dipinjam).

### 2 – Peminjaman Buku  
* Ketik judul buku satu per satu  
* Ketik **selesai** untuk mengakhiri  
* Struk pinjam tercetak otomatis

### 3 – Pengembalian Buku  
* Masukkan judul buku yang dipinjam  
* Input hari keterlambatan (0 jika tepat waktu)  
* Struk pengembalian & denda tercetak

### 4 – Manajemen Buku (Admin)  
Sub‑menu CRUD buku dengan konfirmasi sebelum edit/hapus.

---

## Detail Teknis

| Kelas | Tanggung‑jawab |
|-------|----------------|
| `Books` | Entity buku + getter & setter. |
| `Receipt` | Fungsi statis untuk mencetak struk peminjaman dan pengembalian. |
| `LibraryManager` | *Controller* menu; menyimpan `List<Books>` koleksi & pinjaman; mengelola logika peminjam/return & admin. |
| `Main` | Menjalankan `LibraryManager.mainMenu()`. |

Struktur keputusan dan pengulangan yang digunakan:

* `if / if‑else` – validasi input, status buku  
* `switch‑case` – navigasi menu  
* `for / for‑each` – iterasi koleksi buku  
* `while` – loop input judul sampai valid  
* `do‑while` – menu utama dan menu admin
