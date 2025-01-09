class Stock extends CardHolder {
    public Stock(Game game, String name) {
        super(game, name);
    }

    public void show() {
        for (int i=0; i<size() ; i++) {
            Card c = get(i);
            c.setLocation(game.getWidth()/2-i, game.getHeight()/2-i);  // 重ねる
            c.setFace(false);           // 裏向き
        }
    }

    /* カードに対するアクション時（クリックなど）の処理 */
    public void action(Card cd) {
        System.out.println(name+" Action "+size());
        // スタート時以外は何もしない
        if (size()<=52) {
            return;
        }

        // カードを並べる
        for (int i=0 ; i<13 ; i++) {
            Card c = remove(size()-1);
            game.getPlayer(0).add(c);
            c.setFace(true);
            game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
            moveTo(c, 100+i*40, 600, 1);

            c = remove(size()-1);
            game.getPlayer(1).add(c);
            c.setFace(false);
            game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
            moveTo(c, 710, 70+i*40, 1);

            c = remove(size()-1);
            game.getPlayer(2).add(c);
            c.setFace(false);
            game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
            moveTo(c, 140+i*40, 10, 1);

            c = remove(size()-1);
            game.getPlayer(3).add(c);
            c.setFace(false);
            game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
            moveTo(c, 10, 70+i*40, 1);
        }
        Card c = remove(size()-1);
        game.getPlayer(0).add(c);
        c.setFace(true);
        game.getContentPane().setComponentZOrder(c, game.getComponentCount()-1);
        moveTo(c, 100+13*40, 600, 1);

        // 最初に揃っているカードを場に出す
        for (Player p : game.getPlayers()) {
            p.firstAction();
        }
    }
}
