package net.renfei.service;

import net.renfei.base.BaseService;
import net.renfei.entity.KitBoxMenus;
import net.renfei.entity.LinkVO;
import net.renfei.repository.OnlineDocumentsDOMapper;
import net.renfei.repository.entity.OnlineDocumentsDO;
import net.renfei.repository.entity.OnlineDocumentsDOExample;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: DocsService</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Service
public class DocsService extends BaseService {
    private final OnlineDocumentsDOMapper onlineDocumentsMapper;

    public DocsService(OnlineDocumentsDOMapper onlineDocumentsMapper) {
        this.onlineDocumentsMapper = onlineDocumentsMapper;
    }

    public List<KitBoxMenus> getDocs() {
        List<KitBoxMenus> kitBoxMenus = new ArrayList<>();
        List<Map<String, Object>> category = onlineDocumentsMapper.selectCategory();
        for (Map<String, Object> map : category
        ) {
            KitBoxMenus menus = new KitBoxMenus();
            menus.setTitle(map.get("category").toString());
            OnlineDocumentsDOExample example = new OnlineDocumentsDOExample();
            example.setOrderByClause("version DESC");
            example.createCriteria()
                    .andCategoryEqualTo(map.get("category").toString());
            List<OnlineDocumentsDO> onlineDocumentsDOS = onlineDocumentsMapper.selectByExample(example);
            List<LinkVO> links = new ArrayList<>();
            for (OnlineDocumentsDO onlineDocuments : onlineDocumentsDOS
            ) {
                LinkVO link = new LinkVO();
                link.setHref("/docs/" + onlineDocuments.getCategory() + "/" + onlineDocuments.getTitle() + "/" + onlineDocuments.getVersion() + "/" + onlineDocuments.getLang());
                link.setText(onlineDocuments.getTitle() + " " + onlineDocuments.getVersion() + " (" + onlineDocuments.getLang() + ")");
                link.setRel(onlineDocuments.getDescribe());
                links.add(link);
            }
            menus.setLinks(links);
            kitBoxMenus.add(menus);
        }
        return kitBoxMenus;
    }

    public OnlineDocumentsDO getEmbed(String category, String title, String version, String lang) {
        OnlineDocumentsDOExample example = new OnlineDocumentsDOExample();
        example.createCriteria()
                .andCategoryEqualTo(category)
                .andTitleEqualTo(title)
                .andVersionEqualTo(version)
                .andLangEqualTo(lang);
        return ListUtils.getOne(onlineDocumentsMapper.selectByExample(example));
    }
}
