package com.bahadirakin.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 10240; // 10KB

	private String contentFolderPath;

	@Override
	public void init() throws ServletException {
		// Local diskinizdeki dosya yolu.
		contentFolderPath = "C:\\Content";
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// FileName parametre olarak gönderiliyor.
		String fileName = request.getParameter("fileName");

		// Eðer gönderilen parametre boþsa hata döndürülüyor
		if (fileName == null || "".equals(fileName)) {
			// Loglama ya da artýk ne yapýlmak isteniyorsa.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			// Baþka error sayfalarýnýz varsa onlara yönlendirebilirsiniz.
			return;
		}

		// Sonra istenilen dosya ile content dosya yolu birleþtiriliyor.
		String requestedFile = contentFolderPath + File.separator + fileName;
		File file = new File(requestedFile);

		// Eðer dosya bulunmuyorsa yine hata döndürülüyor.
		if (!file.exists()) {
			// Loglama ya da artýk ne yapýlmak isteniyorsa.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			// Baþka error sayfalarýnýz varsa onlara yönlendirebilirsiniz.
			return;
		}

		// Dosya adýndan content type belirleniyor.
		String contentType = getServletContext().getMimeType(fileName);
		// TODO Eðer content type ile ilgili controller yapacaksanýz tam zamaný.

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		// Response Hazýrlanýyor.
		response.reset();
		response.setBufferSize(BUFFER_SIZE);
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "inline; filename=\""
				+ fileName + "\"");
		copyStreams(new FileInputStream(file), response.getOutputStream());
	}

	// Utility metodlarý
	public static void copyStreams(InputStream source, OutputStream destination)
			throws IOException {
		// Prepare streams.
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			// Open streams.
			input = new BufferedInputStream(source, BUFFER_SIZE);
			output = new BufferedOutputStream(destination, BUFFER_SIZE);

			// Write file contents to response.
			byte[] buffer = new byte[BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			close(output);
			close(input);
		}
	}

	public static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
