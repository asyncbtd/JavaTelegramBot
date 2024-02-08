package by.btd.services;

public enum Emoji {

    //smiles
    GRINNING('\uD83D', '\uDE24'),
    SMILEY('\uD83D', '\uDE03'),
    SMILE('\uD83D', '\uDE04'),
    GRIN('\uD83D', '\uDE01'),
    SATISFIED('\uD83D', '\uDE06'),
    FACE_HOLDING_BACK_TEARS('\uD83E', '\uDD79'),
    SWEAT_SMILE('\uD83D', '\uDE05'),
    JOY('\uD83D', '\uDE02'),
    ROLLING_ON_THE_FLOOR_LAUGHING('\uD83E', '\uDD23'),
    SMILING_FACE_WITH_TEAR('\uD83E', '\uDD72'),
    RELAXED('\u263A', '\uFE0F'),
    BLUSH('\uD83D', '\uDE0A'),
    INNOCENT('\uD83D', '\uDE07'),
    SLIGHTLY_SMILING_FACE('\uD83D', '\uDE42'),
    UPSIDE_DOWN_FACE('\uD83D', '\uDE43'),
    WINK('\uD83D', '\uDE09'),
    RELIEVED('\uD83D', '\uDE0C'),
    HEART_EYES('\uD83D', '\uDE0D'),
    SMILING_FACE_WITH_3_HEARTS('\uD83E', '\uDD70'),
    KISSING_HEART('\uD83D', '\uDE18'),
    KISSING('\uD83D', '\uDE17');

    final Character firstChar;
    final Character secondChar;

    Emoji(Character firstChar, Character secondChar) {
        this.firstChar = firstChar;
        this.secondChar = secondChar;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.firstChar != null) {
            stringBuilder.append(this.firstChar);
        }
        if (this.secondChar != null) {
            stringBuilder.append(this.secondChar);
        }

        return stringBuilder.toString();
    }
}
