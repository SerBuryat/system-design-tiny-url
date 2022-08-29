package com.systemdesign.tinyurl;

import static com.systemdesign.tinyurl.HashCoder.HASH_SYMBOLS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashCoderTest {

    private static final List<String> HASH_SYMBOLS_LIST = Arrays.asList(HASH_SYMBOLS.split(""));

    private StringBuilder strBuilder;

    @BeforeEach
    void init() {
        strBuilder = new StringBuilder();
    }

    @Test
    void intHashToStringHash_when23145() {
        String codedHash = HashCoder.intHashToStringHash(23145);
        String twentyThree = HASH_SYMBOLS_LIST.get(23);
        String fourteen = HASH_SYMBOLS_LIST.get(14);
        String five = HASH_SYMBOLS_LIST.get(5);
        assertEquals(codedHash,
                strBuilder.append(twentyThree)
                        .append(fourteen)
                        .append(five)
                        .toString()
        );
    }

    @Test
    void intHashToStringHash_when1000() {
        String codedHash = HashCoder.intHashToStringHash(1000);
        String ten = HASH_SYMBOLS_LIST.get(10);
        String zero = HASH_SYMBOLS_LIST.get(0);
        assertEquals(codedHash,
                strBuilder.append(ten)
                        .append(zero)
                        .append(zero)
                        .toString()
        );
    }

    @Test
    void intHashToStringHash_when999() {
        String codedHash = HashCoder.intHashToStringHash(999);
        String nine = HASH_SYMBOLS_LIST.get(9);
        assertEquals(codedHash,
                strBuilder.append(nine)
                        .append(nine)
                        .append(nine)
                        .toString()
        );
    }

    @Test
    void intHashToStringHash_when53525150() {
        String codedHash = HashCoder.intHashToStringHash(53525150);
        String fiftyThree = HASH_SYMBOLS_LIST.get(53);
        String fiftyTwo = HASH_SYMBOLS_LIST.get(52);
        String fiftyOne = HASH_SYMBOLS_LIST.get(51);
        String fifty = HASH_SYMBOLS_LIST.get(50);
        assertEquals(codedHash,
                strBuilder.append(fiftyThree)
                        .append(fiftyTwo)
                        .append(fiftyOne)
                        .append(fifty)
                        .toString()
        );
    }

    @Test
    void intHashToStringHash_when333444555() {
        String codedHash = HashCoder.intHashToStringHash(333444555);
        String thirtyThree = HASH_SYMBOLS_LIST.get(33);
        String thirtyFour = HASH_SYMBOLS_LIST.get(34);
        String fortyFour = HASH_SYMBOLS_LIST.get(44);
        String five = HASH_SYMBOLS_LIST.get(5);
        assertEquals(codedHash,
                strBuilder.append(thirtyThree)
                        .append(thirtyFour)
                        .append(fortyFour)
                        .append(five)
                        .append(five)
                        .append(five)
                        .toString()
        );
    }
}
