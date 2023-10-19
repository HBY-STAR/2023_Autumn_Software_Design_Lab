package org.example.receiver;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Test
public class DocumentTest {
    public  static Document document = new Document();

    @Test
    public void testLoad() {
        document.load("test.md");
        assertEquals(Document.file_path,"C:\\Users\\21714\\Desktop\\Code\\SoftwareDesign\\lab1\\SoftwareDesignLab1\\test.md");
        document.load("1.md");
        assertEquals(Document.file_path,"C:\\Users\\21714\\Desktop\\Code\\SoftwareDesign\\lab1\\SoftwareDesignLab1\\1.md");
    }

    @Test
    public void testSave() {
        document.load("test.md");
        int before = Document.doc_lines.size();
        List<String> insert = new ArrayList<>();
        insert.add("#");
        insert.add("Test_save");
        document.insert(1,insert);
        document.save();
        document.load("test.md");
        int after = Document.doc_lines.size();
        assertEquals(after,before+1);
    }

    @Test
    public void testInsert() {
        document.load("test.md");
        List<String> insert1 = new ArrayList<>();
        insert1.add("#");
        insert1.add("Test_header");
        document.insert(1,insert1);
        assertEquals(insert1,Document.doc_lines.get(0));

        List<String> insert2 = new ArrayList<>();
        insert2.add("*");
        insert2.add("Test_text");
        document.insert(2,insert2);
        assertEquals(insert2,Document.doc_lines.get(1));

        List<String> insert3 = new ArrayList<>();
        insert3.add("1.");
        insert3.add("Test_list");
        document.insert(3,insert3);
        assertEquals(insert3,Document.doc_lines.get(2));
    }

    @Test
    public void testDelete_row() {
        document.load("test.md");

        List<String> insert1 = new ArrayList<>();
        insert1.add("#");
        insert1.add("Test_delete_row_1");
        document.insert(1,insert1);

        List<String> insert2 = new ArrayList<>();
        insert2.add("##");
        insert2.add("Test_delete_row_2");
        document.insert(1,insert2);

        document.delete_row(1);

        assertEquals(Document.doc_lines.get(0),insert1);
    }

    @Test
    public void testDelete_text() {
        document.load("test.md");

        List<String> insert1 = new ArrayList<>();
        insert1.add("#");
        insert1.add("Test_delete_row_1");
        document.insert(1,insert1);

        List<String> insert2 = new ArrayList<>();
        insert2.add("##");
        insert2.add("Test_delete_row_2");
        document.insert(1,insert2);

        List<String> check = new ArrayList<>();
        check.add("Test_delete_row_2");
        document.delete_text(check);

        assertEquals(Document.doc_lines.get(0),insert1);
    }

    @Test
    public void testUndo() {
        document.load("test.md");

        List<String> insert1 = new ArrayList<>();
        insert1.add("#");
        insert1.add("Test_redo_row_1");
        document.insert(1,insert1);

        List<String> insert2 = new ArrayList<>();
        insert2.add("##");
        insert2.add("Test_redo_row_2");
        document.insert(1,insert2);

        document.undo();

        assertEquals(Document.doc_lines.get(0),insert1);
    }
    @Test
    public void testRedo() {
        document.load("test.md");

        List<String> insert1 = new ArrayList<>();
        insert1.add("#");
        insert1.add("Test_redo_row_1");
        document.insert(1,insert1);

        List<String> insert2 = new ArrayList<>();
        insert2.add("##");
        insert2.add("Test_redo_row_2");
        document.insert(1,insert2);

        document.undo();
        document.redo();

        assertEquals(Document.doc_lines.get(0),insert2);
    }
}