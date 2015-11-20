package javatests.tests;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;
import javatests.Utils;

//
// for details see https://github.com/java-native-access/jna/blob/master/www/GettingStarted.md
// 
public class FileSystemTest {

    public static class mntent extends Structure {
        public String mnt_fsname; //Device or server for filesystem
        public String mnt_dir; //Directory mounted on
        public String mnt_type; //Type of filesystem: ufs, nfs, etc.
        public String mnt_opts;
        public int mnt_freq;
        public int mnt_passno;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList("mnt_fsname", "mnt_dir", "mnt_type", "mnt_opts", "mnt_freq", "mnt_passno");
        }
    }

    public interface CLib extends Library {
        CLib INSTANCE = (CLib) Native.loadLibrary("c", CLib.class);

        Pointer setmntent(String file, String mode);
        mntent getmntent(Pointer stream);
        int endmntent(Pointer stream);
    }
    
    private static void testMacOS(String[] args) {
        Utils.print("Yet to implement.");
    }

    private static void testLinux(String[] args) {
        Utils.print("Yet to implement.");
    }
    
    private static void testWindows(String[] args) {
        mntent mntEnt;
        Pointer stream = CLib.INSTANCE.setmntent("/etc/mtab", "r");
        while ((mntEnt = CLib.INSTANCE.getmntent(stream)) != null) {
            Utils.print("Mounted from: " + mntEnt.mnt_fsname);
            Utils.print("Mounted on: " + mntEnt.mnt_dir);
            Utils.print("File system type: " + mntEnt.mnt_type);
            Utils.print("-------------------------------");
        }
        CLib.INSTANCE.endmntent(stream);
    }
    
    public static void main(String[] args) {
        
        Utils.print(String.format("//// START %s ////", FileSystemTest.class.getSimpleName()));
        
        if (Platform.isWindows()) {
            testWindows(args);
        } else if (Platform.isLinux()) {
            testLinux(args);
        } else if (Platform.isMac()) {
            testMacOS(args);
        } else {
            Utils.print("Only supported on certain platforms right now.");
        }
        
        Utils.print(String.format("//// END   %s ////", FileSystemTest.class.getSimpleName()));
    }
}    
