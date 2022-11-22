package org.example;

import org.example.classes.HeapAdaptablePriorityQueue;
import org.example.interfaces.Entry;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    private static HeapAdaptablePriorityQueue<Integer, String> heapAdaptablePriorityQueue;
    public Main(){
        heapAdaptablePriorityQueue = new HeapAdaptablePriorityQueue<>();
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(10, 200, 130, 40);
        String text = "Hospital Reception";
        g.setColor(Color.white);
        g.drawString(text, 20,230);
        int count = heapAdaptablePriorityQueue.size();
        for(int i=0;i<count;i++){
            Entry<Integer, String> entry = heapAdaptablePriorityQueue.get(i);
            String name = entry.getValue();
            int key = entry.getKey();
            g.setColor(Color.black);
            g.drawString(String.valueOf(key), (i*100)+160, 150);
            g.drawString(",",(i*100)+170,150);
            g.drawString(name, (i*100)+180,150);
            Image img = new ImageIcon("/home/samuel/Downloads/small_siting_icon.png").getImage();
            g.drawImage(img, (i*100)+160, 160, 80,100,null);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hospital Adaptable Priority Queue");
        JPanel main = new Main();
        JButton min = new JButton("min");
        JButton removeMin = new JButton("removeMin");
        JButton size = new JButton("size");
        JButton isEmpty = new JButton("isEmpty");
        JButton insert = new JButton("insert");
        JButton remove = new JButton("remove");
        JButton replaceKey = new JButton("replaceKey");
        JButton replaceValue = new JButton("replaceValue");
        JTextArea key = new JTextArea();
        JTextArea value = new JTextArea();
        JTextArea entry = new JTextArea();
        JLabel label = new JLabel();
        JLabel keyLabel = new JLabel("Key:");
        JLabel valueLabel = new JLabel("Value:");
        JLabel entryLabel = new JLabel("Entry:");
        key.setBounds(230, 10, 100,30);
        value.setBounds(230, 50, 100,30);
        entry.setBounds(230, 90,100,30);
        label.setBounds(180, 130, 200, 30);
        keyLabel.setBounds(180, 10, 100,30);
        valueLabel.setBounds(180, 50, 100,30);
        entryLabel.setBounds(180, 90,100,30);
        frame.setSize(1020, 800);
        min.setBounds(10, 10, 150,30);
        removeMin.setBounds(10, 50, 150, 30);
        size.setBounds(10,90,150,30);
        isEmpty.setBounds(10,130,150,30);
        insert.setBounds(10,170, 150,30);
        remove.setBounds(10,210, 150, 30);
        replaceKey.setBounds(10,250, 150, 30);
        replaceValue.setBounds(10,290, 150, 30);
        main.setBounds(10, 320, 900, 300);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.add(keyLabel);
        frame.add(valueLabel);
        frame.add(entryLabel);
        frame.add(remove);
        frame.add(replaceKey);
        frame.add(replaceValue);
        frame.add(entry);
        frame.add(value);
        frame.add(label);
        frame.add(main);
        frame.add(min);
        frame.add(removeMin);
        frame.add(size);
        frame.add(isEmpty);
        frame.add(insert);
        frame.add(key);



        insert.addActionListener(e -> {
            int keyVal =Integer.parseInt(key.getText());
            String stringVal = value.getText();
            heapAdaptablePriorityQueue.insert(keyVal, stringVal);
            frame.repaint();
        });
        size.addActionListener(e -> {
            int size1 = heapAdaptablePriorityQueue.size();
            label.setText("Size is "+size1);
        });
        min.addActionListener(e -> {
            int minimumKey = heapAdaptablePriorityQueue.min().getKey();
            String minimum = heapAdaptablePriorityQueue.min().getValue();
            label.setText("min is "+minimum+" ("+minimumKey+","+minimum+")");
        });
        removeMin.addActionListener(e -> {
            Entry<Integer,String> Min = heapAdaptablePriorityQueue.removeMin();
            int minimumKey = Min.getKey();
            String minimum = Min.getValue();
            label.setText("removed "+minimum+" ("+minimumKey+","+minimum+")");
            frame.repaint();
        });
        isEmpty.addActionListener(e -> {
            if(heapAdaptablePriorityQueue.isEmpty()){
                label.setText("true");
            }
            else{
                label.setText("false");
            }
        });
        remove.addActionListener(e -> {
            String toRemove = entry.getText();
            for(Entry<Integer,String> search: heapAdaptablePriorityQueue){
                if(search.getValue().equals(toRemove)){
                    heapAdaptablePriorityQueue.remove(search);
                }
            }
            frame.repaint();
        });
        replaceKey.addActionListener(e -> {
            String toRemove = entry.getText();
            int keyValue = Integer.parseInt(key.getText());
            for (Entry<Integer, String> search: heapAdaptablePriorityQueue){
                if(search.getValue().equals(toRemove)){
                    heapAdaptablePriorityQueue.replaceKey(search, keyValue);
                }
            }
            frame.repaint();
        });
        replaceValue.addActionListener(e -> {
            String toRemove = entry.getText();
            String valueText = value.getText();
            for(Entry<Integer, String> search:heapAdaptablePriorityQueue){
                if(search.getValue().equals(toRemove)){
                    heapAdaptablePriorityQueue.replaceValue(search, valueText);
                }
                frame.repaint();
            }
        });
    }
}