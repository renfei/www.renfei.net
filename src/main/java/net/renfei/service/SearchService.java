package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.entity.ListData;
import net.renfei.entity.SearchItem;
import net.renfei.entity.TypeEnum;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.NumberUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.renfei.entity.SearchItem.INDEX_COORDINATES;

/**
 * <p>Title: SearchService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
public class SearchService extends BaseService {
    private final AggregateService aggregateService;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public SearchService(AggregateService aggregateService,
                         ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.aggregateService = aggregateService;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public ListData<SearchItem> search(String word, String pages, String rows) {
        return search(null, null, null, word, pages, rows);
    }

    public ListData<SearchItem> search(TypeEnum type, String word, String pages, String rows) {
        return search(type, null, null, word, pages, rows);
    }

    public ListData<SearchItem> search(TypeEnum type, Date startDate, Date endDate, String word, String pages, String rows) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (!BeanUtils.isEmpty(word)) {
            QueryBuilder wordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchQuery("title", word))
                    .should(QueryBuilders.matchQuery("content", word));
            queryBuilder.must(wordBuilder);
        }
        if (type != null) {
            queryBuilder.must(QueryBuilders.termQuery("type", type.getName()));
        }
        if (startDate != null) {
            queryBuilder.must(QueryBuilders.rangeQuery("date").gt(startDate));
        }
        if (endDate != null) {
            queryBuilder.must(QueryBuilders.rangeQuery("date").lt(startDate));
        }
        return search(queryBuilder, pages, rows);
    }

    public ListData<SearchItem> search(TypeEnum type, Long originalId, String pages, String rows) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("type", type.getName()))
                .must(QueryBuilders.termQuery("originalId", originalId));
        return search(queryBuilder, pages, rows);
    }

    public ListData<SearchItem> search(QueryBuilder queryBuilder, String pages, String rows) {
        index();
        int page = NumberUtils.parseInt(pages, 1) - 1,
                size = NumberUtils.parseInt(rows, 10);
        if (page < 0) {
            page = 0;
        }
        if (size < 0) {
            size = 10;
        }
        // 关键词高亮的色值
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                //添加分页  注意页码是从0开始的
                //pageable的实现类PageRequest的静态方法of
                //要排序就增加参数3 Sort.Direction.ASC升  Sort.Direction.DESC降
                .withPageable(PageRequest.of(page, size))
                //排序整体
                //根据字段排序fieldSort("字段名")   .order(SortOrder.ASC/DESC)
//                .withSort(SortBuilders.fieldSort("date").order(SortOrder.DESC))
                .build();
        //elasticsearchRestTemplate.search方法参数一,本机查询的构造,参数二index的类,可选参数三再次声明库名(可以多个)
        SearchHits<SearchItem> search = elasticsearchRestTemplate.search(query, SearchItem.class, IndexCoordinates.of(INDEX_COORDINATES));
        ListData<SearchItem> searchItemListData = new ListData<>();
        searchItemListData.setTotal(search.getTotalHits());
        List<SearchItem> searchItems = new ArrayList<>();
        search.forEach(searchItemSearchHit -> searchItems.add(searchItemSearchHit.getContent()));
        searchItemListData.setData(searchItems);
        return searchItemListData;
    }

    /**
     * 创建索引库，如果不存在触发全量同步
     */
    public void index() {
        //设置索引信息(绑定实体类)  返回IndexOperations
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(SearchItem.class);
        if (indexOperations.exists()) {
            // 已经存在
        } else {
            // 不存在索引库，创建索引库
            indexOperations.create();
            //Creates the index mapping for the entity this IndexOperations is bound to.
            //为该IndexOperations绑定到的实体创建索引映射。  有一个为给定类创建索引的重载,需要类的字节码文件
            Document mapping = indexOperations.createMapping();
            //writes a mapping to the index  将刚刚通过类创建的映射写入索引
            indexOperations.putMapping(mapping);
            // 全量同步
            List<SearchItem> searchItemAll = aggregateService.getAllDataBySearchItem();
            this.save(searchItemAll);
        }
    }

    public void save(SearchItem searchItem) {
        index();
        elasticsearchRestTemplate.save(searchItem);
    }

    public void save(List<SearchItem> searchItem) {
        index();
        elasticsearchRestTemplate.save(searchItem);
    }

    public void deleteAll() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(SearchItem.class);
        if (indexOperations.exists()) {
            // 已经存在，删除
            indexOperations.delete();
        }
    }
}
