package views;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.*;
import javax.swing.*;
import static original.Constants.*;

public class Card extends JLabel implements Transferable, DragGestureListener, DropTargetListener {

    private Hand hand;
    private static final DataFlavor cardFlavor = new DataFlavor(Card.class, "Card");

    Card(Hand hand) {
        this.hand = hand;

        ImageIcon icon = new ImageIcon(imagePath(hand));
        setIcon(icon);
        setText(hand.toString());
        // 文字を隠す（他にやりようがありそう）
        setFont(new Font("", 1, 0));

        new DragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
        new DropTarget(this, DnDConstants.ACTION_MOVE, this);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return this.hand;
    }

    /**
     * Cardを入れ替える
     * @param target 入れ替え元のCard
     */
    private void swapCards(Card target) {
        Hand dragHand = target.getHand();
        String dragText = target.getText();
        Icon dragIcon = target.getIcon();

        target.setHand(getHand());
        target.setText(getText());
        target.setIcon(getIcon());
        target.repaint();

        setHand(dragHand);
        setText(dragText);
        setIcon(dragIcon);
    }

    /**
     * CardのあるDeckのupdateHands()を呼ぶ
     */
    private void updateHands() {
        Deck deck = (Deck)getParent();
        deck.updateHands();
    }

    /**
     * 表示したい手の画像パスを返す
     * @param hand 表示したい手
     * @return 画像のパス
     */
    private String imagePath(Hand hand) {
        return "./images/" + hand.toString() + ".png";
    }

    /**
     * カードを閉じる
     */
    public void close() {
        ImageIcon icon = new ImageIcon("./images/CLOSE.png");
        setIcon(icon);
    }

    /**
     * カードを開ける
     */
    public void open() {
        ImageIcon icon = new ImageIcon(imagePath(this.hand));
        setIcon(icon);
    }

    /**
     * DragGestureListenerの実装
     */
    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        dge.startDrag(DragSource.DefaultMoveDrop, this);
    }

    /**
     * Transferableの実装
     */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { cardFlavor };
    }

    /**
     * Transferableの実装
     */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return true;
    }

    /**
     * Transferableの実装
     */
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return this;
    }

    /**
     * DropTargetListenerの実装
     */
    @Override
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

    /**
     * DropTargetListenerの実装
     */
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    /**
     * DropTargetListenerの実装
     */
    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    /**
     * DropTargetListenerの実装
     */
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    /**
     * DropTargetListenerの実装
     */
    @Override
    public void dragExit(DropTargetEvent dte) {
    }
}
