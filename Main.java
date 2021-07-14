import java.io.File;

public class Main {
   
    public static void main(String[] args) {
        try {
            LSBStegnography stegnography = new LSBStegnography();
            if(args[0].equals("encode")) {
                stegnography.setSource(args[1]);
                File f1 = stegnography.addFile(args[2]);
                File f2 = stegnography.addFile(args[2]);
                stegnography.removeFile(f2);
                File f3 = stegnography.addFile(args[2]);
                stegnography.removeFile(f1);
                stegnography.removeFile(f3);
                stegnography.removeFile(f3);
                
                System.out.println(stegnography.getCurrentFilesSize());
                // stegnography.encode();
            } else if(args[0].equals("decode")) {
                stegnography.decode("out.png");
            }
        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }
}