package server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    String code;
    @BeforeEach
    void setUp(){
        code = CodeGenerator.getRandomNumberString();
    }

    @Test
    void testCodeNotEmpty(){
        assertNotEquals("", code);
    }

    @Test
    void testCodeLength() {
        assertEquals(6, code.length());
    }
}