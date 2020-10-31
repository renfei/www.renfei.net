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
    private final AggregateService aggregateService;

    public CronJobs(PostService postService,
                    SearchService searchService,
                    AggregateService aggregateService) {
        this.aggregateService = aggregateService;
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
        List<SearchItem> searchItemAll = aggregateService.getAllDataBySearchItem();
        // 开始查询搜索引擎里的数据
        List<SearchItem> notInSearchEngine = new ArrayList<>();
        for (SearchItem searchItem : searchItemAll
        ) {
            // 检查数据是否在搜索引擎中
            ListData<SearchItem> searchItemListData =
                    searchService.search(TypeEnum.valueOf(searchItem.getType()), searchItem.getOriginalId(), "1", "999");
            if (BeanUtils.isEmpty(searchItemListData) &&
                    searchItemListData.getTotal() == 0 &&
                    BeanUtils.isEmpty(searchItemListData.getData())) {
                notInSearchEngine.add(searchItem);
            }
        }
        if (notInSearchEngine.size() > 0) {
            searchService.save(notInSearchEngine);
        }
        log.info("== UpdateSearchEngineJob <<<<");
    }

    /**
     * 每个月25号凌晨1点重建全部搜索引擎的数据
     */
    @Scheduled(cron = "0 0 1 25 * ? ")
    public void executeClearSearchEngine() {
        searchService.deleteAll();
        searchService.index();
    }
}
