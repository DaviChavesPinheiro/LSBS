public class Main {
   
    public static void main(String[] args) {
        try {
            if(args[0].equals("encode")) {
                LSBStegnography stegnography = new LSBStegnography(args[1]);
                stegnography.addFile(args[2]);
                stegnography.encode();
            } else {
                LSBStegnography stegnography = new LSBStegnography();
                stegnography.decode("out.png");
            }
        } catch (Exception e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }
}