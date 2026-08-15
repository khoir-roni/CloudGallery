# Changelog

Semua perubahan penting pada proyek **CloudGallery** (Chitralaya) akan dicatat di dokumen ini.

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
