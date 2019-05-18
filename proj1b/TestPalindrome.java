import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
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


    @Test
    public void testIsPalindromeOffByOne1() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
    }

    @Test
    public void testIsPalindromeOffByOne2() {
        CharacterComparator offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome("fkake", offByOne));
    }

    @Test
    public void testIsPalindromeOffByOne3() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("&flake%", offByOne));
    }

    @Test
    public void testIsPalindromeOffByN1() {
        CharacterComparator offByOne = new OffByN(1);
        assertTrue(palindrome.isPalindrome("&flake%", offByOne));
    }

    @Test
    public void testIsPalindromeOffByN2() {
        CharacterComparator offByTwo = new OffByN(2);
        assertTrue(palindrome.isPalindrome("a", offByTwo));
    }

    @Test
    public void testIsPalindromeOffByN3() {
        CharacterComparator offByTwo = new OffByN(2);
        assertFalse(palindrome.isPalindrome("&hlake%", offByTwo));
    }

    @Test
    public void testIsPalindromeOffByN4() {
        CharacterComparator offByTwo = new OffByN(2);
        assertTrue(palindrome.isPalindrome("FacD", offByTwo));
    }

    @Test
    public void testIsPalindromeOffByN5() {
        CharacterComparator offByTwo = new OffByN(2);
        assertTrue(palindrome.isPalindrome("AdgefC", offByTwo));
    }

    @Test
    public void testIsPalindromeOffByN6() {
        CharacterComparator offByTwo = new OffByN(2);
        assertFalse(palindrome.isPalindrome("&blake%", offByTwo));
    }

    @Test
    public void testIsPalindromeOffByN7() {
        CharacterComparator offBy5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome("agchbf", offBy5));
    }

    @Test
    public void testIsPalindromeOffByN72() {
        CharacterComparator offBy7 = new OffByN(7);
        assertTrue(palindrome.isPalindrome("a", offBy7));
    }
}
