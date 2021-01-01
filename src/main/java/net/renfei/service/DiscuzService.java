package net.renfei.service;

import lombok.extern.slf4j.Slf4j;
import net.renfei.base.BaseService;
import net.renfei.discuz.repository.*;
import net.renfei.discuz.repository.entity.*;
import net.renfei.entity.DiscuzInfo;
import net.renfei.sdk.utils.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: DiscuzService</p>
 * <p>Description: 论坛服务</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Slf4j
@Service
public class DiscuzService extends BaseService {
    private final DiscuzCommonMemberDOMapper memberMapper;
    private final DiscuzUcenterMembersDOMapper membersMapper;
    private final DiscuzCommonUsergroupDOMapper userGroupMapper;
    private final DiscuzForumPostDOMapper discuzForumPostMapper;
    private final DiscuzCommonMemberCountDOMapper memberCountMapper;

    public DiscuzService(DiscuzCommonMemberDOMapper memberMapper,
                         DiscuzUcenterMembersDOMapper membersMapper,
                         DiscuzCommonUsergroupDOMapper userGroupMapper,
                         DiscuzForumPostDOMapper discuzForumPostMapper,
                         DiscuzCommonMemberCountDOMapper memberCountMapper) {
        this.memberMapper = memberMapper;
        this.membersMapper = membersMapper;
        this.userGroupMapper = userGroupMapper;
        this.discuzForumPostMapper = discuzForumPostMapper;
        this.memberCountMapper = memberCountMapper;
    }

    public DiscuzInfo getDiscuzInfo(String userName) {
        DiscuzCommonMemberDOExample memberExample = new DiscuzCommonMemberDOExample();
        DiscuzUcenterMembersDOExample membersExample = new DiscuzUcenterMembersDOExample();
        DiscuzCommonUsergroupDOExample userGroupExample = new DiscuzCommonUsergroupDOExample();
        DiscuzCommonMemberCountDOExample memberCountExample = new DiscuzCommonMemberCountDOExample();
        membersExample.createCriteria().andUsernameEqualTo(userName);
        DiscuzInfo discuzInfo = new DiscuzInfo();
        try {
            DiscuzUcenterMembersDO members = ListUtils.getOne(membersMapper.selectByExample(membersExample));
            if (members != null) {
                int uid = members.getUid();
                memberExample.createCriteria().andUidEqualTo(uid);
                DiscuzCommonMemberDO member = ListUtils.getOne(memberMapper.selectByExample(memberExample));
                if (member != null) {
                    discuzInfo.setPoints(member.getCredits());
                    userGroupExample.createCriteria().andGroupidEqualTo(member.getGroupid());
                    DiscuzCommonUsergroupDO userGroup = ListUtils.getOne(userGroupMapper.selectByExample(userGroupExample));
                    if (userGroup != null) {
                        discuzInfo.setUserGroup(userGroup.getGrouptitle());
                    }
                }
                memberCountExample.createCriteria().andUidEqualTo(uid);
                DiscuzCommonMemberCountDO memberCount = ListUtils.getOne(memberCountMapper.selectByExample(memberCountExample));
                if (memberCount != null) {
                    discuzInfo.setPostsCount(memberCount.getPosts());
                    discuzInfo.setOltime(memberCount.getOltime());
                    discuzInfo.setPrestige(memberCount.getExtcredits1());
                    discuzInfo.setMoney(memberCount.getExtcredits2());
                    discuzInfo.setContribution(memberCount.getExtcredits3());
                    discuzInfo.setEssenceCount(memberCount.getDigestposts());
                }
            }
            return discuzInfo;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return null;
        }
    }

    public List<DiscuzForumPostDO> getAllPost() {
        DiscuzForumPostDOExample example = new DiscuzForumPostDOExample();
        example.createCriteria()
                .andFirstEqualTo(1)
                .andInvisibleEqualTo(0)
                .andStatusGreaterThanOrEqualTo(0);
        try {
            return discuzForumPostMapper.selectByExampleWithBLOBs(example);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return null;
        }
    }
}
