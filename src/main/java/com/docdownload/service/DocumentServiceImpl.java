package com.docdownload.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docdownload.model.Document;
import com.docdownload.repository.DocumentMapper;


@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentMapper docRepo;


	@Override
	public boolean updateDocumentStatus(Long id,Document doc) {
		try {
			Document d=docRepo.checkDocPresent(id);
			if(d != null) {
								
				int res=docRepo.updateStatus(id, doc.getStatus(), new Date(), doc.getRejectReasons());
				if(res != 0) {
					return true;
				} else {
					return false;
				}
			} else {
				throw new Exception("Document with given id is not present");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	

	@Override
	public Document getDocumentContentById(Long id) {
		try {
			Document d=docRepo.checkDocPresent(id);
			if(d != null) {
				return docRepo.findDocContentById(id);
			} else {
				throw new Exception("Document with given id is not present");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	@Override
	public List<Document> getAllByPendingStatus() {
		return docRepo.findAllByPendingStatus();
	}


}
