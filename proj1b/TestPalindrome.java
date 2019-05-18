import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome1() {
        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void testIsPalindrome2() {
        assertTrue(palindrome.isPalindrome("racecar"));
    }

    @Test
    public void testIsPalindrome3() {
        assertFalse(palindrome.isPalindrome("mamaam"));
    }

    @Test
    public void testIsPalindrome4() {
        assertTrue(palindrome.isPalindrome("maam"));
    }

    @Test
    public void testIsPalindrome5() {
        assertFalse(palindrome.isPalindrome("maAm"));
    }

    @Test
    public void testIsPalindrome6() {
        assertTrue(palindrome.isPalindrome("m!a!m"));
    }
}
// Uncomment this class once you've created your Palindrome class.