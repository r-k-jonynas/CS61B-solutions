public class OffByN 
    implements CharacterComparator{
    int allowedDiff;
    
    OffByN(int N) {
        this.allowedDiff = N;
    }
    /** Returns true if characters are off by exactly N */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == this.allowedDiff;
    }
}