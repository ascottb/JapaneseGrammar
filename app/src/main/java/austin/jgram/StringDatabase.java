package austin.jgram;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Austin on 5/15/16.
 */
public class StringDatabase {
    Context context;
    String data[];
    int dataSize;
    String FILENAME = "j_file";
    public StringDatabase(Context con) {
        context = con;
    }

    public void createData (String stringList[], int size) {
        data = stringList;
        dataSize = size;
    }
    public String findCharacter (char c) {
        int x;
        String find = null;
        for (x = 0; x < dataSize; x++) {
            //find c in data
        }
        return find;
    }

    public void storeToPhone (String stringList[], int size) throws IOException {
        StringBuilder csvList = new StringBuilder();
        for(String s : data){
            csvList.append(s);
            csvList.append(",");
        }

        FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.write(csvList.toString().getBytes());
        fos.close();
    }

    public void loadFromPhone (String stringList[], int size) throws IOException {
        String csvList;
        byte[] buffer = new byte[4];
        FileInputStream fos = context.openFileInput(FILENAME);
        fos.read(buffer);
        fos.close();
        csvList = buffer.toString();

        String[] items = csvList.split(",");
        for(int i=0; i < items.length; i++) {
            data[i] = items[i];
        }

    }

    public boolean isKana(char c) {
        int val = (int)c;
        if (val >= 0x3040 && val <= 0x309f)
            return true; //HIRAGANA
        else if (val >= 0x30a0 && val <= 0x30ff)
            return true; //KATANANA
        else
            return false; //KANJI
    }

    public void getAllWords() {
        //String[] words = new String[20];
        //words[0] = "Test";
        int x, y, z;
        char c;
        for (x = 0; x < data.length; x++) {
            for (y = 0; y < data[x].length(); y++) {
                c = data[x].charAt(y);
                if (isKana(c)) {
                    //TODO: get words
                    z = y;
                    //data[x] = data[x].replace(c, "");
                    data[x] = data[x].substring(0, z) + data[x].substring(z + 1);
                    y--;
                }
            }
        }
        //return words;
    }

}