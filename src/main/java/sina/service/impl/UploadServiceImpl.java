package sina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sina.dao.ArticleDao;
import sina.dao.UserDao;
import sina.dao.UserPhotoDao;
import sina.service.UploadService;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {
    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;
    @Qualifier("articleDao")
    @Autowired
    private ArticleDao articleDao;
    @Qualifier("userPhotoDao")
    @Autowired
    private UserPhotoDao userPhotoDao;

    //status为1代表用户头像上传  为2代表文章图片上传 为3代表背景大图上传 为4代表背景小图上传
    @Override
    public String imageUpload(int status, int id, String filename) {
        try {
            if(status==1){
                String uimage="./images/"+filename;
                System.out.println("uimage为 "+uimage);
                userDao.updateUimage(id,uimage);
                return "头像修改成功";
            }
            if(status==2){
                String aimage="./aimages/"+filename;
                System.out.println("aimage为 "+aimage);
                articleDao.updateAimage(id,aimage);
                return "文章图片上传成功";
            }
            if(status==3){
                String bigphoto="./bimages/big/"+filename;
                System.out.println("bigphoto为 "+bigphoto);
                userPhotoDao.updateBigphoto(id,bigphoto);
                return "背景大图修改成功";
            }
            if(status==4){
                String smallphoto="./bimages/small/"+filename;
                System.out.println("smallphoto为 "+smallphoto);
                userPhotoDao.updateSmallphoto(id,smallphoto);
                return "背景小图修改成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "上传图片失败";
        }
        return "参数传递错误2";
    }

    @Override
    public String videoUpload(int aid, String filename) {
        try {
            String avideo="./videos/"+filename;
            System.out.println("avideo相对路径 "+avideo);
            articleDao.updateAvideo(aid,avideo);
            return "上传视频成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传视频失败";
        }
    }
}
