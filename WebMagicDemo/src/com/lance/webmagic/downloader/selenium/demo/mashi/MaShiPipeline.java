package com.lance.webmagic.downloader.selenium.demo.mashi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lance.webmagic.pageprocessor.demo.githubrepo.GithubRepo;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MaShiPipeline implements Pipeline {
	 protected Logger logger = LoggerFactory.getLogger(getClass());
	 
	@Override
	public void process(ResultItems resultItems, Task task) {
		ItemInfo itemInfo = resultItems.get("item");
		logger.error("itemInfo:"+itemInfo.getItemName());
	}

}
