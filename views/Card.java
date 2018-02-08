package views;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.*;
import javax.swing.*;
import static original.Constants.*;

public class Card extends JLabel implements Transferable, DragGestureListener, DropTargetListener {
    private static final DataFlavor cardFlavor = new DataFlavor(Card.class, "Card");

    public Card(Hand hand) {
        ImageIcon icon = new ImageIcon(imagePath(hand));
        setIcon(icon);
        setText(hand.toString());
        // 文字を隠す（他にやりようがありそう）
        setFont(new java.awt.Font("", 1, 0));

        new DragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
        new DropTarget(this, DnDConstants.ACTION_MOVE, this);
    }

    private String imagePath(Hand hand) {
        return "./images/" + hand.toString() + ".png";
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        dge.startDrag(DragSource.DefaultMoveDrop, this);
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { cardFlavor };
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return true;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return this;
    }

    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
        Transferable tr = dtde.getTransferable();

        if(!dtde.isDataFlavorSupported(cardFlavor)) return;

        Card card = null;
        try {
            card = (Card)tr.getTransferData(cardFlavor);
        } catch(Exception e) {
            System.out.println(e);
            return;
        }

        if(card == null) return;

        // 自分自身にドロップされたら処理しない
        if(card.getText().equals(getText())) return;

        String dragText = card.getText();

        card = null;

        for(Component component : getParent().getComponents()) {
            if(!(component instanceof Card)){
                continue;
            }
            if(((Card)component).getText().equals(dragText)) {
                card = (Card)component;
                break;
            }
        }

        if(card == null) return;

        swapCards(card);
        updateHands();
    }

    private void swapCards(Card target) {
        String dragText = target.getText();
        Icon dragIcon = target.getIcon();

        target.setText(getText());
        target.setIcon(getIcon());
        target.repaint();

        setText(dragText);
        setIcon(dragIcon);
    }

    private void updateHands() {
        Deck deck = (Deck)getParent();
        deck.updateHands();
    }

    public void dragEnter(DropTargetDragEvent dtde) { }
    public void dragOver(DropTargetDragEvent dtde) { }
    public void dropActionChanged(DropTargetDragEvent dtde) { }
    public void dragExit(DropTargetEvent dte) { }
}
