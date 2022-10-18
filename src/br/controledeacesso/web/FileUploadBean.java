//package br.controledeacesso.web;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//
//import org.primefaces.event.FileUploadEvent;
//
//@ManagedBean(name = "fileUploadBean")
//@RequestScoped
//public class FileUploadBean {
//
//	// Primitives
//	private static final int BUFFER_SIZE = 6124;
//	//private String folderToUpload;
//
//	/** Creates a new instance of UploadBean */
//	public FileUploadBean() {
//
//	}
//
//	public void handleFileUpload(FileUploadEvent event) {
//
////		ExternalContext extContext = FacesContext.getCurrentInstance()
////				.getExternalContext();
//
//		File result = new File("C://Uploaded//" + event.getFile().getFileName());
//		try {
//			FileOutputStream fileOutputStream = new FileOutputStream(result);
//
//			byte[] buffer = new byte[BUFFER_SIZE];
//
//			int bulk;
//			InputStream inputStream = event.getFile().getInputstream();
//			while (true) {
//				bulk = inputStream.read(buffer);
//				if (bulk < 0) {
//					break;
//				}
//				fileOutputStream.write(buffer, 0, bulk);
//				fileOutputStream.flush();
//			}
//
//			fileOutputStream.close();
//			inputStream.close();
//
//			FacesMessage msg = new FacesMessage("Sucesso", event.getFile()
//					.getFileName() + " foi carregado.");
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//
//			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//					"O arquivo(s) não foi carregado!", "");
//			FacesContext.getCurrentInstance().addMessage(null, error);
//		}
//	}
//
// }
