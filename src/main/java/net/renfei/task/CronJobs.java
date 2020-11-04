package net.renfei.task;

import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import net.renfei.entity.ListData;
import net.renfei.entity.SearchItem;
import net.renfei.entity.TypeEnum;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务
 *
 * @author renfei
 */
@Slf4j
@Service
public class CronJobs {
    private final PostService postService;
    private final SearchService searchService;

    public CronJobs(PostService postService,
                    SearchService searchService) {
        this.postService = postService;
        this.searchService = searchService;
    }

    /**
     * 每天凌晨2点半执行更新文章评级
     */
    @Scheduled(cron = "0 30 2 * * ? ")
    public void executeUpdatePageRank() {
        log.info("== UpdatePostPageRankJob >>>>");
        postService.updatePageRank();
        log.info("== UpdatePostPageRankJob <<<<");
    }

    /**
     * 每天凌晨3点半执行更新搜索引擎
     */
    @Scheduled(cron = "0 30 3 * * ? ")
    public void executeUpdateSearchEngine() {
        log.info("== UpdateSearchEngineJob >>>>");
        searchService.deleteAll();
        searchService.index();
        log.info("== UpdateSearchEngineJob <<<<");
    }
}
