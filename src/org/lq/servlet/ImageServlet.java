package org.lq.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lq.util.ImageCodeUtil;


public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> words;
	private Random random = new Random();
	private String randomWords() {
		return words.get(random.nextInt(words.size()));
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		String wordPath = config.getInitParameter("wordPath");
		System.out.println("wordPath="+wordPath);
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(wordPath);

		try {
			words = new LinkedList<>();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String line = null;

			while ((line = reader.readLine()) != null) {
				words.add(line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");

		String word = randomWords();
		request.getSession().setAttribute("imageCode", word);
		ImageCodeUtil.drawImageCode(word, 180, 30, response.getOutputStream());

	}

}