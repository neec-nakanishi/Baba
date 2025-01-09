class Place extends CardHolder {
    public Place(Game game, String name) {
        super(game, name);
    }

    /* カードに対するアクション時（クリックなど）の処理 */
    public void action(Card c) {
        System.out.println(name+" Action "+size());
        // ゲームスタート時は何もしない
        if (size()==0) {
            return;
        }
        // ゲームが終了している場合は、再スタート
        if (game.getPlayers().size()==1 && c.getSuit()=="x") {
            try {
                Thread.sleep(2000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            // ジョーカーを場に出す
            game.getPlayer(0).remove(c);
            add(c);
            // プレーヤーを削除
            game.getPlayers().remove(0);
            // カードをリセット
            game.cardReset();
            return;
        }
        // １ターン終了後に手札が無くなったプレーヤーを探して終了にする
        for (int i=0 ; i<game.getPlayers().size() ; i++) {
            if (game.getPlayer(i).size()==0) {
                game.getPlayers().remove(i);
            }
        }
    }
}
