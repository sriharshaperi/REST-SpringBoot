package app.springboot.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.springboot.rest.models.ContentModel;
import app.springboot.rest.models.DocumentObjectModel;
import app.springboot.rest.services.DocumentObjectServices;

@RestController
@RequestMapping("rest")
public class RestResourceController {
	
	@Autowired
	DocumentObjectServices domService;
	
	@PostMapping("postOne")
	public DocumentObjectModel postOne(@RequestBody DocumentObjectModel dom) {
		return domService.postNewDocument(dom);
	}
	
	@PostMapping("postMany")
	public List<DocumentObjectModel> postMany(@RequestBody List<DocumentObjectModel> list) {
		return domService.bulkInsertDocuments(list);
	}
	
	
	@GetMapping("getMany")
	public List<DocumentObjectModel> getAll() {
		return domService.fetchAllDocuments();
	}
	
	@GetMapping("getOne/{id}")
	public DocumentObjectModel getDocument(@PathVariable("id") String id) {
		return domService.getSpecificDocument(id);
	}
	
	@PutMapping("put/{id}")
	public DocumentObjectModel changeDocument(@RequestBody DocumentObjectModel dom, @PathVariable("id") String id) {
		return domService.replaceDocument(dom, id);
	}
	
	@PutMapping ("addContentOne/{id}")
	public DocumentObjectModel addContent(@RequestBody ContentModel cm, @PathVariable("id") String id) {
		return domService.updateContentInDocumentByAdding(cm, id);
	}
	
	@PutMapping ("removeContentOne/{id}")
	public DocumentObjectModel removeContent(@RequestBody ContentModel cm, @PathVariable("id") String id) {
		return domService.updateContentInDocumentByRemoving(cm, id);
	}
	
	@PutMapping ("removeContentMany/{id}")
	public DocumentObjectModel clearContent(@PathVariable("id") String id) {
		return domService.updateContentInDocumentByClearingAll(id);
	}
	
	@PutMapping ("addContentMany/{id}")
	public DocumentObjectModel bulkInsertContent(@RequestBody List<ContentModel> bulkContent, @PathVariable("id") String id) {
		return domService.updateContentInDocumentByBulkInsert(bulkContent, id);
	}
	
	@DeleteMapping("removeOne/{id}")
	public DocumentObjectModel remove(@PathVariable("id") String id) {
		return domService.removeDocument(id);
	}
	
	@DeleteMapping("removeAll")
	public String removeAll() {
		return domService.removeAllDocuments();
	}
}