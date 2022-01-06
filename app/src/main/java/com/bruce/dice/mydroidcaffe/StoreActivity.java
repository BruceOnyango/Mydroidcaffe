package com.bruce.dice.mydroidcaffe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.bumptech.glide.Glide;

public class StoreActivity extends AppCompatActivity {
    private TextView mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        TextView storeTitle = findViewById(R.id.area_title);
        storeTitle.setText(getIntent().getStringExtra("storeTitle"));
        TextView storeDescription = findViewById(R.id.area_description);
        storeDescription.setText(getIntent().getStringExtra("storeDescription"));
        ImageView storeImage = findViewById(R.id.galleria);
        Glide.with(this).load(getIntent().getIntExtra("storeImage", 0)).into(storeImage);
        mShareTextEditText = findViewById(R.id.social_icons2);

    }



    public void share_view(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("share with")
                .setText(txt)
                .startChooser();
        startActivity(sendIntent);

    }
}
