import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;

class Baba extends Game {
    private Player player;      // プレーヤー
    private Player com1;           // コンピュータ１
    private Player com2;           // コンピュータ２
    private Player com3;           // コンピュータ３
    public static void main(String[] args) {
    	new Baba();
    }

    public Baba() {
        setSize(800, 750);
        getContentPane( ).setBackground(new Color(0,196,0));

        // カードの作成
        player = new Player(this, "Player", 100, 600, 40, 0);
        com1 = new Com(this, "COM1", 710, 70, 0, 40);
        com2 = new Com(this, "COM2", 140, 10, 40, 0);
        com3 = new Com(this, "COM3", 10, 70, 0, 40);

        // 表示
        setVisible(true);

        // ゲームをスタート
        gameStart();
    }

    public void cardReset() {
        // プレーヤーを元に戻す
        addPlayer(player);
        addPlayer(com1);
        addPlayer(com2);
        addPlayer(com3);

        // カードをストックに戻す
        while(place.size()!=0) {
			Card c = place.remove(0);
            stock.add(c);
        }

        // カードをシャッフル
        stock.shuffle();
        // ストックを表示
        stock.show();
    }

    public void gameStart() {
        // 初期化
        cardReset();	
    }

    @Override
    public void action(MouseEvent e) {}
}
