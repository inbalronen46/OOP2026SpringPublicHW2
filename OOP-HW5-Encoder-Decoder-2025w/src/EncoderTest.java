import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;

public class EncoderTest {

    // --- בדיקות לוגיקה פנימית ---

    @Test
    public void testFullEncryptionDecryptionCycle() throws IOException {
        String originalText = "Java 2026!";
        String key = "ABC";
        
        StringWriter sw = new StringWriter();
        EncryptorWriter ew = new EncryptorWriter(sw, key, new ForwardShiftTransformer());
        ew.write(originalText);
        ew.close();
        
        String encryptedText = sw.toString();
        assertNotEquals("The encrypted text should not match the original.", originalText, encryptedText);

        StringReader sr = new StringReader(encryptedText);
        DecryptorReader dr = new DecryptorReader(sr, key, new BackwardShiftTransformer());
        
        StringBuilder result = new StringBuilder();
        int data;
        while ((data = dr.read()) != -1) {
            result.append((char) data);
        }
        dr.close();

        assertEquals("The decrypted text must match the original exactly.", originalText, result.toString());
    }

    @Test
    public void testCircularKeyLogic() throws IOException {
        String key = "A"; // ASCII 65
        String input = "AA";
        
        StringWriter sw = new StringWriter();
        EncryptorWriter ew = new EncryptorWriter(sw, key, new ForwardShiftTransformer());
        ew.write(input);
        ew.close();
        
        String result = sw.toString();
        
        // JUnit 4: assertEquals(String message, Object expected, Object actual)
        assertEquals("Key should cycle correctly.", (Object)result.charAt(0), (Object)result.charAt(1));
    }

    // --- בדיקות ארגומנטים של מתודת Main ---

    @Test(expected = IllegalArgumentException.class)
    public void testMainNoArguments() {
        String[] args = {};
        TestEncryptor.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMainInvalidFilePath() {
        String[] args = {"non_existent_file_2026.txt"};
        TestEncryptor.main(args);
    }

    @Test
    public void testMainDefaultKeyUsage() throws IOException {
        // יצירת קובץ זמני תקין לבדיקה
        File tempFile = File.createTempFile("testInput", ".txt");
        tempFile.deleteOnExit();
        
        String[] args = {tempFile.getAbsolutePath()};
        
        try {
            TestEncryptor.main(args);
            File encrypted = new File(tempFile.getParent(), "encrypted_" + tempFile.getName());
            assertTrue("Encrypted file should be created using default key", encrypted.exists());
            encrypted.delete();
        } catch (IllegalArgumentException e) {
            fail("Should not throw IllegalArgumentException when file path is valid");
        }
    }
}