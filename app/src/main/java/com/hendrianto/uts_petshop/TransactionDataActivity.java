package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hendrianto.uts_petshop.adapter.ShopAdapter;
import com.hendrianto.uts_petshop.adapter.TransactionAdapter;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.api.APITransaction;
import com.hendrianto.uts_petshop.entity.Shop;
import com.hendrianto.uts_petshop.entity.Transaction;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class TransactionDataActivity extends AppCompatActivity {
        private List<Transaction> listData;
        private RecyclerView recycler;
        private TransactionAdapter adapter;
        ExtendedFloatingActionButton print;
        private static final String TAG = "PdfCreatorActivity";
        final private int REQUEST_CODE_ASK_PERMISSIONS = 101;
        private File pdfFile;
        private PdfWriter writer;
        private AlertDialog.Builder builder;
        private PdfViewModel pdfViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_data);

        recycler = findViewById(R.id.recycler_view);
        listData = new ArrayList<Transaction>();
        print = findViewById(R.id.print);
        pdfViewModel = new ViewModelProvider(this).get(PdfViewModel.class);

        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, APITransaction.URL_SELECT
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id                  = jsonObject.optInt("id");
                        String nama_barang      = jsonObject.optString("nama_barang");
                        String jenis            = jsonObject.optString("jenis");
                        int jumlah              = jsonObject.optInt("jumlah");
                        double harga            = jsonObject.optDouble("harga");
                        String email            = jsonObject.optString("email");

                        Transaction transaction = new Transaction(id,nama_barang,jenis,email,harga,jumlah);
                        //Menambahkan objek user tadi ke list user
                        listData.add(transaction);
                    }
                    adapter = new TransactionAdapter(TransactionDataActivity.this,listData);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TransactionDataActivity.this);
                    recycler.setLayoutManager(layoutManager);
                    recycler.setAdapter(adapter);

                    print.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TransactionDataActivity.this);

                            alertDialogBuilder.setTitle("Print this ?");
                            alertDialogBuilder
                                    .setMessage("Click Yes to Confirm")
                                    .setIcon(R.mipmap.ic_launcher)
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            try {
                                                createPdfWrapper();
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (DocumentException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            // membuat alert dialog dari builder
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // menampilkan alert dialog
                            alertDialog.show();
                        }
                    });
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                Toast.makeText(TransactionDataActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
    private void createPdf() throws FileNotFoundException, DocumentException {
        //isikan code createPdf()

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Download/");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Direktori baru untuk file pdf berhasil dibuat");
        }
//        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                REQUEST_CODE_ASK_PERMISSIONS);
        String pdfname = "Data_Transaksi_Oracle_Shop"+".pdf";
        pdfFile = new File(docsFolder.getAbsolutePath(), pdfname);
        OutputStream output = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
        writer = PdfWriter.getInstance(document, output);
        document.open();
        Paragraph judul = new Paragraph("Data Transaksi Oracle Pet Shop \n\n", new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 16,
                com.itextpdf.text.Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);
        PdfPTable tables = new PdfPTable(new float[]{16, 8});
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);

        Paragraph Kepada= new Paragraph(
                "Kepada Yth : \n" + "Manager Oracle Pet Shop"+"\n",
                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                        com.itextpdf.text.Font.NORMAL, BaseColor.BLACK)
        );
        cellSupplier.addElement(Kepada);
        tables.addCell(cellSupplier);
        PdfPCell cellPO = new PdfPCell();
        Paragraph NomorTanggal = new Paragraph(
                "UAS PBP" + "Kelompok K" + "\n\n" +
                        "Tanggal : " + "6 Desember 2020" + "\n",
                new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                        com.itextpdf.text.Font.NORMAL, BaseColor.BLACK)
        );
        NomorTanggal.setPaddingTop(5);
        tables.addCell(NomorTanggal);
        document.add(tables);
        com.itextpdf.text.Font f = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
        Paragraph Pembuka = new Paragraph("\nBerikut ini merupakan Data Transaksi Dari Oracle Pet Shop: \n\n",f);
        Pembuka.setIndentationLeft(20);
        document.add(Pembuka);
        PdfPTable tableHeader = new PdfPTable(new float[]{4,4,4});
        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        PdfPCell h1 = new PdfPCell(new Phrase("Nama Item"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Harga"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h4 = new PdfPCell(new Phrase("Pembeli"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h4);
        PdfPCell[] cells = tableHeader.getRow(0).getCells();
        for (int j = 0; j < cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{4,4,4});
        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        int arrLength = listData.size();
        for(int x=0;x<arrLength;x++){
            for(int i=0;i<cells.length;i++){
                if(i==0){
                    tableData.addCell(listData.get(x).getNama_barang());
                }else if(i==1){
                    tableData.addCell(String.valueOf(listData.get(x).getHarga())+" * "+
                            String.valueOf(listData.get(x).getJumlah())+" Unit");
                }else{
                    tableData.addCell(String.valueOf(listData.get(x).getEmail()));
                }
            }
        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String tglDicetak = sdf.format(currentTime);
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_RIGHT);
        document.add(P);
        document.close();
        previewPdf();

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(TransactionDataActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdfWrapper() throws FileNotFoundException, DocumentException {

        int hasWriteStoragePermission = 0;
//        Toast.makeText(TransactionDataActivity.this,"test"+Build.VERSION.SDK_INT+Build.VERSION_CODES.M,Toast.LENGTH_SHORT).show();
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("Izinkan aplikasi untuk akses penyimpanan?",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new
                                                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                        Toast.makeText(TransactionDataActivity.this,"test"+Build.VERSION.SDK_INT+Build.VERSION_CODES.M,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        } else {
            createPdf();
        }

    }

    private void previewPdf() {

        //isikan code previewPdf()
        PackageManager packageManager = TransactionDataActivity.this.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {uri = FileProvider.getUriForFile(TransactionDataActivity.this,
                    TransactionDataActivity.this.getPackageName()+".provider",
                    pdfFile);
            } else {
                uri = Uri.fromFile(pdfFile);
            }
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(uri, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            TransactionDataActivity.this.grantUriPermission("com.hendrianto.uts_petshop", uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(pdfIntent);
        } else {

        }

    }
}