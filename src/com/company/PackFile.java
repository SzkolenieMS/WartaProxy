package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by pllstm on 25.05.2017.
 */
public class PackFile {


    public PackFile() {



    }


    public static void zipFolder(File folder, String name) throws Exception {
        byte[] buffer = new byte[18024];

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(name));
        FileInputStream in = new FileInputStream(folder);

        out.putNextEntry(new ZipEntry(name));

        int len;

        while((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }

        out.closeEntry();
        in.close();
        out.close();
    }
}
