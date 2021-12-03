package com.docdownload.service;

import java.util.List;

import com.docdownload.model.Document;

public interface DocumentService {
	
	public List<Document> getAllByPendingStatus();
	
	public Document getDocumentContentById(Long id);
	
	public boolean updateDocumentStatus(Long id,Document doc);
}
