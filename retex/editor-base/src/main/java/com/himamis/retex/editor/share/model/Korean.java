package com.himamis.retex.editor.share.model;

import java.util.HashMap;

public class Korean {

	static StringBuilder sb;
	static HashMap<Character, Character> koreanLeadToTail;

	static void init() {

		if (koreanLeadToTail == null) {
			koreanLeadToTail = new HashMap<Character, Character>();
		}

		koreanLeadToTail.put(new Character('\u1100'), new Character('\u11a8'));
		koreanLeadToTail.put(new Character('\u1101'), new Character('\u11a9'));
		koreanLeadToTail.put(new Character('\u1102'), new Character('\u11ab'));
		koreanLeadToTail.put(new Character('\u1103'), new Character('\u11ae'));

		// map to itself
		koreanLeadToTail.put(new Character('\u1104'), new Character('\u1104'));

		koreanLeadToTail.put(new Character('\u1105'), new Character('\u11af'));
		koreanLeadToTail.put(new Character('\u1106'), new Character('\u11b7'));
		koreanLeadToTail.put(new Character('\u1107'), new Character('\u11b8'));

		// map to itself
		koreanLeadToTail.put(new Character('\u1108'), new Character('\u1108'));

		koreanLeadToTail.put(new Character('\u1109'), new Character('\u11ba'));
		koreanLeadToTail.put(new Character('\u110a'), new Character('\u11bb'));
		koreanLeadToTail.put(new Character('\u110b'), new Character('\u11bc'));
		koreanLeadToTail.put(new Character('\u110c'), new Character('\u11bd'));

		// map to itself
		koreanLeadToTail.put(new Character('\u110d'), new Character('\u110d'));

		koreanLeadToTail.put(new Character('\u110e'), new Character('\u11be'));
		koreanLeadToTail.put(new Character('\u110f'), new Character('\u11bf'));
		koreanLeadToTail.put(new Character('\u1110'), new Character('\u11c0'));
		koreanLeadToTail.put(new Character('\u1111'), new Character('\u11c1'));
		koreanLeadToTail.put(new Character('\u1112'), new Character('\u11c2'));

	}

	/*
	 * convert eg \uB458 to \u1103\u116e\u11af
	 * 
	 * and \uB450 to \u1103\u116E
	 */
	public static String flattenKorean(String s) {

		init();

		if (sb == null) {
			sb = new StringBuilder();
		} else {
			sb.setLength(0);
		}

		boolean lastWasVowel = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (isKoreanMultiChar(c)) {
				appendKoreanMultiChar(sb, c);
			} else {
				// if a "lead char" follows a vowel, turn into a "tail char"
				if (lastWasVowel && isKoreanLeadChar(c, false)) {
					sb.append(koreanLeadToTail.get(Character.valueOf(c))
							.charValue());
				} else {
					sb.append(c);
				}
			}
			lastWasVowel = isKoreanVowelChar(sb.charAt(sb.length() - 1), false);
		}

		return sb.toString();
	}

	// static {
	// for (char i = 0x1100; i <= 0x1112; i++) {
	// for (char j = 0x1161; j <= 0x1175; j++) {
	// String s = i + "" + j;
	// Log.debug(i + " " + j + " " + StringUtil.toHexString(s)
	// + StringUtil.toHexString(unflattenKorean(s).toString()));
	// }
	// }
	//
	// for (char i = 0xac00; i <= 0xD788; i += 1) {
	// String s = i + "";
	// Log.debug(i + " " + StringUtil.toHexString(s) + " "
	// + isKoreanLeadPlusVowelChar(i));
	// }
	// }

	// from 0xac00 to 0xd788, every 28th character is a combination of 2
	// characters not 3
	public static boolean isKoreanLeadPlusVowelChar(char c) {
		if (c >= 0xac00 && c <= 0xd7af) {

			int ch = c - 0xac00;
			if ((ch % 28) == 0) {
				return true;
			}

		}

		return false;
	}

	public static boolean isKoreanMultiChar(char c) {

		if (c >= 0xac00 && c <= 0xd7af) {
			return true;
		}

		return false;
	}

	public static boolean isKoreanLeadChar(char c, boolean convertJamo) {

		if (convertJamo) {
			c = convertFromCompatibilityJamo(c, true);
		}

		if (c >= 0x1100 && c <= 0x1112) {
			return true;
		}

		return false;
	}

	public static boolean isKoreanVowelChar(char c, boolean convertJamo) {

		if (convertJamo) {
			c = convertFromCompatibilityJamo(c, true);
		}

		if (c >= 0x1161 && c <= 0x1175) {
			return true;
		}

		return false;
	}

	public static boolean isKoreanTailChar(char c, boolean convertJamo) {

		if (convertJamo) {
			c = convertFromCompatibilityJamo(c, false);
		}

		if (c >= 0x11a8 && c <= 0x11c2) {
			return true;
		}

		return false;
	}

	/*
	 * convert eg \u1103\u116e\u11af to \uB458
	 * 
	 * also converts 2 chars eg \u1103\u116E to \uB450
	 */
	public static StringBuilder unflattenKorean(String str) {

		StringBuilder ret = new StringBuilder();

		char lead = 0;
		char vowel = 0;
		char tail = 0;

		for (int i = 0; i < str.length(); i++) {

			boolean korean = false;

			char c = convertFromCompatibilityJamo(str.charAt(i), lead == 0);

			if (isKoreanLeadChar(c, false)) {
				korean = true;
				if (lead != 0) {
					appendKoreanChar(ret, lead, vowel, tail);
					lead = 0;
					vowel = 0;
					tail = 0;
				}
				lead = c;
			}
			if (isKoreanVowelChar(c, false)) {
				korean = true;
				vowel = c;
			}
			if (isKoreanTailChar(c, false)) {
				korean = true;
				tail = c;
				appendKoreanChar(ret, lead, vowel, tail);
				lead = 0;
				vowel = 0;
				tail = 0;
			}

			if (!korean) {
				// needed for eg
				// "\uD56D\uC131\uC740 \uD56D\uC0C1 \uD63C\uC790 \uC788\uB294
				// \uAC83\uC774 \uC544\uB2C8\uB77C, \uB450 \uAC1C
				// \uC774\uC0C1\uC758"
				// to stop order changing
				if (lead != 0) {
					appendKoreanChar(ret, lead, vowel, tail);
					lead = 0;
					vowel = 0;
					tail = 0;

				}
				ret.append(c);
			}
		}

		// make sure last char done!
		if (lead != 0) {
			appendKoreanChar(ret, lead, vowel, tail);
		}

		return ret;
	}

	private static void appendKoreanChar(StringBuilder ret, char lead,
			char vowel, char tail) {

		int lead0 = lead - 0x1100 + 1;
		int vowel0 = vowel - 0x1161 + 1;
		int tail0 = tail == 0 ? 0 : tail - 0x11a8 + 1;

		// http://gernot-katzers-spice-pages.com/var/korean_hangul_unicode.html
		char unicode = (char) (tail0 + (vowel0 - 1) * 28 + (lead0 - 1) * 588
				+ 44032);

		ret.append(unicode);
	}

	/**
	 * https://en.wikipedia.org/wiki/Hangul_Compatibility_Jamo
	 * 
	 * @param ch
	 * @param lead
	 * @return
	 */
	public static char convertFromCompatibilityJamo(char ch, boolean lead) {
		switch (ch) {
		case '\u3131':
			return lead ? '\u1100' : '\u11a8';

		case '\u3132':
			return lead ? '\u1101' : '\u11a9';

		case '\u3133':
			return '\u11aa';

		case '\u3134':
			return lead ? '\u1102' : '\u11ab';

		case '\u3135':
			return '\u11ac';

		case '\u3136':
			return '\u11ad';

		case '\u3137':
			return lead ? '\u1103' : '\u11ae';

		case '\u3138':
			return '\u1104';

		case '\u3139':
			return lead ? '\u1105' : '\u11af';

		case '\u313a':
			return '\u11b0';

		case '\u313b':
			return '\u11b1';

		case '\u313c':
			return '\u11b2';

		case '\u313d':
			return '\u11b3';

		case '\u313e':
			return '\u11b4';

		case '\u313f':
			return '\u11b5';

		case '\u3140':
			return '\u11b6';

		case '\u3141':
			return lead ? '\u1106' : '\u11b7';

		case '\u3142':
			return lead ? '\u1107' : '\u11b8';

		case '\u3143':
			return '\u1108';

		case '\u3144':
			return lead ? '\u1121' : '\u11b9';

		case '\u3145':
			return lead ? '\u1109' : '\u11ba';

		case '\u3146':
			return lead ? '\u110a' : '\u11bb';

		case '\u3147':
			return lead ? '\u110b' : '\u11bc';

		case '\u3148':
			return lead ? '\u110c' : '\u11bd';

		case '\u3149':
			return '\u110d';

		case '\u314a':
			return lead ? '\u110e' : '\u11be';

		case '\u314b':
			return lead ? '\u110f' : '\u11bf';

		case '\u314c':
			return lead ? '\u1110' : '\u11c0';

		case '\u314d':
			return lead ? '\u1111' : '\u11c1';

		case '\u314e':
			return lead ? '\u1112' : '\u11a8';

		case '\u314f':
			return '\u1161';

		case '\u3150':
			return '\u1162';

		case '\u3151':
			return '\u1163';

		case '\u3152':
			return '\u1164';

		case '\u3153':
			return '\u1165';

		case '\u3154':
			return '\u1166';

		case '\u3155':
			return '\u1167';

		case '\u3156':
			return '\u1168';

		case '\u3157':
			return '\u1169';

		case '\u3158':
			return '\u116a';

		case '\u3159':
			return '\u116b';

		case '\u315a':
			return '\u116c';

		case '\u315b':
			return '\u116d';

		case '\u315c':
			return '\u116e';

		case '\u315d':
			return '\u116f';

		case '\u315e':
			return '\u1170';

		case '\u315f':
			return '\u1171';

		case '\u3160':
			return '\u1172';

		case '\u3161':
			return '\u1173';

		case '\u3162':
			return '\u1174';

		case '\u3163':
			return '\u1175';

		}

		return ch;
	}

	/*
	 * http://www.kfunigraz.ac.at/~katzer/korean_hangul_unicode.html
	 * http://gernot-katzers-spice-pages.com/var/korean_hangul_unicode.html
	 */
	private static void appendKoreanMultiChar(StringBuilder sb, char c) {
		char tail = (char) (0x11a7 + (c - 44032) % 28);
		char vowel = (char) (0x1161
				+ ((c - 44032 - (tail - 0x11a7)) % 588) / 28);
		char lead = (char) (0x1100 + (c - 44032) / 588);
		// Application.debug(Util.toHexString(c)+" decoded to
		// "+Util.toHexString(lead)+Util.toHexString(vowel)+Util.toHexString(tail));
		sb.append(lead);
		sb.append(vowel);
		if (!isKoreanLeadPlusVowelChar(c)) {
			sb.append(tail);
		}
	}

	// static {
	// String s = "\ub450";
	// s = flattenKorean(s);
	// Log.debug("length = " + s.length());
	// for (int i = 0; i < s.length(); i++) {
	// Log.debug(StringUtil.toHexString(s.charAt(i)));
	// }
	//
	// s = "\u1103\u116E";
	// s = unflattenKorean(s).toString();
	// Log.debug("\u1103\u116E goes to " + StringUtil.toHexString(s));
	// }

	/*
	 * avoid having to press shift by merging eg \u1100\u1100 to \u1101
	 * http://www.kfunigraz.ac.at/~katzer/korean_hangul_unicode.html
	 */
	public static String mergeDoubleCharacters(String str) {

		if (str.length() < 2) {
			return str;
		}

		if (sb == null) {
			sb = new StringBuilder();
		} else {
			sb.setLength(0);
		}

		char c, c2;

		for (int i = 0; i < str.length() - 1; i++) {
			int offset = 1;
			switch (c = str.charAt(i)) {
			case '\u1161': // these character are "doubled" by adding 2 to their
							// Unicode value
			case '\u1162':
			case '\u1165':
			case '\u1166':
				offset++;
				// fall through
				// case '\u1100' : // these character are "doubled" by adding 1
				// to their Unicode value
			case '\u1103':
				// case '\u1107' :
			case '\u1109':
			case '\u110c':
			case '\u11a8':
			case '\u11ba':
				if (str.charAt(i + 1) == c) {
					sb.append((char) (c + offset)); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1169':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1161') {
					sb.append('\u116a'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1162') {
					sb.append('\u116b'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1175') {
					sb.append('\u116c'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1169') {
					sb.append('\u116d'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1105':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1100') {
					sb.append('\u11b0'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1106') {
					sb.append('\u11b1'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1107') {
					sb.append('\u11b2'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1109') {
					sb.append('\u11b3'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1110') {
					sb.append('\u11b4'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1112') {
					sb.append('\u11b6'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u116e':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1165') {
					sb.append('\u116f'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1166') {
					sb.append('\u1170'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1175') {
					sb.append('\u1171'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u116e') {
					sb.append('\u1172'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1173':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1175') {
					sb.append('\u1174'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1100':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1100') {
					sb.append('\u11a9'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1109') {
					sb.append('\u11aa'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1102':
				c2 = str.charAt(i + 1);
				if (c2 == '\u110c') {
					sb.append('\u11ac'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1112') {
					sb.append('\u11ad'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1111':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1111') {
					sb.append('\u11b5'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1107':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1109') {
					sb.append('\u11b9'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1107') {
					sb.append('\u1108'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			default:
				sb.append(c);
			}
			if (i == str.length() - 2) {
				sb.append(str.charAt(str.length() - 1));
			}

		}

		return sb.toString();
	}

}