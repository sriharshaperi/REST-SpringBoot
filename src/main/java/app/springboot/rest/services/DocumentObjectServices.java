package app.springboot.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.springboot.rest.models.ContentModel;
import app.springboot.rest.models.DocumentObjectModel;
import app.springboot.rest.repositories.DocumentObjectRepository;

@Service
public class DocumentObjectServices {
	
	@Autowired
	DocumentObjectRepository domRepo;
	
	//post a new document
	public DocumentObjectModel postNewDocument(DocumentObjectModel dom) {
		
		boolean hasDocument = false;
		List<DocumentObjectModel> allDocs = domRepo.findAll();
		
		for (int i = 0; i < allDocs.size(); i++) {
			
			if(allDocs.get(i).getEmail().equals(dom.getEmail()))
			{
				hasDocument = true;
				break;
			}
		}
		if(hasDocument==false)
		{
			domRepo.save(dom);
			return dom;
		}
		else
			return new DocumentObjectModel();
	}
	
	
	
	
	
	
	
	//retrieve all documents
	public List<DocumentObjectModel> fetchAllDocuments() {
		return domRepo.findAll();
	}
	
	
	
	
	
	
	//retrieve a specific document based on the id
	public DocumentObjectModel getSpecificDocument(String id) {
		return domRepo.findById(id).orElse(new DocumentObjectModel()); 
	}
	
	
	
	
	
	
	//replace the entire document with another document
	public DocumentObjectModel replaceDocument(DocumentObjectModel dom, String id) {
		
		Optional<DocumentObjectModel> foundDoc = domRepo.findById(id);
		if(foundDoc.isPresent())
		{
			domRepo.delete(foundDoc.get());
			domRepo.save(dom);
		}
		else
			domRepo.save(dom);
		
		return dom;
	}
	
	
	
	
	
	//update specific fields in this document - list of nested documents
	public DocumentObjectModel updateContentInDocumentByAdding(ContentModel cm, String id) {
		
		Optional<DocumentObjectModel> dom = domRepo.findById(id);
		if(dom.isPresent())
		{
			if(dom.get().getContentModel()==null)
			{
				List<ContentModel> list = new ArrayList<>();
				list.add(cm);
				dom.get().setContentModel(list);
			}
			else
			{
				List<ContentModel> cmList = dom.get().getContentModel();
				boolean hasContent = false;
				
				for (int i = 0; i < cmList.size(); i++) {
					
					if(cmList.get(i).getTitle().equals(cm.getTitle()))
					{
						hasContent = true;
						break;
					}
				}
				if(hasContent==false)
					dom.get().getContentModel().add(cm);
			}
			domRepo.save(dom.get());
			
			return dom.get();
		}
		else
			return dom.orElse(new DocumentObjectModel());
	}
	
	
	
	
	
	
	//update specific fields in this document - list of nested documents
	public DocumentObjectModel updateContentInDocumentByRemoving(ContentModel cm, String id) {
		
		Optional<DocumentObjectModel> dom = domRepo.findById(id);
		if(dom.isPresent())
		{
			if(dom.get().getContentModel()==null)
			{
				List<ContentModel> list = new ArrayList<>();
				list.add(cm);
				dom.get().getContentModel().clear();
			}
			else
			{
				List<ContentModel> cmList = dom.get().getContentModel();
				boolean hasContent = false;
				int docAtIndex = 0;
				
				for (int i = 0; i < cmList.size(); i++) {
					
					if(cmList.get(i).getTitle().equals(cm.getTitle()))
					{
						docAtIndex = i;
						hasContent = true;
						break;
					}
				}
				if(hasContent==true)
					dom.get().getContentModel().remove(docAtIndex);
			}
				domRepo.save(dom.get());
			
			return dom.get();
		}
		else
			return dom.orElse(new DocumentObjectModel());
	}
	
	
	
	
	
	
		//update specific fields in this document - list of nested documents
		public DocumentObjectModel updateContentInDocumentByClearingAll(String id) {
			
			Optional<DocumentObjectModel> dom = domRepo.findById(id);
			if(dom.isPresent())
			{
				if(dom.get().getContentModel()!=null)
					dom.get().setContentModel(null);
				
				domRepo.save(dom.get());
				
				return dom.get();
			}
			else
				return dom.orElse(new DocumentObjectModel());
		}
		
		
		
		
		
		
		//update specific fields in this document - list of nested documents
		public DocumentObjectModel updateContentInDocumentByBulkInsert(List<ContentModel> bulkContent, String id) {
			
			Optional<DocumentObjectModel> dom = domRepo.findById(id);
			if(dom.isPresent())
			{
				if(dom.get().getContentModel()==null)
				{
					List<ContentModel> list = new ArrayList<>();
					bulkContent.forEach((n)-> {
						list.add(n);
					});
					dom.get().setContentModel(list);
				}
				else
				{
					bulkContent.forEach((n)-> {
						dom.get().getContentModel().add(n);
					});
				}
				domRepo.save(dom.get());
				
				return dom.get();
			}
			else
				return dom.orElse(new DocumentObjectModel());
		}
		
		
		
		
		
	
		//delete existing document
		public DocumentObjectModel removeDocument(String id) {
			
			Optional<DocumentObjectModel> dom = domRepo.findById(id);
			if(dom.isPresent())
			{
				domRepo.delete(dom.get());
				return dom.get();
			}
			else
				return new DocumentObjectModel();
		}	
		
		//delete existing document
		public String removeAllDocuments() {
			
			if(domRepo.findAll()!=null)
				domRepo.deleteAll();
			return "deleted all documents";
		}
		
		
		
		
		
		//bulk insert of documents
		public List<DocumentObjectModel> bulkInsertDocuments(List<DocumentObjectModel> list) {
			
			list.forEach((n)-> {
				postNewDocument(n);
			});
			
			return domRepo.findAll();
		}	
}
