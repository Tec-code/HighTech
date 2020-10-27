package com.ht.service.impl;

import com.ht.constants.EnterpriseUserRelaType;
import com.ht.dao.IEnterpriseDao;
import com.ht.dao.IEnterpriseDealDao;
import com.ht.dao.IEnterpriseUserRelaDao;
import com.ht.model.EnterpriseInfo;
import com.ht.model.EnterpriseUserRela;
import com.ht.service.IEnterpriseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class EnterpriseServiceImpl implements IEnterpriseService {

    private static final Log logger = LogFactory.getLog(EnterpriseServiceImpl.class);

    @Autowired
    IEnterpriseDao enterpriseDao;

    @Autowired
    IEnterpriseUserRelaDao enterpriseUserRelaDao;

    @Autowired
    IEnterpriseDealDao enterpriseDealDao;

    @Override
    public int CreateEnterprise(int userId, EnterpriseInfo enterpriseInfo) {

        Date now = new Date();
        enterpriseInfo.setCreateTime(now);
        enterpriseInfo.setUpdate(now);
        // todo 需要校验企业名称重复

        try {
            enterpriseDao.insertEnterprise(enterpriseInfo);
            System.out.println(enterpriseInfo.getEnterpriseId());

            EnterpriseUserRela enterpriseUserRela = new EnterpriseUserRela();
            enterpriseUserRela.setUserId(userId);
            enterpriseUserRela.setEnterpriseId(enterpriseInfo.getEnterpriseId());
            enterpriseUserRela.setRelaType(EnterpriseUserRelaType.ENTERPRISE_ADMIN.toString());
            enterpriseUserRela.setRelaStatus(EnterpriseUserRelaType.HANDOVER.toString());
            enterpriseUserRela.setCreateTime(now);
            enterpriseUserRela.setUpdate(now);

            enterpriseUserRelaDao.insertEnterpriseUserRela(enterpriseUserRela);
            return enterpriseInfo.getEnterpriseId();
        } catch (Exception e) {
            logger.error(e);
            return -1;
        }
    }

    @Override
    public List<EnterpriseInfo> GetEnterpriseList(int userId, String enterpriseName) {
        return enterpriseDealDao.getEnterpriseListByEnterpriseName(userId, enterpriseName);
    }
}
