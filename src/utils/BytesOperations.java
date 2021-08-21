package src.utils;
public class BytesOperations {
    public static byte[] concatArrays(byte[] array1, byte[] array2) {
        byte[] bytes = new byte[array1.length + array2.length];
        for (int i = 0; i < array1.length; i++) {
            bytes[i] = array1[i];
        }

        for (int i = array1.length; i < array1.length + array2.length; i++) {
            bytes[i] = array2[i - array1.length];
        }

        return bytes;
    }

    public static byte getByte(int num, int bn) {
        return (byte)((num & (0xFF << (bn * 8))) >> (bn * 8));
    }

    public static int setByte(int num, byte byt, int n) {
        num = num & (~(0xFF << (n * 8)));
        return (num | (((int)byt & 0xFF) << (n * 8)));
    }

    public static int getHalfByte(int num, int bn) {
        return ((num & (0xF << (bn * 4))) >> (bn * 4)) & 0xF;
    }

    public static int getBit(int num, int n) {
        return ((num & (0b1 << n)) >> n) & 0b1;
    }

    public static int setBit(int num, int bit, int n) {
        num = num & (~(0b1 << n));
        return (num | ((bit & 0b1) << n));
    }

    public static int bytesToInt(byte[] bytes) {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            num = setByte(num, bytes[i], 3 - i);
        }
        return num;
    }
}
