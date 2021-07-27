import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

// TargetFile representa um arquivo (zip) com possíveis multiplos sub arquivos
public class TargetFile {
    private File targetFile;
    private List<File> subFiles = new ArrayList<File>();

    // Adiciona um arquivo a lista de subFiles
    public File addSubFile(String path) throws Exception {
        File file = new File(path);
        if(subFiles.indexOf(file) != -1) throw new Exception("[Error]: Duplicate file");
        subFiles.add(file);

        zipSubFiles();
        return file;
    }

    // Remove um arquivo da lista de subFiles
    public void removeSubFile(File file) {
        subFiles.remove(file);
        zipSubFiles();
    }

    // Retorna os subFiles zippados
    private void zipSubFiles() {
        try {
            if(targetFile != null) targetFile.delete();

            targetFile = File.createTempFile("LSBS-", "-files");
            targetFile.deleteOnExit();

            ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(targetFile));

            for (File subFile : subFiles) {
                ZipEntry zEntry = new ZipEntry(subFile.getName());
                stream.putNextEntry(zEntry);
                byte[] allBytes = new byte[(int)subFile.length()];

                InputStream inputStream = new FileInputStream(subFile.getAbsolutePath());
                inputStream.read(allBytes);
                stream.write(allBytes, 0, allBytes.length);

                stream.closeEntry();
            }
            stream.close();
        } catch (Exception e) {
            System.out.println("Exception occured:" + e.getMessage());
        }
    }

    // Retorna os bytes do targetFile
    public byte[] getFileBytes() throws Exception {
        byte[] allBytes = new byte[(int)targetFile.length()];
        InputStream inputStream = new FileInputStream(targetFile.getAbsolutePath());
        inputStream.read(allBytes);
        return allBytes;
    }

    // Retorna o tamanho dos arquivos compactados
    public long getTargetFileSize() {
        return targetFile == null ? 0 : targetFile.length();
    }

    public boolean isEmpty() {
        return subFiles.size() == 0 ? true : false;
    }
}
