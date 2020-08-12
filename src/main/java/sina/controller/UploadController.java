package sina.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sina.domain.ResultInfo;
import sina.service.UploadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;


@Controller("uploadController")
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    //,@RequestParam(value ="image",required = false) MultipartFile image
    //上传图片   有个人头像，背景图片，文章图片三种。主键分别为uid,pid,aid
    //status为1代表用户头像上传 为2代表文章图片上传 为3代表背景大图上传 为4代表背景小图上传
    @RequestMapping("/imageUpload")
    @ResponseBody
    public ResultInfo imageUpload(HttpServletRequest request) throws Exception {
        ResultInfo info=new ResultInfo();
        System.out.println("上传类uploadController  的imageUpload方法");
        // 使用fileupload组件完成文件上传
        // 上传的位置
        String id=request.getParameter("id");
        String status=request.getParameter("status");
        System.out.println("id值为"+id+"  status值为"+status);
        int idNub=0;
        int statusNub=0;
        if(id!=null&&id.length()>0){
            idNub=Integer.parseInt(id);
        }
        if(status!=null&&status.length()>0){
            statusNub= Integer.parseInt(status);
        }
        if(idNub==0||statusNub==0){
            System.out.println("参数异常1");
            throw new RuntimeException("传入参数异常");
        }
        String path1 = request.getSession().getServletContext().getRealPath("./");
        System.out.println(path1);
        String path = "";
        //把图片存到static下
        if(statusNub==1){
            path= request.getSession().getServletContext().getRealPath("./static/images/");
        }else if(statusNub==2){
            path= request.getSession().getServletContext().getRealPath("./static/aimages/");
        }else if(statusNub==3){
            path= request.getSession().getServletContext().getRealPath("./static/bimages/big/");
        }else if(statusNub==4){
            path= request.getSession().getServletContext().getRealPath("./static/bimages/small/");
        }else {
            System.out.println("status传参错误");
            throw new RuntimeException("status传参错误");
        }
        System.out.println("path为 "+path);
        // 判断，该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            // 创建该文件夹
            file.mkdirs();
        }
        //不用springmvc  原生的形式
        // 解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解析request
        List<FileItem> items = upload.parseRequest(request);
        // 遍历
        for(FileItem item:items){
            // 进行判断，当前item对象是否是上传文件项
            if(item.isFormField()){
                // 说明普通表单项  不上传
            }else{
                // 说明上传文件项
                // 获取上传文件的名称
                String filename = item.getName();
                //确保文件名唯一
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid+"_"+filename;
                // 完成文件上传
                item.write(new File(path,filename));
                // 删除临时文件
                item.delete();
                String msg=uploadService.imageUpload(statusNub,idNub,filename);
                info.setErrorMsg(msg);
            }
        }

        /*// 获取上传文件的名称
        String filename = image.getOriginalFilename();
        // 完成文件上传
        image.transferTo(new File(path,filename));
        String msg=uploadService.imageUpload(statusNub,idNub,filename);
        info.setErrorMsg(msg);*/
        System.out.println(info);
        return info;
    }
    //, MultipartFile video
    //视频上传
    @RequestMapping("/videoUpload")
    @ResponseBody
    public ResultInfo videoUpload(HttpServletRequest request) throws Exception {
        ResultInfo info=new ResultInfo();
        System.out.println("上传类uploadController  的videoUpload方法");
        String aid=request.getParameter("aid");
        int aidNub=0;
        if(aid!=null&&aid.length()>0){
            aidNub=Integer.parseInt(aid);
        }
        String path1 = request.getSession().getServletContext().getRealPath("./");
        System.out.println(path1);
        String path = request.getSession().getServletContext().getRealPath("./static/videos/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //不用springmvc  原生的形式
        // 解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解析request
        List<FileItem> items = upload.parseRequest(request);
        // 遍历
        for(FileItem item:items){
            // 进行判断，当前item对象是否是上传文件项
            if(item.isFormField()){
                // 说明普通表单项  不上传
            }else{
                // 说明上传文件项
                // 获取上传文件的名称
                String filename = item.getName();
                //确保文件名唯一
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid+"_"+filename;
                // 完成文件上传
                item.write(new File(path,filename));
                // 删除临时文件
                item.delete();
                String msg=uploadService.videoUpload(aidNub,filename);
                info.setErrorMsg(msg);
            }
        }
        /*String filename = video.getOriginalFilename();
        video.transferTo(new File(path,filename));
        String msg=uploadService.videoUpload(aidNub,filename);
        info.setErrorMsg(msg);*/
        System.out.println(info);
        return info;
    }

}
