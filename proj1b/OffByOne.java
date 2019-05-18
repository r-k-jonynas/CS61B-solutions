public class OffByOne 
    implements CharacterComparator{

    /** Returns true if characters are off by one */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == 1;
    }
}