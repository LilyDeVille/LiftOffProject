package org.launchcode.LiftOffProject.Controllers;

import org.launchcode.LiftOffProject.Repository.PhotoRepository;
import org.launchcode.LiftOffProject.Utilities.FileUploadUtil;
import org.launchcode.LiftOffProject.models.Photo;
import org.launchcode.LiftOffProject.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.Column;
import java.io.IOException;


@Controller
@RequestMapping("photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;


    @GetMapping("add")
    public String displayAddPhotoForm(Model model) {
        model.addAttribute(new Photo());
        return "photos/add";

    }

    @PostMapping("add")
    public String saveUser(Photo photo, Recipe recipe,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        photo.setPhotos(fileName);

       // Photo savedphoto = repo.save(user);

       String uploadDir = "recipe-photos/" + recipe.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "add";
    }
}







    // @PostMapping("add")
   // public String processPhotoForm(@ModelAttribute Photo newPhoto, Model model) {
    //    photoRepository.save(newPhoto);
    //    return "redirect";
  //  }

