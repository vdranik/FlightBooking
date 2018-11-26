package com.vdranik.document.controllers;

import com.vdranik.document.entities.Document;
import com.vdranik.document.repos.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DocumentController {

  @Autowired
  private DocumentRepository repository;

  @RequestMapping("/displayUpload")
  public String displayUpload(ModelMap modelMap){
    List<Document> documents = repository.findAll();
    modelMap.addAttribute("documents", documents);
    return "documentUpload";
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String uploadDocument(@RequestParam("document") MultipartFile multipartFile, @RequestParam("id") long id, ModelMap modelMap){
    Document document = new Document();
    document.setId(id);
    document.setName(multipartFile.getOriginalFilename());
    try {
      document.setData(multipartFile.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
    repository.save(document);

    List<Document> documents = repository.findAll();
    modelMap.addAttribute("documents", documents);
    return "documentUpload";
  }

  @RequestMapping("/download")
  public StreamingResponseBody download(@RequestParam("id") Long id, HttpServletResponse response) {
    Document document = repository.findById(id).get();
    byte[] data = document.getData();

    response.setHeader("Content-Disposition","attachment;filename=" + document.getName() + ".jpeg");
    return outputStream -> {
      outputStream.write(data);
    };
  }
}
