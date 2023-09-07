package br.com.nesher.gerenciamentopessoas.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobTargetOption;
import com.google.cloud.storage.Storage.PredefinedAcl;
import com.google.cloud.storage.StorageOptions;

@Service
public class ArquivoService {

	private static Storage storage = StorageOptions.getDefaultInstance().getService();

	public String write(MultipartFile file) {
		try {
			BlobInfo blobInfo = storage.create(BlobInfo.newBuilder("gerenciamento-pessoas-vfs", file.getOriginalFilename()).build(),
					file.getBytes(),
					BlobTargetOption.predefinedAcl(PredefinedAcl.PUBLIC_READ));
			return blobInfo.getMediaLink();
			
		} catch (IllegalStateException | IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}