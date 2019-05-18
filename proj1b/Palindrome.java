public class Palindrome {
    /** For example, if the word is “persiflage”, 
     * then the returned Deque should have ‘p’ 
     * at the front, followed by ‘e’, and so forth.
     * @param word
     * @return a Deque where the characters appear in the same order as in the String.
     */
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }

        return deque;
    }

    /**
     * Checks if a word is a palindrome.
     * REMEMBER: any word of length 1 or 0 is a palindrome.
     * @param word
     * @return boolean
     */
    public boolean isPalindrome(String word) {
        // TODO: fix
        Palindrome palindromeTester = new Palindrome();
        ArrayDeque<Character> deque = (ArrayDeque<Character>) palindromeTester.wordToDeque(word);
        for(int i = 0; i < word.length() / 2; i++) {
            char temp1 = deque.removeFirst();
            char temp2 = deque.removeLast();
            if (temp1 != temp2) {
                return false;
            }
        }
        return true;
    }
}