package com.skyversion.communicon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.skyversion.communicon.Dialog.CustomDialog;

import graphics.CanvasView;

public class DrawPicture extends AppCompatActivity {

    private CanvasView canvas = null;
    private CustomDialog dialog;
    private ImageView drawBlack;
    private ImageView drawGreen;
    private ImageView drawRed;
    private ImageView drawErase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawmap_activity);

        this.canvas = (CanvasView) this.findViewById(R.id.canvas);

        dialog = new CustomDialog(this, canvas);
        dialog.setCanceledOnTouchOutside(true);

        Button btn = (Button) findViewById(R.id.reset_btn);
        drawBlack = (ImageView) findViewById(R.id.draw_black);
        drawGreen = (ImageView) findViewById(R.id.draw_green);
        drawRed = (ImageView) findViewById(R.id.draw_red);
        drawErase = (ImageView) findViewById(R.id.draw_erase);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.clear();
            }
        });

        drawBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBlack.setImageResource(R.drawable.pencil_black_underlined);
                drawGreen.setImageResource(R.drawable.pencil_green);
                drawRed.setImageResource(R.drawable.pencil_red);
                drawErase.setImageResource(R.drawable.eraser);

                canvas.setColor(1);
            }
        });
        drawGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBlack.setImageResource(R.drawable.pencil_black);
                drawGreen.setImageResource(R.drawable.pencil_green_underlined);
                drawRed.setImageResource(R.drawable.pencil_red);
                drawErase.setImageResource(R.drawable.eraser);

                canvas.setColor(2);
            }
        });
        drawRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBlack.setImageResource(R.drawable.pencil_black);
                drawGreen.setImageResource(R.drawable.pencil_green);
                drawRed.setImageResource(R.drawable.pencil_red_underlined);
                drawErase.setImageResource(R.drawable.eraser);

                canvas.setColor(3);
            }
        });


        drawErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBlack.setImageResource(R.drawable.pencil_black);
                drawGreen.setImageResource(R.drawable.pencil_green);
                drawRed.setImageResource(R.drawable.pencil_red);
                drawErase.setImageResource(R.drawable.eraser_underlined);

                canvas.erase();
//                canvas.setMode(CanvasView.Mode.ERASER);
            }
        });
    }

    public CanvasView getCanvas() {
        return this.canvas;
    }
}