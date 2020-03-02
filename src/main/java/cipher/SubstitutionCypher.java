package cipher;

public class SubstitutionCypher {
	private final String plainAlphabet;
	private final String secretAlphabet;

	public SubstitutionCypher(String plainAlphabet, String secretAlphabet) {
		this.plainAlphabet = plainAlphabet;
		this.secretAlphabet = secretAlphabet;
	}

	public String encode(String plainText) {
		return translate(plainText, plainAlphabet, secretAlphabet);
	}

	public String decode(String secretText) {
		return translate(secretText, secretAlphabet, plainAlphabet);
	}

	private String translate(String text, String sourceAlphabet, String targetAlphabet) {
		validateText(text, sourceAlphabet);
		StringBuilder translation = new StringBuilder();
		for (char sourceCharacter : text.toCharArray()) {
			translation.append(targetAlphabet.charAt(sourceAlphabet.indexOf(sourceCharacter)));
		}
		return translation.toString();
	}

	private void validateText(String text, String alphabet) {
		for (char character : text.toCharArray()) {
			if (!contains(alphabet, character)) {
				throw new InvalidCharacterException();
			}
		}
	}

	private boolean contains(String alphabet, char character) {
		return alphabet.indexOf(character) != -1;
	}
}
