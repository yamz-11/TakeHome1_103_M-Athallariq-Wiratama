/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.pws.take.home1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Wiratama
 */
@Controller
public class tokoController {
    
    @RequestMapping("/toko")
    public String buyer(@RequestParam(value="nama") String nama,
                        @RequestParam(value="harga") String harga,
                        @RequestParam(value="jumlah") String item,
                        @RequestParam(value="bayar") String bayar,
                        Model model){
        
        int hargaAwal, jmlh, totalAwal, totalAkhir, diskon, tunai;
        
        hargaAwal = Integer.parseInt(harga);
        jmlh = Integer.parseInt(item);
        tunai = Integer.parseInt(bayar);
        
        // Proses hitung diskon dan kembalian
        totalAwal = getTotal(hargaAwal, jmlh);
        totalAkhir = getHitung(hargaAwal, jmlh);
        diskon = getDiskon(hargaAwal, jmlh);
        String Kembalian = getKembalian(tunai, totalAkhir);
        
        model.addAttribute("nama", nama);
        model.addAttribute("hargaAwal","Rp " +hargaAwal);
        model.addAttribute("jmlh", jmlh);
        model.addAttribute("totalAwal", "Rp " + totalAwal);
        model.addAttribute("tunai","Rp " +  tunai);
        model.addAttribute("diskon", diskon + " %");
        model.addAttribute("totalAkhir", "Rp " + totalAkhir ); 
        model.addAttribute("kembalian", Kembalian);
        
        
        return "hasil";
    }
    
    public int getTotal(int harga, int barang){
        int total = harga * barang;
        
        return total;
    }
    
//    Diskon
    public int getHitung(int harga, int barang){
        int total = harga * barang;
        int diskon;
        
        // Potongan 0%
        if(total <= 10000){
            diskon = total - (total * 0);
        }
        // Diskon 5%
        else if((total > 10000) && (total <= 50000)){
            diskon = total - (total * 5/100);
        }
        // Diskon 10 %
        else if( (total > 50000) && (total <= 100000)){
            diskon = total - (total * 10/100);
        }
        // Diskon 15%
        else{
            diskon = total - (total * 15/100);
        }
        
        return diskon;
    }

//  Keterangan Diskon
    public int getDiskon(int harga, int barang){
        int total = harga * barang;
        int diskon;
        
        // Potongan 0 %
        if(total <= 10000){
            diskon = 0;
        }
        // Diskon 5 %
        else if((total > 10000) && (total <= 50000)){
            diskon = 5;
        }
        // Diskon 10 %
        else if((total > 50000) && (total <= 100000)){
            diskon = 10;
        }
        // Diskon 15 %
        else{
            diskon = 15;
        }
        
        return diskon;
    }
    
    public String getKembalian(int tunai, int potonganHarga){
        int kembalian = tunai- potonganHarga;
        
        String hasil;
        
        if(tunai >= potonganHarga){
            hasil = "Rp " + kembalian;
        }
        else{
            hasil = "Uang tidak cukup";
        }
        
        return hasil;
    }
    
}
