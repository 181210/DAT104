package no.hvl.dat104;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import no.hvl.dat104.util.ValidatorUtil;

public class ValidatorUtilTest {
    private String in1 = "<h1 style=\"font-size:\n" +
            "20em;position:absolute;top:0;left:0;margin:0;padding:0;\n" +
            "background:red\">Hei</h1>";
    private String in4 = "Ola Nordman";
    private String in5 = "";
    private String in6 = null;
    private String in7 = "1234567";
    private String in8 = "@\"'*^%Â¤#";

    /*
     * < = &lt;
     * > = &gt;
     * " = &quot;
     * & = &amp;
     */
    private String r1 = "&lt;h1 style=&quot;font-size:\n" +
            "20em;position:absolute;top:0;left:0;margin:0;padding:0;\n" +
            "background:red&quot;&gt;Hei&lt;/h1&gt;";


    @Before
    public void setUp() {

    }

    @Test
    public void validerTall() {
        assertTrue(ValidatorUtil.isValidNumber(in7));
    }

    @Test
    public void validerUgyldigStreng() {
        assertFalse(ValidatorUtil.isValidNumber(in4));
    }

    @Test
    public void validerNavn() {
        assertTrue(ValidatorUtil.isValidString(in4));
    }

    @Test
    public void validerTomStreng() {
        assertFalse(ValidatorUtil.isValidString(in5));
    }

    @Test
    public void validerNull() {
        assertFalse(ValidatorUtil.isValidString(in6));
    }

    @Test
    public void validerUgyldigeTall() {
        assertFalse(ValidatorUtil.isValidString(in7));
    }

    @Test
    public void validerUgyligeSymboler() {
        assertFalse(ValidatorUtil.isValidString(in8));
    }

    @Test
    public void escapeHtmlHTML() {
        assertEquals(r1, ValidatorUtil.escapeHtml(in1));
    }

    @Test
    public void escapeHtmlNavn() {
        assertEquals(in4, ValidatorUtil.escapeHtml(in4));
    }

    @Test
    public void escapeHtmlEmpty() {
        assertEquals(in5, ValidatorUtil.escapeHtml(in5));
    }

    @Test
    public void escapeHtmlNull() {
        assertNull(ValidatorUtil.escapeHtml(in6));
    }

    @Test
    public void escapeHtmlAPI() {
    }

    @Test
    public void validerSubmit() {
    }
}