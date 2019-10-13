package com.jni.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Utility class for loading a native library.
 * <p>
 * This class uses {@link System#loadLibrary(String)} to load
 * a library. If library is bundled with the Jar file and not
 * found in the java.library.path This class will extract the
 * library to Jar location and and loads the library.
 * If library is also missing in the Jar will throw an
 * {@link UnsatisfiedLinkError}.
 *
 * @author Yasitha Thilakaratne
 */
public class DLLLoader {

    /**
     * Loads the given native library.
     * If not found in the java.library.path will check and extract
     * from the Jar.
     *
     * @param libName name of the library
     */
    public static void loadLibrary(String libName) {
        try {
            System.loadLibrary(libName);
        } catch (UnsatisfiedLinkError e) {
            if (!extractLibFromJar(libName))
                throw new UnsatisfiedLinkError("Loading lib from Jar failed.");
            System.loadLibrary(libName);
        }
    }

    /**
     * Extracts the .dll library from thr Jar to current Jar location.
     *
     * @param libName name of the library
     * @return true if successfully extracted
     */
    private static boolean extractLibFromJar(String libName) {
        boolean success = true;

        String fileName = libName + ".dll";
        try {
            InputStream in = DLLLoader.class.getResourceAsStream("/" + fileName);
            String destination = new File(DLLLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            destination = destination.substring(0, destination.lastIndexOf('\\')) + '\\' + fileName;

            Files.copy(in, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }
}
