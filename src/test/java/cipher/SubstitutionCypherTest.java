package cipher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SubstitutionCypherTest {
	private SubstitutionCypher cypher;

	@Test
	public void encode_plaintext_to_secrettext() throws Exception {
		SubstitutionCypher cypher1 = new SubstitutionCypher("abcdhejsan", "wxyzquake3");
		assertThat(cypher1.encode("a"), equalTo("w"));
		assertThat(cypher1.encode("c"), equalTo("y"));
		assertThat(cypher1.encode("da"), equalTo("zw"));
		SubstitutionCypher cypher2 = create();
		assertThat(cypher2.decode("x"), equalTo("b"));
		assertThat(cypher2.decode("y"), equalTo("c"));
		assertThat(cypher2.decode("yz"), equalTo("c" + "d"));
		SubstitutionCypher cypher3 = new SubstitutionCypher("xzabcd", "uvwxyz");
		try {
			cypher3.encode("t");
			fail("Expected InvalidCharacterException for encoding character " + "t");
		} catch (InvalidCharacterException e) {
		}
		try {
			cypher3.encode("u");
			fail("Expected InvalidCharacterException for encoding character " + "u");
		} catch (InvalidCharacterException e) {
		}
		try {
			cypher3.encode("auc");
			fail("Expected InvalidCharacterException for encoding character " + "auc");
		} catch (InvalidCharacterException e) {
		}
		SubstitutionCypher cypher4 = new SubstitutionCypher("abcd", "wxyz");
		try {
			cypher4.decode("m");
			fail("Expected InvalidCharacterException for decoding character " + "m");
		} catch (InvalidCharacterException e) {
		}
		try {
			cypher4.decode("n");
			fail("Expected InvalidCharacterException for decoding character " + "n");
		} catch (InvalidCharacterException e) {
		}
		try {
			cypher4.decode("xyn");
			fail("Expected InvalidCharacterException for decoding character " + "xyn");
		} catch (InvalidCharacterException e) {
		}
	}

	private SubstitutionCypher create() {
		return new SubstitutionCypher("abcdef", "wxyzab");
	}
}
