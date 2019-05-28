# spring-config-client
Spring Config Client membutuhkan Spring Config Server, yaitu pada saat aplikasi client hidup dan berdasarkan property yang ada di client, maka client akan berusaha untuk meminta konfigurasi di Spring Config Server berdasarkan nama aplikasi. Nama aplikasi di sini berperan secara krusial

# dependencies
Config Client
Actuator
Web

# how to
1. Tidak ada penambahan anotation pada SpringApplication Class
2. Untuk mengetest konfigurasi dari sisi client terdapat 2 hal yang perlu di pertimbangkan
a. Konfigurasi ke load dari Server Config
b. Konfigurasi dapat di reload bila terdapat perubahan dari konfigurasi
  
