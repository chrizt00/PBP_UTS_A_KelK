package com.hendrianto.uts_petshop.entity;

public class News {
    private int id;
    private String title;
    private String caption;
    private String paragraph1;
    private String paragraph2;
    private String paragraph3;
    private String paragraph4;
    private String imageUrl;
    private String source;

    public News(){}

    public News(int id,String title, String caption, String paragraph1,String paragraph2,
                String paragraph3,String paragraph4,String imageUrl,String source){
        this.setId(id);
        this.setTitle(title);
        this.setCaption(caption);
        this.setParagraph1(paragraph1);
        this.setParagraph2(paragraph2);
        this.setParagraph3(paragraph3);
        this.setParagraph4(paragraph4);
        this.setImageUrl(imageUrl);
        this.setSource(source);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getParagraph1() {
        return paragraph1;
    }

    public void setParagraph1(String paragraph1) {
        this.paragraph1 = paragraph1;
    }

    public String getParagraph2() {
        return paragraph2;
    }

    public void setParagraph2(String paragraph2) {
        this.paragraph2 = paragraph2;
    }

    public String getParagraph3() {
        return paragraph3;
    }

    public void setParagraph3(String paragraph3) {
        this.paragraph3 = paragraph3;
    }

    public String getParagraph4() {
        return paragraph4;
    }

    public void setParagraph4(String paragraph4) {
        this.paragraph4 = paragraph4;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public News[] getAllNews(){
        News[] newsdata = {
                new News(1,"Hewan di Bumi Sulit Beradaptasi dengan Perubahan Iklim","Para ahli biologi telah memperingatkan bahwa hewan-hewan di Bumi berisiko mengalami kepunahan karena suhu Bumi yang menghangat.",
                        "Para ahli biologi telah memperingatkan bahwa hewan-hewan di Bumi berisiko mengalami kepunahan karena habitatnya menghangat lebih cepat sebelum mereka bisa beradaptasi. Menurut hasil meta-analisis terbaru, kondisi itu berlaku kepada semua spesies.\n" +
                                "Bumi telah memanas dan mendingin beberapa kali. Setiap siklusnya bertanggung jawab atas musnahnya beberapa spesies di Bumi. Namun, pada kasus sebelumnya, perubahan berlangsung cukup lambat sehingga hewan-hewan mampu beradaptasi sambil menemukan tempat tinggal baru.",
                        "Beberapa dari mereka berhasil bertahan dengan berganti habitat, sementara yang lainnya bergantung dengan perubahan genetika. Hilangnya spesies kemudian diimbangi dengan kemunculan spesies baru.\n" +
                                "\n" +
                                "Sayangnya, dengan suhu global yang meningkat lebih cepat dari jutaan tahun lalu, maka tantangan yang mereka hadapi lebih besar. Lalu bagaimana hewan-hewan tersebut menghadapinya? Menurut Dr. Viktoriia Radchuk, dari Leibniz Institute for Zoo and Wildlife Research, hasilnya tidak begitu baik.",
                        "Meninjau 10.090 abstrak dan 71 penelitian mengenai 4.835 spesies, Radchuk menyatakan bahwa hewan sedang beradaptasi dengan perubahan iklim, tapi mereka tidak cukup cepat untuk mengimbanginya.\n"+
                                "Pada studi Radchuk yang dipublikasikan di Nature Communications ini , ia menemukan banyak perubahan dalam tubuh dan perilaku hewan selama beberapa dekade terakhir–sebagian besar akibat naiknya suhu. Itu memengaruhi perubahan waktu reproduksi dan migrasi hewan.\n" +
                                "\n" +
                                "Hewan-hewan biasanya berkembang biak lebih awal di musim semi ketika iklim menghangat. Namun, belakangan ini, musim semi berubah lebih cepat dari yang lainnya sehingga mengganggu ekosistem.",
                        "","https://asset-a.grid.id/crop/0x0:0x0/700x465/photo/2019/04/22/1861632199.png",
                        "source : nationalgeographic.grid.id"),
                new News(2,"Di Masa Mendatang, Ukuran Hewan Akan Menyusut Demi Bertahan Hidup ","Ukuran hewan di Bumi akan semakin mengecil di masa depan. Pasalnya, mereka 'terpaksa' menyusut agar bisa bertahan hidup.",
                        "Ukuran hewan di Bumi akan semakin mengecil di masa depan. Pasalnya, mereka 'terpaksa' menyusut agar bisa bertahan hidup. Menurut para ilmuwan, spesies-spesies hewan bertubuh besar akan punah akibat ulah manusia yang merusak habitat mereka. \n" +
                                "\n" +
                                "Hewan pengerat, termasuk hamster kerdil dan burung penyanyi seperti sparrow-weaver berkulit putih, kemungkinan besar berhasil bertahan hidup. Namun, elang kuning kecoklatan dan badak hitam akan menghadapi kepunahan.",
                        "Rata-rata, makhluk hidup di Bumi diharapkan dapat mengecil 25% dalam 100 tahun agar mereka dapat beradaptasi dengan ancaman deforestasi, perburuan, alih fungsi lahan, urbanisasi, dan pemanasan global. \n" +
                                "\n" +
                                "Saat ini, ukuran tubuh spesies berkurang 14% dalam 130 ribu tahun terakhir sejak Zaman Es.\n" +
                                "\n" +
                                "Untuk mendapatkan hasil penelitian ini, para ahli dari Southampton University mempelajari massa tubuh, habitat, pola makan, dan harapan hidup dari 15.484 hewan-hewan dan burung yang masih hidup.",
                        "Mereka juga menggunakan daftar hewan terancam dari International Union for Conservation of Nature untuk memprediksi kepunahan. \n" +
                                "\n" +
                                "\"Di masa depan, spesies hewan yang bertubuh lebih kecil dan berumur pendek dapat tumbuh subur di berbagai habitat sehingga mereka dapat mendominasi,\" ujar salah satu juru bicara.",
                        "","https://asset-a.grid.id/crop/0x0:0x0/700x465/photo/2018/11/05/1927916012.jpg","source : nationalgeographic.grid.id"),
                new News (3,"Ini Segudang Manfaat Pelihara Ikan di Tengah Pandemi","Pandemi Covid-19 telah menciptakan beragam hobi baru di kalangan masyarakat. Salah satu yang diminati saat ini adalah memelihara ikan di akuarium.",
                        "Dalam menjalani aktivitas harian, kita tentu tidak pernah lepas dari rasa stres. Dengan memelihara ikan di akuarium, masalah kesehatan mental ini rupanya bisa diatasi. Menurut sebuah penelitian, hal tersebut disebabkan oleh warna ikan di dalam akuarium. Para ahli menyimpulkan bahwa ini bisa memberikan efek yang menenangkan sehingga membuat seseorang menjadi lebih rileks.",
                        "Tak jarang banyak orang yang mengalami kesulitan untuk tidur di malam hari. Padahal, istirahat yang cukup adalah kunci dari kesehatan jangka panjang. Nah, bagi Anda yang mengalami insomnia, memelihara ikan di akuarium pun bisa mengatasi masalah ini. Khususnya jika diletakkan di dalam kamar tidur, suara aliran airnya yang lembut akan membantu Anda tertidur lebih cepat.",
                        "Memiliki tangki berisikan ikan ternyata juga dapat meningkatkan kesehatan jantung Anda. Menurut sebuah penelitian, melihat tangki ikan dapat memperlambat detak jantung dan menurunkan tekanan darah Anda sebanyak tiga hingga tujuh persen. Tak heran jika Anda memiliki masalah tekanan darah tinggi dan masalah kesehatan jantung lainnya, para ahli akan menyarankan untuk memelihara ikan.",
                        "Akuarium ikan yang diletakkan di ruang kerja Anda dipercaya dapat membantu meningkatkan fokus dan kreativitas Anda secara maksimal. Stimulasi visual dari akuarium ikan yang aktif mungkin juga memiliki efek terapeutik sehingga memberikan pikiran sesuatu yang lain untuk difokuskan selama beberapa waktu, terlebih saat Anda sedang dihadapkan oleh suatu masalah.\n",
                        "https://statik.tempo.co/data/2018/07/06/id_717101/717101_720.jpg","source : gaya.tempo.co"),
                new News (4,"Kucing Sudah Jadi Peliharaan di Jalur Sutra Sejak 1.000 Tahun","Tidak hanya di peradaban Mesir kuno, kucing juga diyakini menjadi hewan peliharaan orang-orang di Jalur Sutra sejak 1.000 tahun lalu.",
                        "Claudio Ottoni, seorang peneliti pasca-doktoral di Universitas Sapienza Roma, yang bekerja pada penelitian lain yang berfokus pada asal-usul kucing domestik di Afrika Timur. Kerangka kucing yang ditemukan di sepanjang Jalur Sutra itu langka dan merupakan bukti paling awal dari sisa kucing domestik di wilayah itu, katanya.\n" +
                                "\n" +
                                "\"Kucing telah banyak diabaikan untuk waktu yang lama karena sisa-sisa mereka dalam konteks arkeologis jarang terjadi,\" kata Ottoni. \"Plus, identifikasi mereka berdasarkan bukti osteologis seringkali tidak mudah.\"\n" +
                                "\n" +
                                "Osteologi, studi tentang tulang, menceritakan kisah kehidupan binatang, dan kerangka kucing ini memiliki banyak hal untuk dikatakan.",
                        "\"Tulang-tulang itu tidak hanya memberi tahu kita hewan apa itu, tetapi juga memberi tahu kita beberapa hal lain, seperti leluhurnya (melalui DNA purba) dan makanannya (melalui analisis isotop kimia),\" kata Haruda kepada CNN via email.\n" +
                                "\n" +
                                "Hewan itu menderita banyak trauma dalam hidup menurut temuan dalam penelitian. Pertama-tama, kucing itu menderita beberapa patah tulang dan tidak memiliki set gigi lengkap ketika mati.\n" +
                                "\n" +
                                "\"Kita bisa melihat bahwa itu telah kehilangan gigi taring dan beberapa gigi lainnya sepenuhnya dan bahwa akar gigi telah sembuh,\" kata Haruda. \"Hilangnya gigi-gigi ini akan membuat kucing sulit berburu dengan sukses.\"",
                        "Analisis lebih lanjut menunjukkan bahwa kucing itu dirawat dengan baik berdasarkan makanannya, kata Haruda.\n" +
                                "\n" +
                                "\"Analisis kimiawi terhadap tulang menunjukkan bahwa kucing memiliki diet protein yang sangat tinggi, lebih tinggi daripada anjing dan hewan lain di situs arkeologi yang sama, jadi itu bukan, misalnya, memakan bubur gandum atau produk limbah lainnya,\" kata Haruda . \"Sebaliknya, lebih mungkin seseorang memberi kucing makanan daging.\"",
                        "Kucing itu kemungkinan besar dimiliki oleh orang-orang Oghuz, suku Turki penggembala, menurut penelitian. Orang-orang Oghuz tinggal di stepa Asia Tengah dekat Mongolia modern, Kazakhstan, dan beberapa negara di sekitarnya, menurut Haruda.\n" +
                                "\n" +
                                "Meskipun orang-orang ini sering bepergian, mereka juga memiliki ibu kota bernama Dhzankent yang terletak di Kazakhstan modern, di mana kerangka kucing itu ditemukan. Tidak biasa menemukan kucing peliharaan di sini karena orang-orang Oghuz hanya memiliki binatang yang memiliki tujuan, kata Haruda. Sebagai contoh, anjing digunakan untuk menjaga kawanan gembala, kata Haruda.\n" +
                                "\n" +
                                "Penemuan kerangka kucing ini mengungkapkan bahwa mereka dipelihara sebagai hewan peliharaan, yang Haruda gambarkan sebagai pertukaran budaya di sepanjang Jalur Sutra.",
                        "https://cdn.tmpo.co/data/2020/07/18/id_953699/953699_720.jpg","source : dunia.tempo.co"),
                new News (5,"Mengapa Kucing Suka Mengedipkan Matanya Secara Perlahan?","Ternyata ini alasan mengapa kucing suka mengedipkan matanya secara perlahan.",
                        "Mikel Delgado, peneliti kucing di Fakultas Kedokteran Hewan Universitas University California, mengatakan kucing sering mengungkapkan emosi negatif dengan menghindari kontak mata. Jika mereka takut atau merasa terancam, mereka tidak akan menatap mata. ",
                        "Jika mereka fokus pada sesuatu tanpa berkedip, kemungkinan mereka merasa agresif. \n" +
                                "\n" +
                                "Oleh karena itu, salah satu cara kucing dapat melakukan kontak mata tanpa memberi tanda bahwa Anda akan diserang adalah mengedipkan mata.",
                        "Ini bukan gerakan refleksif juga. Kucing telah diamati bahwa mereka berkedip dengan cara yang berbeda, menutup mata mereka dengan cepat dan membuka dengan lambat sementara juga menutup dan membuka dengan kecepatan yang disengaja. Dengan kata lain, kucing itu berkedip dengan cara yang diinginkannya.",
                        "Meskipun itu mungkin bukan ungkapan cinta, itu mungkin merupakan tanda kepuasan. Jika kucing merasa nyaman berada di dekat manusia, memejamkan matanya adalah indikasi bahwa ia tidak perlu memantau Anda sebagai ancaman.",
                        "https://cdn1-production-images-kly.akamaized.net/YA4uz4dVBcVmCKlm75R2U7XDj2o=/640x360/smart/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3017613/original/057211900_1578567631-hima.jpg","source : liputan6.com"),
                new News (6,"Kamu Pencinta Kucing atau Anjing? Pilihanmu Bisa Ungkap Kepribadian","Ternyata kalian bisa loh dengan mudah menebak kepribadian seseorang dari hewan peliharaannya, simak penjelasannya berikut ini.",
                        "Apa kalian adalah pemelihara kucing? Kalian yang merupakan pencinta kucing dikatakan sebagai sosok yang penuh percaya diri dan lemah lembut. Kalian juga dikatakan sebagai orang yang sederhana, namun tahu betul bagaimana menempatkan dirinya dengan baik di mana pun berada.",
                        "Sementara untuk urusan perasaan, para pencinta kucing dikatakan cukup sensitif. Kalian adalah pribadi yang senang diperhatikan, disayangi, serta dicintai dengan tulus. Sebuah studi yang dilakukan di Carroll University di Wisconsin mengungkapkan para pencinta kucing juga cukup introvert namun cerdas dan kreatif. Namun, jika disakiti mereka akan sulit sekali untuk memaafkan.",
                        "Nah, sekarang bagaimana dengan kepribadian orang pencinta anjing? Mereka dikatakan sebagai orang yang cukup loyal. Mereka juga disebut sebagai pribadi yang menyenangkan, penuh percaya diri, dan mudah bergaul. Namun meski mudah bergaul, mereka tetap berhati-hati terhadap orang yang baru dikenal.",
                        "Tak cukup sampai di situ, para pencinta anjing dikatakan sebagai sosok penyayang keluarga. Mereka juga merupakan pendengar dan penasihat yang sangat baik. Tak hanya itu, pencinta anjing merupakan orang-orang yang cukup berpengaruh di lingkungannya.",
                        "https://cdn0-production-images-kly.akamaized.net/EUp1m3A87euwusTxYUqUxz2xGZI=/0x0:2896x1632/640x360/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/2387501/original/046350700_1539926875-1.jpg","source : liputan6.com"),
                new News (7,"Bagaimana Cara Membuat Kucing dan Anjing Berteman?","Seringkali binatang peliharaan yang berbeda jenis saling bertengkar satu sama lain. Salah satu contoh yang paling lekat adalah antara anjing dan kucing",
                        "Menurut Hartstein, Anda dapat membantu anjing dan kucing untuk memiliki ikatan dengan melatih mereka secara individu kemudian bersama-sama.  Langkah ini penting agar kucing dan anjing dapat saling menunjukkan kemampuan dan berkomunikasi dengan pemilik secara individu, dan juga dengan lingkungan yang lebih luas, yaitu dengan anggota keluarga atau binatang peliharaan lain. Dengan mempraktikan latihan secara individu dan kemudian bersama-sama, Anda akan mengajarkan binatang peliharaan tentang kesabaran.\n" +
                                "\n",
                        "Mungkin sebagian dari Anda berpikir untuk menggunakan teknik hukuman jika anjing atau kucing bertindak agresif satu sama lain. Namun, sebaiknya memberikan penghargaan kepada binatang peliharaan saat mereka tenang dan baik satu sama lain.\n" +
                                "\nJangan meremehkan manfaat dari memiliki waktu bersama. Penting untuk bermain di dekat satu sama lain untuk melatih toleransi. Namun, pastikan untuk tidak melakukan permainan yang akan membuat mereka terlalu bersemangat karena dapat membuat mereka menjadi terlalu reaktif.\n",
                        "Penting untuk memberikan ruang pada masing-masing hewan peliharaan karena memaksanya selalu bersama bukanlah pilihan terbaik. \"Anda harus mengarantina anjing dan kucing jika keduanya terlibat dalam pertengkaran atau stres yang intens,\" kata Harstein. Artinya, keduanya berada dalam ruangan terpisah selama beberapa waktu. Tindakan ini sama dengan yang diberlakukan pada manusia, di mana terkadang setiap orang butuh waktunya sendiri dari orang lain setelah bertengkar.\n",
                        "Saat memelihara hewan, salah satu aspek terpenting yang perlu dijaga adalah keamanan fisik. Jika Anda benar-benar tidak bisa mendamaikan anjing dan kucing yang dimiliki, tidak ada salahnya meminta bantuan kepada ahlinya.  Akan tetapi, yang terpenting adalah, lakukan yang terbaik dan terus tunjukan kasih sayang kepada mereka. \n" +
                                "\n",
                        "https://asset.kompas.com/crops/HDqDEHkhMa2vRKe5L6OHjueUzm8=/0x0:780x390/750x500/data/photo/2015/05/12/1606319shutterstock-234079726780x390.jpg","source : kompas.com"),
                new News (8,"Waduh, Hewan Peliharaan Ternyata Lebih Rentan Terinfeksi Covid-19","Terdapat Peran Potensial Hewan Peliharaan Dalam Penyebaran Virus Corona.",
                        "Hewan peliharaan mungkin lebih rentan terhadap infeksi Covid-19 daripada yang diperkirakan sebelumnya. Pernyataan tersebut disampaikan peneliti dari Prancis belum lama ini.\n" +
                                "\n" +
                                "Dalam laporan South China Morning Post, tim peneliti dari French National Research Institute for Sustainable Development mengumpulkan sampel darah dari 47 kucing dan anjing yang tinggal bersama dengan keluarga yang setidaknya ada satu anggotanya dinyatakan positif Covid-19. Ke-47 anjing dan kucing tersebut pun menjalani tes antibodi SARS-CoV2.",
                        "Dari hasil penelitian, diketahui bahwa lebih dari 20 persen hewan-hewan tersebut dinyatakan positif Covid-19 dalam tiga kali tes, lalu 53 persen dinyatakan positif dalam sekali tes. Data ini memperbaharui fakta sebelumnya yang hanya menyatakan nol hingga 15 persen saja hewan peliharaan bisa terinfeksi Covid-19.\n" +
                                "\n" +
                                "Studi Prancis ini dipublikasikan di situs pracetak bioRxiv.org pada Selasa (22/9/2020) yang berarti temuan ini belum ditinjau rekan sejawat.\n" +
                                "\n" +
                                "\"Hasilnya terdapat peran potensial hewan peliharaan dalam penyebaran virus corona,\" kata Dr Eric Leroy, ketua penelitian.",
                        "Menjadi catatan penting di sini ialah Leroy mengatakan, timnya tidak menemukan strain virus korona hidup dari hewan peliharaan mana pun, yang menunjukkan sangat tidak mungkin patogen itu dapat ditularkan oleh hewan ke manusia yang mereka temui saat berada di luar rumah.\n" +
                                "\n" +
                                "Meski begitu, orang yang sering melakukan kontak dekat dengan hewan yang terinfeksi Covid-19 harus melakukan tindakan pencegahan. Perlu diketahui, studi di China menemukan fakta bahwa kucing rupanya dua kali lebih mungkin tertular virus corona daripada anjing.",
                        "Sementara itu, studi terpisah yang dilakukan ilmuwan di Spanyol menemukan bahwa angka kematian di antara anjing yang menderita penyakit pernapasan meningkat selama pandemi. Berdasarkan data yang dikumpulkan dari dokter hewan antara April hingga Juni, tim menemukan bahwa tingkat kematian meningkat dari 1 ke 2 persen dan kini menjadi setinggi 40 persen.\n" +
                                "\n" +
                                "Tim peneliti ini pun mengatakan dalam makalah yang diterbitkan di bioRxiv.org bahwa tidak jelas apakah lonjakan kasus tersebut dapat dikaitkan dengan krisis kesehatan yang memengaruhi manusia sekarang. Namun, antibodi virus corona ditemukan pada 40 anjing yang sakit atau mati di periode tersebut.",
                        "https://img.okezone.com/content/2020/09/28/612/2284712/waduh-hewan-peliharaan-ternyata-lebih-rentan-terinfeksi-covid-19-pbN1X3NnDt.jpg","source : okezone.co.id"),

        };
        return newsdata;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
