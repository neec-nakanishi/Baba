import java.util.ArrayList;

class Player extends CardHolder {
    protected int sx;
    protected int sy;
    protected int dx;
    protected int dy;

    public Player(Game game, String name, int sx, int sy, int dx, int dy) {
        super(game, name);
        this.sx = sx;
        this.sy = sy;
        this.dx = dx;
        this.dy = dy;
    }

    public void show() {
        for (int i=0 ; i<size() ; i++) {
            Card c = get(i);
            c.setLocation(sx+i*dx, sy+i*dy);
            game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
        }
    }

    public void firstAction() {
        ArrayList<Card> pair = new ArrayList<>();
        // 揃うペアを探す
        loop: for (int i=size()-1 ; i>=1 ; i--) {
            for (int j=i-1 ; j>=0 ; j--) {
                if (match(get(i), get(j))) {
                    remove(get(i));
                    remove(get(j));
                    show();
                    i--;
                    continue loop;
                }
            }
        }
    }

    boolean match(Card c1, Card c2) {
        if (c1.getNum()==c2.getNum()) {
            game.getPlace().add(c1);
            int x = (int)(Math.random()*15)-8;
            int y = (int)(Math.random()*15)-8;
            game.getContentPane().setComponentZOrder(c1, game.getComponentCount()-1);
            moveTo(c1, game.getWidth()/2+x*3-30, game.getHeight()/2+y*3-45, 1);
            c1.setFace(true);

            game.getPlace().add(c2);
            x = (int)(Math.random()*15)-8;
            y = (int)(Math.random()*15)-8;
            game.getContentPane().setComponentZOrder(c2, game.getComponentCount()-1);
            moveTo(c2, game.getWidth()/2+x*3-30, game.getHeight()/2+y*3-45, 1);
            c2.setFace(true);
            return true;
        }
        return false;
    }

    /* カードに対するアクション時（クリックなど）の処理 */
    public boolean action(Player target, Card c) {
        System.out.println(name + " Action "+size());
        // スタート時は何もしない
        if (game.getStock().size()>=52) {
            return false;
        }

        // 手持ちのカードが無い場合は何もしない
        if (size()==0 || target.size()==0) {
            return false;
        }

        // ターゲットがいない場合終了
        if (target==this) {
            get(0).setFace(true);
            return false;
        }
        
        // ターゲット以外のカードをクリックした場合は何もしない
        if (! target.contains(c)) {
            return false;
        }

        // ターゲットからカードを取ってくる
        target.remove(c);
        game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
        moveTo(c, get(size()-1).getX()+dx, get(size()-1).getY()+dy, 1);
        reverse(c, 0);
        target.show();

        // 手札と揃うカードがあるか調べる
        for (Card card : this) {
            if (match(c, card)) {
                remove(card);
                show();
                return true;
            }
        }
        // 揃わなければ手札に加える
        int n = (int)(Math.random()*size());
        add(n, c);
        show();
        return true;
    }
}
