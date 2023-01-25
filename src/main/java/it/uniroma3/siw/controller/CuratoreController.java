package it.uniroma3.siw.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.service.CuratoreService;
import it.uniroma3.siw.upload.FileUploadUtil;
import it.uniroma3.siw.validation.CuratoreValidator;

@Controller
public class CuratoreController {
	
	@Autowired
	CuratoreService curatoreService;
	
    @Autowired
    CuratoreValidator curatoreValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/admin/curatore", method = RequestMethod.GET)
    public String addCuratore(Model model) {
    	logger.debug("addCuratore");
    	model.addAttribute("curatore", new Curatore());
        return "curatoreForm.html";
    }
    
    @RequestMapping(value="/admin/curatore", method=RequestMethod.POST)
    public RedirectView newCuratore(@ModelAttribute("curatore") Curatore curatore,
    		@RequestParam("image") MultipartFile multipartFile,Model model, BindingResult bindingResult) throws IOException {
    	
    this.curatoreValidator.validate(curatore, bindingResult);
      if (!bindingResult.hasErrors()) {
    	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    	curatore.setPhotos(fileName);
    	
    	Curatore savedCuratore =this.curatoreService.inserisci(curatore);
    	
    	String uploadDir = "curatore-photos/" + savedCuratore.getId();
    	
    	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    	
    	return new RedirectView("curatori");
    	}
      return new RedirectView("curatoreForm");
    }

    @RequestMapping(value = "/curatore/{id}", method = RequestMethod.GET)
    public String getCuratore(@PathVariable("id") Long id, Model model) {
    	Curatore curatore=this.curatoreService.curatorePerId(id);
    	model.addAttribute("curatore",curatore );
    	model.addAttribute("role", this.curatoreService.getCredentialsService().getRoleAuthenticated());

    	return "curatore.html";
    }

    @RequestMapping(value = "/curatore", method = RequestMethod.GET)
    public String getCuratori(Model model) {
    		model.addAttribute("curatori", this.curatoreService.tutti());
        	model.addAttribute("role", this.curatoreService.getCredentialsService().getRoleAuthenticated());

    		return "curatori.html";
    }
    
    @RequestMapping(value ="/admin/curatoreUpdate")
    public RedirectView updateCuratore(@ModelAttribute("curatore") Curatore curatore,
    		@RequestParam("image") MultipartFile multipartFile,
    		Model model, BindingResult bindingResult) throws IOException {
    	
    	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    	curatore.setPhotos(fileName);
    	
    	Curatore savedCuratore =this.curatoreService.inserisci(curatore);
    	
    	String uploadDir = "curatore-photos/" + savedCuratore.getId();
    	
    	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    	
    	return new RedirectView("curatori");
    	

}
    @RequestMapping(value = "/admin/curatori", method = RequestMethod.GET)
    public String getCuratori2(Model model) {
    		model.addAttribute("curatori", this.curatoreService.tutti());
    		return "uploadSuccessful.html";
    }
    @RequestMapping(value="/admin/curatoreForm", method = RequestMethod.GET)
    public String addCuratori2(Model model) {
    	logger.debug("addCuratoreFailed");
    	model.addAttribute("curatore", new Curatore());
        return "curatoreForm.html";
    }
    @RequestMapping(value="/admin/modCuratore/{id}",method= RequestMethod.GET)
    public String updateCuratore(@PathVariable("id")Long id, Model model) {
    	logger.debug("UpdateCuratore");
    	model.addAttribute("curatore", this.curatoreService.curatorePerId(id));

        return "curatoreFormMod.html";
    }
    @RequestMapping(value="/admin/curatore/{id}", method= RequestMethod.GET)
    public String removeCuratore(@PathVariable("id")Long id, Model model) {
    	logger.debug("inizio eliminazione");
    		this.curatoreService.deletedCuratore(id);
    		logger.debug("curatore cancellato");
    		model.addAttribute("curatori",this.curatoreService.tutti());
        	model.addAttribute("role", this.curatoreService.getCredentialsService().getRoleAuthenticated());

    		return "curatori.html";
    }
}
