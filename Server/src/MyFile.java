public class MyFile {
    private int id;
    private String name;
    private byte[] array;
    private String fileExtension;

    public MyFile(int id, String name, byte[] array, String fileExtension) {
        this.id = id;
        this.name = name;
        this.array = array;
        this.fileExtension = fileExtension;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getArray() {
        return array;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
