package com.jansh.core.web.sitemesh.tagrules;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

public class ScriptTagRuleBundle implements TagRuleBundle {
	public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
		// Core rules for SiteMesh to be functional.
		defaultState.addRule("appscript",
				new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("appscript"), false));
	}

	public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
	}
}
