public class Main {
   
    public static void main(String[] args) {
        try {
            LSBStegnography stegnography = new LSBStegnography();
            if(args[0].equals("encode")) {
                stegnography.setSource(args[1]);
                stegnography.addFile(args[2]);
                // stegnography.encode();
            } else if(args[0].equals("decode")) {
                stegnography.decode("out.png");
            }
        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }
}