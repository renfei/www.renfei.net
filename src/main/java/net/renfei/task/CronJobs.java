package net.renfei.task;

import lombok.extern.slf4j.Slf4j;
import net.renfei.service.MailService;
import net.renfei.service.PostService;
import net.renfei.service.SearchService;
import net.renfei.service.SslService;
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
    private final SslService sslService;
    private final PostService postService;
    private final MailService mailService;
    private final SearchService searchService;

    public CronJobs(SslService sslService,
                    PostService postService,
                    MailService mailService,
                    SearchService searchService) {
        this.sslService = sslService;
        this.postService = postService;
        this.mailService = mailService;
        this.searchService = searchService;
    }

    /**
     * 每天凌晨2点半执行更新文章评级
     */
    @Scheduled(cron = "0 30 2 * * ? ")
    public void executeUpdatePageRank() {
        log.info("== UpdatePostPageRankJob >>>>");
        try {
            postService.updatePageRank();
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("UpdatePostPageRankJob：更新文章评级");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            log.error("UpdatePostPageRankJob：更新文章评级", exception);
            mailService.send("i@renfei.net", "RenFei", "定时任务【UpdatePostPageRankJob】执行失败通知", data);
        }
        log.info("== UpdatePostPageRankJob <<<<");
    }

    /**
     * 每天凌晨3点半执行更新搜索引擎
     */
    @Scheduled(cron = "0 30 3 * * ? ")
    public void executeUpdateSearchEngine() {
        log.info("== UpdateSearchEngineJob >>>>");
        try {
            searchService.deleteAll();
            searchService.index();
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("UpdateSearchEngineJob：更新搜索引擎");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            log.error("UpdateSearchEngineJob：更新搜索引擎", exception);
            mailService.send("i@renfei.net", "RenFei", "定时任务【UpdateSearchEngineJob】执行失败通知", data);
        }
        log.info("== UpdateSearchEngineJob <<<<");
    }

    /**
     * 每天凌晨4点半执行更新SSL证书
     */
//    @Scheduled(cron = "0 30 4 * * ? ")
    public void executeCheckSslCertificate() {
        log.info("== CheckSslCertificateJob >>>>");
        try {
            sslService.checkSslCertificate();
        } catch (Exception exception) {
            List<String> data = new ArrayList<>();
            data.add("CheckSslCertificateJob：更新SSL证书");
            data.add("定时执行任务失败。");
            data.add("异常信息：");
            data.add(exception.getMessage());
            log.error("CheckSslCertificateJob：更新SSL证书", exception);
            mailService.send("i@renfei.net", "RenFei", "定时任务【CheckSslCertificateJob】执行失败通知", data);
        }
        log.info("== CheckSslCertificateJob <<<<");
    }
}
