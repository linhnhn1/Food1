package com.example.pc.foodorder_tn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc.foodorder_tn.DAO.NhanVienDAO;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDongYDN,btnDangKyDN;
    EditText edTenDangNhapDN, edMatKhauDN;
    NhanVienDAO nhanVienDAO;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDangKyDN = (Button) findViewById(R.id.btnDangKyDN);
        btnDongYDN = (Button) findViewById(R.id.btnDongYDN);
        edMatKhauDN = (EditText) findViewById(R.id.edMatKhauDN);
        edTenDangNhapDN = (EditText) findViewById(R.id.edTenDangNhapDN);

        nhanVienDAO = new NhanVienDAO(this);
        btnDongYDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);

        HienThiButtonDangKyVSDongY();

    }
    private void HienThiButtonDangKyVSDongY (){
       boolean kiemtra = nhanVienDAO.KiemTraNhanVien();
       if (kiemtra){
           btnDangKyDN.setVisibility(View.GONE);
           btnDongYDN.setVisibility(View.VISIBLE);
       } else {
           btnDangKyDN.setVisibility(View.VISIBLE);
           btnDongYDN.setVisibility(View.GONE);
       }
    }
    private void btnDongY(){
        String sTenDangNhap = edTenDangNhapDN.getText().toString();
        String sMatKhau = edMatKhauDN.getText().toString();
        boolean kiemtra = nhanVienDAO.KiemTraDangNhap(sTenDangNhap,sMatKhau);
        if(kiemtra){
            Intent iTrangChu = new Intent(DangNhapActivity.this,TrangChuActivity.class);
            iTrangChu.putExtra("tendn", edTenDangNhapDN.getText().toString());
            startActivity(iTrangChu);
        } else {
            Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
    private void btnDangKy(){
        Intent iDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
        startActivity(iDangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiButtonDangKyVSDongY();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDongYDN:
                btnDongY();
                ;break;

            case R.id.btnDangKyDN:
                btnDangKy();
                ;break;
        }
    }
}
