# Penjelasan Fitur Sync Cloud & Restore Photos

Dokumen ini menjelaskan fungsi dan alur kerja dari dua fitur utama di tab Pengaturan: **Sync Cloud Photos** dan **Restore Missing Photos**.

---

## 1. Sync Cloud Photos (Sinkronisasi Awan)

### Fungsi
Fitur ini bertujuan untuk **mendaftarkan** semua foto/media yang pernah diunggah ke grup/channel Telegram Anda ke dalam database lokal aplikasi. Ini adalah proses "pendataan" dan bukan proses pengunduhan file fisik.

### Alur Kerja (Workflow)
1.  **Inisialisasi**: Aplikasi mengambil ID Grup/Channel Telegram yang telah Anda konfigurasi.
2.  **Pemindaian (Scanning)**: Aplikasi melalui Bot API memindai pesan-pesan di Telegram (biasanya dalam batch 50-100 pesan).
3.  **Identifikasi Media**: Mencari pesan yang mengandung foto, video, atau dokumen gambar.
4.  **Verifikasi Database**: Aplikasi mengecek apakah ID unik file tersebut sudah ada di tabel `remote_photos` di database lokal.
5.  **Penyimpanan**: Jika file baru ditemukan:
    *   Informasi meta (ID file, nama, ukuran, tanggal unggah) disimpan ke database.
    *   Data ini nantinya akan muncul di tab "Cloud" (Foto Awan) di aplikasi.
6.  **Selesai**: Aplikasi menampilkan jumlah total media yang berhasil ditemukan di cloud.

> [!NOTE]
> **Batasan Telegram Bot API**: Bot Telegram secara resmi hanya bisa mengakses pesan-pesan terbaru (biasanya dalam 24 jam terakhir) kecuali bot tersebut dijadikan Admin di grup/channel. Pastikan bot Anda adalah Admin untuk hasil maksimal.

---

## 2. Restore Missing Photos (Pulihkan Foto yang Hilang)

### Fungsi
Fitur ini digunakan untuk **mengunduh kembali** file asli dari Telegram ke penyimpanan HP Anda. Sangat berguna jika Anda baru saja ganti HP atau tidak sengaja menghapus foto lokal namun masih ingin menyimpannya di galeri HP.

### Alur Kerja (Workflow)
1.  **Analisis Perbandingan**: Aplikasi membandingkan data di tabel `remote_photos` (apa yang ada di cloud) dengan tabel `photos` (apa yang ada di HP).
2.  **Penyaringan (Filtering)**: Mencari foto yang:
    *   Ada di database Cloud.
    *   **TIDAK** ditemukan file fisiknya di penyimpanan HP (berdasarkan ID remote dan hash konten).
3.  **Proses Pengunduhan**:
    *   Aplikasi meminta Bot Telegram untuk mengirimkan file asli berdasarkan ID-nya.
    *   File diterima dalam bentuk byte stream.
4.  **Penyimpanan Lokal**:
    *   Aplikasi menghitung Hash (sidik jari digital) file untuk memastikan file tidak rusak.
    *   File disimpan ke folder: `Internal Storage/Downloads/Chitralaya/`.
    *   File didaftarkan ke MediaStore Android agar muncul di galeri sistem.
5.  **Pembaruan Database**: Menambahkan catatan baru di database lokal `photos` dengan status `DONE` agar aplikasi tahu foto tersebut sudah aman di HP.

---

## Kesimpulan Perbedaan

| Fitur | Objek yang Diproses | Tujuan Utama |
| :--- | :--- | :--- |
| **Sync Cloud** | Metadata / Informasi | Agar aplikasi "tahu" apa saja yang ada di Telegram. |
| **Restore Missing** | File Fisik (.jpg, .mp4, dll) | Mengembalikan file asli dari Telegram ke memori HP. |

**Urutan yang Disarankan:**
Jika Anda baru menginstal aplikasi di HP baru, lakukan **Import Database** (jika ada backup .json), lalu jalankan **Sync Cloud Photos**, dan terakhir jalankan **Restore Missing Photos** untuk menarik kembali semua foto ke HP Anda.
