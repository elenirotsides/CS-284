import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	@Test
	void testGetMaxEntries() {
		Anagrams a = new Anagrams();
		
		a.addWord("eleni");
		a.addWord("alerts");
		a.addWord("alters");
		a.addWord("artels");
		a.addWord("estral");
		a.addWord("rotsides");
		a.addWord("laster");
		a.addWord("lastre");
		a.addWord("rastle");
		a.addWord("ratels");
		a.addWord("anotherdiversion");
		a.addWord("relast");
		a.addWord("resalt");
		a.addWord("salter");
		a.addWord("slater");
		a.addWord("staler");
		a.addWord("stelar");
		a.addWord("tryingtotrickyou");
		a.addWord("talers");
		

		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		
		assertEquals(maxEntries.toString(), "[236204078=[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]");
	}
	
	@Test
	void testManyMaxEntries() {
		Anagrams a = new Anagrams();
		
		a.addWord("eleni");
		a.addWord("leeni");
		a.addWord("bob");
		a.addWord("ieenl");
		a.addWord("artels");
		a.addWord("bill");
		a.addWord("estral");
		a.addWord("alerts");

		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		
		assertEquals(maxEntries.toString(), "[4427753=[eleni, leeni, ieenl], 236204078=[artels, estral, alerts]]");
	}
	
	@Test
	void testGetMaxEntriesFromTxtFile() {
		Anagrams a = new Anagrams();

		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
				
		assertEquals(maxEntries.toString(), "[236204078=[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]");
	}
	
	@Test
	void testHashCode() {
		Anagrams a = new Anagrams();
		
		long hashCode = a.myHashCode("alerts");
		long hashCode2 = a.myHashCode("alters");
		
		assertEquals(hashCode, 236204078);
		assertEquals(hashCode2, 236204078);
	}
	
	@Test
	void testInvalidArguments() {
		Anagrams a = new Anagrams();
		
		assertThrows(IllegalArgumentException.class, () -> { a.addWord("eleni!"); });
		assertThrows(IllegalArgumentException.class, () -> { a.addWord(null); });
		assertThrows(IllegalArgumentException.class, () -> { a.addWord(""); });
		assertThrows(IllegalArgumentException.class, () -> { a.myHashCode("!"); });
		assertThrows(IllegalArgumentException.class, () -> { a.myHashCode(null); });
	}
	
	@Test
	void testAddDuplicateWord() {
		Anagrams a = new Anagrams();
		
		a.addWord("eleni");
		
		assertThrows(IllegalArgumentException.class, () -> { a.addWord("eleni"); });
	}
}
