package com.ht.service.impl;

import com.ht.dao.*;
import com.ht.model.EnterpriseProject;
import com.ht.model.EnterpriseProjectApproval;
import com.ht.model.EnterpriseProjectBasic;
import com.ht.service.IEnterpriseProjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class EnterpriseProjectServiceImpl implements IEnterpriseProjectService {

    private static final Log logger = LogFactory.getLog(EnterpriseProjectServiceImpl.class);

    @Autowired
    IEnterpriseProjectDao enterpriseProjectDao;

    @Autowired
    IEnterpriseDao enterpriseDao;

    @Autowired
    IEnterpriseProjectApprovalDao enterpriseProjectApprovalDao;

    @Autowired
    IEnterpriseProjectBasicDao enterpriseProjectBasicDao;

    @Override
    public List<EnterpriseProject> getProjectList(String projectName) {
        return enterpriseProjectDao.getEnterpriseProjectByProjectName(projectName);
    }

    @Override
    public EnterpriseProject getProject(String projectId) {
        return enterpriseProjectDao.getEnterpriseProjectByProjectId(projectId);
    }

    @Override
    public String createProject(EnterpriseProject enterpriseProject) {

        Date now = new Date();
        enterpriseProject.setCreateTime(now);
        enterpriseProject.setUpdateTime(now);

        try {
            // 向企业项目表中插入数据
            enterpriseProjectDao.insertEnterpriseProject(enterpriseProject);
            int enterpriseId = enterpriseProject.getEnterpriseId();
            String projectId = enterpriseProject.getProjectId();

            System.out.println("enterpriseId:" + enterpriseId);
            System.out.println("projectId:" + projectId);
            System.out.println("projectName:" + enterpriseProject.getProjectName());

            // 向企业项目立项信息表中插入数据
            EnterpriseProjectApproval enterpriseProjectApproval = new EnterpriseProjectApproval();
            enterpriseProjectApproval.setEnterpriseId(enterpriseId);
            enterpriseProjectApproval.setProjectId(projectId);
            enterpriseProjectApproval.setCreateTime(now);
            enterpriseProjectApproval.setUpdateTime(now);
            enterpriseProjectApprovalDao.insertEnterpriseProjectApproval(enterpriseProjectApproval);

            // 向企业信息基础表中插入数据
            EnterpriseProjectBasic enterpriseProjectBasic = new EnterpriseProjectBasic();
            enterpriseProjectBasic.setEnterpriseId(enterpriseId);
            enterpriseProjectBasic.setProjectId(projectId);
            enterpriseProjectBasic.setCreateTime(now);
            enterpriseProjectBasic.setUpdateTime(now);
            enterpriseProjectBasicDao.insertEnterpriseProjectBasic(enterpriseProjectBasic);

            return enterpriseProject.getProjectId();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String deleteProject(String projectId) {

        try {
            // 向企业项目表中插入数据
            enterpriseProjectDao.deleteEnterpriseProject(projectId);
            System.out.println("projectId:" + projectId);

            // 删除企业项目立项信息表数据
            enterpriseProjectApprovalDao.deleteEnterpriseProjectApproval(projectId);

            // 删除企业信息基础表中数据
            enterpriseProjectBasicDao.deleteEnterpriseProjectBasic(projectId);

            // TODO 删除与项目相关的费用表


            // TODO 删除知识产权、成果等文件

            return projectId;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String updateProject(EnterpriseProject enterpriseProject) {
        enterpriseProjectDao.updateEnterpriseProject(enterpriseProject);
        return enterpriseProject.getProjectId();
    }


}
