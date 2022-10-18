package br.controledeacesso.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.hibernate.HibernateException;
import org.primefaces.event.CaptureEvent;

@ManagedBean
@ViewScoped
public class PhotoCamView {

	private String filename;
	private boolean exibeImagemCapturada;
	private ServletContext servletContext;

	public String getFilename() {
		return filename;
	}

	public boolean isExibeImagemCapturada() {
		return exibeImagemCapturada;
	}

	public void setExibeImagemCapturada(boolean exibeImagemCapturada) {
		this.exibeImagemCapturada = exibeImagemCapturada;
	}

	public void oncapture(CaptureEvent captureEvent) throws IOException {
		filename = "foto";

		String newFileName = servletContext.getRealPath(File.separator
				+ "photocam" + File.separator + filename + ".png");

		byte[] data = captureEvent.getData();
		exibeImagemCapturada = true;

		// D:\Eclipse Kepler x64\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ControleDeAcesso\photocam

		FileImageOutputStream imageOutput;

		try {
			imageOutput = new FileImageOutputStream(new File(newFileName));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();

		} catch (HibernateException e) {
			throw new FacesException("Erro ao capturar a imagem.", e);
		}
	}

	public byte[] fotoSave() throws IOException {

		// ServletContext servletContext = (ServletContext) FacesContext
		// .getCurrentInstance().getExternalContext().getContext();

		File foto = new File(servletContext.getRealPath(File.separator
				+ "photocam" + File.separator + "foto" + ".png"));

		byte[] bFoto = new byte[(int) foto.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(foto);
			fileInputStream.read(bFoto);
			fileInputStream.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return bFoto;

	}

	public PhotoCamView() {
		exibeImagemCapturada = false;
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

}
