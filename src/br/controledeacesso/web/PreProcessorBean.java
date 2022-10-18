package br.controledeacesso.web;

import java.io.File;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;

@ManagedBean(name = "preProcessorBean")
public class PreProcessorBean {

	private ServletContext servletContext;
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void preProcessPDF(Object document) throws IOException,
			BadElementException, DocumentException {

		filename = "controlSecLogoBlack2";
		Document pdf = (Document) document;
		HeaderFooter headerFooter = new HeaderFooter(new Phrase(
				"Relatório de Visitas"), true);

		pdf.open();
		pdf.setPageSize(PageSize.A4);
		pdf.setHeader(headerFooter);

		String logo = servletContext.getRealPath(File.separator + "logo"
				+ File.separator + filename + ".png");

		pdf.add(Image.getInstance(logo));

	}

	public PreProcessorBean() {
		servletContext = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

//	public void postProcessPDF(Object document) throws Exception {
//		com.itextpdf.text.Document pdf = (com.itextpdf.text.Document) document;
//		final String functionName = "postProcessPDF";
//		logger.enterFunction(functionName);
//		com.itextpdf.text.Paragraph space = new com.itextpdf.text.Paragraph();
//		addEmptyLine(space, 2);
//		pdf.add(space);
//		// Legal Disclaimer section
//		pdf.add(new com.itextpdf.text.Paragraph(rb
//				.getString("lbl.common.legalDesclaimer"), legalDisFont));
//		space.clear();
//		addEmptyLine(space, 1);
//		pdf.add(space);
//		// Legal Info section
//		pdf.add(new com.itextpdf.text.Paragraph(rb
//				.getString("lbl.common.exportdisclaimer1"), legalDisInfoFont));
//		space.clear();
//		// addEmptyLine(space, 1);
//		pdf.add(space);
//
//	}
}
