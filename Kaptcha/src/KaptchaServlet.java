import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KaptchaServlet extends HttpServlet implements Servlet {
	private Properties props;
	private Producer kaptchaProducer;
	private String sessionKeyValue;

	public KaptchaServlet() {
		this.props = new Properties();

		this.kaptchaProducer = null;

		this.sessionKeyValue = null;
	}

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);

		ImageIO.setUseCache(false);

		Enumeration initParams = conf.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			String key = (String) initParams.nextElement();
			String value = conf.getInitParameter(key);
			this.props.put(key, value);
		}

		Config config = new Config(this.props);
		this.kaptchaProducer = config.getProducerImpl();
		this.sessionKeyValue = config.getSessionKey();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setDateHeader("Expires", 0L);

		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		resp.addHeader("Cache-Control", "post-check=0, pre-check=0");

		resp.setHeader("Pragma", "no-cache");

		resp.setContentType("image/jpeg");

		String capText = this.kaptchaProducer.createText();
		String s1 = capText.substring(0, 1);
		String s2 = capText.substring(1, 2);
		int r = Integer.valueOf(s1).intValue() + Integer.valueOf(s2).intValue();

		req.getSession().setAttribute(this.sessionKeyValue, String.valueOf(r));

		BufferedImage bi = this.kaptchaProducer.createImage(s1+"+"+s2+"=?");

		ServletOutputStream out = resp.getOutputStream();

		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}
}