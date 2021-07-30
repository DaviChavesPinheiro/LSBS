package src.backend;
public class Main {
    public static void main(String[] args) {
        try {
            LSBStegnography stegnography = new LSBStegnography();
            if(args[0].equals("encode")) {
                TargetFile targetFile = new TargetFile();
                targetFile.addSubFile("doge.png");
                targetFile.addSubFile("Main.java");
                targetFile.addSubFile("LSBStegnography.java");

                stegnography.setSource(args[1]);
                
                System.out.println("SpaceAvailable: \t" + stegnography.getMaxSpaceAvailable());
                System.out.println("TargetFileSize: \t" + targetFile.getTargetFileSize());
                stegnography.encode(targetFile);
            } else if(args[0].equals("decode")) {
                stegnography.decode(args[1]);
            }
        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }
}