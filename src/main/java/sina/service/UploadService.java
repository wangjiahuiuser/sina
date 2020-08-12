package sina.service;

public interface UploadService {


    String imageUpload(int status, int id, String filename);

    String videoUpload(int aid, String filename);
}
