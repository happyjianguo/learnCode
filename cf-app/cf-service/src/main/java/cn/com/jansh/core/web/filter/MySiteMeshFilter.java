package cn.com.jansh.core.web.filter;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import cn.com.jansh.core.web.sitemesh.tagrules.ScriptTagRuleBundle;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

	/**
	 * 配置渲染器
	 */
	private Map<String, String> dcoratorPaths;
	/**
	 * 不被渲染的路径
	 */
	private List<String> excludedPaths;

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

		builder = builder.addTagRuleBundles(new ScriptTagRuleBundle());

		// 配置渲染器. 将应用于指定的路径
		Set<Entry<String, String>> dcoratorEntrys = dcoratorPaths.entrySet();
		for (Entry<String, String> entry : dcoratorEntrys) {
			builder = builder.addDecoratorPath(entry.getKey(), entry.getValue());
		}
		// 不被渲染的路径
		for (String excluded : excludedPaths) {
			builder = builder.addExcludedPath(excluded);
		}

		// builder.addTagRuleBundles(new ScriptTagRuleBundle())
		// // 配置默认的渲染器. 将应用于所有路径.
		// .addDecoratorPath("/*", "/WEB-INF/views/layout/main.jsp")
		// // 不被渲染的路径
		// .addExcludedPath("/resources/*").addExcludedPath("/*/ajax/*").addExcludedPath("/login*");

		// 配置特定路径的渲染器.
		// .addDecoratorPath("/admin/*", "/another-decorator.html")
		// .addDecoratorPath("/*.special.jsp",
		// "/special-decorator.html")
		// 配置多个渲染器.
		// .addDecoratorPaths("/articles/*", "/decorators/article.html",
		// "/decoratos/two-page-layout.html",
		// "/decorators/common.html")

	}

	public Map<String, String> getDcoratorPaths() {
		return dcoratorPaths;
	}

	public void setDcoratorPaths(Map<String, String> dcoratorPaths) {
		this.dcoratorPaths = dcoratorPaths;
	}

	public List<String> getExcludedPaths() {
		return excludedPaths;
	}

	public void setExcludedPaths(List<String> excludedPaths) {
		this.excludedPaths = excludedPaths;
	}

}
