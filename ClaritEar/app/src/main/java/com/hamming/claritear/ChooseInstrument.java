package com.hamming.claritear;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Class untuk memilih jenis instrument gitar atau piano
 */

public class ChooseInstrument extends AppCompatActivity {
    ImageView guitar, piano;

    /**
     * method override untuk mendeclare segala komponen pada file xml ke file java
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_instrument);
        guitar = (ImageView)findViewById(R.id.iv_guitar_icon);
        piano = (ImageView)findViewById(R.id.iv_piano_icon);
    }

    /**
     * method yang dipanggil saat tombol gitar dipilih yang akan mengirim user ke playing
     * activty gitar
     * @param view
     */
    public void playGuitar(View view){
        Intent guitarIntent = new Intent(ChooseInstrument.this,PlayingActivity.class);
        startActivity(guitarIntent);
        finish();
    }

    /**
     * method yang dipanggil saat tombol piano dipilih yang akan mengirim user ke playing activty piano
     * @param v
     */
    public void playPiano(View v){
        Intent pianoIntent = new Intent(ChooseInstrument.this,PlayingPianoActivity.class);
        startActivity(pianoIntent);
        finish();
    }

    /**
     * method override untuk kembali ke activity home saat tombol back pada handphone ditekan
     */
    @Override
    public void onBackPressed() {
        Intent toHome = new Intent(ChooseInstrument.this, HomeActivity.class);
        startActivity(toHome);
        finish();
    }
}
