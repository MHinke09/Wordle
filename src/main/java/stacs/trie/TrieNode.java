package stacs.trie;
//https://github.com/eugenp/tutorials/tree/master/data-structures/src/main/java/com/baeldung/trie
import java.util.HashMap;
import java.util.Map;

class TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}