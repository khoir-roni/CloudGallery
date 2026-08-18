# Changelog

Semua perubahan penting pada proyek **CloudGallery** (Chitralaya) akan dicatat di dokumen ini.

---

## [0.75] - 2026-08-18

### Diperbaiki
- **Optimasi Performa Grid Cloud**: Mengganti sistem Paging dengan pemuatan daftar lengkap untuk memastikan ribuan foto cloud ter-load dan terkelompokkan berdasarkan tanggal secara instan tanpa ada data yang tertinggal.
- **Stabilitas UI (Anti-Freeze)**: Optimalisasi proses sinkronisasi database dengan sistem *Batch Delete* dan pencarian efisien untuk mencegah aplikasi macet (frozen) saat memproses belasan ribu data foto.
- **Kompatibilitas Database (v11)**: Menambahkan dukungan field `topicId` dan `topicName` (info album) pada tabel foto lokal agar riwayat album dari perangkat lama tetap terjaga.
- **Bug Sinkronisasi Cloud**: Memperbaiki `ClassCastException` pada penyimpanan data preferensi waktu sinkronisasi.

### Diubah
- Peningkatan versi aplikasi (`versionCode` ke `13` dan `versionName` ke `"0.75"`).
- Perbaikan konfigurasi JDK pada proyek untuk kompatibilitas build yang lebih baik.

---

## [0.74] - 2026-08-15

### Diperbaiki
- **Stabilitas Import/Sync Database**:
  - Mengganti penggunaan *spread operator* dengan List-based insertion untuk mencegah crash saat memproses database dengan jumlah foto yang sangat besar.
  - Memperbaiki alur import yang sebelumnya menyebabkan data "hilang" karena dihapus otomatis oleh proses sinkronisasi background. Sekarang aplikasi memicu sinkronisasi manual segera setelah import selesai.
  - Memperbaiki logika identifikasi perangkat saat import agar tetap sinkron meskipun aplikasi di-instal ulang.

---

## [0.73] - 2026-08-15

### Diperbaiki
- **Bug Import Database**: Memperbaiki masalah di mana data foto lokal tidak ter-import jika melakukan instal ulang aplikasi (karena perubahan ID perangkat). Sekarang aplikasi akan otomatis mengenali dan mengadopsi identitas perangkat dari file backup jika database dalam keadaan kosong.

---

## [0.72] - 2026-08-15

### Ditambahkan
- **Pengaturan Batch Size Backup**: Sekarang Anda dapat memilih jumlah foto yang akan diunggah dalam satu sesi backup (50, 100, 1000, atau Unlimited).

### Diubah
- Peningkatan versi aplikasi (`versionCode` ke `10` dan `versionName` ke `"0.72"`).

---

## [0.71] - 2026-08-14

### Ditambahkan
- **Konfigurasi Telegram di Settings**: Menambahkan kemampuan untuk memperbarui *Bot Token* dan *Group/Chat ID* langsung dari menu pengaturan tanpa harus melakukan instal ulang aplikasi atau melewati ulang proses *onboarding*.
- **Fitur Toggle "Backup Until Finished"**:
  - Pilihan pengaturan baru di menu *Settings* untuk mengunggah seluruh foto yang tertunda sekaligus, menggantikan batasan default (50 foto per batch).
  - Integrasi dengan Android *Foreground Service* agar proses pengunggahan di latar belakang stabil dan tidak dihentikan paksa oleh sistem operasi saat mengunggah banyak foto.
  - Penanganan penghentian tugas yang aman menggunakan flag `isStopped` untuk menghormati pembatalan dari sistem atau pengguna.

### Diubah
- Peningkatan versi aplikasi (`versionCode` ke `9` dan `versionName` ke `"0.71"`).

### Diperbaiki
- **Masalah Upload pada APK Release**: Menambahkan aturan ProGuard/R8 untuk mencegah penghapusan kode pada library Telegram.
- **Bot Initialization**: Memperbaiki masalah di mana bot tidak memperbarui token secara otomatis setelah proses Setup selesai, yang sebelumnya menyebabkan error `401 Unauthorized`.
