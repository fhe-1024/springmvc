package demo;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class LaughDemoTest extends BreadthCrawler {

	public LaughDemoTest(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		// TODO Auto-generated constructor stub
		this.addSeed("http://www.qiushibaike.com/text/page/1");
		this.addRegex("^*/text/page/*");
		/* do not fetch jpg|png|gif */
		this.addRegex("-.*\\.(jpg|png|gif).*");
		/* do not fetch url contains # */
		this.addRegex("-.*#.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		Elements elements = page.select("div#content-left>div");
		for (int rank = 0; rank < elements.size(); rank++) {
			Element e = elements.get(rank);
			System.out.println("标签id:" + e.id().substring(11));
			System.out.println("头像图片:" + e.select("img").attr("src"));
			System.out.println("昵称:" + e.select("img").attr("alt"));
			System.out.println("用户id:" + e.select("a").eq(1).attr("href").substring(7, 15));
			System.out.println("性别:" + (e.select("div.articleGender").attr("class").contains("womenIcon") ? "女" : "男"));
			System.out.println("糗事内容:" + e.select("div.content").text());
			System.out.println("笑脸:" + e.select("li.up").text());
			System.out.println("哭脸:" + e.select("li.down").text());
		}
		// System.out.println("begin------" +
		// page.select("ul.pagination>li>a").last().attr("href") + "------end");
		// next.add("http://www.qiushibaike.com" +
		// page.select("ul.pagination>li>a").last().attr("href"));
	}

	// @Override
	// public void visit(Page page, CrawlDatums next) {
	// Elements elements = page.select("div.content");
	// for (int rank = 0; rank < elements.size(); rank++) {
	// Element e = elements.get(rank);
	// System.out.println(e.text());
	// }
	// System.out.println("begin------" +
	// page.select("ul.pagination>li>a").last().attr("href") + "------end");
	// next.add("http://www.qiushibaike.com" +
	// page.select("ul.pagination>li>a").last().attr("href"));
	// }

	public static void main(String[] args) throws Exception {
		LaughDemoTest crawler = new LaughDemoTest("crawl", true);
		crawler.setThreads(50);
		crawler.setTopN(50);
		crawler.start(10);
		// System.out.println(System.currentTimeMillis());
	}

}
