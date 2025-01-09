import java.util.ArrayList;

class Com extends Player {
    public Com(Game game, String name, int sx, int sy, int dx, int dy) {
        super(game, name, sx, sy, dx, dy);
    }

    public void show() {
        for (int i=0 ; i<size() ; i++) {
            Card c = get(i);
            c.setLocation(sx+i*dx, sy+i*dy);
            game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
        }
    }

    /* カードに対するアクション時（クリックなど）の処理 */
    @Override
    public boolean action(Player target, Card cd) {
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
        
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
        }

        int n = (int)(Math.random()*target.size());

        // ターゲットからカードを取ってくる
        Card c = target.remove(n);
        game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
        moveTo(c, get(size()-1).getX()+dx, get(size()-1).getY()+dy, 1);
        c.setFace(false);
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
        int r = (int)(Math.random()*size());
        add(r, c);
        show();
        return true;
    }
}
