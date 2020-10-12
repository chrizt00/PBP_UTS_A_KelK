package com.hendrianto.uts_petshop.entity;

public class Shop {
    private String nama;
    private String jenis;
    private double harga;
    private String imgUrl;

    public Shop(){ }

    public Shop(String nama, String jenis, double harga, String imgUrl){
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
        this.imgUrl = imgUrl;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Shop[] getDataMammals(){
        Shop[] shops = {
                new Shop("Bull Dog","Mammals",9999000,"https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2020/07/20085935/Bulldog-puppy-laying-down-in-the-grass.20200803190248656.jpg"),
                new Shop("Poodle Dog","Mammals",4000000,"https://www.thesprucepets.com/thmb/sA4TZFBqiHjjn2toV36rd5yhVIY=/3264x3264/smart/filters:no_upscale()/portrait-of-brown-poodle-sitting-on-sofa-678441021-590519945f9b5810dccfd490.jpg"),
                new Shop("Persian Cat","Mammals",7500000,"https://www.hospitalveterinariglories.com/wp-content/uploads/2020/06/26-06-20-gato-persa-676x451.jpg"),
                new Shop("Dwarf Rabbit","Mammals",1200000,"https://i.pinimg.com/originals/ce/72/14/ce7214c02608057caa560beda1606914.jpg"),
                new Shop("Alpen Marmoet","Mammals",3200000,"https://upload.wikimedia.org/wikipedia/commons/2/26/Marmota_marmota_Alpes2.jpg"),
                new Shop("Sphynx","Mammals",9000000,"https://images2.minutemediacdn.com/image/upload/c_fill,g_auto,h_1248,w_2220/f_auto,q_auto,w_1100/v1555382975/shape/mentalfloss/sphinxhed.png"),
                new Shop("Holland Lop","Mammals",3300000,"https://vagusnet.com/wp-content/uploads/2019/10/French-Lop-1568x1315.jpg"),
                new Shop("Shih Tzu","Mammals",100000,"https://s3.amazonaws.com/petcentral.com/wp-content/uploads/2016/09/30144407/Shih-Tzu2.jpg"),
                new Shop("White Mice","Mammals",300000,"https://www.peta.org/wp-content/uploads/2019/10/iStock-1054713206-mouse-Flore-Sakowski-1.jpg"),
                new Shop("Russian blue","Mammals",400000,"https://hewany.com/wp-content/uploads/2020/03/Sejarah-Kucing-Russian-Blue.jpg")
               };
        return shops;
    }

    public Shop[] getDataFish(){
        Shop[] shops = {
                new Shop("Betta Fish","Fish",3000000,"https://cdn.mos.cms.futurecdn.net/RY2EpSo74hvYXyAVpTN2Gg-1200-80.jpg"),
                new Shop("Arowana Fish","Fish",4000000,"https://4.imimg.com/data4/WG/CV/ANDROID-66209662/product-500x500.jpeg"),
                new Shop("Starfish","Fish",1000000,"https://worldstrides.com/wp-content/uploads/2015/10/starfish-e1332425283914-1280x720.jpg"),
                new Shop("Seahorses","Fish",7500000,"https://www.newportaquarium.com/-/media/Project/HFE/NAQ/Animals/Seahorse_Card.jpg?h=600&iar=0&w=600"),
                new Shop("Clownfish","Fish",1500000,"https://floridapets.com/WebRoot/Store6/Shops/047c0125-dd4b-4d34-97de-30717de1adee/5BFC/132D/7548/AD52/2AE3/0A48/331F/297B/Ocellaris-clown-seg.jpg"),
                new Shop("Blue Discus","Fish",1000000,"https://cdn11.bigcommerce.com/s-kkon4imfg5/images/stencil/1280x1280/products/600/866/pro1__78412.1522375309.jpg?c=2"),
                new Shop("Clown Botia","Fish",3000000,"https://www.ikanhias.id/wp-content/uploads/2019/12/botia-clown-loach-1-630x380.jpg"),
                new Shop("Gupi Fish","Fish",4000000,"https://ecs7.tokopedia.net/img/cache/700/product-1/2018/1/13/15894078/15894078_aea440ca-b1ca-4ed0-815a-a3f6df2a8be7_450_327.jpg"),
                new Shop("Apple Snail","Fish",2500000,"https://upload.wikimedia.org/wikipedia/commons/9/9e/Pomacea.jpg"),
                new Shop("Blue Crab","Fish",7000000,"https://i.ytimg.com/vi/D7MgdoVOmWk/maxresdefault.jpg")
        };
        return shops;
    }
    public Shop[] getDataReptile(){
        Shop[] shops = {
                new Shop("Baby Python","Reptile",2000000,"https://preview.redd.it/dkem07wznfk41.jpg?auto=webp&s=061e23da995d65bd547da4268316203cfd6a598c"),
                new Shop("Chameleon","Reptile",2000000,"https://ecs7.tokopedia.net/img/cache/700/product-1/2019/3/8/36825808/36825808_f3f5efd4-b3ec-4e5f-a29c-eca9958c5502_700_700.jpg"),
                new Shop("Green Chameleion","Reptile",1000000,"https://previews.123rf.com/images/gaschwald/gaschwald1307/gaschwald130700008/20985304-green-chameleon-on-a-tree.jpg"),
                new Shop("Emydidae","Reptile",4000000,"https://i.pinimg.com/originals/59/40/67/594067b50f090415b2d2a6396d998665.jpg"),
                new Shop("Chelydridae","Reptile",1000000,"https://www2.illinois.gov/dnr/education/PublishingImages/WATSnapping.JPG"),
                new Shop("Green Iguana","Reptile",7000000,"https://undergroundreptiles.com/wp-content/uploads/2020/05/ug_baby_green_iguana_.jpg")
        };
        return shops;
    }

    public Shop[] getDataBird(){
        Shop[] shops = {
                new Shop("Love Bird","Bird",2000000,"https://cdn.idntimes.com/content-images/post/20200122/lovebirds-497303431-940x529-903f4cb3f6e025da496cf809920cc604_600x400.jpg"),
                new Shop("Canaries","Bird",3000000,"https://petcentral.chewy.com/wp-content/uploads/shutterstock_775421128-940x624.jpg?x92391"),
                new Shop("Parakeet","Bird",5000000,"https://www.thesprucepets.com/thmb/odJ4k1tvHWh2FNxkvF4Pr0MGGv4=/3280x3280/smart/filters:no_upscale()/parakeet-budgie-budgerigar-952787256-5b52a7d046e0fb0037bec8a7.jpg"),
                new Shop("Cockatiel","Bird",6000000,"https://static3.bigstockphoto.com/4/5/1/large1500/1546775.jpg"),
                new Shop("Owl","Bird",7000000,"https://ogden_images.s3.amazonaws.com/www.lockhaven.com/images/2019/12/27162258/Owl1-560x840.jpg"),
                new Shop("Parrot","Bird",2500000,"https://cdn.britannica.com/s:800x450,c:crop/87/196687-138-2D734164/facts-parrots.jpg"),
                new Shop("Sparrows","Bird",1200000,"https://www.thespruce.com/thmb/-JU3VTdAZWcs2cbAJcMz_2ft4x0=/1000x1000/smart/filters:no_upscale()/house-sparrow-5974a13bd963ac00103febf7.jpg")
                };
        return shops;
    }
    public Shop[] getDataAccesories(){
        Shop[] shops = {
                new Shop("Bird Cage","Accesories",3000000,"https://i.pinimg.com/originals/23/7e/2e/237e2ee45dcc68c8094049c276280af3.jpg"),
                new Shop("Dog/Cat Cage","Accesories",2000000,"https://cdn.elevenia.co.id/ex_t/R/360x360/1/90/1/src/g/5/6/4/3/2/5/24564325_B.jpg"),
                new Shop("Fish/Reptile Aquarium","Accesories",3000000,"https://upload.wikimedia.org/wikipedia/commons/a/a8/Amaterske_akvarium.jpg"),
        };
        return shops;
    }
    public Shop[] getDataFood(){
        Shop[] shops = {
                new Shop("Dog Food","Food",30000,"https://images-na.ssl-images-amazon.com/images/I/81U6D983SxL._AC_SL1500_.jpg"),
                new Shop("Cat Food","Food",50000,"https://images-na.ssl-images-amazon.com/images/I/61nLtOuAlYL._SX425_.jpg"),
                new Shop("Bird Food","Food",60000,"https://images-na.ssl-images-amazon.com/images/I/81gv0cbscjL._AC_SL1500_.jpg"),
                new Shop("Pellets","Food",70000,"https://id-test-11.slatic.net/p/9b5ce0c5805c07d2d8ea7ce9c519dd54.jpg_340x340q80.jpg_.webp"),
                new Shop("Small Incets","Food",30000,"https://www.thermaxglobal.com/wp-content/uploads/2020/05/image-not-found.jpg")
          };
        return shops;
    }

}
