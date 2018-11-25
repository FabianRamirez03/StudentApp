package com.example.ramir.studentapp.draw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.ramir.studentapp.R;
import com.example.ramir.studentapp.map.Graph;
import com.example.ramir.studentapp.map.MapGenerator;
import com.example.ramir.studentapp.map.Node;
import com.example.ramir.studentapp.util.DoubleArray;
import com.example.ramir.studentapp.util.Math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Drawer extends View {

    private Canvas canvas;
    private Paint paint = new Paint();
    private Paint textPaint = new Paint();
    private List<Node<String>> nodes = new ArrayList<>();
    private List<Sprite> drawings = new ArrayList<>();
    private List<DoubleArray<Sprite, Sprite>> roads = new ArrayList<>();
    private List<Integer> hList = houseList();
    private List<Integer> bList = buildingList();
    private Graph graph = MapGenerator.generateGraph(20);

    private int xPoss = 0;
    private int yPoss = 0;
    private int lastxPoss = 0;
    private int lastyPoss = 0;


    public Drawer(Context context) {
        super(context);
        init(null);
    }

    public Drawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Drawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Drawer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        paint.setStrokeWidth(20);
        paint.setColor(Color.CYAN);
        textPaint.setTextSize(70);
        textPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

        if (drawings.isEmpty()) drawMap();

        // Draws the roads
        for (DoubleArray<Sprite, Sprite> array : roads) {
            Sprite sprite1 = array.getFirst();
            Sprite sprite2 = array.getSecond();
            canvas.drawLine(sprite1.getX(), sprite1.getY(), sprite2.getX(), sprite2.getY(), paint);
            String text = Integer.toString(sprite1.getNode().getAdjacent().get(sprite2.getNode()));
            canvas.drawText(text,(sprite1.getX() + sprite2.getX())/2, (sprite1.getY() + sprite2.getY())/2, textPaint);
        }

        // Draws the buildings
        for (Sprite sprite : drawings) {
            canvas.drawBitmap(sprite.getBitmap(), sprite.getX() - sprite.getWidth() / 2, sprite.getY() - sprite.getHeight() / 2, null);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xPoss = (int) event.getX();
                yPoss = (int) event.getY();
                lastxPoss = xPoss;
                lastyPoss = yPoss;
                break;

            case MotionEvent.ACTION_MOVE:
                xPoss = (int) event.getX();
                yPoss = (int) event.getY();

                if (xPoss != lastxPoss || yPoss != lastyPoss) {
                    for (Sprite sprite : drawings) {
                        int x = sprite.getX() + (xPoss - lastxPoss);
                        int y = sprite.getY() + (yPoss - lastyPoss);
                        sprite.setX(x);
                        sprite.setY(y);
                    }
                    lastxPoss = xPoss;
                    lastyPoss = yPoss;
                    this.invalidate();
                }
                break;
        }
        return true;
    }

    private List<Integer> houseList() {
        List<Integer> commonList = new ArrayList<>();
        commonList.add(R.drawable.comun1);
        commonList.add(R.drawable.comun2);
        commonList.add(R.drawable.comun3);
        commonList.add(R.drawable.comun4);
        commonList.add(R.drawable.comun5);
        commonList.add(R.drawable.comun6);
        commonList.add(R.drawable.comun7);
        commonList.add(R.drawable.comun8);
        commonList.add(R.drawable.comun9);
        commonList.add(R.drawable.comun10);
        commonList.add(R.drawable.comun11);
        commonList.add(R.drawable.comun12);
        commonList.add(R.drawable.comun13);
        commonList.add(R.drawable.comun14);
        commonList.add(R.drawable.comun15);
        commonList.add(R.drawable.comun16);
        commonList.add(R.drawable.comun17);
        commonList.add(R.drawable.comun18);
        commonList.add(R.drawable.comun19);
        commonList.add(R.drawable.comun20);

        return commonList;
    }

    private List<Integer> buildingList() {
        List<Integer> buildingsList = new ArrayList<>();
        //buildingsList.add(R.drawable.edificio1);
        buildingsList.add(R.drawable.edificio2);
        buildingsList.add(R.drawable.edificio3);
        buildingsList.add(R.drawable.edificio4);
        buildingsList.add(R.drawable.edificio5);
        buildingsList.add(R.drawable.edificio6);
        buildingsList.add(R.drawable.edificio7);
        buildingsList.add(R.drawable.edificio8);
        buildingsList.add(R.drawable.edificio9);
        buildingsList.add(R.drawable.edificio10);

        return buildingsList;
    }

    private void drawMap() {
        List<Node<String>> vertices = graph.getVertices();

        // Sets the TEC in the center of the map
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.edificio1);
        bitmap = Bitmap.createScaledBitmap(bitmap, 250, 200, false);
        Sprite TEC = new Sprite(x, y, 250, 200, vertices.get(0));
        TEC.setBitmap(bitmap);

        setSpritePoss(TEC);
    }

    private void setSpritePoss(Sprite sprite) {
        Node<String> node = sprite.getNode();
        nodes.add(node);
        drawings.add(sprite);
        HashMap<Node<String>, Integer> adjacent = node.getAdjacent();
        adjacent.forEach((k, v) -> {
            if (!nodes.contains(k)) {
                Sprite s = wrapToSprite(k);
                nodes.add(k);
                drawings.add(s);

                // Set the position of the adjacent nodes
                int randX = Math.getRandomNumberInRange(-1, 1);
                int randY = Math.getRandomNumberInRange(-1, 1);
                if (randX == 0 && randY == 0) randX = randY = 1;
                int x = sprite.getX() + v * randX * 400;
                int y = sprite.getY() + v * randY * 400;
                s.setX(x);
                s.setY(y);

                // Draws the road from node to node
                roads.add(new DoubleArray<>(sprite, s));

                // Make recursive connections
                setSpritePoss(s);
            }
        });
    }

    private Sprite wrapToSprite(Node<String> node) {
        String name = node.getElement();
        Sprite sprite = null;

        if (name.startsWith("B")) {
            int b = Math.getRandomNumberInRange(0, bList.size() - 1);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bList.get(b));
            bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
            sprite = new Sprite(0, 0, 200, 200, node);
            sprite.setBitmap(bitmap);
        } else if (name.startsWith("C")) {
            int h = Math.getRandomNumberInRange(0, hList.size() - 1);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), hList.get(h));
            bitmap = Bitmap.createScaledBitmap(bitmap, 160, 160, false);
            sprite = new Sprite(0, 0, 160, 160, node);
            sprite.setBitmap(bitmap);
        }

        return sprite;
    }

}
