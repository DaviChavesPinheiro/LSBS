public class Main {
    public static void main(String[] args) {
        try {
            LSBStegnography stegnography = new LSBStegnography();
            if(args[0].equals("encode")) {
                stegnography.setSource(args[1]);
                stegnography.addFile("doge.png");
                stegnography.addFile("Main.java");
                stegnography.addFile("LSBStegnography.java");
                
                System.out.println("SpaceAvailable: \t" + stegnography.getMaxSpaceAvailable());
                System.out.println("CurrentSize: \t\t" + stegnography.getCurrentZipSize());
                stegnography.encode();
            } else if(args[0].equals("decode")) {
                stegnography.decode(args[1]);
            }
        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }
}