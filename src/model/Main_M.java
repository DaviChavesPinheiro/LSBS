package src.model;

public class Main_M {
    public static void main(String[] args) {
        try {
            LSBStegnography_M stegnography = new LSBStegnography_M();
            if(args[0].equals("encode")) {
                TargetFile_M targetFile = new TargetFile_M();
                targetFile.addSubFile("doge.png");
                targetFile.addSubFile("Main.java");
                targetFile.addSubFile("LSBStegnography.java");

                stegnography.setSource(args[1]);
                
                System.out.println("SpaceAvailable: \t" + stegnography.getMaxSpaceAvailable());
                System.out.println("TargetFileSize: \t" + targetFile.getTargetFileSize());
                stegnography.encode(targetFile);
            } else if(args[0].equals("decode")) {
                stegnography.decode();
            }
        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }
}