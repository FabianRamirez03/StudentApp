package com.example.ramir.studentapp.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.ramir.studentapp.R;
import com.example.ramir.studentapp.map.Graph;
import com.example.ramir.studentapp.map.MapGenerator;
import com.example.ramir.studentapp.map.Node;
import com.example.ramir.studentapp.util.Math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Drawer extends View {

    private Canvas canvas;
    private List<Node<String>> nodes = new ArrayList<>();
    private List<Sprite> buildings = new ArrayList<>();
    private List<Integer> hList = houseList();
    private List<Integer> bList = buildingList();
    private Graph graph = MapGenerator.generateGraph(10);

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

    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

        if (buildings.isEmpty()) drawMap();
        for (Sprite sprite : buildings) {
            canvas.drawBitmap(sprite.getBitmap(), sprite.getX() - sprite.getWidth(), sprite.getY() - sprite.getWidth(), null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                for (Sprite sprite : buildings) {
                    sprite.setX(x);
                    sprite.setY(y);
                }
                this.invalidate();
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
        buildings.add(sprite);
        HashMap<Node<String>, Integer> adjacent = node.getAdjacent();
        adjacent.forEach((k, v) -> {
            if (!nodes.contains(k)) {
                nodes.add(k);
                Sprite s = wrapToSprite(k);

                // Set the position of the adjacent nodes
                int randX = Math.getRandomNumberInRange(-1, 1);
                int randY = Math.getRandomNumberInRange(-1, 1);
                if (randX == 0 && randY == 0) randX = randY = 1;
                int x = sprite.getX() + v * randX * 400;
                int y = sprite.getY() + v * randY * 400;
                s.setX(x);
                s.setY(y);

                // Draws the road from node to node

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
