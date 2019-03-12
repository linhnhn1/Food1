package com.example.pc.foodorder_tn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.foodorder_tn.DAO.NhanVienDAO;
import com.example.pc.foodorder_tn.DTO.NhanVienDTO;
import com.example.pc.foodorder_tn.Database.CreateDatabase;
import com.example.pc.foodorder_tn.FragmentApp.DatePickerFragment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText edTenDangNhapDK, edMatKhauDK, edNgaySinhDK, edCMNDK;
    Button btnDongYDK, btnThoatDK;
    RadioGroup rgGioiTinh;
    RadioButton rdNam,rdNu;
    String sGioiTinh;
    TextView txtTieuDeDangKy;
    NhanVienDAO nhanVienDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edTenDangNhapDK = (EditText) findViewById(R.id.edTenDangNhapDK);
        edMatKhauDK = (EditText) findViewById(R.id.edMatKhauDK);
        edNgaySinhDK = (EditText) findViewById(R.id.edNgaySinhDK);
        txtTieuDeDangKy = (TextView) findViewById(R.id.txtTieuDeDangKy);
        rdNam = (RadioButton) findViewById(R.id.rdNam);
        rdNu = (RadioButton) findViewById(R.id.rdNu);
        edCMNDK = (EditText) findViewById(R.id.edCMNDDK);
        btnDongYDK = (Button) findViewById(R.id.btnDongYDK);
        btnThoatDK = (Button) findViewById(R.id.btnThoatDK);
        rgGioiTinh = (RadioGroup) findViewById(R.id.rgGioiTinh);
//
//        CreateDatabase createDatabase = new CreateDatabase(this);
////        createDatabase.open();
        btnDongYDK.setOnClickListener(this);
        btnThoatDK.setOnClickListener(this);
        edNgaySinhDK.setOnFocusChangeListener(this);

        nhanVienDAO = new NhanVienDAO(this);

    }

    @Override
    public void onClick(View v) {

        int id =v.getId();
        //dùng if else cx dc
        switch (id){
            case R.id.btnDongYDK:
                String sTenDangNhap= edTenDangNhapDK.getText().toString();
                String sMatKhau = edMatKhauDK.getText().toString();
                switch (rgGioiTinh.getCheckedRadioButtonId()) {
                    case R.id.rdNam :
                        sGioiTinh= "Nam";break;

                    case R.id.rdNu:
                        sGioiTinh ="Nữ";break;
                }
                String sNgaySinh = edNgaySinhDK.getText().toString();
                int sCMND = Integer.parseInt(edCMNDK.getText().toString());

                if(sTenDangNhap == null || sTenDangNhap.equals("") ){
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loinhaptendangnhap) , Toast.LENGTH_SHORT).show();
                }
                else if(sMatKhau == null || sMatKhau.equals("")){
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loinhapmatkhau), Toast.LENGTH_SHORT).show();
                }
                else{

                    NhanVienDTO nhanVienDTO = new NhanVienDTO() ;
                    nhanVienDTO.setTENDN(sTenDangNhap);
                    nhanVienDTO.setMATKHAU(sMatKhau);
                    nhanVienDTO.setNGAYSINH(sNgaySinh);
                    nhanVienDTO.setCMND(sCMND);
                    nhanVienDTO.setGIOITINH(sGioiTinh);

                    nhanVienDAO.ThemNhanVien(nhanVienDTO);
                }
                ;break;

            case  R.id.btnThoatDK:
                finish();break;
        }


    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
            case R.id.edNgaySinhDK:
                if(hasFocus){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(),"Ngày sinh");
                    //popup
                }
                ;break;
        }
    }
}

